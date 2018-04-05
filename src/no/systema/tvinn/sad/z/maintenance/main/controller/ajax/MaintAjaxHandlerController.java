package no.systema.tvinn.sad.z.maintenance.main.controller.ajax;

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
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaRecord;
import no.systema.tvinn.sad.z.maintenance.main.service.MaintKodtvaService;
import no.systema.tvinn.sad.z.maintenance.main.url.store.MaintenanceUrlDataStore;



/**
 * Maintenance Syft02r Controller - Tollkurser
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintAjaxHandlerController.class.getName());
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft02r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintKodtvaRecord> getRecordSyft02
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String date) {
		final String METHOD = "[DEBUG] getRecordSyft02";
		logger.info(METHOD + " Inside...");
		List<JsonMaintKodtvaRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft02(applicationUser, id, date);
    	
    	return result;
	
	}
	

	private Collection<JsonMaintKodtvaRecord> fetchListSyft02(String applicationUser, String id, String date){
		String dateISO = dateFormatter.convertToDate_ISO(date);
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SYFT02R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kvakod=" + id + "&kvadt=" + dateISO;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintKodtvaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintKodtvaContainer container = this.maintKodtvaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintKodtvaRecord record: list){
	        		//logger.info(record.getKvakod());
	        	}
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
	
	
	@Qualifier ("maintKodtvaService")
	private MaintKodtvaService maintKodtvaService;
	@Autowired
	@Required
	public void setMaintKodtvaService (MaintKodtvaService value){ this.maintKodtvaService = value; }
	public MaintKodtvaService getMaintKodtvaService(){ return this.maintKodtvaService; }
	
	
}

