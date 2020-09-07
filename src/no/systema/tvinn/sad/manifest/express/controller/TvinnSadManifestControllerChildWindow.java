package no.systema.tvinn.sad.manifest.express.controller;

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
import no.systema.tvinn.sad.sadimport.service.SadImportGeneralCodesChildWindowService;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodttsService;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;




/**
 * Manifest Express Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TvinnSadManifestControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TvinnSadManifestControllerChildWindow.class.getName());
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
	@RequestMapping(value="tvinnsadmanifest_childwindow_tollstedcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitTollstedCodes(@ModelAttribute ("record") JsonTvinnSadCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTollstedCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		String ktspnr = request.getParameter("ktspnr");
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_tollstedcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			List list00= this.getTollstedList(appUser.getUser(), ktspnr);
			model.put("tollstedCodeList", list00);
			
			List list = this.getCodeList(appUser, typeCode);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param ktspnr
	 * @return
	 */
	private List<JsonMaintSadImportKodttsRecord> getTollstedList(String applicationUser, String ktspnr){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT04R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		//Either id or alfa but not both...
		if(ktspnr!=null && !"".equals(ktspnr)){
			urlRequestParams.append("&ktspnr=" + ktspnr);
			urlRequestParams.append("&sh=y");			
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportKodttsRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodttsContainer container = this.maintSadImportKodttsService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodttsRecord record : list){
	        		//logger.info("KTSPNR:" + record.getKtspnr());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * 	1=Ekspedisjonstyper(import)
	 * 	2=Landkoder                     
	 *	3=Transaksjonstyper     
	 * 	4=Transportmåter               
	 *	5=Tollnedsettelser 
	 *	6=Preferanser                 
	 *	7=V.F. koder                 
	 *	8=Avgiftkoder 
	 *  8B=Avgiftkoder sekv.                     
	 *	9=Ekspedisjonstyper(eksport)              
	
	 *	A=Enhetskoder                          
	 *	B=Dok./Sertifikat kode (TVINN-import)  
	 *	C=Dok./sertifikat kode (TVINN-eksport) 
	 *	D=lagringssted                         
	 *	E=fylkeskoder                          
	 *	O=Typetilfelle (omberegning)
	 *	L=Incoterms
	 *	V=Valutakoder
	 * 
	 *  @param appUser
	 *  @param codeType
	 *  @return
	 * 
	 */
	
	private List<JsonTvinnSadCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonTvinnSadCodeRecord> list = new ArrayList<JsonTvinnSadCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
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
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Autowired
	private SadImportGeneralCodesChildWindowService sadImportGeneralCodesChildWindowService;
	public void setSadImportGeneralCodesChildWindowService(SadImportGeneralCodesChildWindowService value){this.sadImportGeneralCodesChildWindowService = value;}
	public SadImportGeneralCodesChildWindowService getSadImportGeneralCodesChildWindowService(){ return this.sadImportGeneralCodesChildWindowService; }
	
	@Autowired
	private MaintSadImportKodttsService maintSadImportKodttsService;
	public void setMaintSadImportKodttsService (MaintSadImportKodttsService value){ this.maintSadImportKodttsService = value; }
	public MaintSadImportKodttsService getMaintSadImportKodttsService(){ return this.maintSadImportKodttsService; }
	
	
}

