package no.systema.tvinn.sad.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts2Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts2ServiceImpl;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;

/**
 * 
 * @author oscardelatorre
 *
 */
public class TvinnSadGeneralDatabaseManager {
	private static final Logger logger = Logger.getLogger(TvinnSadGeneralDatabaseManager.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	//
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private MaintSadImportKodts2Service maintSadImportKodts2Service = new MaintSadImportKodts2ServiceImpl();
	/**
	 * 
	 * @param applicationUser
	 * @param ks2lk
	 * @return
	 */
	public  List<JsonMaintSadImportKodts2Record> fetchListAll_Countries(String applicationUser, String ks2lk){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS2R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		//Either id or alfa but not both...
		if(ks2lk!=null && !"".equals(ks2lk)){
			urlRequestParams.append("&ks2lk=" + ks2lk);
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportKodts2Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts2Container container = this.maintSadImportKodts2Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts2Record record : list){
	        		//logger.info(TODO);
	        	}
	        }
    	}
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param ks2lk
	 * @return
	 */
	public  List<String> fetchListEU_Countries(String applicationUser){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS2R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<String> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts2Container container = this.maintSadImportKodts2Service.getList(jsonPayload);
	        if(container!=null){
	        	for(JsonMaintSadImportKodts2Record record : container.getList()){
	        		if("A".equals(record.getKs2pre()) || "B".equals(record.getKs2pre()) || "C".equals(record.getKs2pre()) ){
	        			list.add(record.getKs2lk());
	        		}
	        	}
	        }
    	}
    	return list;
    	
	}
}
