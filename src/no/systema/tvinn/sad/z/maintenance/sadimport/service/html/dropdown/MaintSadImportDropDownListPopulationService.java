/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.html.dropdown;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

import org.apache.log4j.Logger;


import no.systema.main.util.JsonDebugger;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.z.maintenance.sadimport.controller.MaintSadImportSyft04rController;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodttsMapper;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;



/**
 * Accesses the mapper 
 * 
 * @author oscardelatorre
 * @date Jun 21, 2014
 * 
 */
public class MaintSadImportDropDownListPopulationService {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportDropDownListPopulationService.class.getName());
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param applicationUser
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public List<JsonMaintSadImportKodttsRecord> getPostnrKodttsContainer(UrlCgiProxyService urlCgiProxyService, String applicationUser) throws Exception{
		MaintSadImportKodttsMapper mapper = new MaintSadImportKodttsMapper();
		List<JsonMaintSadImportKodttsRecord> list = new ArrayList();
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_DROPDOWN_SYFT04R_GET_POSTNR_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
  
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodttsContainer container = mapper.getContainer(jsonPayload);
	        if(container!=null){
	        	list = (List) container.getList();
	        	//DEBUG
	        	for(JsonMaintSadImportKodttsRecord record : container.getList()){
	        		//logger.info("KTXPNR:" + record.getKtxpnr());
	        	}
	        }
    	}
    	
    	return list;
	}
	
	
	
}
