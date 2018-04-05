package no.systema.tvinn.sad.nctsimport.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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


import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorRecord;

import no.systema.tvinn.sad.sadimport.service.SadImportGeneralCodesChildWindowService;
import no.systema.tvinn.sad.service.TvinnSadTullkontorService;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;

/**
 * 
 * SAD NCTS Import Header Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Feb 26, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadNctsImportHeaderControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SadNctsImportHeaderControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
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
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_edit_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTvinnSadCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_edit_childwindow_generalcodes");
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
	@RequestMapping(value="tvinnsadnctsimport_edit_childwindow_tullkontor.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitTullkontor(@ModelAttribute ("record") JsonTvinnSadTullkontorRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTullkontor");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String tullkontorCode = request.getParameter("tkkode");
		String tullkontorName = request.getParameter("tktxtn");
		String tullkontorType = request.getParameter("tktype");
		
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_edit_childwindow_tullkontor");
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
	106= KOD_TULLKONTOR_REF
	116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
	302= KOD_STATUS_KODER_NCTS_EXPORT
	
	BORROWED FROM SAD EXPORT/IMPORT
	2= Country code
	V= Currency code
	4 = KOD_TRANSPORTMÅTE

	 * 
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
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTvinnSadCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadImportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
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
	 * @param tullkontorName
	 * @param tullkontorCode
	 * @param kontorType
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
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private SadImportGeneralCodesChildWindowService sadImportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSadImportGeneralCodesChildWindowService(SadImportGeneralCodesChildWindowService value){this.sadImportGeneralCodesChildWindowService = value;}
	public SadImportGeneralCodesChildWindowService getSadImportGeneralCodesChildWindowService(){ return this.sadImportGeneralCodesChildWindowService; }
	
	
	@Qualifier ("tvinnSadTullkontorService")
	private TvinnSadTullkontorService tvinnSadTullkontorService;
	@Autowired
	@Required
	public void setTvinnSadTullkontorService (TvinnSadTullkontorService value){ this.tvinnSadTullkontorService = value; }
	public TvinnSadTullkontorService getTvinnSadTullkontorService(){ return this.tvinnSadTullkontorService; }
	
	
}

