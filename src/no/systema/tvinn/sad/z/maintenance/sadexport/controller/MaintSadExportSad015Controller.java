package no.systema.tvinn.sad.z.maintenance.sadexport.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportSadavgeService;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadexport.validator.MaintSadExportSad015Validator;


/**
 *  TVINN Maintenance Export Sad015 Controller 
 * 
 * @author Fredrik Möller
 * @date Aug 12, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportSad015Controller {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(MaintSadExportSad015Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();


	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceexport_sad015.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintExportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceexport_sad015");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String agtanr = request.getParameter("searchAgtanr");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
	    	List<JsonMaintSadExportSadavgeRecord> list = fetchList(appUser.getUser(), agtanr);
	    		    	
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("agtanr", agtanr);
	    	
	    	model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceexport_sad015_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintExportEdit(@ModelAttribute ("record") JsonMaintSadExportSadavgeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceexport_sad015");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		String agtanr = request.getParameter("searchAgtanr");
		
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			adjustFields(recordToValidate);
			logger.info("recordToValidate="+recordToValidate.toString());
			MaintSadExportSad015Validator validator = new MaintSadExportSad015Validator();
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

			
			List<JsonMaintSadExportSadavgeRecord> list = this.fetchList(appUser.getUser(), agtanr);

			//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("agtanr", agtanr);
	    	
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	

	private List<JsonMaintSadExportSadavgeRecord> fetchList(String applicationUser, String agtanr){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD015_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		if (agtanr != null && !"".equals(agtanr)) {
			urlRequestParams.append("&agtanr=" + agtanr);
		}	
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	List<JsonMaintSadExportSadavgeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
    		JsonMaintSadExportSadavgeContainer container = this.maintSadExportSadavgeService.getList(jsonPayload);
    		list=  (List<JsonMaintSadExportSadavgeRecord>) container.getList();
    	}
    	return list;
	}
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSadExportSadavgeRecord record, String mode, StringBuffer errMsg){
		int retval = 0;    	
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD015_DML_UPDATE_URL;
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
    		JsonMaintSadExportSadavgeContainer container = this.maintSadExportSadavgeService.doUpdate(jsonPayload);
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

	
	private void adjustFields(JsonMaintSadExportSadavgeRecord record) {
		record.setAgdtf(dateFormatter.convertToDate_ISO(record.getAgdtfNO()));
		record.setAgdtt(dateFormatter.convertToDate_ISO(record.getAgdttNO()));
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	@Qualifier ("maintSadExportSadavgeService")
	private MaintSadExportSadavgeService maintSadExportSadavgeService;
	@Autowired
	@Required
	public void setMaintSadExportSadavgeService (MaintSadExportSadavgeService value){ this.maintSadExportSadavgeService = value; }
	public MaintSadExportSadavgeService getMaintSadImportSadsdService(){ return this.maintSadExportSadavgeService; }
	
}

