package no.systema.tvinn.sad.nctsimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.DateTimeManager;


//TVINN
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.nctsimport.validator.SadNctsImportUnloadingValidator;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingRecord;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicService;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicUnloadingService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.nctsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsimport.util.manager.CodeDropDownMgr;



/**
 * TVINN-NCTS Import Unloading Header Controller 
 * 
 * @author oscardelatorre
 * @date Sep 1, 2014
 */

@Controller
@Scope("session")
public class SadNctsImportUnloadingHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadNctsImportUnloadingHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new NctsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_unloading_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_unloading_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->tvinnsadnctsimport_unloading_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			this.setCodeDropDownMgr(appUser, model);
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
	    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
	    		
	    		//domain
	    		successView.addObject("model", model);
	    		successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_CREATE);

		}
		
		return successView;
	}
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_unloading_edit.do")
	public ModelAndView doNctsImportUnloadingEdit(@ModelAttribute ("record") JsonSadNctsImportSpecificTopicUnloadingRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_unloading_edit");
		String method = "doNctsImportUnloadingEdit [RequestMapping-->tvinnsadnctsimport_unloading_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = this.getAction(request.getParameter("action"));
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String origin = request.getParameter("origo");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Origo:" + origin);
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;

		}else{
			if(action!=null){
		    	//----------------------------
				//UPDATE RECORD
				//----------------------------	
				if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
					//---------------------
					//Validation Light GUI
					//---------------------
					SadNctsImportUnloadingValidator validator = new SadNctsImportUnloadingValidator();
					logger.info("VALIDATING...");
					validator.validate(recordToValidate, bindingResult);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	origin="list";
					    	//put domain objects and do go back to the original view...
					    //recordToValidate.setTiavd(avd);
					    	//recordToValidate.setTisg(sign);
					    //	this.setDomainObjectsInView(session, model, recordToValidate);
					    	
				    }else{
				    		JsonSadNctsImportSpecificTopicUnloadingRecord jsonNctsImportSpecificTopicUnloadingRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsImportSpecificTopicUnloadingRecord = new JsonSadNctsImportSpecificTopicUnloadingRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicUnloadingRecord);
							binder.bind(request);
							this.adjustFieldsAfterBind(request, jsonNctsImportSpecificTopicUnloadingRecord);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("TIENKL [after bind]: " + jsonNctsImportSpecificTopicUnloadingRecord.getTienkl());
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
					    String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_UNLOADING_URL;
						//-----------------------------------
						//add URL-parameter "mode=U" (Update)
						//-----------------------------------
						String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
						//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
						String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicUnloadingRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
						//for debug purposes in GUI
						session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL); 
				    	
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
				    	logger.info("URL PARAMS: " + urlRequestParams);
				    	//----------------------------------------------------------------------------
				    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
				    	//----------------------------------------------------------------------------
				    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
						//Debug --> 
				    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				    	//we must evaluate a return RPG code in order to know if the Update was OK or not
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicUnloadingRecord);
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Record successfully updated, OK ");	
				    	}		
				    }
				}
				
				//-------------
				//FETCH RECORD
				//-------------
				logger.info("FETCH record transaction...");
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_URL;
				//url params
				action = TvinnSadConstants.ACTION_FETCH;
				String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
				//for debug purposes in GUI
				session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSadNctsImportSpecificTopicUnloadingContainer jsonNctsImportSpecificTopicUnloadingContainer = this.sadNctsImportSpecificTopicUnloadingService.getNctsImportSpecificTopicUnloadingContainer(jsonPayload);
		    		//add drop-downs
		    		this.setCodeDropDownMgr(appUser, model);
					this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicUnloadingContainer);
		    		//We must fetch the parent topic record when the end user is coming from the list of topics and not from the topic view
		    		if(origin!=null && origin.equals("list")){
		    			logger.info("Fetching the specific topic from origin=list...");
		    			this.getSpecificTopicRecord(session,avd,opd,sign,appUser);
		    		}
		    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
					//put the doUpdate action since we are preparing the record for an update (when saving)
					successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
			    		
			    	}else{
			    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
			    		return loginView;
			    	}    			
			}
			successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE); //remove this line	
			return successView;
		}
		
	}
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_unloading_send.do")
	public ModelAndView doNctsImportUnloadingSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadnctsimport.do?action=doFind&sign=" + appUser.getTvinnSadSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS_IMPORT);
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = TvinnSadConstants.ACTION_SEND;
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
		    //---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_UNLOADING_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=S" (Send)
			//-----------------------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there is only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			//for debugging purposes
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 */
	private void getSpecificTopicRecord(HttpSession session, String avd, String opd, String sign, SystemaWebUser appUser){
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParameters(TvinnSadConstants.ACTION_FETCH, avd, opd, sign, appUser);
		//for debug purposes in GUI
		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSadNctsImportSpecificTopicContainer jsonNctsImportSpecificTopicContainer = this.sadNctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		for (JsonSadNctsImportSpecificTopicRecord record : jsonNctsImportSpecificTopicContainer.getOneorder()){
	    			//model.put(TdsConstants.DOMAIN_RECORD, record);
	    			
	    			//put the header topic in session for the coming item lines
	    			session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
	    		}
	    		
	    	}
	}
	
	/**
	 * 
	 * @param action
	 * @return
	 */
	private String getAction(String action){
		String retval = action;
		
		//default behavior
		if(action==null || "".equals(action)){
			retval = TvinnSadConstants.ACTION_FETCH;
			logger.info("setting default value <doFetch> to action");
		}
		
		return retval;
	}
	
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadNctsImportSpecificTopicUnloadingRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}

	
	/**
	 * Gets the key parameter string (such as avd, opd, user, mode)
	 * @param action
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String avd, String opd, String sign, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		if(TvinnSadConstants.ACTION_FETCH.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		}else if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd); //tiavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd); //titdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_UPDATE);
			
		}else if(TvinnSadConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + sign);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
			
		}else if(TvinnSadConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsImportSpecificTopicUnloadingContainer
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNctsImportSpecificTopicUnloadingContainer container){
		//SET HEADER RECORDS  (from RPG)
		
		for (JsonSadNctsImportSpecificTopicUnloadingRecord record : container.getOneorder()){
			//logger.info("nidfkd:" + record.getNidfkd());
			//set default current date as unloading date ...
			if(record.getNidtl()==null || "".equals(record.getNidtl())){
				record.setNidtl(this.dateTimeManager.getCurrentDate_NO());
			}else{
				record.setNidtl(this.dateFormatter.convertToDate_NO(record.getNidtl()));
			}
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD_UNLOADING, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNctsImportSpecificTopicUnloadingRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSadNctsImportSpecificTopicUnloadingRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
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
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
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
		String NCTS_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
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
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadNctsImportSpecificTopicUnloadingRecord record){
		String dateNidtlISO = null;
		if(record.getNidtl()!=null){
			dateNidtlISO = this.dateFormatter.convertToDate_ISO(record.getNidtl());
			record.setNidtl(dateNidtlISO);
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("sadNctsImportSpecificTopicService")
	private SadNctsImportSpecificTopicService sadNctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadNctsImportSpecificTopicService (SadNctsImportSpecificTopicService value){ this.sadNctsImportSpecificTopicService = value; }
	public SadNctsImportSpecificTopicService getSadNctsImportSpecificTopicService(){ return this.sadNctsImportSpecificTopicService; }
	
	@Qualifier ("sadNctsImportSpecificTopicUnloadingService")
	private SadNctsImportSpecificTopicUnloadingService sadNctsImportSpecificTopicUnloadingService;
	@Autowired
	@Required
	public void setSadNctsImportSpecificTopicUnloadingService (SadNctsImportSpecificTopicUnloadingService value){ this.sadNctsImportSpecificTopicUnloadingService = value; }
	public SadNctsImportSpecificTopicUnloadingService getSadNctsImportSpecificTopicUnloadingService(){ return this.sadNctsImportSpecificTopicUnloadingService; }
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
	
}

