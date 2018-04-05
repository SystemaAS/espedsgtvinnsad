package no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;
import no.systema.tvinn.sad.z.maintenance.sad.service.MaintSadSadlService;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportSadavgeService;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportSaehService;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportTvineService;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;


/**
 *  TVINN Maintenance Export AJAX Controller 
 * 
 * @author Fredrik Möller
 * @date Aug 10, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadExportAjaxHandlerController.class.getName());
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();


	@RequestMapping(value = "getSpecificRecord_tvi99d.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintSadExportTvineRecord> getRecordTvi99d(@RequestParam String applicationUser,
			@RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordTvi99d ";
		logger.info(METHOD + " applicationUser" + applicationUser + "id=" + id);
		List<JsonMaintSadExportTvineRecord> result = new ArrayList();
		// get table
		return (List) this.fetchListTvi99d(applicationUser, id);
	}

	@RequestMapping(value="getSpecificRecord_sad015.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadExportSadavgeRecord> getRecordSad015
	  	(@RequestParam String applicationUser, @RequestParam String agtanr, @RequestParam String agakd, @RequestParam String agskv) {
		final String METHOD = "[DEBUG] getRecordSad015 ";
		logger.info(METHOD + " applicationUser"+applicationUser+", agtanr="+agtanr+",agakd "+agakd+", agskv "+agskv);
		List<JsonMaintSadExportSadavgeRecord> result = new ArrayList();
	 	//get table
    	return (List)this.fetchSpecificSad015(applicationUser, agtanr, agakd, agskv);
	}
		

	@RequestMapping(value="getSpecificRecord_sad024.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadExportSaehRecord> getRecordSad024
	  	(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		final String METHOD = "[DEBUG] getRecordSad024 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadExportSaehRecord> result = new ArrayList();
	 	//get table
    	return (List)this.fetchListSad024(applicationUser, avd, opd);
	}
	

	@RequestMapping(value="getSpecificRecord_sad004.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadSadlRecord> getRecordSad004
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String kundnr) {
		List<JsonMaintSadSadlRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad004(applicationUser, id, kundnr);
    	
    	return result;
	
	}	
	
	private Collection<JsonMaintSadExportTvineRecord> fetchListTvi99d(String applicationUser, String id){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&e9705=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadExportTvineRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadExportTvineContainer container = this.maintSadExportTvineService.getList(jsonPayload);
			if (container != null) {
				// list = (List)container.getList();
				for (JsonMaintSadExportTvineRecord record : container.getList()) {
					list.add(record);
				}
			}
		}
    	return list;
	}

	
	private Collection<JsonMaintSadExportSadavgeRecord> fetchSpecificSad015(String applicationUser, String agtanr, String agakd, String agskv){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD015_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&agtanr=" + agtanr + "&agakd=" + agakd + "&agskv="+ agskv;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadExportSadavgeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
    		JsonMaintSadExportSadavgeContainer container = this.maintSadExportSadavgeService.getList(jsonPayload);
    		list=  (List<JsonMaintSadExportSadavgeRecord>) container.getList();
    	}
    	return list;
	}	
	
	private Collection<JsonMaintSadExportSaehRecord> fetchListSad024(String applicationUser, String avd, String opd){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD024_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&seavd=" + avd + "&setdn=" + opd ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadExportSaehRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadExportSaehContainer container = this.maintSadExportSaehService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
	}	
	

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param levenr
	 * @return
	 */
	private Collection<JsonMaintSadSadlRecord> fetchListSad004(String applicationUser, String id, String levenr){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD004R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&slalfa=" + id + "&slknr=" + levenr;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		// extract
		List<JsonMaintSadSadlRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadSadlContainer container = this.maintSadSadlService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}
		return list;

	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintSadExportTvineService")
	private MaintSadExportTvineService maintSadExportTvineService;
	@Autowired
	@Required
	public void setMaintSadExportTvineService (MaintSadExportTvineService value){ this.maintSadExportTvineService = value; }
	public MaintSadExportTvineService getMaintSadExportTvineService(){ return this.maintSadExportTvineService; }
	
	@Qualifier ("maintSadExportSadavgeService")
	private MaintSadExportSadavgeService maintSadExportSadavgeService;
	@Autowired
	@Required
	public void setMaintSadExportSadavgeService (MaintSadExportSadavgeService value){ this.maintSadExportSadavgeService = value; }
	public MaintSadExportSadavgeService getMaintSadExportSadavgeService(){ return this.maintSadExportSadavgeService; }

	@Qualifier ("maintSadExportSaehService")
	private MaintSadExportSaehService maintSadExportSaehService;
	@Autowired
	@Required
	public void setMaintSadExportSaehService (MaintSadExportSaehService value){ this.maintSadExportSaehService = value; }
	public MaintSadExportSaehService getMaintSadExportSaehService(){ return this.maintSadExportSaehService; }
	
	@Qualifier ("maintSadSadlService")
	private MaintSadSadlService maintSadSadlService;
	@Autowired
	@Required
	public void setMaintSadImportSadlService (MaintSadSadlService value){ this.maintSadSadlService = value; }
	public MaintSadSadlService getMaintSadImportSadlService(){ return this.maintSadSadlService; }
	

}

