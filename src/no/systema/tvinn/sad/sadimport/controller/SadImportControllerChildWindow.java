package no.systema.tvinn.sad.sadimport.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
/*
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
*/
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalRecord;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;
import no.systema.tvinn.sad.util.TvinnSadConstants;




/**
 * SAD Import Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Nov 19, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportControllerChildWindow {
	
	private static final Logger logger = LoggerFactory.getLogger(SadImportControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";

	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_childwindow_external_invoices.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tvinnsadImportExternalInvoices(@ModelAttribute ("record") JsonSadImportTopicFinansOpplysningerExternalRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tvinnsadImportExternalInvoices");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_childwindow_external_invoices");
		JsonSadImportTopicFinansOpplysningerExternalRecord jsonSadImportTopicInvoiceExternalRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			
			//this fragment gets some header fields needed for the validator
			//JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			//String invoiceTotalAmount = headerRecord.getSveh_fabl();
			model.put("avd", avd);
			model.put("opd", opd);
			
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL;
			urlRequestParamsKeys = "user=" + appUser.getUser();
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadImportTopicFinansOpplysningerExternalContainer container = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainerContainerExternal(jsonPayloadFetch);
	    	//drop downs populated from back-end
	    	this.setDomainObjectsForListInView(model, container);
			
	    	successView.addObject("model",model);
			
	    	logger.info("END of method");
	    	return successView;
		}
		
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_childwindow_external_invoices_delete.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tvinnsadimportExternalInvoicesDelete(@ModelAttribute ("record") JsonSadImportTopicFinansOpplysningerExternalRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tvinnsadimportExternalInvoicesDelete");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//redirect view
		StringBuffer paramsRedirect = new StringBuffer();
		paramsRedirect.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getSfavd() + "&opd=" + recordToValidate.getSfopdn());
		ModelAndView successView = new ModelAndView("redirect:tvinnsadimport_edit_childwindow_external_invoices.do?" + paramsRedirect);
		
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser == null || "".equals(appUser)){
		  return this.loginView;
		}else{
		
		  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
		  String params = "user=" + appUser.getUser() + "&mode=D" + "&reff=" + recordToValidate.getSfreff() + "&unik=" + recordToValidate.getSfunik();
		  logger.info("URL:" + BASE_URL);
		  logger.info("PARAMS:" + params);
		  
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, params);
		  JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer container = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate(jsonPayload);
		  
		  if(container!=null && ( container.getErrmsg()!=null && !"".equals(container.getErrmsg())) ){
			  logger.info("[ERROR] " + container.getErrmsg());
		  }else{
			  logger.info("[INFO]" + " Update successfully done!");
		  }
		  //logger.info("END of method");
		  return successView;
		}
		
	}
	/**
	 * 
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsForListInView(Map model, JsonSadImportTopicFinansOpplysningerExternalContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSadImportTopicFinansOpplysningerExternalRecord record : container.getListexternfakt()){
				//this.adjustDatesOnFetch(record);
				list.add(record);
			}
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
		
	}
	/**
	 * 
	 * @param appUser
	 * @param countryCode
	 * @param itemCode
	 * @return
	 */
	/*
	private List<JsonTdsTillaggskodRecord> getTillagsCodesList(SystemaWebUser appUser, String countryCode, String itemCode){
		List<JsonTdsTillaggskodRecord> list = new ArrayList<JsonTdsTillaggskodRecord>();
		String IE_MODE = "I";
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_TILLAGSKODER_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + IE_MODE);
		urlRequestParams.append("&lk=" + countryCode + "&vata=" + itemCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTdsTillaggskodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsTillaggskoderService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsTillaggskodRecord  record : container.getR33tillkoder()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	*/
	/**
	 * 
	 * @param appUser
	 * @param countryCode
	 * @param itemCode
	 * @param formansCode
	 * @return
	 */
	/*
	private List<JsonTdsBilagdaHandlingarYKoderRecord> getBilagdaHandlingarCodesList(SystemaWebUser appUser, String countryCode, String itemCode, String formansCode){
		List<JsonTdsBilagdaHandlingarYKoderRecord> list = new ArrayList<JsonTdsBilagdaHandlingarYKoderRecord>();
		
		String IE_MODE = "I";
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_BILAGDA_HANDLIGAR_YKODER_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + IE_MODE);
		urlRequestParams.append("&lk=" + countryCode + "&vata=" + itemCode + "&fokd=" + formansCode);
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		  
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTdsBilagdaHandlingarYKoderContainer container = null;
		
		try{
			if(jsonPayload!=null){
				container = this.tdsBilagdaHandlingarYKoderService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsBilagdaHandlingarYKoderRecord  record : container.getBilhandykoder()){
						list.add(record);
	    			}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	*/

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	/**
	@Qualifier 
	private TdsTillaggskoderService tdsTillaggskoderService;
	@Autowired
	@Required	
	public void setTdsTillaggskoderService(TdsTillaggskoderService value){this.tdsTillaggskoderService = value;}
	public TdsTillaggskoderService getTdsTillaggskoderService(){ return this.tdsTillaggskoderService; }
	
	
	@Qualifier 
	private TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService;
	@Autowired
	@Required	
	public void setTdsBilagdaHandlingarYKoderService(TdsBilagdaHandlingarYKoderService value){this.tdsBilagdaHandlingarYKoderService = value;}
	public TdsBilagdaHandlingarYKoderService getTdsBilagdaHandlingarYKoderService(){ return this.tdsBilagdaHandlingarYKoderService; }
	*/
	
	@Qualifier ("sadImportSpecificTopicService")
	private SadImportSpecificTopicService sadImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicService (SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	public SadImportSpecificTopicService getSadImportSpecificTopicService(){ return this.sadImportSpecificTopicService; }
	
}

