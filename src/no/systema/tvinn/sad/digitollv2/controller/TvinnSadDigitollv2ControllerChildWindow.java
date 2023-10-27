package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

import org.apache.http.util.TextUtils;
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

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
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.context.TdsAppContext;
import no.systema.main.model.SystemaWebUser;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.service.TvinnSadTullkontorService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadOppdragService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
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
	DateTimeManager dateMgr = new DateTimeManager();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
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
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_routinginfo.do",  method={RequestMethod.GET} )
	public ModelAndView doRoutingInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doRoutingInfo");
		Map model = new HashMap();
		String level = request.getParameter("level");
		
		
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
					url.append("TODO.do");
				}else if (level.equals("h")) {
					url.append("getRoutingHouseConsignment.do");
				}
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
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
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_manifestinfo");
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
    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		
    		model.put("content", jsonPayload);

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
		//
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
	@RequestMapping(value="tvinnsaddigitollv2_childwindow_oppdrag.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doShowOppdrag(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doShowOppdrag");
		Map model = new HashMap();
		
		String tur = request.getParameter("tur");
		String lnrt = request.getParameter("lnrt");
		String lnrm = request.getParameter("lnrm");
		model.put("tur", tur);
		model.put("lnrt", lnrt);
		model.put("lnrm", lnrm);
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_childwindow_oppdrag");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//get all masters
			List list = this.getOppdrag(appUser, tur, lnrt, lnrm);  
			model.put("list", list);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
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
	 * @return
	 */
	private List<SadOppdragRecord> getOppdrag(SystemaWebUser appUser, String tur, String lnrt, String lnrm) {
		List<SadOppdragRecord> resultList = new ArrayList();
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&tur=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

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
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_UTFARTS_TULLKONTOR_URL;
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

		  //logger.debug(jsonPayload);
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
	private SadOppdragService sadOppdragService;
	@Autowired
	private TvinnSadTullkontorService tvinnSadTullkontorService;
	@Autowired
	private SadTurService sadTurService;
	
	@Autowired
	private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	
}

