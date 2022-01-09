package no.systema.tvinn.sad.nctsimport.controller;

import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.nctsimport.util.manager.CodeDropDownMgr;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsexport.filter.SearchFilterSadNctsExportTopicList;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListRecord;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportTopicListService;
import no.systema.tvinn.sad.nctsimport.filter.SearchFilterSadNctsImportTopicList;
import no.systema.tvinn.sad.nctsimport.validator.SadNctsImportListValidator;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;



/**
 * Tvinn Sad Ncts-Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Sep 1, 2014
 * 
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadNctsImportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadNctsImportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

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
	@RequestMapping(value="tvinnsadnctsimport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterSadNctsImportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonSadNctsImportTopicListRecord> outputList = new ArrayList<JsonSadNctsImportTopicListRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//-----------
			//Validation
			//-----------
			SadNctsImportListValidator validator = new SadNctsImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.setCodeDropDownMgr(appUser, model);
				//
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT_NCTS, recordToValidate);
				
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    	SearchFilterSadNctsImportTopicList searchFilter = new SearchFilterSadNctsImportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT_NCTS, searchFilter);
	            }else{
	            	SearchFilterSadNctsImportTopicList sessionFilter = (SearchFilterSadNctsImportTopicList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT_NCTS);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            //get BASE URL
		    		final String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_TOPICLIST_URL;
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
			    		
			    		JsonSadNctsImportTopicListContainer jsonSadNctsImportTopicListContainer = this.sadNctsImportTopicListService.getNctsImportTopicListContainer(jsonPayload);
			    		//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
					outputList = jsonSadNctsImportTopicListContainer.getOrderList();	
					//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
					/*TODO ?
					if(!"D".equals(searchFilter.getStatus())){
						//To avoid the ...ConcurrentModificationException we make a copy of the existing list and iterate over new copy
						for (JsonSadNctsImportTopicListRecord record : new ArrayList<JsonSadNctsImportTopicListRecord>(outputList)){
							if("D".equals(record.getStatus())){
								outputList.remove(record);
							}
						}
					}
			    		*/
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					this.setCodeDropDownMgr(appUser, model);
					
					//domain and search filter
					successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
					successView.addObject(TvinnSadConstants.DOMAIN_LIST_SIZE, outputList.size());	
					successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
					//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
					session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, outputList);
					
					
					if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT_NCTS) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADIMPORT_NCTS).equals("")){
						successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADIMPORT_NCTS, searchFilter);
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
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="tvinnsadnctsimport.do", params="action=doDelete",  method={RequestMethod.GET} )
	public ModelAndView doDelete(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		logger.info("Inside doDelete");
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");

		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
				
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=D" (Delete)
			//-----------------------------------
			String urlRequestParams = this.getRequestUrlKeyParametersDoDelete(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
	    	
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//--------------------------------------
		    	//EXECUTE the DELETE (RPG program) here 
		    	//--------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		    	//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalError(model, rpgReturnResponseHandler);
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully updated, OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicRecord, sumOfAntalKolliInItemLines, sumOfAntalItemLines );
		    	}
			
			//init filter 
			SearchFilterSkatImportTopicList searchFilter = new SearchFilterSkatImportTopicList();
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getSkatSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			successView.addObject(SkatConstants.DOMAIN_LIST,new ArrayList());
			
			return successView;
			
		}
		
	}
	*/
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        //this.setDomainObjectsInView(model, record);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
			
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //Import
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_AVDELNINGAR_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadAvdelningContainer container = this.tvinnSadDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTvinnSadAvdelningRecord> list = new ArrayList();
			for(JsonTvinnSadAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
				//logger.info("Avd-tst:" + record.getAvd() + "XX" + record.getTst());
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			session.setAttribute(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG, list);
			
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
		String NCTS_IMPORT_IE = "N"; //NCTS import: ie=N 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:"  + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadSignatureContainer container = this.tvinnSadDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTvinnSadSignatureRecord> list = new ArrayList();
			for(JsonTvinnSadSignatureRecord record: container.getSignaturer()){
				list.add(record);
				//logger.info("Sign-tst:" + record.getSign());
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
	private String getRequestUrlKeyParameters(SearchFilterSadNctsImportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getMrnNr()!=null && !"".equals(searchFilter.getMrnNr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mrn=" + searchFilter.getMrnNr());
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
		if(searchFilter.getForenklad()!=null && !"".equals(searchFilter.getForenklad())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "forenklad=" + searchFilter.getForenklad());
		}
		if(searchFilter.getAnsNavn()!=null && !"".equals(searchFilter.getAnsNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ansNavn=" + searchFilter.getAnsNavn());
		}
		if(searchFilter.getGodsNr()!=null && !"".equals(searchFilter.getGodsNr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "godsNr=" + searchFilter.getGodsNr());
		}
		if(searchFilter.getDatumFr()!=null && !"".equals(searchFilter.getDatumFr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datumFr=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatumFr()));
		}
		
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersDoDelete(String avd, String opd, SystemaWebUser appUser){
		final String MODE_DELETE = "D";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_DELETE);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/*this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		*/
	}
	
	//SERVICES
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
	
	@Qualifier ("sadNctsImportTopicListService")
	private SadNctsImportTopicListService sadNctsImportTopicListService;
	@Autowired
	@Required
	public void setSadNctsImportTopicListService (SadNctsImportTopicListService value){ this.sadNctsImportTopicListService = value; }
	public SadNctsImportTopicListService getSadNctsImportTopicListService(){ return this.sadNctsImportTopicListService; }
	
	
	
	
}

