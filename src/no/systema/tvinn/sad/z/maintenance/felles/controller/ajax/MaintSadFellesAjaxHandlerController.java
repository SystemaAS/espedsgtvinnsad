package no.systema.tvinn.sad.z.maintenance.felles.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
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

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiRecord;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbRecord;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariRecord;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariRecord;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesKodtsiService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesKodtlbService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesTariService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesSoktariService;
import no.systema.tvinn.sad.z.maintenance.felles.url.store.TvinnSadMaintenanceFellesUrlDataStore;



/**
 *  TVINN Maintenance Felles AJAX Controller 
 * 
 * @author oscardelatorre
 * @date Okt 20, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadFellesAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadFellesAjaxHandlerController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft10r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadFellesKodtsiRecord> getRecordSyft10
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft10 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadFellesKodtsiRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft10(applicationUser, id); 
    	
    	return result;
    	
	}
	
	

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad010r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadFellesTariRecord> getRecordSad010
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String alfa) {
		final String METHOD = "[DEBUG] getRecordSad010 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadFellesTariRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad010(applicationUser, id, alfa);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad012r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadFellesKodtlbRecord> getRecordSad012
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSad012 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadFellesKodtlbRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad012(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param alfa
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad062r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadFellesSoktariRecord> getRecordSad062
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String alfa) {
		final String METHOD = "[DEBUG] getRecordSad062 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadFellesSoktariRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad062(applicationUser, id, alfa);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param alfa
	 * @return
	 */
	private Collection<JsonMaintSadFellesSoktariRecord> fetchListSad062(String applicationUser, String id, String alfa){
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD062R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tariff=" + id + "&beskr1=" + alfa;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadFellesSoktariRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadFellesSoktariContainer container = this.maintSadImportSoktariService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadFellesSoktariRecord record: list){
	        		logger.info(record.getTariff());
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
	private Collection<JsonMaintSadFellesKodtsiRecord> fetchListSyft10(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SYFT10R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksisig=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadFellesKodtsiRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadFellesKodtsiContainer container = this.maintSadFellesKodtsiService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadFellesKodtsiRecord record: list){
	        		//logger.info(record.getKlikod());
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
	private Collection<JsonMaintSadFellesTariRecord> fetchListSad010(String applicationUser, String id, String alfa){
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD010R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tatanr=" + id + "&taalfa=" + alfa;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadFellesTariRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadFellesTariContainer container = this.maintSadFellesTariService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadFellesTariRecord record: list){
	        		logger.info(record.getTatanr());
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
	private Collection<JsonMaintSadFellesKodtlbRecord> fetchListSad012(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD012R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&klbkod=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadFellesKodtlbRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadFellesKodtlbContainer container = this.maintSadFellesKodtlbService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadFellesKodtlbRecord record: list){
	        		logger.info("X"+record.getKlbxxx_mot()+"X");
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
	
	
	
	@Qualifier ("maintSadFellesKodtsiService")
	private MaintSadFellesKodtsiService maintSadFellesKodtsiService;
	@Autowired
	@Required
	public void setMaintSadFellesKodtsiService (MaintSadFellesKodtsiService value){ this.maintSadFellesKodtsiService = value; }
	public MaintSadFellesKodtsiService getMaintSadFellesKodtsiService(){ return this.maintSadFellesKodtsiService; }

	@Qualifier ("maintSadFellesKodtlbService")
	private MaintSadFellesKodtlbService maintSadFellesKodtlbService;
	@Autowired
	@Required
	public void setMaintSadFellesKodtlbService (MaintSadFellesKodtlbService value){ this.maintSadFellesKodtlbService = value; }
	public MaintSadFellesKodtlbService getMaintSadFellesKodtlbService(){ return this.maintSadFellesKodtlbService; }
	
	@Qualifier ("maintSadFellesTariService")
	private MaintSadFellesTariService maintSadFellesTariService;
	@Autowired
	@Required
	public void setMaintSadFellesTariService (MaintSadFellesTariService value){ this.maintSadFellesTariService = value; }
	public MaintSadFellesTariService getMaintSadFellesTariService(){ return this.maintSadFellesTariService; }
	
	
	@Qualifier ("maintSadImportSoktariService")
	private MaintSadFellesSoktariService maintSadImportSoktariService;
	@Autowired
	@Required
	public void setMaintSadImportSoktariService (MaintSadFellesSoktariService value){ this.maintSadImportSoktariService = value; }
	public MaintSadFellesSoktariService getMaintSadImportSoktariService(){ return this.maintSadImportSoktariService; }
	
	

}

