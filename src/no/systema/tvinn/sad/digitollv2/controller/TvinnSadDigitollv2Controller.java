package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

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
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.digitollv2.filter.SearchFilterDigitollTransportList;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.manifest.express.filter.SearchFilterManifestList;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;




/**
 * Tvinn Sad Digitoll v2 Controller 
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
@Controller
public class TvinnSadDigitollv2Controller {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2Controller.class.getName());
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
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doListTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmotfRecord> outputList = new ArrayList<SadmotfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String gate = request.getParameter("gate");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			SearchFilterDigitollTransportList searchFilter = new SearchFilterDigitollTransportList();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST, searchFilter);
            }else{
            	SearchFilterDigitollTransportList sessionFilter = (SearchFilterDigitollTransportList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST);
            	if(sessionFilter!=null){
            		//Use the session filter when applicable
            		searchFilter = sessionFilter;
            		
            	}else{
            		//first time propose today
            		searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            	}
            }
            
            if(StringUtils.isNotEmpty(gate)) {
            	//nothing
            	//The first search from the Digitoll meny is the gate and it should not fire any query...
            	//with no gate as parameter will be normal-behavior in the search GUI (with filter)
            	
            }else {
            	
	            //get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.warn("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		
		    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
		    		//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
					outputList = jsonContainer.getList();
					if(outputList!=null && outputList.size() > SadmotfContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS){
						outputList = new ArrayList();
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, "Too many lines. Narrow your search please ...");
					}else{
						for(SadmotfRecord record: outputList){
							/*//check if the manifest cargo lines are valid
							if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
								record.setOwn_valid(-1);
							}
							//check it the manifest is editable
							if(!manifestExpressMgr.isEditableManifest(appUser, record)){
								record.setOwn_editable(-1);
							}
							//dates
							this.adjustFieldsForFetch(record);
							*/
						}
						logger.info(outputList.toString());
					}
					
		    	}	
            }
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
    			
			if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST).equals("")){
				successView.addObject(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST, searchFilter);
			}
	    	
			//this.populateCustomsOfficeOfFirstEntryHtmlDropDown(model);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmotfRecord> outputList = new ArrayList<SadmotfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_transport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String etlnrt = request.getParameter("etlnrt");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			/*
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			SearchFilterManifestList searchFilter = new SearchFilterManifestList();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST, searchFilter);
            }else{
            	SearchFilterManifestList sessionFilter = (SearchFilterManifestList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST);
            	if(sessionFilter!=null){
            		//Use the session filter when applicable
            		searchFilter = sessionFilter;
            		
            	}else{
            		//first time propose today
            		searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            	}
            }
            */
            //get BASE URL
    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
    		//add URL-parameters
    		String urlRequestParams = "user=" + appUser.getUser() + "&etlnrt=" + etlnrt;
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
	    		outputList = jsonContainer.getList();
				if(outputList!=null && outputList.size() > SadmotfContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS){
					outputList = new ArrayList();
					model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, ".. No records ? ...");
				}else{
					for(SadmotfRecord record: outputList){
						//get all masters
						this.getMasters(appUser, record);
						//now we have all master consignments in this transport
						model.put("record", record);
						logger.info(record.toString());
					}
					
				}
				
	    	}	
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			
			//master consignments' list
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST_SIZE, outputList.size());	
			//successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
    		
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param tranportId
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
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		record.setListMasters(jsonContainer.getList());
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
	@RequestMapping(value="tvinnsaddigitollv2_edit_manifest.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditManifest(@ModelAttribute ("record") SearchFilterManifestList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTvinnSadManifestRecord> outputList = new ArrayList<JsonTvinnSadManifestRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_manifest");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			/*
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			SearchFilterManifestList searchFilter = new SearchFilterManifestList();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST, searchFilter);
            }else{
            	SearchFilterManifestList sessionFilter = (SearchFilterManifestList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST);
            	if(sessionFilter!=null){
            		//Use the session filter when applicable
            		searchFilter = sessionFilter;
            		
            	}else{
            		//first time propose today
            		searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            	}
            }
            
            //get BASE URL
    		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_URL;
    		//add URL-parameters
    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		
	    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
	    		//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				outputList = jsonTvinnSadManifestContainer.getList();
				if(outputList!=null && outputList.size() > JsonTvinnSadManifestContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_MANIFESTS){
					outputList = new ArrayList();
					model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, "Too many lines. Narrow your search please ...");
				}else{
					for(JsonTvinnSadManifestRecord record: outputList){
						//check if the manifest cargo lines are valid
						if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
							record.setOwn_valid(-1);
						}
						//check it the manifest is editable
						if(!manifestExpressMgr.isEditableManifest(appUser, record)){
							record.setOwn_editable(-1);
						}
						//dates
						this.adjustFieldsForFetch(record);
					}
					logger.info(outputList.toString());
				}
				
	    	}	
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
    		
			if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST).equals("")){
				successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADMANIFEST, searchFilter);
			}
	    	*/
			this.populateCustomsOfficeOfFirstEntryHtmlDropDown(model);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditHouse(@ModelAttribute ("record") SearchFilterManifestList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTvinnSadManifestRecord> outputList = new ArrayList<JsonTvinnSadManifestRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_house");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			/*
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			SearchFilterManifestList searchFilter = new SearchFilterManifestList();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST, searchFilter);
            }else{
            	SearchFilterManifestList sessionFilter = (SearchFilterManifestList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST);
            	if(sessionFilter!=null){
            		//Use the session filter when applicable
            		searchFilter = sessionFilter;
            		
            	}else{
            		//first time propose today
            		searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            	}
            }
            
            //get BASE URL
    		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_URL;
    		//add URL-parameters
    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		
	    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
	    		//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				outputList = jsonTvinnSadManifestContainer.getList();
				if(outputList!=null && outputList.size() > JsonTvinnSadManifestContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_MANIFESTS){
					outputList = new ArrayList();
					model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, "Too many lines. Narrow your search please ...");
				}else{
					for(JsonTvinnSadManifestRecord record: outputList){
						//check if the manifest cargo lines are valid
						if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
							record.setOwn_valid(-1);
						}
						//check it the manifest is editable
						if(!manifestExpressMgr.isEditableManifest(appUser, record)){
							record.setOwn_editable(-1);
						}
						//dates
						this.adjustFieldsForFetch(record);
					}
					logger.info(outputList.toString());
				}
				
	    	}	
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
    		
			if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADMANIFEST).equals("")){
				successView.addObject(TvinnSadConstants.DOMAIN_SEARCH_FILTER_SADMANIFEST, searchFilter);
			}
	    	*/
			this.setCodeDropDownMgr(appUser, model);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	
	
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
	
	private void populateCustomsOfficeOfFirstEntryHtmlDropDown(Map model) {
		List<JsonTvinnSadCodeRecord> list = new ArrayList();
		list.add(this.setRecordCustomsOffice("NO371001", "Svinesund N tollsted"));
		list.add(this.setRecordCustomsOffice("NO372001", "Ørje tollsted"));
		model.put("custOfficeList", list);
	}
	private JsonTvinnSadCodeRecord setRecordCustomsOffice(String code, String text) {
		JsonTvinnSadCodeRecord record = new JsonTvinnSadCodeRecord();
		record.setZkod(code); record.setZtxt(text);
		return record;
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
	private String getRequestUrlKeyParameters(SearchFilterDigitollTransportList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etavd=" + searchFilter.getAvd());
		}
		if(searchFilter.getTurnr()!=null && !"".equals(searchFilter.getTurnr())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etpro=" + searchFilter.getTurnr());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etsg=" + searchFilter.getSign());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etdtr=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
		}
		/*
		if(searchFilter.getDatumt()!=null && !"".equals(searchFilter.getDatumt())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "own_efdtr=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatumt()));
		}*/
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getEtaDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etetad=" + this.dateFormatter.convertToDate_ISO(searchFilter.getEtaDatum()));
		}
		/*
		if(searchFilter.getEtaDatumt()!=null && !"".equals(searchFilter.getEtaDatumt())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "own_etetad=" + this.dateFormatter.convertToDate_ISO(searchFilter.getEtaDatumt()));
		}*/
		
		
		return urlRequestParamsKeys.toString();
	}
	
	
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFPR.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	private void adjustFieldsForFetch(JsonTvinnSadManifestRecord recordToValidate){
		recordToValidate.setEfeta(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfeta()));
		recordToValidate.setEfdtr(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfdtr()));
		recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfsjadt()));
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
	private ManifestExpressMgr manifestExpressMgr;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;

}

