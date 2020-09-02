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
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

/**
 * Sad Manifest Header Controller
 * 
 * @author oscardelatorre
 * @date Sep 2018
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
	@RequestMapping(value="tvinnsadmanifest_edit.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestEdit(ModelMap model, @ModelAttribute ("record") JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_edit");
		logger.info("Inside: doManifestEdit");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		//String updateFlag = request.getParameter("updateFlag");
		logger.info("action:" + action);
		
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
					
					if(strMgr.isNotNull( recordToValidate.getEfuuid()) ){
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
						model.addAttribute(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					}else{
						//Create OK. Prepare for upcoming Update
						//model.addAttribute("updateFlag", "1");
					}
			    }
			}
			//--------------
			//Fetch record
			//--------------
			if(strMgr.isNotNull(recordToValidate.getEfuuid()) ){
				
				if(isValidRecord){
					//GodsjfDao updatedDao = this.getRecordGodsjf(appUser, recordToValidate);
					//this.adjustFieldsForFetch(updatedDao);
					//model.addAttribute(TvinnSadConstants.DOMAIN_RECORD, updatedDao);
					
				}else{
					//in case of validation errors
					//this.adjustFieldsForFetch(recordToValidate);
					model.addAttribute(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			
			if(action==null || "".equals(action)){ 
				action = "doUpdate";	
			}else if (action.equals(TvinnSadConstants.ACTION_CREATE)){
				action = "doUpdate";
			}
			
			model.addAttribute("action", action);
			//model.addAttribute("avd", avd);
			//logger.info("AVD:" + avd);
			logger.info("END of method");
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
	public ModelAndView doGodsnoDelete(ModelMap model, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
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
	private void adjustFieldsForUpdate(GodsjfDao recordToValidate){
		/*
		recordToValidate.setGogrdt(this.convertToDate_ISO(recordToValidate.getGogrdt()));
		recordToValidate.setGolsdt(this.convertToDate_ISO(recordToValidate.getGolsdt()));
		
		//Numbers... since the fucking Spring converter is not working ...
		if(recordToValidate.getGotrdt()==null){ recordToValidate.setGotrdt(0); }
		//date and time
		if(recordToValidate.getGogrdt()==null){ recordToValidate.setGogrdt("0"); }
		if(recordToValidate.getGogrkl()==null){ recordToValidate.setGogrkl(0); }
		//date and time
		if(recordToValidate.getGolsdt()==null){ recordToValidate.setGolsdt("0"); }
		if(recordToValidate.getGolskl()==null){ recordToValidate.setGolskl(0); }
		*/
		
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(GodsjfDao recordToValidate){
		/*
		recordToValidate.setGogrdt(this.convertToDate_NO(recordToValidate.getGogrdt()));
		recordToValidate.setGolsdt(this.convertToDate_NO(recordToValidate.getGolsdt()));
		*/
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
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
}

