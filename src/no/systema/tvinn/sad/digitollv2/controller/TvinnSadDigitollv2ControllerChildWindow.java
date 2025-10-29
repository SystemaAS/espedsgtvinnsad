package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

import org.apache.http.util.TextUtils;
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.DateValidator;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.main.context.TdsAppContext;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.service.TvinnSadCustomerService;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.service.TvinnSadTullkontorService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorRecord;
import no.systema.tvinn.sad.digitollv2.controller.service.HouseDocLogControllerService;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.api.entrymovementroad.EntryMovRoadDto;
import no.systema.tvinn.sad.digitollv2.model.api.routing.EntryRoutingDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiHouseRefsRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiMasterRefsContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiMasterRefsRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiMrnStatusWithDescendantsRecordDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiRefsWithDescendantsContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ApiRefsWithDescendantsLightContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.HouseConsignments;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.MasterConsignments;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmobuplgContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmobuplgRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolffContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolffRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolhffContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolhffRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadOppdragService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
import no.systema.tvinn.sad.digitollv2.service.SadmobuplgListService;
import no.systema.tvinn.sad.digitollv2.service.SadmocfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmolffListService;
import no.systema.tvinn.sad.digitollv2.service.SadmolhffListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.service.ZadmoattfListService;
import no.systema.tvinn.sad.digitollv2.service.ZadmomlfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;




/**
 * Tvinn Sad Digitoll v2 Controller Child window
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
@Controller
public class TvinnSadDigitollv2ControllerChildWindow {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2ControllerChildWindow.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private DateValidator dateValidator = new DateValidator();
	private DateTimeManager dateMgr = new DateTimeManager();
	private Integer sadiSearchNrOfDaysBackwards = -0;
	//
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_customer.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView searchCustomer(HttpSession session, HttpServletRequest request){
		logger.info("Inside searchCustomer");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_customer");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		logger.info(callerType);
		String customerName = request.getParameter("sonavn");
		String customerNr = request.getParameter("knr");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		if(appUser==null){
			return this.loginView;
				
		}else{
			Collection<JsonTvinnSadCustomerRecord> list = new ArrayList<JsonTvinnSadCustomerRecord>();
			//prepare the access CGI with RPG back-end
			if( (customerNr!=null && !"".equals(customerNr)) || (customerName!=null && !"".equals(customerName)) ){
				String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_CUSTOMER_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(appUser.getUser(), customerName, customerNr);
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
				logger.info(jsonPayload);
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				  
				if(jsonPayload!=null){
			  		JsonTvinnSadCustomerContainer container = this.tvinnSadCustomerService.getTvinnSadCustomerContainer(jsonPayload);
			  		if(container!=null){
			  			list = container.getCustomerlist();
			  			for(JsonTvinnSadCustomerRecord  record : container.getCustomerlist()){
			  				logger.info("CUSTOMER: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
			  				//logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
			  			}
			  		}
			  	}
			}
			
			model.put("customerList", list);
			model.put("sonavn", customerName);
			model.put("knr", customerNr);
			model.put("ctype", callerType);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;	
		  	
		}
		
	}
	/**
	 * Render the status of the manifest (transport, master or house level ...) from toll.no
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_manifestinfo.do",  method={RequestMethod.GET} )
	public ModelAndView doManifestInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doManifestInfo");
		Map model = new HashMap();
		String id = request.getParameter("id");
		String level = request.getParameter("level");
		String apiType = request.getParameter("apiType");
		logger.info("#########:" + apiType);
		if(apiType==null) { apiType = ""; }
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			
			if(StringUtils.isNotEmpty(level) && (level.equals("t")||level.equals("m")||level.equals("h"))) {
				if(level.equals("t")) {
					url.append("getTransport.do");
				}else if (level.equals("m")) {
					url.append("getMasterConsignment.do");
				}else if (level.equals("h")) {
					url.append("getHouseConsignment.do");
				}
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&lrn=" + id + "&apiType=" + apiType;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		model.put("content", jsonPayload);
			}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_routinginfo.do",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doRoutingInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doRoutingInfo");
		Map model = new HashMap();
		String level = request.getParameter("level");
		String uuid = request.getParameter("uuid");
		
		
		//OLD ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transport_routing_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			
			if(StringUtils.isNotEmpty(level) && (level.equals("t")||level.equals("m")||level.equals("h"))) {
				if(level.equals("t")) {
					url.append("getRoutingTransport.do");
				}else if (level.equals("h")) {
					url.append("getRoutingHouseConsignment.do");
				}
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&uuid=" + uuid;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		
	    		//Old case ... model.put("content", jsonPayload);
	    		
	    		try {
	    			ApiGenericDtoResponse obj = new ObjectMapper().readValue(jsonPayload, ApiGenericDtoResponse.class);
	    			
	    			if(obj!=null && obj.getErrMsg().isEmpty()) {
	    				
	    				model.put("list", obj.getEntryList());
						for (EntryRoutingDto dto: obj.getEntryList()) {
							//DEBUG
							logger.debug("#entrySummaryDeclarationMRN#:" + dto.getEntrySummaryDeclarationMRN());
							logger.debug("#transportDocumentHouseLevel#");
							logger.debug("referenceNumber:" + dto.getTransportDocumentHouseLevel().getReferenceNumber());
							logger.debug("type:" + dto.getTransportDocumentHouseLevel().getType());
							logger.debug("#routingResult#");
							logger.debug("id:" + dto.getRoutingResult().getId());
							logger.debug("routing:" + dto.getRoutingResult().getRouting());
						}
						
	    				//to allow local tests where the payload does not exist
	    				/*
						if(obj.getEntryList().isEmpty() && appUser.getUser().equals("OSCAR")) {
							String FAKE_LIST = "testFakeRouting.json";
							String jsonTest = textFileReaderService.getFileLinesStringPayload(TdsServletContext.getTdsServletContext().getResourceAsStream(AppConstants.RESOURCE_FILES_PATH + "digitoll/" + FAKE_LIST));
							obj = new ObjectMapper().readValue(jsonTest, ApiGenericDtoResponse.class); 
							logger.debug(obj.getEntryList().toString());
							
							model.put("list", obj.getEntryList());
							for (EntryRoutingDto dto: obj.getEntryList()) {
								//DEBUG
								logger.debug("#entrySummaryDeclarationMRN#:" + dto.getEntrySummaryDeclarationMRN());
								logger.debug("#transportDocumentHouseLevel#");
								logger.debug("referenceNumber:" + dto.getTransportDocumentHouseLevel().getReferenceNumber());
								logger.debug("type:" + dto.getTransportDocumentHouseLevel().getType());
								logger.debug("#routingResult#");
								logger.debug("id:" + dto.getRoutingResult().getId());
								logger.debug("routing:" + dto.getRoutingResult().getRouting());
							}
							
						}else {
							
							model.put("list", obj.getEntryList());
							for (EntryRoutingDto dto: obj.getEntryList()) {
								//DEBUG
								logger.debug("#entrySummaryDeclarationMRN#:" + dto.getEntrySummaryDeclarationMRN());
								logger.debug("#transportDocumentHouseLevel#");
								logger.debug("referenceNumber:" + dto.getTransportDocumentHouseLevel().getReferenceNumber());
								logger.debug("type:" + dto.getTransportDocumentHouseLevel().getType());
								logger.debug("#routingResult#");
								logger.debug("id:" + dto.getRoutingResult().getId());
								logger.debug("routing:" + dto.getRoutingResult().getRouting());
							}
						}*/
						
	    			}else {
	    				logger.info("ErrorMsg:" + obj.getErrMsg());
	    				model.put("list", null);
	    			}
	    		}catch(Exception e) {
	    			e.toString();
	    		}
			}
			model.put("uuid", uuid);
			model.put("level", level);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * ICS2 - Presentation Entry-Summary-Declaration
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_ics2_presentation_ensinfo.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doPresentationIcs2EnsInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doPresentationIcs2EnsInfo");
		Map model = new HashMap();
		String mrn = request.getParameter("mrn");
		String lrn = request.getParameter("lrn");
		//for TEST
		//mrn = "23NO00000000000004"; //from toll.no predefined test-value
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_ics2_ensinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//end-user enters ENS-MRN
			if(StringUtils.isNotEmpty(mrn)) {
			
				StringBuilder url = new StringBuilder();
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				
				if(StringUtils.isNotEmpty(mrn)) {
					url.append("postEntrySummaryDeclaration.do");
					
					String BASE_URL = url.toString();
		    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&ensMrn=" + mrn + "&ensRequestId=" + lrn;
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonPayload);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    		
		    		model.put("lrn", lrn);
		    		model.put("mrn", mrn);
		    		model.put("content", jsonPayload);
		    		
		    		try {
		    			ApiGenericDtoResponse obj = new ObjectMapper().readValue(jsonPayload, ApiGenericDtoResponse.class);
		    			if(obj!=null) {
		    				EntryMovRoadDto dto = obj.getEntryMovementRoad();
							logger.debug(dto.toString());
							
		    			}
		    		}catch(Exception e) {
		    			e.toString();
		    		}
				}
			}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_movroad_entryinfo.do",  method={RequestMethod.GET} )
	public ModelAndView doEntryInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doEntryInfo");
		Map model = new HashMap();
		String mrn = request.getParameter("mrn");
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			
			if(StringUtils.isNotEmpty(mrn)) {
				url.append("getMovementRoadEntry.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + mrn;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		model.put("content", jsonPayload);
	    		
	    		try {
	    			ApiGenericDtoResponse obj = new ObjectMapper().readValue(jsonPayload, ApiGenericDtoResponse.class);
	    			if(obj!=null) {
	    				EntryMovRoadDto dto = obj.getEntryMovementRoad();
						logger.debug(dto.toString());
						
	    			}
	    		}catch(Exception e) {
	    			e.toString();
	    		}
			}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_movrail_entryinfo.do",  method={RequestMethod.GET} )
	public ModelAndView doEntryRailInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doEntryRailInfo");
		Map model = new HashMap();
		String mrn = request.getParameter("mrn");
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			
			if(StringUtils.isNotEmpty(mrn)) {
				url.append("getMovementRailEntry.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + mrn;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		model.put("content", jsonPayload);
	    		
	    		try {
	    			ApiGenericDtoResponse obj = new ObjectMapper().readValue(jsonPayload, ApiGenericDtoResponse.class);
	    			if(obj!=null) {
	    				EntryMovRoadDto dto = obj.getEntryMovementRoad();
						logger.debug(dto.toString());
						
	    			}
	    		}catch(Exception e) {
	    			e.toString();
	    		}
			}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_movair_entryinfo.do",  method={RequestMethod.GET} )
	public ModelAndView doEntryAirInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doEntryAirInfo");
		Map model = new HashMap();
		String mrn = request.getParameter("mrn");
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			
			if(StringUtils.isNotEmpty(mrn)) {
				url.append("getMovementAirEntry.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + mrn;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		model.put("content", jsonPayload);
	    		
	    		try {
	    			ApiGenericDtoResponse obj = new ObjectMapper().readValue(jsonPayload, ApiGenericDtoResponse.class);
	    			if(obj!=null) {
	    				EntryMovRoadDto dto = obj.getEntryMovementRoad();
						logger.debug(dto.toString());
						
	    			}
	    		}catch(Exception e) {
	    			e.toString();
	    		}
			}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_loginfo.do",  method={RequestMethod.GET} )
	public ModelAndView doLogInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doManifestInfo");
		Map model = new HashMap();
		String id1 = request.getParameter("id1");
		String id2 = request.getParameter("id2");
		String id3 = request.getParameter("id3");
		String level = request.getParameter("level");
		logger.info(id1 + id2 + id3 + level);
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_loginfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_LOG_URL);
			StringBuilder urlRequestParamsKeys = new StringBuilder();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&ellnrt=" + id1);
			if(StringUtils.isNotEmpty(id2)) {
				urlRequestParamsKeys.append("&ellnrm=" + id2);
			}
			if(StringUtils.isNotEmpty(id3)) {
				urlRequestParamsKeys.append("&ellnrh=" + id3);
			}
			//if(StringUtils.isNotEmpty(level) && (level.equals("t")||level.equals("m")||level.equals("h"))) {
				
				String BASE_URL = url.toString();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    		//Debug -->
		    	logger.debug(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		model.put("content", jsonPayload);
	    		
	    		//show pretty (does not work in html)
	    		/*Gson prettyGsonObject = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	    		JsonElement jsonElement = new JsonParser().parse(jsonPayload);
	    	    String prettyJsonString = prettyGsonObject.toJson(jsonElement);
	    	    model.put("content", prettyJsonString);
	    	    logger.info(prettyJsonString);
	    	    */
	    	    
			//}
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_transportdocs_rec.do",  method={RequestMethod.GET} )
	public ModelAndView doTransportDocsReceived(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doTransportDocsReceived");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transportdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecTransport.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.info(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    				model.put("listAux", container.getListAux());
    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getTransportDescendants(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			try {
    				ApiRefsWithDescendantsContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getSumOfWeightForMasterConsignments());
	    				logger.info(dto.getObject().getMasterConsignments().toString());
	    				for(MasterConsignments rec : dto.getObject().getMasterConsignments()) {
	    					for(HouseConsignments houses : rec.getHouseConsignments()) {
	    						logger.info(houses.getStatus());
	    						model.put("dtoHouseList", rec.getHouseConsignments());
	    					}
	    				}
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    		}
    		
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	
	/**
	 * La till nytt endepunkt for å hente oppsummert informasjon om status på transport og tilknyttede house og master consignments.
	 * was updated at toll.no on 2024-04-30
	 * 
	 * @param user
	 * @param mrn
	 * @return
	 */
	private String getTransportDescendants(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getTransportDescendants");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecTransport_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;
		
		
		
	}
	/*=====================
	 * RAIL
	 * ====================
	*/
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_transportdocs_rec_rail.do",  method={RequestMethod.GET} )
	public ModelAndView doTransportDocsReceivedRail(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doTransportDocsReceivedRail");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transportdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecTransportRail.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    				model.put("listAux", container.getListAux());
    			}
    			}catch(Exception e) {
    				logger.info(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getTransportDescendantsRail(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			try {
    				ApiRefsWithDescendantsContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getSumOfWeightForMasterConsignments());
	    				logger.info(dto.getObject().getMasterConsignments().toString());
	    				for(MasterConsignments rec : dto.getObject().getMasterConsignments()) {
	    					for(HouseConsignments houses : rec.getHouseConsignments()) {
	    						logger.info(houses.getStatus());
	    						model.put("dtoHouseList", rec.getHouseConsignments());
	    					}
	    				}
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    		}
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	
	private String getTransportDescendantsRail(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getTransportDescendantsRail");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecTransportRail_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;

	}
	/*=====================
	 * AIR
	 * ====================
	*/
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_transportdocs_rec_air.do",  method={RequestMethod.GET} )
	public ModelAndView doTransportDocsReceivedAir(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doTransportDocsReceivedRail");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transportdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecTransportAir.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    				model.put("listAux", container.getListAux());
    			}
    			}catch(Exception e) {
    				logger.info(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getTransportDescendantsAir(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			try {
    				ApiRefsWithDescendantsContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getSumOfWeightForMasterConsignments());
	    				logger.info(dto.getObject().getMasterConsignments().toString());
	    				for(MasterConsignments rec : dto.getObject().getMasterConsignments()) {
	    					for(HouseConsignments houses : rec.getHouseConsignments()) {
	    						logger.info(houses.getStatus());
	    						model.put("dtoHouseList", rec.getHouseConsignments());
	    					}
	    				}
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    		}
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	
	private String getTransportDescendantsAir(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getTransportDescendantsAir");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecTransportAir_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;

	}

	/**
	 * Runs the API method
	 * http://localhost:8080/syjservicestn-expft/digitollv2/getDocsRecMasterConsignment.do?user=OSCAR&mrn=23NONJB08UP98SOBT7
	 * 
	 * Gets the list of all documents received so far (at toll.no)
	 * Example
	 * --> https://api-test.toll.no/api/movement/road/status/v2/master-consignment/23NONJB08UP98SOBT7/transport-document/status
	 * --> json answer (toll.no): --> getBody():[{"documentNumber":"111222333898758711","type":"N741","received":false}, "documentNumber":"9999999999999999999","type":"N741","received":true}, ] 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_masterdocs_rec.do",  method={RequestMethod.GET} )
	public ModelAndView doMasterDocsReceived(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doMasterDocsReceived");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_masterdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecMasterConsignment.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    			}
    			}catch(Exception e) {
    				logger.info(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getMasterDescendants(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			
    			try {
    				ApiRefsWithDescendantsLightContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsLightContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getDocumentNumber());
	    				
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}
    		
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	
	private String getMasterDescendants(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getMasterDescendants");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecMasterConsignment_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;
		
		
		
	}
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_masterdocs_rec_rail.do",  method={RequestMethod.GET} )
	public ModelAndView doMasterDocsReceivedRail(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doMasterDocsReceivedRail");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_masterdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecMasterConsignmentRail.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    			}
    			}catch(Exception e) {
    				logger.info(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getMasterDescendantsRail(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			
    			try {
    				ApiRefsWithDescendantsLightContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsLightContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getDocumentNumber());
	    				
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}
    		
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	private String getMasterDescendantsRail(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getMasterDescendants");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecMasterConsignmentRail_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;
		
		
		
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_masterdocs_rec_air.do",  method={RequestMethod.GET} )
	public ModelAndView doMasterDocsReceivedAir(HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doMasterDocsReceivedAir");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_masterdocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecMasterConsignmentAir.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			try {
    			ApiMasterRefsContainer container = new ObjectMapper().readValue(jsonPayload, ApiMasterRefsContainer.class);
    			if(container!=null) {
    				model.put("mrn", container.getMrn());
    				model.put("list", container.getList());
    			}
    			}catch(Exception e) {
    				logger.info(e.toString());
    			}
    		}
    		//============================
    		//extra list with descendants
    		//============================
    		String jsonPayloadDescendants = this.getMasterDescendantsAir(appUser.getUser(), id);
    		if(StringUtils.isNotEmpty(jsonPayloadDescendants)) {
    			
    			try {
    				ApiRefsWithDescendantsLightContainer dto = new ObjectMapper().readValue(jsonPayloadDescendants, ApiRefsWithDescendantsLightContainer.class);
	    			if(dto!=null) {
	    				logger.info(dto.getObject().getDocumentNumber());
	    				
	    				model.put("mrnWithDescendants", id);
	    				model.put("dto", dto.getObject());
	    				
	    				//model.put("listAuxWithDescendants", container.getListAux());
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}
    		
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
		}
		
		return successView;
	}
	private String getMasterDescendantsAir(String user, String mrn) {
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: getMasterDescendants");
		
		
		StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
		url.append("getDocsRecMasterConsignmentAir_withDescendants.do");
		
		String BASE_URL = url.toString();
		String urlRequestParamsKeys = "user=" + user + "&mrn=" + mrn;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.info(jsonPayload);
    
		
		return jsonPayload;
		
		
		
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_housedocs_rec.do",  method={RequestMethod.GET} )
	public ModelAndView doHouseDocsReceived(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doHouseDocsReceived");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_housedocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecHouseConsignment.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		logger.info(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			
    			try {
    				ApiHouseRefsRecord dto = new ObjectMapper().readValue(jsonPayload, ApiHouseRefsRecord.class);
	    			if(dto!=null) {
	    				logger.info(dto.getDocumentNumber());
	    				
	    				model.put("mrn", id);
	    				model.put("dto", dto);
	    				
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}

    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_housedocs_rec_rail.do",  method={RequestMethod.GET} )
	public ModelAndView doHouseDocsReceivedRail(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doHouseDocsReceivedRail");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_housedocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecHouseConsignmentRail.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		logger.info(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			
    			try {
    				ApiHouseRefsRecord dto = new ObjectMapper().readValue(jsonPayload, ApiHouseRefsRecord.class);
	    			if(dto!=null) {
	    				logger.info(dto.getDocumentNumber());
	    				
	    				model.put("mrn", id);
	    				model.put("dto", dto);
	    				
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}


    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_housedocs_rec_air.do",  method={RequestMethod.GET} )
	public ModelAndView doHouseDocsReceivedAir(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doHouseDocsReceivedAir");
		Map model = new HashMap();
		String id = request.getParameter("id");
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_housedocrefs_api");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			url.append("getDocsRecHouseConsignmentAir.do");
			
			String BASE_URL = url.toString();
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mrn=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		logger.info(jsonPayload);
    		if(StringUtils.isNotEmpty(jsonPayload)) {
    			
    			try {
    				ApiHouseRefsRecord dto = new ObjectMapper().readValue(jsonPayload, ApiHouseRefsRecord.class);
	    			if(dto!=null) {
	    				logger.info(dto.getDocumentNumber());
	    				
	    				model.put("mrn", id);
	    				model.put("dto", dto);
	    				
	    			}
    			}catch(Exception e) {
    				logger.error(e.toString());
    			}
    			
    		}


    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_docapi_log.do",  method={RequestMethod.GET} )
	public ModelAndView doHouseDocApiLog(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doHouseDocApiLog");
		Map model = new HashMap();
		String ehdkh = request.getParameter("ehdkh");
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_docapi_log");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			StringBuilder url = new StringBuilder();
			
			List list = (List)this.houseDocLogControllerService.getList(appUser.getUser(), ehdkh );
			model.put("list", list);
			model.put("ehdkh", ehdkh);
    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_tullkontor.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitTullkontor(@ModelAttribute ("record") JsonTvinnSadTullkontorRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTullkontor");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String tullkontorCode = request.getParameter("tkkode");
		String tullkontorName = request.getParameter("tktxtn");
		String tullkontorType = request.getParameter("tktype");
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_tullkontor");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			if(StringUtils.isEmpty(tullkontorName) && StringUtils.isEmpty(tullkontorCode)) {
				tullkontorCode = "NO"; //to avoid big list on all the planet ...
			}	
			List list = this.getTullkontorList(appUser, tullkontorName, tullkontorCode, tullkontorType);
			model.put("tullkontorList", list);
			
			model.put("callerType", callerType);
			model.put("tkkode", tullkontorCode);
			model.put("tktxtn", tullkontorName);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_tur.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitTur(@ModelAttribute ("record") SadTurRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTur");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String tudt = recordToValidate.getTudt();
		String tuavd = recordToValidate.getTuavd();
		String tupro = recordToValidate.getTupro();
		logger.info("caller:" + callerType);
		logger.info("tudt (fromDate):" + tudt);
		logger.info("tuavd:" + tuavd);
		logger.info("tupro:" + tupro);
		
		if(StringUtils.isEmpty(tudt)) {
			if(StringUtils.isEmpty(tuavd) && StringUtils.isEmpty(tupro)) {
				tudt = dateMgr.getNewDateFromNow(DateTimeManager.ISO_FORMAT, this.sadiSearchNrOfDaysBackwards);
				recordToValidate.setTudt(tudt);
			}
		}else {
			if(!dateValidator.validateDate(tudt, DateValidator.DATE_MASK_ISO)) {
				tudt = dateMgr.getNewDateFromNow(DateTimeManager.ISO_FORMAT, this.sadiSearchNrOfDaysBackwards);	
				recordToValidate.setTudt(tudt);
			}
		}
		
		model.put("tudt", tudt);
		//antingen eller och inte båda 2...Turen overrides avd if it exists
		if(StringUtils.isNotEmpty(tupro)) {
			model.put("tupro", tupro);
		}else {
			model.put("tuavd", tuavd);
		}
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_tur");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getTurList(appUser, recordToValidate);
			model.put("turList", list);
			model.put("callerType", callerType);
			//model.put("tkkode", tullkontorCode);
			//model.put("tktxtn", tullkontorName);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_master.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitExternalMaster(@ModelAttribute ("record") ZadmomlfRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalMaster");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String date = request.getParameter("date");
		String emdkm = request.getParameter("emdkm");
		//this is one is optional and exists only when the child window is opened from the transport-parent-window
		String etlnrt = request.getParameter("etlnrt");

		logger.info("caller:" + callerType);
		//logger.info("tuavd:" + tuavd);
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_master");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getExternalMasterList(appUser, recordToValidate);
			model.put("mainList", list);
			model.put("callerType", callerType);
			model.put("date", date);
			model.put("emdkm", emdkm);
			model.put("etlnrt", etlnrt);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_master_attachments.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitExternalMasterAttachments(@ModelAttribute ("record") ZadmoattfRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalMasterAttachments");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String date = request.getParameter("date");
		String docref = request.getParameter("docref");
		//this is one is optional and exists only when the child window is opened from the transport-parent-window
		//String etlnrt = request.getParameter("etlnrt");

		logger.info("caller:" + callerType);
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_master_attachments");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getExternalMasterAttachmentsList(appUser, recordToValidate);
			model.put("mainList", list);
			model.put("callerType", callerType);
			model.put("date", date);
			model.put("docref", docref);
			//model.put("etlnrt", etlnrt);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	/**
	 * 
	 * @param houseRecord
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_goodsitem.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doGoodsItem(@ModelAttribute ("record") SadmohfRecord houseRecord, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doGoodsItem");
		Map model = new HashMap();
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_goodsitem");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//get all masters
			this.getItemLines(appUser, houseRecord);  
			model.put("record", houseRecord);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_transports.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShowTransport(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doShowTransport");
		Map model = new HashMap();
		
		String id = request.getParameter("id");
		String[] idRec = id.split("_"); 
		Integer emlnrt = Integer.valueOf(idRec[0].replace("emlnrt", ""));
		Integer emlnrm = Integer.valueOf(idRec[1].replace("emlnrm", ""));
		String etktyp = idRec[2].replace("etktyp", "");
		logger.info("emlnrt:" + emlnrt + " emlnrm:" + emlnrm + " etktyp:" + etktyp);
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transports");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//get all masters
			List list = this.getTransports(appUser, emlnrt, emlnrm, etktyp);  
			model.put("list", list);
			model.put("fromEmlnrt", emlnrt);
			model.put("fromEmlnrm", emlnrm);
			model.put("fromEtktyp", etktyp);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_transports_consolidated.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShowTransportsConsolidated(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doShowTransportsConsolidated");
		Map model = new HashMap();
		
		String id = request.getParameter("etlnrt");
		String idpro = request.getParameter("etpro");
		Integer etlnrt = Integer.valueOf(id.replace("etlnrt", ""));
		Integer etpro = Integer.valueOf(idpro.replace("etpro", ""));
		logger.info("etlnrt:" + etlnrt);
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_transports_consolidated");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//get all masters
			List list = this.getTransportsConsolidated(appUser, etlnrt); 
			if(list!=null) {
				logger.info("list size to show:" + list.size());
			}
			model.put("list", list);
			model.put("lnrt", etlnrt.toString());
			model.put("tur", etpro.toString());
			logger.info(etlnrt.toString());
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_oppdrag.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShowOppdrag(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doShowOppdrag");
		Map model = new HashMap();
		
		String tur = request.getParameter("tur");
		String lnrt = request.getParameter("lnrt");
		String lnrm = request.getParameter("lnrm");
		String sitle = request.getParameter("sitle");
		
		
		
		model.put("tur", tur);
		model.put("lnrt", lnrt);
		model.put("lnrm", lnrm);
		model.put("sitle", sitle);
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_oppdrag");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//get all masters
			List list = this.getOppdrag(appUser, tur, lnrt, lnrm, sitle);  
			model.put("list", list);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_oppdragJson.do",  method={RequestMethod.GET} )
	public ModelAndView doShowOppdragJson(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doOpdJson");
		Map model = new HashMap();
		String tur = request.getParameter("tur");
		String lnrt = request.getParameter("lnrt");
		String lnrm = request.getParameter("lnrm");
		String opd = request.getParameter("opd");
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_loginfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			model.put("content", this.getOppdragRawJson(appUser, tur, lnrt, lnrm, opd));
	    	
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_sadi.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShowSadi(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doShowSadi");
		Map model = new HashMap();
		String tur = request.getParameter("tur");
		String bilnr = request.getParameter("bil");
		String dato = request.getParameter("dato");
		String sitle = request.getParameter("sitle");
		String lnrt = request.getParameter("lnrt");
		String lnrm = request.getParameter("lnrm");
		if(StringUtils.isEmpty(dato)) {
			dato = dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, this.sadiSearchNrOfDaysBackwards);
		}else {
			if(!dateValidator.validateDate(dato, DateValidator.DATE_MASK_NO)) {
				dato = dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, this.sadiSearchNrOfDaysBackwards);	
			}
		}
		model.put("tur", tur);
		model.put("bil", bilnr);
		model.put("dato", dato);
		model.put("sitle", sitle);
		model.put("lnrt", lnrt);
		model.put("lnrm", lnrm);
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_sadi");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//validate and convert to ISO
			String datoISO = getDatoISOSadi(dato);
			//get all masters
			List list = this.getSadi(appUser, bilnr, datoISO, lnrt, lnrm, sitle);  
			model.put("list", list);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	private String getDatoISOSadi(String dato) {
		String datoISO = "";
		//STEP 1
		if(StringUtils.isEmpty(dato)) {
			dato = dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, this.sadiSearchNrOfDaysBackwards);
		}else {
			if(!dateValidator.validateDate(dato, DateValidator.DATE_MASK_NO)) {
				dato = dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, this.sadiSearchNrOfDaysBackwards);	
			}
		}
		//now convert to ISO
		datoISO = dateMgr.getDateFormatted_ISO(String.valueOf(dato), DateTimeManager.NO_FORMAT);
		
		//STEP 2
		//validate ISO otherwise create own
		if(!dateValidator.validateDateIso203_YYYYMMDD(datoISO)) {
			datoISO = dateMgr.getNewDateFromNow(this.sadiSearchNrOfDaysBackwards);
		}
		
		return datoISO;
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_tolltariff.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitTolltariff(@ModelAttribute ("record") JsonTvinnSadTolltariffVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTolltariff");
		Map model = new HashMap();
		String varuKod = request.getParameter("vkod");
		String text = request.getParameter("tekst");
		String ieMode = "I";
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_tolltariff");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList();
			if(text!=null && !"".equals(text)){
				list = this.getTulltaxaListFromDesc(appUser, text, ieMode);
				model.put("tekst", text);
			}else{
				list = this.getTolltariffList(appUser, varuKod, ieMode);
				model.put("vkod", varuKod);
			}
			model.put("tolltariffList", list);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * setup file for external houses (främmande houser) sadmocf
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_houses.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitExternalHouses(@ModelAttribute ("record") SadmocfRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalHouses");
		Map model = new HashMap();
		String emlnrt = request.getParameter("emlnrt");
		String emlnrm = request.getParameter("emlnrm");
		String emdkm = request.getParameter("emdkm");
		String ctype = request.getParameter("ctype");
		//checks if this UCase = UseCase - Send House back to the carrier (ombud/representative)
		if(StringUtils.isNotEmpty(ctype) && ctype.contains("Ombud")) {
			String ehlnrt = request.getParameter("ehlnrt");
			String ehlnrm = request.getParameter("ehlnrm");
			String ehlnrh = request.getParameter("ehlnrh");
			String ehdkh = request.getParameter("ehdkh");
			model.put("ehlnrt", ehlnrt);
			model.put("ehlnrm", ehlnrm);
			model.put("ehlnrh", ehlnrh);
			model.put("ehdkh", ehdkh);
		}
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_houses");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getSadmocfList(appUser, recordToValidate.getOrgnr());
			
			model.put("sadmocfList", list);
			model.put("emlnrt", emlnrt);
			model.put("emlnrm", emlnrm);
			model.put("emdkm", emdkm);
			model.put("ctype", ctype);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_houses_log.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitExternalHousesLog(@ModelAttribute ("record") SadmolffRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalHousesLog");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_houses_log");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getSadmolffList(appUser, recordToValidate);
			model.put("id", recordToValidate.getEmdkm());
			model.put("date", recordToValidate.getDate());
			model.put("sadmolffList", list);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_houses_bupfiles_log.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitExternalHousesBupfilesLog(@ModelAttribute ("record") SadmobuplgRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalHousesBupfilesLog");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_houses_bupfiles_log");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//security measure
			if(StringUtils.isEmpty(recordToValidate.getDate())){
				recordToValidate.setDate(dateMgr.getCurrentDate_ISO());
			}
			List list = this.getSadmobuplgList(appUser, recordToValidate);
			model.put("date", recordToValidate.getDate());
			model.put("list", list);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_external_houses_backto_ombud_log.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitExternalHousesBackToOmbudLog(@ModelAttribute ("record") SadmolhffRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalHousesBackToOmbudLog");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_external_houses_backto_ombud_log");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getSadmolhffList(appUser, recordToValidate.getEhdkh());
			model.put("sadmolhffList", list);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_externalhouse_attachments.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitExternalHouseAttachments(@ModelAttribute ("record") ZadmoattfRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitExternalHouseAttachments");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String date = request.getParameter("date");
		String docref = request.getParameter("docref");
		//this is one is optional and exists only when the child window is opened from the transport-parent-window
		//String etlnrt = request.getParameter("etlnrt");

		logger.info("caller:" + callerType);
		//logger.info("tuavd:" + tuavd);
		//logger.info("tupro:" + tupro);
		//
		
		//antingen eller och inte båda 2...Turen overrides avd if it exists
		/*if(StringUtils.isNotEmpty(tupro)) {
			model.put("tupro", tupro);
		}else {
			model.put("tuavd", tuavd);
		}*/
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_externalhouse_attachments");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getExternalHouseAttachmentsList(appUser, recordToValidate);
			model.put("mainList", list);
			model.put("callerType", callerType);
			model.put("date", date);
			model.put("docref", docref);
			//model.put("etlnrt", etlnrt);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}

	
	
	
	/**
	 * 
	 * @param appUser
	 * @param description
	 * @param ieMode
	 * @return
	 */
	private List<JsonTvinnSadTolltariffVarukodRecord> getTulltaxaListFromDesc(SystemaWebUser appUser, String description, String ieMode){
		List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList<JsonTvinnSadTolltariffVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_FROM_DESC_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&sok=" + description);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonTvinnSadTolltariffVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTvinnSadTolltariffVarukodRecord  record : container.getTariclist()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param appUser
	 * @param orgnr
	 * @return
	 */
	private List<SadmocfRecord> getSadmocfList(SystemaWebUser appUser, String orgnr){
		List<SadmocfRecord> list = new ArrayList<SadmocfRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&orgnr=" + orgnr);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		SadmocfContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadmocfListService.getListContainer(jsonPayload);
				if(container!=null){
					for(SadmocfRecord  record : container.getList()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private List<SadmolffRecord> getSadmolffList(SystemaWebUser appUser, SadmolffRecord recordToValidate){
		List<SadmolffRecord> list = new ArrayList<SadmolffRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_FTP_LOG_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(StringUtils.isNotEmpty(recordToValidate.getEmdkm())) {
			urlRequestParams.append("&emdkm=" + recordToValidate.getEmdkm());
		}
		if(StringUtils.isNotEmpty(recordToValidate.getDate())) {
			urlRequestParams.append("&date=" + recordToValidate.getDate());
		}
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		SadmolffContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadmolffListService.getListContainer(jsonPayload);
				if(container!=null){
					for(SadmolffRecord  record : container.getList()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Bup files log ...
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private List<SadmobuplgRecord> getSadmobuplgList(SystemaWebUser appUser, SadmobuplgRecord recordToValidate){
		List<SadmobuplgRecord> list = new ArrayList<SadmobuplgRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_BUPFILES_LOG_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(StringUtils.isNotEmpty(recordToValidate.getFile())) {
			urlRequestParams.append("&file=" + recordToValidate.getFile());
		}
		if(StringUtils.isNotEmpty(recordToValidate.getDate())) {
			urlRequestParams.append("&date=" + recordToValidate.getDate());
		}
		if(StringUtils.isNotEmpty(recordToValidate.getDate())) {
			urlRequestParams.append("&time=" + recordToValidate.getTime());
		}
		if(StringUtils.isNotEmpty(recordToValidate.getMsgid())) {
			urlRequestParams.append("&msgid=" + recordToValidate.getMsgid());
		}
		if(StringUtils.isNotEmpty(recordToValidate.getPeppolid())) {
			urlRequestParams.append("&peppolid=" + recordToValidate.getPeppolid());
		}
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		SadmobuplgContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadmobuplgListService.getListContainer(jsonPayload);
				if(container!=null){
					for(SadmobuplgRecord  record : container.getList()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param ehdkh
	 * @return
	 */
	private List<SadmolhffRecord> getSadmolhffList(SystemaWebUser appUser, String ehdkh){
		List<SadmolhffRecord> list = new ArrayList<SadmolhffRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_BACKTO_OMBUD_FTP_LOG_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ehdkh=" + ehdkh);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		SadmolhffContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadmolhffListService.getListContainer(jsonPayload);
				if(container!=null){
					for(SadmolhffRecord  record : container.getList()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	private List<ZadmoattfRecord> getExternalHouseAttachmentsList(SystemaWebUser appUser, ZadmoattfRecord recordToValidate){
		  List<ZadmoattfRecord> result = new ArrayList<ZadmoattfRecord>();
		
		  logger.info("Inside getExternalMasterList");
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSE_ATTACHMENTS_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUser.getUser());
		  urlRequestParamsKeys.append("&date=" + recordToValidate.getDate());
		  if(StringUtils.isNotEmpty(recordToValidate.getDocref())) {
			  urlRequestParamsKeys.append("&docref=" + recordToValidate.getDocref());
		  }
		  
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){ 
	    		ZadmoattfContainer container = this.zadmoattfListService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
	    				result = (List)container.getList();
	    				
	    			}
	    		}
	    	}
		  return result;
	}
	
	
	private List<JsonTvinnSadTolltariffVarukodRecord> getTolltariffList(SystemaWebUser appUser, String tolltariffVarekod, String ieMode){
		List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList<JsonTvinnSadTolltariffVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&kod=" + tolltariffVarekod);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		JsonTvinnSadTolltariffVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTvinnSadTolltariffVarukodRecord  record : container.getTariclist()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param appUser
	 * @param tur
	 * @param lnrt
	 * @param lnrm
	 * @param sitle
	 * @return
	 */
	private List<SadOppdragRecord> getOppdrag(SystemaWebUser appUser, String tur, String lnrt, String lnrm, String sitle) {
		List<SadOppdragRecord> resultList = new ArrayList();
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer("user=" + appUser.getUser() + "&tur=" + tur);
		if(StringUtils.isNotEmpty(sitle)) {
			urlRequestParams.append("&sitle=" + sitle);
		}
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && !container.getOrderList().isEmpty()) {
    			for(SadOppdragRecord record: container.getOrderList()) {
    				if(StringUtils.isNotEmpty(record.getSidt())) {
						if (record.getSidt().length()==8) {
							record.setSidt(this.dateMgr.getDateFormatted_NO(record.getSidt(), DateTimeManager.ISO_FORMAT));
						}
					}
    				//Only those that have not been chosen in this same master
    				if(!this.houseExists(appUser, record.getSitdn(), lnrt , lnrm )) {
    					resultList.add(record);
    				}
    			}
    		}
    		
    	}
    	
    	return resultList;
	}
	private String getOppdragRawJson(SystemaWebUser appUser, String tur, String lnrt, String lnrm, String opd) {
		String result = "...";
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&tur=" + tur + "&opd=" + opd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		result = jsonPayload;
    	}
    	
    	return result;
	}
	/**
	 * 
	 * @param appUser
	 * @param bilnr
	 * @param dato
	 * @param lnrt
	 * @param lnrm
	 * @return
	 */
	private List<SadOppdragRecord> getSadi(SystemaWebUser appUser, String bilnr, String dato, String lnrt, String lnrm, String sitle) {
		List<SadOppdragRecord> resultList = new ArrayList();
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_SADI_URL; //SADI_URL
		//add URL-parameters
		StringBuilder urlRequestParams = new StringBuilder( "user=" + appUser.getUser());
		if(StringUtils.isNotEmpty(bilnr)) {
			urlRequestParams.append("&bil=" + bilnr);
		}
		if(StringUtils.isNotEmpty(dato)) {
			urlRequestParams.append("&dato=" + dato);
		}
		if(StringUtils.isNotEmpty(sitle)) {
			urlRequestParams.append("&sitle=" + sitle);
		}
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && container.getList() !=null) {
    			for(SadOppdragRecord record: container.getList()) {
    				if(StringUtils.isNotEmpty(record.getSidt())) {
						if (record.getSidt().length()==8) {
							record.setSidt(this.dateMgr.getDateFormatted_NO(record.getSidt(), DateTimeManager.ISO_FORMAT));
						}
					}
    				//Only those that have not been chosen in this same master
    				//TODO!!
    				/*if(!this.houseExists(appUser, record.getSitdn(), lnrt , lnrm )) {
    					resultList.add(record);
    				}*/
    				resultList.add(record);
    			}
    		}
    		
    	}
    	
    	return resultList;
	}
	/**
	 * 
	 * @param appUser
	 * @param etktyp
	 * @return
	 */
	private List<SadmotfRecord> getTransports(SystemaWebUser appUser, Integer emlnrt, Integer emlnrm, String etktyp) {
		
		List<SadmotfRecord> result = new ArrayList<SadmotfRecord>();
		int DAYS_BACK_FROM_NOW = -10;
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add from date in order to limit the list
		String fromRegDate = this.dateMgr.getSpecificDayFrom_CurrentDate_ISO(DAYS_BACK_FROM_NOW);
		String urlRequestParams = "user=" + appUser.getUser() + "&etdtr=" + fromRegDate;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	//logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			for(SadmotfRecord record : outputList) {
				//get masters to know in the GUI ... (only transports without masters are allow to change to)
				this.getMasters(appUser, record);
				
				//check if it is a transport with the same api (road or air)
				if(etktyp.startsWith(SadDigitollConstants.API_AIR_INDICATOR)) {
					if(record.getEtktyp().startsWith(SadDigitollConstants.API_AIR_INDICATOR)) {
						//Eliminate one-self
						if( record.getEtlnrt() != emlnrt) {
							result.add(record);
						}
					}
				}else {
					if(!record.getEtktyp().startsWith(SadDigitollConstants.API_AIR_INDICATOR)) {
						//Eliminate one-self
						if(record.getEtlnrt() != emlnrt) {
							result.add(record);
						}
					}
				}
				
			}
			
    	}
    	
    	return result;
	}
	
	private List<SadmotfRecord> getTransportsConsolidated(SystemaWebUser appUser, Integer etlnrt) {
		
		List<SadmotfRecord> result = new ArrayList<SadmotfRecord>();
		int DAYS_BACK_FROM_NOW = -10;
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add from date in order to limit the list
		//String fromRegDate = this.dateMgr.getSpecificDayFrom_CurrentDate_ISO(DAYS_BACK_FROM_NOW);
		String urlRequestParams = "user=" + appUser.getUser() + "&cb_C=Z";
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	//logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		List<SadmotfRecord> outputList = (List)jsonContainer.getList();
    		if(outputList!=null) {
    			logger.debug("list size - RAW:" + outputList.size());
    		}
			for(SadmotfRecord record : outputList) {
				//remove the parent transport himself, being used to consolidate houses
				if(!record.getEtlnrt().equals(etlnrt)) {
					logger.debug("AFTER-->record.getEtlnrt():" + record.getEtlnrt());
					logger.debug("AFTER-->etlnrt:" + etlnrt);
					result.add(record);
				}
			}
			
    	}
    	
    	return result;
	}

	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getMasters(SystemaWebUser appUser, SadmotfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&emlnrt=" + record.getEtlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	//logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		record.setListMasters(jsonContainer.getList());
    		
    	}
    	
	}
	/**
	 * 
	 * @param appUser
	 * @param tdn
	 * @param lnrt
	 * @param lnrm
	 * @return
	 */
	private boolean houseExists(SystemaWebUser appUser, String tdn, String lnrt, String lnrm ) {
		boolean retval = false;
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&ehlnrt=" + lnrt + "&ehlnrm=" + lnrm + "&ehtdn=" + tdn;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		//record.setListHouses(jsonContainer.getList());
    		if(jsonContainer!=null && !jsonContainer.getList().isEmpty()) {
    			retval = true;
    		}
    		
    	}
    	return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getItemLines(SystemaWebUser appUser, SadmohfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&eilnrt=" + record.getEhlnrt() + "&eilnrm=" + record.getEhlnrm() + "&eilnrh=" + record.getEhlnrh();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
    		record.setListItemLines(jsonContainer.getList());
    	}
    	
	}
	
	/**
	 * 
	 * @param appUser
	 * @param tullkontorName
	 * @param tullkontorCode
	 * @param tullkontorType
	 * @return
	 */
	private List<JsonTvinnSadTullkontorRecord> getTullkontorList(SystemaWebUser appUser, String tullkontorName, String tullkontorCode, String tullkontorType){
		  List<JsonTvinnSadTullkontorRecord> result = new ArrayList<JsonTvinnSadTullkontorRecord>();
		
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_UTFARTS_TULLKONTOR_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchUtfartsTullkontor(appUser.getUser(), tullkontorName, tullkontorCode, tullkontorType);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			  JsonTvinnSadTullkontorContainer container = this.tvinnSadTullkontorService.getTvinnSadTullkontorContainer(jsonPayload);
			  if(container!=null){
				  for(JsonTvinnSadTullkontorRecord  record : container.getCustomslist()){
					  //logger.info("Kontorsnamn via AJAX: " + record.getTktxtn() + " Code:" + record.getTkkode());
					  result.add(record);
				  }
			  }
		  }
		  return result;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param fromDate
	 * @param turNr
	 * @return
	 */
	private List<SadTurRecord> getTurList(SystemaWebUser appUser, SadTurRecord recordToValidate){
		  List<SadTurRecord> result = new ArrayList<SadTurRecord>();
		
		  logger.info("Inside getTurList");
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TUR_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUser.getUser());
		  urlRequestParamsKeys.append("&wtudt=" + recordToValidate.getTudt()); //fromDate);
		  if(StringUtils.isNotEmpty(recordToValidate.getTupro())) {
			  urlRequestParamsKeys.append("&wsstur=" + recordToValidate.getTupro());
		  }
		  if(StringUtils.isNotEmpty(recordToValidate.getTuavd())) {
			  urlRequestParamsKeys.append("&wssavd=" + recordToValidate.getTuavd());
		  }
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadTurContainer container = this.sadTurService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
	    				result = (List)container.getWrktriplist();
		    			/*for(SadTurRecord  record : container.getWrktriplist()){
		    				logger.info("Bilnr: " + record.getTubiln());
		    				logger.info("Tollsted(a): " + record.getTuto1a());
		    				logger.info("Fører: " + record.getTusjn1());
		    				//transport måte
		    				if(StringUtils.isNotEmpty(record.getTutrma())) { this.washTranspMate(record); }
		    				//eta (NO format)
		    				if(StringUtils.isNotEmpty(record.getTueta()) && record.getTueta().equals("0") && record.getTueta().length()==8 ){
		    					record.setTueta(this.dateMgr.getDateFormatted_NO(record.getTueta(), DateTimeManager.ISO_FORMAT));
		    				}
		    				//
		    				result.add(record);
		    			}*/
	    				
	    			}
	    		}
	    	}
		  return result;
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private List<ZadmomlfRecord> getExternalMasterList(SystemaWebUser appUser, ZadmomlfRecord recordToValidate){
		  List<ZadmomlfRecord> result = new ArrayList<ZadmomlfRecord>();
		
		  logger.info("Inside getExternalMasterList");
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_MASTER_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUser.getUser());
		  urlRequestParamsKeys.append("&date=" + recordToValidate.getDate());
		  if(StringUtils.isNotEmpty(recordToValidate.getEmdkm())) {
			  urlRequestParamsKeys.append("&emdkm=" + recordToValidate.getEmdkm());
		  }
		  
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){ 
	    		ZadmomlfContainer container = this.zadmomlfListService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
	    				result = (List)container.getList();
	    				
	    			}
	    		}
	    	}
		  return result;
	}
	
	private List<ZadmoattfRecord> getExternalMasterAttachmentsList(SystemaWebUser appUser, ZadmoattfRecord recordToValidate){
		  List<ZadmoattfRecord> result = new ArrayList<ZadmoattfRecord>();
		
		  logger.info("Inside getExternalMasterList");
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_MASTER_ATTACHMENTS_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUser.getUser());
		  urlRequestParamsKeys.append("&date=" + recordToValidate.getDate());
		  if(StringUtils.isNotEmpty(recordToValidate.getDocref())) {
			  urlRequestParamsKeys.append("&docref=" + recordToValidate.getDocref());
		  }
		  
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){ 
	    		ZadmoattfContainer container = this.zadmoattfListService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
	    				result = (List)container.getList();
	    				
	    			}
	    		}
	    	}
		  return result;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param soName
	 * @param code
	 * @param tullkontorType
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchUtfartsTullkontor(String applicationUser, String soName, String code, String tullkontorType){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(soName!=null && !"".equals(soName) && code!=null && !"".equals(code)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }else if (soName!=null && !"".equals(soName)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
		  }else if (code!=null && !"".equals(code)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }
		//append the type when applicable
		  if (tullkontorType!=null && !"".equals(tullkontorType)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST);
			  if("avg".equals(tullkontorType)){
				  sb.append("avg=J");
			  }else if ("ank".equals(tullkontorType)){
				  sb.append("ank=J");
			  }else if ("trs".equals(tullkontorType)){
				  sb.append("trs=J");
			  }
		  }
		  return sb.toString();
	  }
	
	private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private SadmobuplgListService sadmobuplgListService;
	
	@Autowired
	private SadmocfListService sadmocfListService;
	@Autowired
	private SadmolffListService sadmolffListService;
	@Autowired
	private SadmolhffListService sadmolhffListService;
	@Autowired
	private ZadmomlfListService zadmomlfListService;
	@Autowired
	private ZadmoattfListService zadmoattfListService;
	
	@Autowired
	private SadOppdragService sadOppdragService;
	@Autowired
	private TvinnSadTullkontorService tvinnSadTullkontorService;
	@Autowired
	private SadTurService sadTurService;
	@Autowired
	private TvinnSadCustomerService tvinnSadCustomerService;
	@Autowired
	private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	@Autowired
	private HouseDocLogControllerService houseDocLogControllerService;
}

