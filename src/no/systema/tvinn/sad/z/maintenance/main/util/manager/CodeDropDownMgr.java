	/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiRecord;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesKodtsiService;
import no.systema.tvinn.sad.z.maintenance.felles.url.store.TvinnSadMaintenanceFellesUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Container;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Record;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportKodts6Service;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;


/**
 * The class handles general gui drop downs aspect population for TVINN-SAD Vedlikehold
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 15, 2016
 * 
 * 	
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();

	/**
	 * Populate model with available pref codes, derived from @see {@link TvinnSadMaintenanceExportUrlDataStore}
	 * 
	 * model attribute: prefCodeList
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadExportKodts6Service
	 * @param model
	 * @param applicationUser
	 */
	public void populatePrefCodesHtmlDropDownsSadExport(UrlCgiProxyService urlCgiProxyService, MaintSadExportKodts6Service maintSadExportKodts6Service, Map model, String applicationUser ){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS6R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadExportKodts6Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadExportKodts6Container container = maintSadExportKodts6Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	
    	model.put("kalle", list);  
    	
	}
	
	/**
	 * Populate r31List with J, N
	 * 
	 * model attribute: r31List
	 * 
	 * @param model
	 */
	public void populateR31HtmlDropDownsSadExport(Map model) {
		String[] r31List = {"J","N"};
		model.put("r31List", r31List);
	}
	
	
	/**
	 * Populate mfList with F
	 * 
	 * model attribute: mfList
	 * 
	 * @param model
	 */
	public void populateMfHtlDropDownSadExport(Map model) {
		String[] mfList = {"F"};
		model.put("mfList", mfList);
	}


	/**
	 * Populate all transit code for NCTS
	 * 
	 * model attribute: transitCodeList
	 * 
	 * @param urlCgiProxyService
	 * @param maintNctsExportTrkodfService
	 * @param model, put in to transitCodeList
	 * @param applicationUser
	 */
	public void populateTransitKoder(UrlCgiProxyService urlCgiProxyService, MaintNctsExportTrkodfService maintNctsExportTrkodfService, Map model,
			String applicationUser) {
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_KODE_TYPER_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintNctsTransitKodeTypeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintNctsTransitKodeTypeContainer container = maintNctsExportTrkodfService.getTransitKoderList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	
    	model.put("transitCodeList", list);
	}	

	
	/**
	 * Populate Tollsteds type; tkavg, tkank, tktrs with J, N
	 * 
	 * model attribute: tkavgList, tkankList, tktrsList
	 * 
	 * @param model
	 */
	public void populateTollstedstypeHtmlDropDownsNctsExport(Map model) {
		String[] yesNo = {"J","N"};
		model.put("tkavgList", yesNo);
		model.put("tkankList", yesNo);
		model.put("tktrsList", yesNo);
		
	}
	
	/**
	 * Populate with all delsystem, (excluding label-post)
	 * 
	 * model attribute: delSystemList
	 * 
	 * @param maintMainKofastService
	 * @param model
	 * @param applicationUser
	 */
	public void popluateDelsystem(UrlCgiProxyService urlCgiProxyService, MaintMainKofastService maintMainKofastService, Map model, String applicationUser) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kftyp=" + FasteKoder.DELSYS.toString());
		logger.info(BASE_URL);
		logger.info(urlRequestParams);

		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonMaintMainChildWindowKofastContainer container = null;
		Collection <JsonMaintMainChildWindowKofastRecord> list = new ArrayList<JsonMaintMainChildWindowKofastRecord>();
		try {
			if (jsonPayload != null) {
				container = maintMainKofastService.getContainer(jsonPayload);
				if (container != null) {
					for (JsonMaintMainChildWindowKofastRecord jsonMaintMainChildWindowKofastRecord :  container.getDtoList()) {
						if (!"DEFN".equals(jsonMaintMainChildWindowKofastRecord.getKfkod())) {
							list.add(jsonMaintMainChildWindowKofastRecord);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("Error: ",e);
		}
		
    	model.put("delSystemList", list);
	}	

	/**
	 * Populate avdList with data from MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL
	 * 
	 * @param urlCgiProxyService
	 * @param specialListPopulationService
	 * @param model holding avdList
	 * @param appUser
	 */
	public void populateCodesHtmlDropDownsFromJsonAvdelning(UrlCgiProxyService urlCgiProxyService, MaintMainKodtaService specialListPopulationService, Map model, SystemaWebUser appUser) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		List<JsonMaintMainKodtaRecord> list = new ArrayList();
		if (jsonPayload != null) {
			JsonMaintMainKodtaContainer container = specialListPopulationService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}

		model.put("avdList", list);

	}
	
	/**
	 * Populate signatureList with data from TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SYFT10R_GET_LIST_URL
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadFellesKodtsiService
	 * @param model holding signatureList
	 * @param appUser
	 */
	public void populateCodesHtmlDropDownsFromJsonSignature(UrlCgiProxyService urlCgiProxyService, MaintSadFellesKodtsiService maintSadFellesKodtsiService, Map model, SystemaWebUser appUser) {
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SYFT10R_GET_LIST_URL;
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		List<JsonMaintSadFellesKodtsiRecord> list = new ArrayList();
		if (jsonPayload != null) {
			JsonMaintSadFellesKodtsiContainer container = maintSadFellesKodtsiService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
			}
		}

		model.put("signatureList", list);

	}
	
}
