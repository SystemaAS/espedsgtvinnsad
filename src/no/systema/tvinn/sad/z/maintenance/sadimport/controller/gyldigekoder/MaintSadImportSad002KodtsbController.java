package no.systema.tvinn.sad.z.maintenance.sadimport.controller.gyldigekoder;

import java.util.*;

import org.slf4j.*;
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

import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodtsbService;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;
import no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder.MaintSadImportSad002KodtsbrValidator;

/**
 *  TVINN Maintenance Import Sad002r Controller 
 * 
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportSad002KodtsbController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSadImportSad002KodtsbController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceimport_sad002_kodtsbr.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad002_kodtsbr");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		
		logger.info("Inside method: doSadMaintImportList");
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
			String id = null;
			List<JsonMaintSadImportKodtsbRecord> list = this.fetchList(appUser.getUser(), id);
			
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	//model.put("ks8avg", ks8avg);
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
	@RequestMapping(value="tvinnsadmaintenanceimport_sad002_kodtsbr_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintSadImportKodtsbRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad002_kodtsbr");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//process
			MaintSadImportSad002KodtsbrValidator validator = new MaintSadImportSad002KodtsbrValidator();
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
					recordToValidate.setKsbkd(null);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
			}
			//------------
			//FETCH table
			//------------
			String id = null;// in order to get all the list
	    	List<JsonMaintSadImportKodtsbRecord> list = this.fetchList(appUser.getUser(), id);
	    	//set domain objets
	    	model.put("dbTable", dbTable);
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param ks3trt
	 * @return
	 */
	private List<JsonMaintSadImportKodtsbRecord> fetchList(String applicationUser, String ks7tln){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTSBR_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		//Either id or alfa but not both...
		if(ks7tln!=null && !"".equals(ks7tln)){
			urlRequestParams.append("&ks7tln=" + ks7tln);
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportKodtsbRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodtsbContainer container = this.maintSadImportKodtsbService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	/*for(JsonMaintSadImportKodtsbRecord record : list){
	        		//logger.info(TODO);
	        	}*/
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
	private int updateRecord(String applicationUser, JsonMaintSadImportKodtsbRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTSBR_DML_UPDATE_URL;
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
    		JsonMaintSadImportKodtsbContainer container = this.maintSadImportKodtsbService.doUpdate(jsonPayload);
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
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSadImportKodtsbService")
	private MaintSadImportKodtsbService maintSadImportKodtsbService;
	@Autowired
	@Required
	public void setMaintSadImportKodtsbService (MaintSadImportKodtsbService value){ this.maintSadImportKodtsbService = value; }
	public MaintSadImportKodtsbService getMaintSadImportKodtsbService(){ return this.maintSadImportKodtsbService; }
	
}

