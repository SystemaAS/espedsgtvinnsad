package no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax.manager;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoRecord;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdRecord;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaRecord;
import no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder.MaintSadKodtsoService;
import no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder.MaintSadKodtsdService;
import no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder.MaintSadKodtsaService;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.*;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.*;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;
import no.systema.tvinn.sad.z.maintenance.sad.url.store.TvinnSadMaintenanceUrlDataStoreGyldigeKoder;


/**
 *  TVINN Maintenance Import SAD002 - Gyldige koder Manager
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 */
@Component
public class MaintSadImportGyldigeKoderAjaxHandlerManager {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportGyldigeKoderAjaxHandlerManager.class.getName());
		
	
	/**
	 * 
	 * @param maintSadImportKodts1Service
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts1Record> fetchListKodts1(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS1R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks1typ=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintSadImportKodts1Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts1Container container = this.maintSadImportKodts1Service.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts1Record record: list){
	        		logger.info(record.getKs1typ());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts2Record> fetchListKodts2(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS2R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks2lk=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintSadImportKodts2Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts2Container container = this.maintSadImportKodts2Service.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts2Record record: list){
	        		//logger.info(record.getKs2lk());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts3Record> fetchListKodts3(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS3R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks3trt=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts3Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts3Container container = this.maintSadImportKodts3Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts3Record record: list){
	        		logger.info(record.getKs3trt());
	        	}
	        }
    	}
    	return list;	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts4Record> fetchListKodts4(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks4trm=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts4Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts4Container container = this.maintSadImportKodts4Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts4Record record: list){
	        		logger.info(record.getKs4trm());
	        	}
	        }
    	}
    	return list;	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts5Record> fetchListKodts5(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS5R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks5tln=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts5Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts5Container container = this.maintSadImportKodts5Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts5Record record: list){
	        		logger.info(record.getKs5tln());
	        	}
	        }
    	}
    	return list;	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts6Record> fetchListKodts6(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS6R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks6pre=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts6Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts6Container container = this.maintSadImportKodts6Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts6Record record: list){
	        		//logger.info(record.getKs6pre());
	        	}
	        }
    	}
    	return list;	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts7Record> fetchListKodts7(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS7R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks7vf=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts7Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts7Container container = this.maintSadImportKodts7Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts7Record iRecord: list){
	        		//logger.info(record.getKs7vf());
	        	}
	        }
    	}
    	return list;	
	}

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts8Record> fetchListKodts8(String applicationUser, String avgId, String skvId){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS8R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks8avg=" + avgId + "&ks8skv=" + skvId;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts8Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts8Container container = this.maintSadImportKodts8Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts8Record record: list){
	        		logger.info(record.getKs8avg());
	        	}
	        }
    	}
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadKodtsaRecord> fetchListKodtsa(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_BASE_SAD002_KODTSAR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksakd=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadKodtsaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadKodtsaContainer container = this.maintSadKodtsaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadKodtsaRecord record: list){
	        		logger.info(record.getKsakd());
	        	}
	        }
    	}
    	return list;
    	
	}

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodtsbRecord> fetchListKodtsb(String applicationUser, String id){
	
	String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTSBR_GET_LIST_URL;
	String urlRequestParams = "user=" + applicationUser + "&ksbkd=" + id;
	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	logger.info("URL PARAMS: " + urlRequestParams);
	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	//extract
	List<JsonMaintSadImportKodtsbRecord> list = new ArrayList();
	if(jsonPayload!=null){
		//lists
		JsonMaintSadImportKodtsbContainer container = this.maintSadImportKodtsbService.getList(jsonPayload);
        if(container!=null){
        	list = (List)container.getList();
        	for(JsonMaintSadImportKodtsbRecord record: list){
        		logger.info(record.getKsbkd());
        	}
        }
	}
	return list;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadKodtsdRecord> fetchListKodtsd(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_BASE_SAD002_KODTSDR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksdls=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		//extract
		List<JsonMaintSadKodtsdRecord> list = new ArrayList();
		if(jsonPayload!=null){
			//lists
			JsonMaintSadKodtsdContainer container = this.maintSadKodtsdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadKodtsdRecord record: list){
	        		logger.info(record.getKsdls());
	        	}
	        }
		}
		return list;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadKodtsoRecord> fetchListKodtso(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_BASE_SAD002_KODTSOR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksokd=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		//extract
		List<JsonMaintSadKodtsoRecord> list = new ArrayList();
		if(jsonPayload!=null){
			//lists
			JsonMaintSadKodtsoContainer container = this.maintSadKodtsoService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadKodtsoRecord record: list){
	        		logger.info(record.getKsokd());
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
	
	
	@Qualifier ("maintSadImportKodts1Service")
	private MaintSadImportKodts1Service maintSadImportKodts1Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts1Service (MaintSadImportKodts1Service value){ this.maintSadImportKodts1Service = value; }
	public MaintSadImportKodts1Service getMaintSadImportKodts1Service(){ return this.maintSadImportKodts1Service; }
	
	
	@Qualifier ("maintSadImportKodts2Service")
	private MaintSadImportKodts2Service maintSadImportKodts2Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts2Service (MaintSadImportKodts2Service value){ this.maintSadImportKodts2Service = value; }
	public MaintSadImportKodts2Service getMaintSadImportKodts2Service(){ return this.maintSadImportKodts2Service; }
	
	
	@Qualifier ("maintSadImportKodts3Service")
	private MaintSadImportKodts3Service maintSadImportKodts3Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts3Service (MaintSadImportKodts3Service value){ this.maintSadImportKodts3Service = value; }
	public MaintSadImportKodts3Service getMaintSadImportKodts3Service(){ return this.maintSadImportKodts3Service; }
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	@Qualifier ("maintSadImportKodts5Service")
	private MaintSadImportKodts5Service maintSadImportKodts5Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts5Service (MaintSadImportKodts5Service value){ this.maintSadImportKodts5Service = value; }
	public MaintSadImportKodts5Service getMaintSadImportKodts5Service(){ return this.maintSadImportKodts5Service; }
	
	@Qualifier ("maintSadImportKodts6Service")
	private MaintSadImportKodts6Service maintSadImportKodts6Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts6Service (MaintSadImportKodts6Service value){ this.maintSadImportKodts6Service = value; }
	public MaintSadImportKodts6Service getMaintSadImportKodts6Service(){ return this.maintSadImportKodts6Service; }
	
	@Qualifier ("maintSadImportKodts7Service")
	private MaintSadImportKodts7Service maintSadImportKodts7Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts7Service (MaintSadImportKodts7Service value){ this.maintSadImportKodts7Service = value; }
	public MaintSadImportKodts7Service getMaintSadImportKodts7Service(){ return this.maintSadImportKodts7Service; }
	
	
	@Qualifier ("maintSadImportKodts8Service")
	private MaintSadImportKodts8Service maintSadImportKodts8Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts8Service (MaintSadImportKodts8Service value){ this.maintSadImportKodts8Service = value; }
	public MaintSadImportKodts8Service getMaintSadImportKodts8Service(){ return this.maintSadImportKodts8Service; }
	
	
	@Qualifier ("maintSadKodtsaService")
	private MaintSadKodtsaService maintSadKodtsaService;
	@Autowired
	@Required
	public void setMaintSadKodtsaService (MaintSadKodtsaService value){ this.maintSadKodtsaService = value; }
	public MaintSadKodtsaService getMaintSadKodtsaService(){ return this.maintSadKodtsaService; }
	
	
	@Qualifier ("maintSadImportKodtsbService")
	private MaintSadImportKodtsbService maintSadImportKodtsbService;
	@Autowired
	@Required
	public void setMaintSadImportKodtsbService (MaintSadImportKodtsbService value){ this.maintSadImportKodtsbService = value; }
	public MaintSadImportKodtsbService getMaintSadImportKodtsbService(){ return this.maintSadImportKodtsbService; }
	
	
	@Qualifier ("maintSadKodtsdService")
	private MaintSadKodtsdService maintSadKodtsdService;
	@Autowired
	@Required
	public void setMaintSadKodtsdService (MaintSadKodtsdService value){ this.maintSadKodtsdService = value; }
	public MaintSadKodtsdService getMaintSadKodtsdService(){ return this.maintSadKodtsdService; }
	
	
	@Qualifier ("maintSadKodtsoService")
	private MaintSadKodtsoService maintSadKodtsoService;
	@Autowired
	@Required
	public void setMaintSadKodtsoService (MaintSadKodtsoService value){ this.maintSadKodtsoService = value; }
	public MaintSadKodtsoService getMaintSadKodtsoService(){ return this.maintSadKodtsoService; }
	
}

