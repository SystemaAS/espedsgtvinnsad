/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.util.manager;

import java.util.*;
import org.apache.log4j.Logger;

import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.manifest.express.util.TvinnSadManifestConstants;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * The class handles general gui drop downs aspect population
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 *  
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	
	public static final String CODE_VEHICLE_TYPES = "SADEFBKODE";
	
	
	/**
	 * Type is always a predefined value as in SADEFBKODE or FUNKSJON, etc
	 * @param appUser
	 * @param type
	 */
	public void populateCodesHtmlDropDownsFromJsonString(SystemaWebUser appUser, String type, Map model, 
										UrlCgiProxyService urlCgiProxyService, MaintMainKofastService maintMainKofastService){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&kftyp=" + FasteKoder.SADEFBKODE.toString());
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
		
    	model.put(TvinnSadManifestConstants.RESOURCE_MODEL_KEY_VEHICLE_LIST, list);
	}	

    	
}

	
