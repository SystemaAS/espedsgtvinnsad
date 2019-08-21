package no.systema.tvinn.sad.sadimport.controller;

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
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainernrContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainernrRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportGeneralCodesChildWindowService;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrRecord;
import no.systema.tvinn.sad.sadexport.util.manager.SadExportItemsContainernrMgr;
import no.systema.tvinn.sad.sadexport.validator.SadExportItemsContainernrValidator;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.util.manager.SadImportItemsContainernrMgr;
import no.systema.tvinn.sad.sadimport.validator.SadImportItemsContainernrValidator;
import no.systema.tvinn.sad.util.TvinnSadConstants;




/**
 * Sad Import Items Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Jan 19, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportItemsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SadImportItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";

	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private StringManager strMgr = new StringManager();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_items_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTvinnSadCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_items_childwindow_generalcodes");
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
	
	@RequestMapping(value="tvinnsadimport_edit_items_childwindow_tolltariff.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitTolltariff(@ModelAttribute ("record") JsonTvinnSadTolltariffVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTolltariff");
		Map model = new HashMap();
		String varuKod = request.getParameter("vkod");
		String text = request.getParameter("tekst");
		String ieMode = "I";
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_items_childwindow_tolltariff");
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
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_items_childwindow_kundensvarereg.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitKundVareReg(@ModelAttribute ("record") JsonSadImportTolltariffKundensRegisterVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitKundVareReg");
		Map model = new HashMap();
		String receiverId = request.getParameter("recId");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_items_childwindow_kundensvarereg");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			String vkod = request.getParameter("vkod");  
			List<JsonSadImportTolltariffKundensRegisterVarukodRecord> list = new ArrayList<JsonSadImportTolltariffKundensRegisterVarukodRecord>();
			if("POST".equals(request.getMethod())){
				//Otherwise could be millions of records. NOTE: wildcard on vkod should be implemented on the service side
				//COVI TODO ta bort efter demoif( (vkod!=null && !"".equals(vkod)) && (receiverId!=null && !"".equals(receiverId))){
					list = this.getKundVareRegList(appUser, receiverId, vkod);
				//}
			}
			model.put("kundensVareRegList", list);
			model.put("vkod", vkod);
			model.put("recId", receiverId);
			
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
	@RequestMapping(value="tvinnsadimport_edit_items_childwindow_containernr.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitContainernr(@ModelAttribute ("record") JsonSadImportSpecificTopicItemContainernrContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitContainernr");
		Map model = new HashMap();
		model.put("avd", recordToValidate.getAvd());
		model.put("opd", recordToValidate.getOpd());
		//default
		String lineNr = recordToValidate.getLin();
		model.put("lin", lineNr);
		
		if(strMgr.isNull(lineNr) ){
			//special case since the item line will be created after the container nr. has been created. 
			//meaning that this number will create an orphan line nr in the container nr db-table (until the item line nr. (parent) has been created)
			if(strMgr.isNotNull(request.getParameter("linx"))){
				Integer nextLineNr = Integer.parseInt(request.getParameter("linx"));
				String futureLineNr = String.valueOf(++ nextLineNr);
				model.put("lin", futureLineNr);
				lineNr = futureLineNr;
			}else{
				lineNr = "1";
				model.put("lin", lineNr);
			}
		}
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_items_childwindow_containernr");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			SadImportItemsContainernrMgr containerMgr = new SadImportItemsContainernrMgr(this.getSadImportSpecificTopicItemService(), recordToValidate.getAvd(), recordToValidate.getOpd(), lineNr, null);
			List list = containerMgr.getContainernrList(appUser.getUser());
			logger.info("List:" + list.size());
			model.put("containernrList", list);
			
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
	@RequestMapping(value="tvinnsadimport_edit_items_childwindow_containernr_edit.do",   method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditContainernr(@ModelAttribute ("record") JsonSadImportSpecificTopicItemContainernrRecord recordToValidate, BindingResult bindingResult,HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doEditContainernr");
		Map model = new HashMap();
		model.put("avd", recordToValidate.getSvavd());
		model.put("opd", recordToValidate.getSvtdn());
		model.put("lin", recordToValidate.getSvli());
		String action = request.getParameter("action");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_items_childwindow_containernr");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			SadImportItemsContainernrMgr containerMgr = new SadImportItemsContainernrMgr(this.getSadImportSpecificTopicItemService(), recordToValidate.getSvavd(), recordToValidate.getSvtdn(), recordToValidate.getSvli(), recordToValidate.getSvcnr());
			
			//-----------
			//Validation
			//-----------
			SadImportItemsContainernrValidator validator = new SadImportItemsContainernrValidator();
			if(!"doDelete".equals(action)){
				validator.validate(recordToValidate, bindingResult);
			}
			//check for ERRORS
			if(bindingResult.hasErrors()){
			    logger.info("Error in validation ...");	
				model.put("record", recordToValidate);
				
		    }else{
				//Update
				String mode = TvinnSadConstants.MODE_ADD;
				if("doDelete".equals(action)){
					mode = TvinnSadConstants.MODE_DELETE;
				}
				containerMgr.updateContainernr(appUser.getUser(), mode);
			}
			//get list
			List list = containerMgr.getContainernrList(appUser.getUser());
			model.put("containernrList", list);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
		
	}
	
	/**
	 * Get varukoder from tolltariff
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
		logger.info(urlRequestParams);
		
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
	 * @param receiverId
	 * @param vkod
	 * @return
	 */
	private List<JsonSadImportTolltariffKundensRegisterVarukodRecord> getKundVareRegList(SystemaWebUser appUser, String receiverId, String vkod){
		List<JsonSadImportTolltariffKundensRegisterVarukodRecord> list = new ArrayList<JsonSadImportTolltariffKundensRegisterVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_FETCH_TOLLTARIFF_KUNDENSVAREREG_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&levenr=" + receiverId);
		urlRequestParams.append("&varnr=" + vkod);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		JsonSadImportTolltariffKundensRegisterVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadImportSpecificTopicItemService.getKundRegisterVarukodContainer(jsonPayload);
				if(container!=null){
					for(JsonSadImportTolltariffKundensRegisterVarukodRecord  record : container.getKundvarlist()){
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
	 * @param description
	 * @param ieMode
	 * @return
	 */
	
	private List<JsonTvinnSadTolltariffVarukodRecord> getTulltaxaListFromDesc(SystemaWebUser appUser, String description, String ieMode){
		List<JsonTvinnSadTolltariffVarukodRecord> list = new ArrayList<JsonTvinnSadTolltariffVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_FROM_DESC_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&sok=" + description);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
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
	 * @param appUser
	 * @param codeType
	 * @return
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
	
	
	@Qualifier 
	private SadImportGeneralCodesChildWindowService sadImportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSadImportGeneralCodesChildWindowService(SadImportGeneralCodesChildWindowService value){this.sadImportGeneralCodesChildWindowService = value;}
	public SadImportGeneralCodesChildWindowService getSadImportGeneralCodesChildWindowService(){ return this.sadImportGeneralCodesChildWindowService; }
	
	
	@Qualifier 
	private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	@Autowired
	@Required	
	public void setTvinnSadTolltariffVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTolltariffVarukodService = value;}
	public TvinnSadTolltariffVarukodService getTvinnSadTolltariffVarukodService(){ return this.tvinnSadTolltariffVarukodService; }
	
	
	@Qualifier 
	private SadImportSpecificTopicItemService sadImportSpecificTopicItemService;
	@Autowired
	@Required	
	public void setSadImportSpecificTopicItemService(SadImportSpecificTopicItemService value){this.sadImportSpecificTopicItemService = value;}
	public SadImportSpecificTopicItemService getSadImportSpecificTopicItemService(){ return this.sadImportSpecificTopicItemService; }
	
	
}

