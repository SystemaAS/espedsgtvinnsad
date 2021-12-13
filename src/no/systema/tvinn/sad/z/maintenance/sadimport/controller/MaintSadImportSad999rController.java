package no.systema.tvinn.sad.z.maintenance.sadimport.controller;

import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportSadsdService;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.validator.MaintSadImportSad999rValidator;


/**
 *  TVINN Maintenance Import Sad999r Controller 
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportSad999rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(MaintSadImportSad999rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceimport_sad999r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad999r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String sdtnrf = request.getParameter("searchSdtnrf");
		String sdkdae = request.getParameter("searchSdkdae");
		String taalfa = request.getParameter("searchTaalfa");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintSadImportSadsdRecord> list = new ArrayList();
	    	if( (sdtnrf!=null && !"".equals(sdtnrf)) || (taalfa!=null && !"".equals(taalfa)) || (sdkdae!=null && !"".equals(sdkdae))){
	    		list = this.fetchList(appUser.getUser(), sdtnrf, taalfa, sdkdae);
	    	}
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	this.setDomainObjectsInView(model, sdtnrf, taalfa, sdkdae);
	    	
	    	model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
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
	@RequestMapping(value="tvinnsadmaintenanceimport_sad999r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintSadImportSadsdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad999r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		String sdtnrf = request.getParameter("searchSdtnrf");
		String sdkdae = request.getParameter("searchSdkdae");
		String taalfa = request.getParameter("searchTaalfa");
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			//adjust values
			this.adjustSomeRecordValues(recordToValidate);
			logger.info("recordToValidate="+recordToValidate);
			//Move on
			MaintSadImportSad999rValidator validator = new MaintSadImportSad999rValidator();
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
					
				}
				model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (TvinnSadMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(TvinnSadMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(TvinnSadMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(TvinnSadMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					if(updateId!=null && !"".equals(updateId)){
						//meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
	    	List<JsonMaintSadImportSadsdRecord> list = this.fetchList(appUser.getUser(), sdtnrf, taalfa, sdkdae);
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	this.setDomainObjectsInView(model, sdtnrf, taalfa, sdkdae);
	    	
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustSomeRecordValues(JsonMaintSadImportSadsdRecord recordToValidate){
		final String ZERO = "0";
		//--------
		//Dates
		//--------
		if(recordToValidate.getSddtf()!=null && !"".equals(recordToValidate.getSddtf())){
			//nothing
		}else{
			recordToValidate.setSddtf(ZERO);
		}
		if(recordToValidate.getSddtt()!=null && !"".equals(recordToValidate.getSddtt())){
			//nothing
		}else{
			recordToValidate.setSddtt(ZERO);
		}
		
		recordToValidate.setSddtf(dateFormatter.convertToDate_ISO(recordToValidate.getSddtfNO()));
		recordToValidate.setSddtt(dateFormatter.convertToDate_ISO(recordToValidate.getSddttNO()));

		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param sdtnrf
	 * @param taalfa
	 * @param sdkdae
	 * @return
	 */
	private List<JsonMaintSadImportSadsdRecord> fetchList(String applicationUser, String sdtnrf, String taalfa, String sdkdae){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		//Either id or alfa but not both...
		if(sdtnrf!=null && !"".equals(sdtnrf)){
			urlRequestParams.append("&sdtnrf=" + sdtnrf);
		}else if (sdkdae!=null && !"".equals(sdkdae)){
			urlRequestParams.append("&sdkdae=" + sdkdae);
		}else if (taalfa!=null && !"".equals(taalfa)){
			urlRequestParams.append("&taalfa=" + taalfa);
		}else{
			//no further search. Just return an empty list
			return new ArrayList();
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportSadsdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportSadsdContainer container = this.maintSadImportSadsdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportSadsdRecord record : list){
	        		//logger.info("SDTNRF:" + record.getSdtnrf());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSadImportSadsdRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportSadsdContainer container = this.maintSadImportSadsdService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = TvinnSadMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}
    	
    	return retval;
    
	}
	/**
	 * 
	 * @param model
	 * @param sdtnrf
	 * @param taalfa
	 * @param sdkdae
	 */
	private void setDomainObjectsInView(Map model, String sdtnrf, String taalfa, String sdkdae ){
    	model.put("sdtnrf", sdtnrf);
    	model.put("taalfa", taalfa);
    	model.put("sdkdae", sdkdae);
    	
	}
	
	private void adjustFields(JsonMaintSadImportSadsdRecord record) {
		record.setSddtf(dateFormatter.convertToDate_ISO(record.getSddtfNO()));
		record.setSddtt(dateFormatter.convertToDate_ISO(record.getSddttNO()));
	}

	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	@Qualifier ("maintSadImportSadsdService")
	private MaintSadImportSadsdService maintSadImportSadsdService;
	@Autowired
	@Required
	public void setMaintSadImportSadsdService (MaintSadImportSadsdService value){ this.maintSadImportSadsdService = value; }
	public MaintSadImportSadsdService getMaintSadImportSadsdService(){ return this.maintSadImportSadsdService; }
	
}

