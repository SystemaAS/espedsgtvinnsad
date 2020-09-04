package no.systema.tvinn.sad.manifest.express.controller;

import java.util.*;
import java.util.regex.*;

import javax.annotation.PostConstruct;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.util.DateTimeManager;


import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;

/**
 * Sad Manifest Header Controller
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */

@Controller
public class TvinnSadManifestHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(TvinnSadManifestHeaderController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private LoginValidator loginValidator = new LoginValidator();
	private StringManager strMgr = new StringManager();
	DateTimeManager dateMgr = new DateTimeManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	
	@Autowired
	ManifestExpressMgr manifestExpressMgr;
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
		  
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestEdit(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_edit");
		logger.warn("Inside: doManifestEdit");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("actionU");
		logger.warn("actionU:" + action);
		logger.warn("uuid:" + recordToValidate.getEfuuid());
		/*if(strMgr.isNotNull(updateFlag)){
			model.addAttribute("updateFlag", "1");
		}*/
		boolean isValidRecord = true;
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			logger.warn("ASAVD:" + appUser.getAsavd());
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//validator
				//GodsnoRegistreringValidator validator = new GodsnoRegistreringValidator();
				//validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] record does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
		    		isValidRecord = false;
		    		
			    }else{
			    	//adjust some db-fields
			    	this.adjustFieldsForUpdate(recordToValidate);
			    	
			    	//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					logger.warn("step 2");
					if(strMgr.isNotNull( recordToValidate.getEfuuid()) ){
						
						logger.warn("doUpdate:" + recordToValidate.getEfuuid());
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_UPDATE, errMsg);
							
					}else{
						//set manifest-uuid
						try{recordToValidate.setEfuuid(this.manifestExpressMgr.getUuid());logger.info(recordToValidate.getEfuuid()); }
						catch(Exception e){ logger.error("#####################:" + e.getMessage()); }
						
						logger.info("doCreate branch starting...");
						logger.info("doCreate");
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_ADD, errMsg);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					if(dmlRetval<0){
						isValidRecord = false;
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					}else{
						//Create OK. Prepare for upcoming Update
						//model.addAttribute("updateFlag", "1");
					}
			    }
			}
			//--------------
			//Fetch record
			//--------------
			//dropdowns
			this.setCodeDropDownMgr(appUser, model);
			
			if(strMgr.isNotNull(recordToValidate.getEfuuid()) ){
				if(isValidRecord){
					JsonTvinnSadManifestRecord record = this.getRecord(appUser, recordToValidate.getEfuuid());
					this.adjustFieldsForFetch(record);
					model.put(TvinnSadConstants.DOMAIN_RECORD, record);
					
				}else{
					//in case of validation errors
					//this.adjustFieldsForFetch(recordToValidate);
					model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			
			if(action==null || "".equals(action)){ 
				action = "doUpdate";	
			}else if (action.equals(TvinnSadConstants.ACTION_CREATE)){
				action = "doUpdate";
			}
			
			model.put("action", action);
			//model.addAttribute("avd", avd);
			//logger.info("AVD:" + avd);
			logger.info("END of method");
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
			return successView;
		}
	}
	/**
	 * Get a specific manifest
	 * @param appUser
	 * @param efuuid
	 * @return
	 */
	private JsonTvinnSadManifestRecord getRecord(SystemaWebUser appUser, String efuuid){
		JsonTvinnSadManifestRecord retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&efuuid=" + efuuid;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = record;
					//logger.info(retval.toString());
				}
			}
    	}
    	return retval;
	}
	
	/**
	 * Update record
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonTvinnSadManifestRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = 0;
					logger.warn(record.toString());
				}
			}
    	}
    	
    	return retval;
	}
	
	/**
	 * 
	 * @param model
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit_delete.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doGodsnoDelete(BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("redirect:tvinnsadmanifest.do");
		logger.info("Inside: doGodsnoDelete");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//----------------------------
			//Fetch record and update it 
			//----------------------------
			//TODO
			/*
			if(strMgr.isNotNull(recordToValidate.getGogn()) ){
				int dmlRetval = 0;
				StringBuffer errMsg = new StringBuffer();
				//fetch record
				GodsjfDao recordToDelete = this.getRecordGodsjf(appUser, recordToValidate);
				if(recordToDelete!=null && strMgr.isNotNull( recordToDelete.getGogn()) ){
					//adjust some db-fields
					recordToDelete.setGotrnr(DELETE_TEXT_ON_DB);
					this.adjustFieldsForUpdate(recordToDelete);
					logger.info("doDelete");
					//Update with delete flag
					dmlRetval = this.updateRecord(appUser.getUser(), recordToDelete, GodsnoConstants.MODE_UPDATE, errMsg);
					if(dmlRetval<0){
						logger.info("ERROR on delete ... ??? check your code");
						model.addAttribute(GodsnoConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						
					}else{
						logger.info("doDelete = OK");
					}
				}
				
			}*/

			return successView;
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 */

	private void adjustFieldsForUpdate(JsonTvinnSadManifestRecord recordToValidate){
		
		recordToValidate.setEfdtr(new DateTimeManager().getCurrentDate_ISO());
		recordToValidate.setEfeta(this.convertToDate_ISO(recordToValidate.getEfeta()));
		
	}
	
	private void adjustFieldsForFetch(JsonTvinnSadManifestRecord recordToValidate){
		recordToValidate.setEfeta(this.convertToDate_NO(recordToValidate.getEfeta()));
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	/*
	private void adjustFieldsForFetch(GodsjfDao recordToValidate){
		
		recordToValidate.setGogrdt(this.convertToDate_NO(recordToValidate.getGogrdt()));
		recordToValidate.setGolsdt(this.convertToDate_NO(recordToValidate.getGolsdt()));
		
	}*/
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String convertToDate_ISO (String value){
		String retval = null;
		
		if(strMgr.isNotNull(value)){
			DateTimeManager dateMgr = new DateTimeManager();
			retval = dateMgr.getDateFormatted_ISO(value, DateTimeManager.NO_FORMAT);
		}
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String convertToDate_NO (String value){
		
		DateTimeManager dateMgr = new DateTimeManager();
		return dateMgr.getDateFormatted_NO(value, DateTimeManager.ISO_FORMAT);
	}

	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, this.codeDropDownMgr.CODE_VEHICLE_TYPES, model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	public void setTvinnSadManifestListService (TvinnSadManifestListService value){ this.tvinnSadManifestListService = value; }
	public TvinnSadManifestListService getTvinnSadManifestListService(){ return this.tvinnSadManifestListService; }
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	public void setMaintMainKofastService(MaintMainKofastService value) { this.maintMainKofastService = value; }
	public MaintMainKofastService getMaintMainKofastService() { return this.maintMainKofastService; }
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
}

