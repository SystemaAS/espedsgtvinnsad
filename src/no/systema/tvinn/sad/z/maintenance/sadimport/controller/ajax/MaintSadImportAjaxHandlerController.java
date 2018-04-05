package no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiRecord;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariRecord;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesKodtsiService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesKodtlbService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesTariService;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesSoktariService;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;
import no.systema.tvinn.sad.z.maintenance.sad.service.MaintSadSadlService;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.*;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.*;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.felles.url.store.TvinnSadMaintenanceFellesUrlDataStore;


/**
 *  TVINN Maintenance Import AJAX Controller 
 * 
 * @author oscardelatorre
 * @date Mar 30, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportAjaxHandlerController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft18r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportCundfLikvKodeRecord> getRecordSyft18
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft18 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportCundfLikvKodeRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft18(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft19r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodtlikRecord> getRecordSyft19
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft19 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtlikRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft19(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft04r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodttsRecord> getRecordSyft04
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft04 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodttsRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft04(applicationUser, id); 
    	
    	return result;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param kundnr
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad001ar.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportSadvareRecord> getRecordSad001A
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String kundnr) {
		final String METHOD = "[DEBUG] getRecordSad001A ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportSadvareRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad001A(applicationUser, id, kundnr);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param kundnr
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad004r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadSadlRecord> getRecordSad004
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String kundnr) {
		final String METHOD = "[DEBUG] getRecordSad004 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadSadlRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad004(applicationUser, id, kundnr);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad006r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportSadhHeadfRecord> getRecordSad006
	  	(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		final String METHOD = "[DEBUG] getRecordSad006 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportSadhHeadfRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad006(applicationUser, avd, opd);
    	
    	return result;
	
	}

	
	
	
	@RequestMapping(value="getSpecificRecord_sad999r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportSadsdRecord> getRecordSad999
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String alfa, @RequestParam String fdate, @RequestParam String tdate ) {
		final String METHOD = "[DEBUG] getRecordSad010 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportSadsdRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad999(applicationUser, id, alfa, fdate, tdate);
    	
    	return result;
	
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSadImportKodtlikRecord> fetchListSyft19(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&klikod=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodtlikRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
	        JsonMaintSadImportKodtlikContainer container = this.maintSadImportKodtlikService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodtlikRecord record: list){
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
	private Collection<JsonMaintSadImportCundfLikvKodeRecord> fetchListSyft18(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kundnr=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportCundfLikvKodeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportCundfLikvKodeContainer container = this.maintSadImportCundfLikvKodeService.getList(jsonPayload);
	        if(container!=null){
	        	//list = (List)container.getList();
	        	for(JsonMaintSadImportCundfLikvKodeRecord record : container.getList()){
	        		//Exception case for Systema. 
	        		if("SS".equals(record.getFirma()) ){
	        			//do not include
	        		}else{
	        			list.add(record);
	        		}
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
	private Collection<JsonMaintSadImportKodttsRecord> fetchListSyft04(String applicationUser, String id){
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT04R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ktspnr=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodttsRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodttsContainer container = this.maintSadImportKodttsService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodttsRecord record: list){
	        		//logger.info(record.getKtspnr());
	        	}
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
	private Collection<JsonMaintSadImportSadvareRecord> fetchListSad001A(String applicationUser, String id, String levenr){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD001AR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&varenr=" + id + "&levenr=" + levenr ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportSadvareRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportSadvareContainer container = this.maintSadImportSadvareService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportSadvareRecord record: list){
	        		//logger.info("my text");
	        	}
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
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&slalfa=" + id + "&slknr=" + levenr ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadSadlRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadSadlContainer container = this.maintSadSadlService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadSadlRecord record: list){
	        		//logger.info("my text");
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private Collection<JsonMaintSadImportSadhHeadfRecord> fetchListSad006(String applicationUser, String avd, String opd){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD006R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&siavd=" + avd + "&sitdn=" + opd ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportSadhHeadfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportSadhHeadfContainer container = this.maintSadImportSadhHeadfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportSadhHeadfRecord record: list){
	        		logger.info(record.getSitdn());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param alfa
	 * @return
	 */
	private Collection<JsonMaintSadImportSadsdRecord> fetchListSad999(String applicationUser, String id, String alfa, String fdate, String tdate){
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&sdtnrf=" + id + "&sddtf=" + fdate + "&sddtt=" + tdate ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportSadsdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportSadsdContainer container = this.maintSadImportSadsdService.getList(jsonPayload);
	        if(container!=null){
	        	for(JsonMaintSadImportSadsdRecord record: container.getList()){
	        		if(alfa.equals(record.getTaalfa())){
	        			logger.info(record.getSdtnrf());
		        		list.add(record);
	        		}
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
	
	
	@Qualifier ("maintSadImportKodtlikService")
	private MaintSadImportKodtlikService maintSadImportKodtlikService;
	@Autowired
	@Required
	public void setMaintSadImportKodtlikService (MaintSadImportKodtlikService value){ this.maintSadImportKodtlikService = value; }
	public MaintSadImportKodtlikService getMaintSadImportKodtlikService(){ return this.maintSadImportKodtlikService; }
	
	@Qualifier ("maintSadImportKodtsiService")
	private MaintSadFellesKodtsiService maintSadImportKodtsiService;
	@Autowired
	@Required
	public void setMaintSadImportKodtsiService (MaintSadFellesKodtsiService value){ this.maintSadImportKodtsiService = value; }
	public MaintSadFellesKodtsiService getMaintSadImportKodtsiService(){ return this.maintSadImportKodtsiService; }


	@Qualifier ("maintSadImportKodtlbService")
	private MaintSadFellesKodtlbService maintSadImportKodtlbService;
	@Autowired
	@Required
	public void setMaintSadImportKodtlbService (MaintSadFellesKodtlbService value){ this.maintSadImportKodtlbService = value; }
	public MaintSadFellesKodtlbService getMaintSadImportKodtlbService(){ return this.maintSadImportKodtlbService; }
	

	@Qualifier ("maintSadImportCundfLikvKodeService")
	private MaintSadImportCundfLikvKodeService maintSadImportCundfLikvKodeService;
	@Autowired
	@Required
	public void setMaintSadImportCundfLikvKodeService (MaintSadImportCundfLikvKodeService value){ this.maintSadImportCundfLikvKodeService = value; }
	public MaintSadImportCundfLikvKodeService getMaintSadImportCundfLikvKodeService(){ return this.maintSadImportCundfLikvKodeService; }
	
	
	@Qualifier ("maintSadImportSadsdService")
	private MaintSadImportSadsdService maintSadImportSadsdService;
	@Autowired
	@Required
	public void setMaintSadImportSadsdService (MaintSadImportSadsdService value){ this.maintSadImportSadsdService = value; }
	public MaintSadImportSadsdService getMaintSadImportSadsdService(){ return this.maintSadImportSadsdService; }
	
	
	@Qualifier ("maintSadImportSoktariService")
	private MaintSadFellesSoktariService maintSadImportSoktariService;
	@Autowired
	@Required
	public void setMaintSadImportSoktariService (MaintSadFellesSoktariService value){ this.maintSadImportSoktariService = value; }
	public MaintSadFellesSoktariService getMaintSadImportSoktariService(){ return this.maintSadImportSoktariService; }
	
	@Qualifier ("maintSadImportSadvareService")
	private MaintSadImportSadvareService maintSadImportSadvareService;
	@Autowired
	@Required
	public void setMaintSadImportSadvareService (MaintSadImportSadvareService value){ this.maintSadImportSadvareService = value; }
	public MaintSadImportSadvareService getMaintSadImportSadvareService(){ return this.maintSadImportSadvareService; }
	
	
	@Qualifier ("maintSadSadlService")
	private MaintSadSadlService maintSadSadlService;
	@Autowired
	@Required
	public void setMaintSadImportSadlService (MaintSadSadlService value){ this.maintSadSadlService = value; }
	public MaintSadSadlService getMaintSadImportSadlService(){ return this.maintSadSadlService; }
	
	
	@Qualifier ("maintSadImportSadhHeadfService")
	private MaintSadImportSadhHeadfService maintSadImportSadhHeadfService;
	@Autowired
	@Required
	public void setMaintSadImportSadhHeadfService (MaintSadImportSadhHeadfService value){ this.maintSadImportSadhHeadfService = value; }
	public MaintSadImportSadhHeadfService getMaintSadImportSadhHeadfService(){ return this.maintSadImportSadhHeadfService; }
	
	
	@Qualifier ("maintSadImportKodttsService")
	private MaintSadImportKodttsService maintSadImportKodttsService;
	@Autowired
	@Required
	public void setMaintSadImportKodttsService (MaintSadImportKodttsService value){ this.maintSadImportKodttsService = value; }
	public MaintSadImportKodttsService getMaintSadImportKodttsService(){ return this.maintSadImportKodttsService; }
	
	
}

