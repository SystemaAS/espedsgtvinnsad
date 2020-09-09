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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.util.DateTimeManager;
import no.systema.jservices.common.dao.GodsafDao;
import no.systema.jservices.common.dao.GodsjfDao;
import no.systema.jservices.common.dao.GodsgfDao;

import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

/**
 * Sad Manifest Header Items Controller
 * 
 * @author oscardelatorre
 * @date Sep 2018
 * 
 */

@Controller
public class TvinnSadManifestHeaderCargoLinesController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(TvinnSadManifestHeaderCargoLinesController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private LoginValidator loginValidator = new LoginValidator();
	private StringManager strMgr = new StringManager();
	DateTimeManager dateMgr = new DateTimeManager();
	//private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	
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
	@RequestMapping(value="tvinnsadmanifest_edit_cargolines.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestEditCargolines(JsonTvinnSadManifestCargoLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_edit_cargolines");
		logger.info("Inside: doManifestEditCargolines");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		Map model = new HashMap();
		String action = request.getParameter("action");
		String efuuid = request.getParameter("efuuid");
		String efsg = request.getParameter("efsg");
		String efavd = request.getParameter("efavd");
		
		
		//String updateFlag = request.getParameter("updateFlag");
		logger.warn("action:" + action);
		
		/*if(strMgr.isNotNull(updateFlag)){
			model.addAttribute("updateFlag", "1");
		}*/
		boolean isValidRecord = true;
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			logger.info("ASAVD:" + appUser.getAsavd());
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
			    	//this.adjustFieldsForUpdate(recordToValidate);
			    	
			    	//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					
					if(strMgr.isNotNull( recordToValidate.getClpro()) ){
						logger.info("doUpdate");
						//dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_UPDATE, errMsg);
							
					}else{
						logger.info("doCreate branch starting...");
						logger.info("doCreate");
						//dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_ADD, errMsg);
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
			if(strMgr.isNotNull(recordToValidate.getClpro()) ){
				List outputList =  null;
				if("doFetch".equals(action)){
					this.adjustFieldsForFetch(recordToValidate);
					outputList = (List)this.getList(appUser, recordToValidate.getClpro());
					model.put("list", outputList );
				}else{
					//this.adjustFieldsForFetch(updatedDao);
					//model.put("list", this.getList(appUser, updatedDao.getClpro());
				}
				
				/*
				if(isValidRecord){
					//GodsjfDao updatedDao = this.getRecordGodsjf(appUser, recordToValidate);
					//this.adjustFieldsForFetch(updatedDao);
					//model.addAttribute(TvinnSadConstants.DOMAIN_RECORD, updatedDao);
					
				}else{
					//in case of validation errors
					//this.adjustFieldsForFetch(recordToValidate);
					model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				}*/
				
			}
			
			if(action==null || "".equals(action)){ 
				action = "doUpdate";	
			}else if (action.equals(TvinnSadConstants.ACTION_CREATE)){
				action = "doUpdate";
			}
			
			model.put("action", action);
			model.put("efuuid", efuuid);
			model.put("efsg", efsg);
			model.put("efavd", efavd);
			model.put("efpro", recordToValidate.getClpro());
			
			logger.info("END of method");
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
			
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
	@RequestMapping(value="tvinnsadmanifest_edit_cargolines_delete.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCargolinesDelete(JsonTvinnSadManifestCargoLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("redirect:tvinnsadmanifest_edit_cargolines.do");
		logger.info("Inside: doCargolinesDelete");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		Map model = new HashMap();
		
		String efuuid = request.getParameter("efuuid");
		String efsg = request.getParameter("efsg");
		
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
	 * @param appUser
	 * @param tur
	 * @return
	 */
	private Collection<JsonTvinnSadManifestCargoLinesRecord> getList(SystemaWebUser appUser, String tur){
		Collection<JsonTvinnSadManifestCargoLinesRecord> retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINESURL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&clpro=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();	
			retval = outputList;
    	}
    	return retval;
	}
	
	private void adjustFieldsForUpdate(JsonTvinnSadManifestCargoLinesRecord recordToValidate){
	
		recordToValidate.setCl0068a(this.convertToDate_ISO(recordToValidate.getCl0068a()));
		
	}
	
	private void adjustFieldsForFetch(JsonTvinnSadManifestCargoLinesRecord recordToValidate){
		recordToValidate.setCl0068a(this.convertToDate_NO(recordToValidate.getCl0068a()));
	}
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
	
	
	
	//SERVICES
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	
}

