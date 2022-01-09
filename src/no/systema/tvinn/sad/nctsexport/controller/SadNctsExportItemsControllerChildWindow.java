package no.systema.tvinn.sad.nctsexport.controller;

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

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListRecord;
import no.systema.tvinn.sad.sadexport.service.SadExportTopicListService;
import no.systema.tvinn.sad.sadexport.filter.SearchFilterSadExportTopicList;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.sadexport.service.SadExportGeneralCodesChildWindowService;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

/**
 * SAD NCTS Export Item Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Feb 22, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadNctsExportItemsControllerChildWindow {
	
	private static final Logger logger = LoggerFactory.getLogger(SadNctsExportItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	//customer
	private final String DATATABLE_LIST = "list";
	private final String GENERAL_CODE_2_COUNTRY = "2";
	private final String GENERAL_CODE_V_CURRENCY = "V";
	private final String GENERAL_CODE_4_CARRIER_TYPE = "4";
	
	
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
	 * @param searchFilter
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_edit_items_childwindow_oppdragslist_gettoitemlines.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doFindAngivelseListToImportToItemlines(@ModelAttribute ("record") SearchFilterSadExportTopicList searchFilter, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindAngivelseListToImportToItemlines");
		Map model = new HashMap();
		String avdNcts = request.getParameter("avdNcts");
		String opdNcts = request.getParameter("opdNcts");
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit_items_childwindow_oppdragslist_gettoitemlines");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			logger.info(searchFilter.getDatum());
			List<JsonSadExportTopicListRecord> list = (List)this.getArendeList(appUser, searchFilter);
			model.put("angivelseList", list);
			model.put("avdNcts", avdNcts);
			model.put("opdNcts", opdNcts);
			
			successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER , searchFilter);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param searchFilter
	 * @return
	 */
	private Collection<JsonSadExportTopicListRecord> getArendeList(SystemaWebUser appUser, SearchFilterSadExportTopicList searchFilter){
		Collection<JsonSadExportTopicListRecord> list = new ArrayList<JsonSadExportTopicListRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//get BASE URL
		final String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadExportTopicListContainer jsonSadExportTopicListContainer = this.sadExportTopicListService.getSadExportTopicListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			list = jsonSadExportTopicListContainer.getOrderList();	
			//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
			if(!"D".equals(searchFilter.getStatus())){
				//To avoid the ...ConcurrentModificationException we take a copy of the existing list and iterate over new copy
				for (JsonSadExportTopicListRecord record : new ArrayList<JsonSadExportTopicListRecord>(list)){
					if("D".equals(record.getStatus())){
						//list.remove(record);
					}
				}
			}
    	}
		return list;
	}
	
	
	/**
	 * 
	 * @param searchFilter
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_edit_items_childwindow_oppdragslist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doFindOppdragsList(@ModelAttribute ("record") SearchFilterSadExportTopicList searchFilter, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindOppdragsList");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit_items_childwindow_oppdragslist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			List<JsonSadExportTopicListRecord> list = (List<JsonSadExportTopicListRecord>)this.getOppdragsList(appUser, searchFilter);
			model.put("oppdragsList", list);
			successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER , searchFilter);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param searchFilter
	 * @return
	 */
	private Collection<JsonSadExportTopicListRecord> getOppdragsList(SystemaWebUser appUser, SearchFilterSadExportTopicList searchFilter){
		Collection<JsonSadExportTopicListRecord> list = new ArrayList<JsonSadExportTopicListRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//get BASE URL
		final String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadExportTopicListContainer jsonSadExportTopicListContainer = this.sadExportTopicListService.getSadExportTopicListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			list = jsonSadExportTopicListContainer.getOrderList();	
			//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
			if(!"D".equals(searchFilter.getStatus())){
				//To avoid the ...ConcurrentModificationException we take a copy of the existing list and iterate over new copy
				for (JsonSadExportTopicListRecord record : new ArrayList<JsonSadExportTopicListRecord>(list)){
					if("D".equals(record.getStatus())){
						list.remove(record);
					}
				}
			}
    	}
		return list;
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_edit_items_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTvinnSadCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit_items_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeList(appUser, typeCode);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
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
	@RequestMapping(value="tvinnsadnctsexport_edit_items_childwindow_tolltariff.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitTolltariff(@ModelAttribute ("record") JsonTvinnSadTolltariffVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTolltariff");
		Map model = new HashMap();
		String varuKod = request.getParameter("vkod");
		String text = request.getParameter("tekst");
		String ieMode = "E";
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit_items_childwindow_tolltariff");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList();
			if(text!=null && !"".equals(text)){
				//TODO (CB) list = this.getTulltaxaListFromDesc(appUser, text, ieMode);
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
	012= KOD_SPRAK
	013= KOD_DOK
	014= KOD_TIDIGARE_DOK
	017= KOD_KOLLI_TYP
	031= KOD_DEKL_TYP
	039= KOD_TILLAGSUPP
	047= KOD_KONTROLLRESULTAT
	064= KOD_KANSLIGVARA
	096= KOD_SPEC_OMST
	105= KOD_TILLGANGASKOD_GARANTI
	106= KOD_TULLKONTOR_REF (OBSOLETE! TNOG004R is used instead)
	116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
	302= KOD_STATUS_KODER_NCTS_EXPORT
	
	BORROWED FROM SAD EXPORT/IMPORT
	2= Country code
	V= Currency code
	4 = KOD_TRANSPORTMÅTE

	 *  @param appUser
	 *  @param codeType
	 *  @return
	 * 
	 */
	
	private List<JsonTvinnSadCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonTvinnSadCodeRecord> list = new ArrayList<JsonTvinnSadCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_NCTS_CODES_URL;
		//Exception for CODE_URL (MUST be borrowed from SAD EKS/IMP
		if(this.GENERAL_CODE_2_COUNTRY.equalsIgnoreCase(typeCode) || this.GENERAL_CODE_V_CURRENCY.equalsIgnoreCase(typeCode) || 
		   this.GENERAL_CODE_4_CARRIER_TYPE.equalsIgnoreCase(typeCode) ){
		   BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
		}
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTvinnSadCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadExportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonTvinnSadCodeRecord  record : container.getKodlista()){
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
	 * @param tolltariffVarekod
	 * @param ieMode
	 * @return
	 */
	private List<JsonTvinnSadTolltariffVarukodRecord> getTolltariffList(SystemaWebUser appUser, String tolltariffVarekod, String ieMode){
		List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList<JsonTvinnSadTolltariffVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&kod=" + tolltariffVarekod);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info(jsonPayload);
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
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSadExportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getXref()!=null && !"".equals(searchFilter.getXref())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "xref=" + searchFilter.getXref());
		}
		if(searchFilter.getSg()!=null && !"".equals(searchFilter.getSg())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sg=" + searchFilter.getSg());
		}
		if(searchFilter.getSetll()!=null && !"".equals(searchFilter.getSetll())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lopenr=" + searchFilter.getSetll());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
		}
		if(searchFilter.getDatumt()!=null && !"".equals(searchFilter.getDatumt())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datumt=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatumt()));
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getAart()!=null && !"".equals(searchFilter.getAart())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "aart=" + searchFilter.getAart());
		}
		if(searchFilter.getInnstikk()!=null && !"".equals(searchFilter.getInnstikk())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "innstikk=" + searchFilter.getInnstikk());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private SadExportGeneralCodesChildWindowService sadExportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSadExportGeneralCodesChildWindowService(SadExportGeneralCodesChildWindowService value){this.sadExportGeneralCodesChildWindowService = value;}
	public SadExportGeneralCodesChildWindowService getSadExportGeneralCodesChildWindowService(){ return this.sadExportGeneralCodesChildWindowService; }
	
	
	@Qualifier 
	private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	@Autowired
	@Required	
	public void setTvinnSadTolltariffVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTolltariffVarukodService = value;}
	public TvinnSadTolltariffVarukodService getTvinnSadTolltariffVarukodService(){ return this.tvinnSadTolltariffVarukodService; }
	
	
	@Qualifier ("sadExportTopicListService")
	private SadExportTopicListService sadExportTopicListService;
	@Autowired
	@Required
	public void setSadExportTopicListService (SadExportTopicListService value){ this.sadExportTopicListService = value; }
	public SadExportTopicListService getSadExportTopicListService(){ return this.sadExportTopicListService; }
	
	
}

