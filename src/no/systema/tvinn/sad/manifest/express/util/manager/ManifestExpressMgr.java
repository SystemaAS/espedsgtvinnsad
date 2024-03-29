/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.util.manager;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestLoggingContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestLoggingRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.TvinnSadManifestConstants;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.sadexport.util.SadExportConstants;
import no.systema.tvinn.sad.sadimport.util.SadImportConstants;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.manager.ArchiveGoogleCloudManager;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * The class handles general express-manifest handy general methods
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 *  
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
@Service
public class ManifestExpressMgr {
	private static final Logger logger = LoggerFactory.getLogger(ManifestExpressMgr.class.getName());
	
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	
	/**
	 *REQUEST: http://localhost:8080/syjservicesbcore/syjsuuid.do
	 *RESPONSE: {"uuid":"65a074ca-a87f-477c-90ac-6d1db5305350"} 
	 * @return
	 * @throws Exception
	 */
	public String getUuid() throws Exception{
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_UUID_URL;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + (BASE_URL));
    	logger.info("URL PARAMS: ");
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, "");
		Map<String,Object> result = new ObjectMapper().readValue(jsonPayload, HashMap.class);
		logger.info(result.toString());
		
		return (String)result.get("uuid");
	}
	
	/**
	 * 
	 * @param appUser
	 * @param tur
	 * @return
	 */
	public boolean isValidManifest(SystemaWebUser appUser, String tur){
		String STATUS_OK = "O";
		boolean retval = true;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&clpro=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
    		if(container.getList()!=null && container.getList().size()>0){
    			Collection<JsonTvinnSadManifestCargoLinesRecord> list = container.getList();	
				outer: for(JsonTvinnSadManifestCargoLinesRecord record: list ){
					if (!record.getClst().equals(STATUS_OK)){
						retval = false;
						break outer;
					}
				}
    		}else{
    			retval = false;
    		}
    	}
    	return retval;
	}
	/**
	 * Checks if the manifest has passed the today date (or today's time)
	 * @param appUser
	 * @param tur
	 * @return
	 */
	public boolean isEditableManifest(SystemaWebUser appUser, JsonTvinnSadManifestRecord record){
		boolean retval = true;
		//check all this ONLY when SUBMITTED or DELETED to toll.no
		if(JsonTvinnSadManifestRecord.MANIFEST_SUBMITTED.equals(record.getEfst2()) || 
				JsonTvinnSadManifestRecord.MANIFEST_DELETED.equals(record.getEfst2()) ||
				JsonTvinnSadManifestRecord.MANIFEST_COMPLETED.equals(record.getEfst2()) ){
			//under sending
			if(JsonTvinnSadManifestRecord.MANIFEST_COMPLETED.equals(record.getEfst2()) ){
				retval = false;
			}else if(JsonTvinnSadManifestRecord.MANIFEST_INTERNAL_STATUS_SENDING.equals(record.getEfst()) ||
					JsonTvinnSadManifestRecord.MANIFEST_INTERNAL_STATUS_DELETED.equals(record.getEfst()) ){ 
				retval = false;
			}else{
				//logger.warn("A");
				//now check deeper into date and/or time(in case of today)
				DateTimeManager dateTimeMgr = new DateTimeManager();
				String eta = record.getEfeta();
				String etm = record.getEfetm();
				if(eta!=null && eta.length()>6){ //ISO
					//logger.warn("B");
					eta = new ManifestDateManager().convertToDate_NO(eta);
				}
				//logger.warn("eta:" + eta);
				if(dateTimeMgr.isValidForwardDateNO(eta)){
					//OK
				}else{
					if(dateTimeMgr.isToday(eta, DateTimeManager.NO_FORMAT)){
						//logger.warn("C");
						etm = dateTimeMgr.adjustUserTimeToHHmm(etm);
						//check the hour. At least 2 hour ahead
						if(!dateTimeMgr.isValidTime(etm, JsonTvinnSadManifestRecord.MANIFEST_AT_LEAST_HOURS_AHEAD_VALID)){
							retval = false;
						}
					}else{
						//logger.warn("ZZZ");
						//meaning is before today
						retval = false;
					}
				}
				
			}
		}else if(JsonTvinnSadManifestRecord.MANIFEST_INTERNAL_STATUS_DELETED.equals(record.getEfst())){
			retval = false;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param tdn
	 * @return
	 */
	
	public Collection<JsonTvinnSadManifestArchivedDocsRecord> fetchArchiveDocs(SystemaWebUser appUser, String avd, String tdn) {
		 Collection<JsonTvinnSadManifestArchivedDocsRecord> outputList = new ArrayList<JsonTvinnSadManifestArchivedDocsRecord>();
		 //===========
		 //FETCH LIST
		 //===========
		 logger.warn("Inside: fetchArchiveDocs");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_ARCHIVED_UPLOADED_DOCS_URL;
		 
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + tdn ;
		 logger.warn("URL: " + BASE_URL);
		 logger.warn("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTvinnSadManifestArchivedDocsContainer container = this.tvinnSadManifestListService.getArchiveDocsContainer(jsonPayload);
			 		//adjust to google cloud if needed
		    		container = new ArchiveGoogleCloudManager().adjustUrl(appUser, container);
		    		
					if(container!=null){
						Collection<JsonTvinnSadManifestArchivedDocsRecord> tmp = container.getGetdoc();
						for(JsonTvinnSadManifestArchivedDocsRecord record : tmp){
							logger.info("####Link:" + record.getDoclnk());
							//in case Google link (bad to show)
							if(record.getDoclnk().startsWith("http")) {
								//default
								record.setDoctxtaux(record.getDoclnk());
								//adjust
								int i = record.getDoclnk().indexOf("&filename=");
								if(i>=0) {
									String xx = record.getDoclnk().substring(i);
									xx = xx.replace("&filename=", ".../"); 
									record.setDoctxtaux(xx);
								}
							}
							outputList.add(record);
						}
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 return outputList;
	}
	
	

	/**
	 * Gets all logging on db
	 * 
	 * @param applicationUser
	 * @param pro
	 * @return
	 */
	public Collection<JsonTvinnSadManifestLoggingRecord> fetchLogging(String applicationUser, String pro) {
		 Collection<JsonTvinnSadManifestLoggingRecord> outputList = new ArrayList<JsonTvinnSadManifestLoggingRecord>();
		 //===========
		 //FETCH LIST
		 //===========
		 logger.warn("Inside: fetchLogging");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_LOGGING_URL;
		 
		 String urlRequestParamsKeys = "user=" + applicationUser + "&pro=" + pro;
		 logger.warn("URL: " + BASE_URL);
		 logger.warn("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTvinnSadManifestLoggingContainer container = this.tvinnSadManifestListService.getLoggingContainer(jsonPayload);
					if(container!=null){
						outputList = container.getLogg();
						for(JsonTvinnSadManifestLoggingRecord record : outputList){
							//todo
						}
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 return outputList;
	}

}

	
