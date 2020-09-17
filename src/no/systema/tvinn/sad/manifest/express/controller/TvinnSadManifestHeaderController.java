package no.systema.tvinn.sad.manifest.express.controller;

import java.util.*;
import java.util.regex.*;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
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
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;


import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
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
	
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * Update the manifest status (SUBMITTED, DELETED, REOPENED/DRAFT) - COMPLETED is done in toll.no not here
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_updateStatus.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestUpdateStatus(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.warn("Inside: doManifestUpdateStatus");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = "redirect:tvinnsadmanifest_edit.do?action=doFetch&user=" + appUser.getUser() + "&efuuid=" + recordToValidate.getEfuuid();
		ModelAndView successView = new ModelAndView(redirect);
		//Execute
		StringBuffer errMsg = new StringBuffer();
		int dmlRetval = 0;
		if(StringUtils.isNotEmpty(recordToValidate.getEfuuid()) && StringUtils.isNotEmpty(recordToValidate.getEfst2())){
			dmlRetval = this.updateManifestStatus(appUser.getUser(), recordToValidate, errMsg);
		}
		//Result
		if(dmlRetval<0){
			model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
			successView.addObject(model);
			logger.error("ERROR!!!!!!!!!!ERROR!!!!!!!!!!!!!ERROR!:" + errMsg.toString());
		}	
		
		return successView;
	}	  
	
	/**
	 * Sends the manifest to a queue
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_send.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestSend(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.warn("Inside: doManifestSend");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = "redirect:tvinnsadmanifest_edit.do?action=doFetch&user=" + appUser.getUser() + "&efuuid=" + recordToValidate.getEfuuid();
		ModelAndView successView = new ModelAndView(redirect);
		
		//TODO
		
		return successView;
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
						
						logger.warn("doCreate branch starting...");
						logger.warn("doCreate");
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
					//check if the manifest cargo lines are valid
					if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
						model.put("invalidManifest", "1");
					}
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
	 * 
	 * @param model
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit_delete.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDelete(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		ModelAndView successView = new ModelAndView("redirect:tvinnsadmanifest.do?action=doFind&sign=" + appUser.getSignatur());
		
		logger.warn("Inside: doDelete");
		String uuid = null;
		String notisText = null;
		String status = null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentUuid")){
    				uuid = value;
    			}else if(element.startsWith("selectedStatus")){
    				status = value;
    			}else if(element.startsWith("currentText")){
    				notisText = value;
    			}
    		}
    	}
	    
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//----------------------------
			//Fetch record and update it 
			//----------------------------
			//TODO
			
			if(strMgr.isNotNull( uuid )){
				recordToValidate.setEfuuid(uuid);
				recordToValidate.setEfst(status);
				int dmlRetval = 0;
				StringBuffer errMsg = new StringBuffer();
				//fetch record
				logger.warn("doDelete");
				//Update with delete flag
				dmlRetval = this.deleteRecord(appUser.getUser(), recordToValidate, errMsg);
				if(dmlRetval<0){
					logger.info("ERROR on delete ... ??? check your code");
					//model.addAttribute(GodsnoConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					
				}else{
					logger.info("doDelete = OK");
				}
				
				
			}

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
	 * @param applicationUser
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int deleteRecord(String applicationUser, JsonTvinnSadManifestRecord recordToValidate, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=D" + "&efuuid=" + recordToValidate.getEfuuid() + "&efst=" + recordToValidate.getEfst());
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
	 * @param applicationUser
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int updateManifestStatus(String applicationUser, JsonTvinnSadManifestRecord recordToValidate, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		//http://localhost:8080/syjservicestn/syjsSADEFFR_U.do?user=OSCAR&mode=UMS&efuuid=cb9717e2-e55a-4ff0-9931-60e0a7c5fbe7&efst2=S
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=UMS" + "&efuuid=" + recordToValidate.getEfuuid() + "&efst2=" + recordToValidate.getEfst2());
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
	 * @param recordToValidate
	 */

	private void adjustFieldsForUpdate(JsonTvinnSadManifestRecord recordToValidate){
		
		recordToValidate.setEfdtr(new DateTimeManager().getCurrentDate_ISO());
		recordToValidate.setEfeta(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfeta()));
		recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfsjadt()));
	}
	
	private void adjustFieldsForFetch(JsonTvinnSadManifestRecord recordToValidate){
		recordToValidate.setEfeta(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfeta()));
		recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfsjadt()));
	}
	
	

	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFBKODE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private ManifestExpressMgr manifestExpressMgr;

}

