package no.systema.tvinn.sad.sadimport.controller;

import java.lang.reflect.Field;
import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.sadexport.filter.SearchFilterSadExportTopicList;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
//
import no.systema.tvinn.sad.sadimport.validator.SadImportListValidator;
import no.systema.tvinn.sad.sadimport.filter.SearchFilterSadImportTopicList;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigRecord;
import no.systema.tvinn.sad.sadimport.service.SadImpDigTopicListService;
import no.systema.tvinn.sad.sadimport.service.SadImportTopicListService;



/**
 * TVINN-SAD Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadImportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private StringManager strMgr = new StringManager();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	/**
	 * Obsolete (doFind method is now used as default)
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="tvinnsadimport.do", method=RequestMethod.GET)
	public ModelAndView doTvinnSadImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadimport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterSadImportTopicList searchFilter = new SearchFilterSadImportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			this.setCodeDropDownMgr(appUser, model);
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			
			//init filter with users signature (for starters)
			searchFilter.setSg(appUser.getTvinnSadSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	*/
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterSadImportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonSadImportTopicListRecord> outputList = new ArrayList<JsonSadImportTopicListRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsadimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setCodeDropDownMgr(appUser, model);
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT, recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
	    		SearchFilterSadImportTopicList searchFilter = new SearchFilterSadImportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            		session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT, searchFilter);
	            }else{
	            	SearchFilterSadImportTopicList sessionFilter = (SearchFilterSadImportTopicList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            
	            //get BASE URL
	            String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_TOPICLIST_URL;
	    		//only when Inv exists
	    		if(searchFilter!=null && strMgr.isNotNull(searchFilter.getInv())){
	    			BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_TOPICLIST_INVOICEREF_URL;
	    		//only when r31 exists	
	    		}else if(searchFilter!=null && strMgr.isNotNull(searchFilter.getR31())){
	    			BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_TOPICLIST_R31REF_URL;
	    		}
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
	    		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + "?" + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSadImportTopicListContainer jsonSadImportTopicListContainer = this.sadImportTopicListService.getSadImportTopicListContainer(jsonPayload);
		    		//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					outputList = jsonSadImportTopicListContainer.getOrderList();
					for(JsonSadImportTopicListRecord record : outputList) {
						//TODO hämta from digitoll sadmotf och/eller sadmohf joins
						//använd befintliga digitolls syjservicestn...
					}
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.setCodeDropDownMgr(appUser, model);
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
					//domain and search filter
					successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
					successView.addObject(TvinnSadConstants.DOMAIN_LIST_SIZE, outputList.size());	
					successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
					//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
					session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, outputList);
					
					if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT).equals("")){
						successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT, searchFilter);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	
					return successView;
					
		    	}else{
					logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
		
	}
	/**
	 * This was added to help DSV with STATS in Excel
	 * STATS must take Digitoll into account. The service is a java service. Omberegning is not included
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport", params="action=doFindWithDigitoll",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindWithDigitoll(@ModelAttribute ("record") SearchFilterSadImportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<SadImpDigRecord> outputList = new ArrayList<SadImpDigRecord>();
		Map model = new HashMap();
		//TO TEST - change on JSP to thismapping!!
		
		ModelAndView successView = new ModelAndView("tvinnsadimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setCodeDropDownMgr(appUser, model);
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT, recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
	    		SearchFilterSadImportTopicList searchFilter = new SearchFilterSadImportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            		session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT, searchFilter);
	            }else{
	            	SearchFilterSadImportTopicList sessionFilter = (SearchFilterSadImportTopicList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            
	            //get BASE URL
	            String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_TOPICLIST_DIGITOLL_URL;
	    		
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParametersDigitoll(searchFilter, appUser);
	    		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + "?" + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

				//Debug --> 
		    	logger.debug(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		SadImpDigContainer container = this.sadImpDigTopicListService.getSadImpDigContainer(jsonPayload);
		    		//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					if(container!=null) {
						outputList = container.getList();
					}
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.setCodeDropDownMgr(appUser, model);
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
					//domain and search filter
					successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
					successView.addObject(TvinnSadConstants.DOMAIN_LIST_SIZE, outputList.size());	
					successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
					//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
					session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, outputList);
					
					if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT).equals("")){
						successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT, searchFilter);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	
					return successView;
					
		    	}else{
					logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @return
	 */
	/*
	private Collection<JsonNotisblockRecord> getNotisblockList(SystemaWebUser appUser, JsonSadImportTopicListRecord record){
		Collection<JsonNotisblockRecord> list = new ArrayList<JsonNotisblockRecord>();
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		//get BASE URL
		final String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_NOTIS_BLOCK_FETCH_LIST_URL;
		//add URL-parameters
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + record.getAvd());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + record.getOpd());
		
		String urlRequestParams = urlRequestParamsKeys.toString();
		//logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	//logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	//logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
	    		list = jsonNotisblockContainer.getFreetextlist();
	    		for(JsonNotisblockRecord notisRecord : list){
	    			//DEBUG logger.info("Notisblock free text:" + notisRecord.getFrttxt());
	    		}
	    	}
		return list;
	}
	*/
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TYPE_IE = "I"; //Import
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TYPE_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadAvdelningContainer container = this.tvinnSadDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTvinnSadAvdelningRecord> list = new ArrayList();
			for(JsonTvinnSadAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TYPE_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TYPE_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadSignatureContainer container = this.tvinnSadDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTvinnSadSignatureRecord> list = new ArrayList();
			for(JsonTvinnSadSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSadImportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&usrspcname=" + appUser.getUser());
		
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
		if(searchFilter.getSitll()!=null && !"".equals(searchFilter.getSitll())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lopenr=" + searchFilter.getSitll());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatumt())){
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
		if(searchFilter.getGodsnr()!=null && !"".equals(searchFilter.getGodsnr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "godsnr=" + searchFilter.getGodsnr());
		}
		if(searchFilter.getInnstikk()!=null && !"".equals(searchFilter.getInnstikk())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "innstikk=" + searchFilter.getInnstikk());
		}
		if(searchFilter.getInv()!=null && !"".equals(searchFilter.getInv())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "inv=" + searchFilter.getInv());
		}
		if(searchFilter.getR31()!=null && !"".equals(searchFilter.getR31())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "r31=" + searchFilter.getR31());
		}
		if(searchFilter.getEta()!=null && !"".equals(searchFilter.getEta())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "detain=" + this.dateFormatter.convertToDate_ISO(searchFilter.getEta()));
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersDigitoll(SearchFilterSadImportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		//urlRequestParamsKeys.append("&usrspcname=" + appUser.getUser());
		
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "siavd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sitdn=" + searchFilter.getOpd());
		}
		
		if(searchFilter.getSg()!=null && !"".equals(searchFilter.getSg())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sisg=" + searchFilter.getSg());
		}
		if(searchFilter.getSitll()!=null && !"".equals(searchFilter.getSitll())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sitll=" + searchFilter.getSitll());
		}
		if(searchFilter.getSitle()!=null && !"".equals(searchFilter.getSitle())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sitle=" + searchFilter.getSitle());
		}
		if(searchFilter.getSitrid()!=null && !"".equals(searchFilter.getSitrid())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sitrid=" + searchFilter.getSitrid());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sidt=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatumt())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sidt_to=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatumt()));
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sist=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sinas=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sinak=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getGodsnr()!=null && !"".equals(searchFilter.getGodsnr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getGodsnr());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/* TODO COVI Status
		 * 
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		*/
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("sadImportTopicListService")
	private SadImportTopicListService sadImportTopicListService;
	@Autowired
	public void setSadImportTopicListService (SadImportTopicListService value){ this.sadImportTopicListService = value; }
	public SadImportTopicListService getSadImportTopicListService(){ return this.sadImportTopicListService; }
	
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
	@Autowired
	private SadImpDigTopicListService sadImpDigTopicListService;
	
	
	
	
	
}

