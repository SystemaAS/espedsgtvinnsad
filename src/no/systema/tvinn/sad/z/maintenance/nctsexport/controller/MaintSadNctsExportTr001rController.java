package no.systema.tvinn.sad.z.maintenance.nctsexport.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.sadimport.filter.SearchFilterSadImportTopicList;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.main.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.nctsexport.validator.MaintNctsExportTr001rValidator;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;


/**
 *  TVINN Maintenance NCTS Export Tr001r Controller - Koderegister.
 * 
 * @author Fredrik Möller
 * @date Okt 14, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadNctsExportTr001rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSadNctsExportTr001rController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	
	@RequestMapping(value="tvinnsadmaintenance_nctsexport_tr001r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doNctsKoderRegisterList(@ModelAttribute ("searchRecord") SearchFilterTransitKoder searchRecord, HttpSession session, HttpServletRequest request, BindingResult bindingResult){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_nctsexport_tr001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		String searchTkunik = searchRecord.getSearchTkunik();
		String searchTkkode = searchRecord.getSearchTkkode();
		String searchTktxtn = searchRecord.getSearchTktxtn();
		MaintNctsExportTr001rValidator validator = new MaintNctsExportTr001rValidator();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{

	    	validator.restrictOnMandatorySearchFields(searchRecord ,bindingResult);

			//get table
	    	List<JsonMaintNctsTrkodfRecord> list = new ArrayList();
	    	if (!bindingResult.hasErrors() && searchRecord.getSearchTkunik() != null) {
	    		list = this.fetchList(appUser.getUser(), searchTkunik, searchTkkode, searchTktxtn);	
	    	} else {
	    		searchTkunik = "012"; //default tkunik=012, Språkkode
	    	}
	    	
		    this.populateDropDowns(model, appUser.getUser());
	    	model.put("dbTable", dbTable);
	    	model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_SEARCH_FILTER_NCTS_EXPORT_KODEREGISTER, searchRecord);    	
	    	successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	@RequestMapping(value="tvinnsadmaintenance_nctsexport_tr001r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doNctsKoderRegisterEdit(@ModelAttribute ("record") JsonMaintNctsTrkodfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_nctsexport_tr001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		String searchTkunik = request.getParameter("searchTkunik");
		String searchTkkode = request.getParameter("searchTkkode");
		String searchTktxtn = request.getParameter("searchTktxtn");
		recordToValidate.setTkunik(searchTkunik);
		
		SearchFilterTransitKoder searchRecord = new SearchFilterTransitKoder();
		searchRecord.setSearchTkunik(searchTkunik);
		searchRecord.setSearchTkkode(searchTkkode);
		searchRecord.setSearchTktxtn(searchTktxtn);
		
		int dmlRetval = 0;
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintNctsExportTr001rValidator validator = new MaintNctsExportTr001rValidator();
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
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
				dmlRetval = 0;
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
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					if(updateId!=null && !"".equals(updateId)){
						//meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
				}
				
			}
			//------------
			//FETCH table
			//------------

			List<JsonMaintNctsTrkodfRecord> list = this.fetchList(appUser.getUser(),  searchTkunik, searchTkkode, searchTktxtn);
	
			this.populateDropDowns(model, appUser.getUser());
			model.put("dbTable", dbTable);
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_SEARCH_FILTER_NCTS_EXPORT_KODEREGISTER, searchRecord);    	
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	private List<JsonMaintNctsTrkodfRecord> fetchList(String applicationUser, String tkunik, String tkkode, String tktxtn){
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&tkunik="+ tkunik);
		
		if(tkkode!=null && !"".equals(tkkode)){
			urlRequestParams.append("&tkkode=" + tkkode);
		}
		if(tktxtn!=null && !"".equals(tktxtn)){
			urlRequestParams.append("&tktxtn=" + tktxtn);
		}

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintNctsTrkodfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintNctsTrkodfContainer container = this.maintNctsExportTrkodfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	private int updateRecord(String applicationUser, JsonMaintNctsTrkodfRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	if(jsonPayload!=null){
    		JsonMaintNctsTrkodfContainer container = this.maintNctsExportTrkodfService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        			errMsg.append(container.getErrMsg());
	        			retval = TvinnSadMaintenanceConstants.ERROR_CODE;
	        	}
	        }
    	}
    	
    	return retval;
	}
	
	
	private void populateDropDowns(Map model, String applicationUser){
		codeDropDownMgr.populateTransitKoder(urlCgiProxyService, maintNctsExportTrkodfService, model, applicationUser);
		codeDropDownMgr.populateTollstedstypeHtmlDropDownsNctsExport(model);
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintNctsExportTrkodfService")
	private MaintNctsExportTrkodfService  maintNctsExportTrkodfService;
	@Autowired
	@Required
	public void setMaintNctsExportTrkodfService (MaintNctsExportTrkodfService value){ this.maintNctsExportTrkodfService = value; }
	public MaintNctsExportTrkodfService getMaintNctsExportTrkodfService(){ return this.maintNctsExportTrkodfService; }

}

