package no.systema.tvinn.sad.nctsexport.controller;

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
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.JavaReflectionUtil;
import no.systema.main.model.SystemaWebUser;


import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsexport.validator.SadNctsExportHeaderValidator;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicCopiedContainer;

import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicService;
import no.systema.tvinn.sad.nctsexport.service.html.dropdown.SadNctsExportDropDownListPopulationService;
import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
import no.systema.tvinn.sad.nctsexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.nctsexport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import no.systema.tvinn.sad.nctsexport.mapper.url.request.UrlRequestParameterMapper;



/**
 * SAD-NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Feb 16, 2016
 * 
 */

@Controller
@Scope("session")
public class SadNctsExportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadNctsExportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new NctsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){

		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->tvinnsadnctsexport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//add gui lists here
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
	@RequestMapping(value="tvinnsadnctsexport_edit.do")
	public ModelAndView doNctsExportEdit(@ModelAttribute ("record") JsonSadNctsExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit");
		String method = "doNctsExportEdit [RequestMapping-->tvinnsadnctsexport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		//String dkxh_0035 = request.getParameter("dkxh_0035"); //test indicator (only in production)
		
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//-------------
				//FETCH RECORD
				//-------------
				if(TvinnSadConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
					//for debug purposes in GUI
					session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "?" + urlRequestParamsKeys.toString()); 
					
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
			    		JsonSadNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
			    		this.fetchSecurityRecord(jsonNctsExportSpecificTopicContainer, appUser.getUser(), avd, opd );
			    		//add gui lists here
			    		this.setCodeDropDownMgr(appUser, model);	
			    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer);
			    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
						//put the doUpdate action since we are preparing the record for an update (when saving)
						successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);	
			    	}else{
			    		logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
			    		return loginView;
					}
			    //----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				}else if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
					boolean isValidCreatedRecordTransactionOnRPG = true;
					//---------------------
					//Validation Light GUI
					//---------------------
					SadNctsExportHeaderValidator validator = new SadNctsExportHeaderValidator();
					logger.info("VALIDATING...");
					if(opd!=null && !"".equals(opd)){
						//Update...
						//nothing
					}else{
						logger.info("avdXX: " + avd);
						logger.info("signXX: " + sign);
						
						//Create
						//we must lend these dropdown variables to the validation object
						recordToValidate.setThavd(avd);
						recordToValidate.setThsg(sign);
					}
					validator.validate(recordToValidate, bindingResult);
					//test indicator in validation field
					//recordToValidate.setDkxh_0035(dkxh_0035);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	//put domain objects and do go back to the original view...
				    	recordToValidate.setThavd(avd);
				    	recordToValidate.setThsg(sign);
				    	this.setDomainObjectsInView(session, model, recordToValidate);
				    	isValidCreatedRecordTransactionOnRPG = false;
				    	if(opd==null || "".equals(opd)){
				    		action = TvinnSadConstants.ACTION_CREATE;
				    	}
				    }else{
				    	
			    		JsonSadNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsExportSpecificTopicRecord = new JsonSadNctsExportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
							
							//binder.registerCustomEditor(...); // if needed
							binder.bind(request);
				            this.adjustFieldsAfterBind(request, jsonNctsExportSpecificTopicRecord);
				            
				            logger.info("THNAS [after bind]: " + jsonNctsExportSpecificTopicRecord.getThnas());
							logger.info("THTUID [after bind]: " + jsonNctsExportSpecificTopicRecord.getThtuid());
							
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonNctsExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonNctsExportSpecificTopicRecord!=null){
								//avd = jsonNctsExportSpecificTopicRecord.getThavd();
								//sign = jsonNctsExportSpecificTopicRecord.getThsg();
								opd = jsonNctsExportSpecificTopicRecord.getThtdn();
								tuid = jsonNctsExportSpecificTopicRecord.getThtuid();
								
								//take the rest from GUI.
								jsonNctsExportSpecificTopicRecord = new JsonSadNctsExportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonNctsExportSpecificTopicRecord.setThavd(avd);
					            jsonNctsExportSpecificTopicRecord.setThtdn(opd);
					            jsonNctsExportSpecificTopicRecord.setThsg(sign);
					            jsonNctsExportSpecificTopicRecord.setThtuid(tuid);
					            
					            //adjust fields in order to comply to the back-end requirements
					            this.adjustFieldsAfterBind(request, jsonNctsExportSpecificTopicRecord);
					            
					            //test indicator in validation field
					            //jsonNctsExportSpecificTopicRecord.setDkxh_0035(dkxh_0035);
							}else{
								//Some kind of error occurred. Set the transaction as invalid...
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsExportSpecificTopicRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update successfully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		
					    		//------------------------------------
					    		//Update/Create now Sikkerhet record
					    		//------------------------------------
					    		//Check if there is a record minimum of information in order to go on with security record - update
					    		if(this.isQualifiedForUpdateSecurityRecord(jsonNctsExportSpecificTopicRecord)){
						    		String mode = TvinnSadConstants.MODE_ADD; //Add - default
						    		if(this.existsSecurityRecord(appUser.getUser(), avd, opd)){
						    			mode = TvinnSadConstants.MODE_UPDATE; //Update
						    		}
						    		if(!this.updateSecurityRecord(jsonNctsExportSpecificTopicRecord, mode, appUser.getUser(), avd, opd)){
						    			this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
							    		isValidCreatedRecordTransactionOnRPG = false;	
						    		}
					    		}
					    		//put domain objects
					    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicRecord);
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }
					//add gui lists here
					this.setCodeDropDownMgr(appUser, model);	
		    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
					successView.addObject("model" , model);
					successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		            //Edit or Create offset
			    	if(isValidCreatedRecordTransactionOnRPG){
		            	successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
		            }else{
		            	//Validation errors have been generated and we must offset to some state (set or changed above in some flow)
		            	successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, action);
		            }
					
				//------------------------
				//CREATE-ADD [PURE] RECORD
				//-------------------------
				}else if(TvinnSadConstants.ACTION_CREATE.equals(action)){
					//OBSOLETE
					//This action is not used as an isolated create. It is realized in the UPDATE above in 2 transactions
					//Could be needed in the future.
					
				//------------------
				//REMOVE RECORD
				//------------------	
				}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
					//Remove Topic
					//Could be delete OR set a remove status...(no physical delete)
					//TODO
				}
			}
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param jsonNctsExportSpecificTopicRecord
	 * @return
	 */
	private boolean isQualifiedForUpdateSecurityRecord(JsonSadNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		boolean retval = false;
		if(jsonNctsExportSpecificTopicRecord.getThdta()!=null && !"".equals(jsonNctsExportSpecificTopicRecord.getThdta())){
			retval = true;
		}
		if(jsonNctsExportSpecificTopicRecord.getThtkbm()!=null && !"".equals(jsonNctsExportSpecificTopicRecord.getThtkbm())){
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_send.do")
	public ModelAndView doNctsExportSend(HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadnctsexport.do?action=doFind&sign=" + appUser.getTvinnSadSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
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
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
	 * Prints a specific topic
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsexport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doNctsExportEditPrintTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadnctsexport.do?action=doFind&sign=" + appUser.getTvinnSadSign());
		
		String method = "doNctsExportEditPrintTopic [RequestMapping-->tvinnsadnctsexport_edit_printTopic.do]";
		logger.info("Method: [RequestMapping-->tvinnsadnctsexport_edit_printTopic.do]");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "?" + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the Print (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
		    		
		}
		
		return successView;
	}
	
	
	/**
	 *
	 * Copies one topic(ärende) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="tvinnsadnctsexport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		ModelAndView successView = new ModelAndView("tvinnsadnctsexport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("redirect:tvinnsadnctsexport.do?action=doFind&sign=" + appUser.getTvinnSadSign());
		
		JsonSadNctsExportTopicCopiedContainer jsonNctsExportTopicCopiedContainer = null;
		String method = "doCopyTopic [RequestMapping-->tvinnsadnctsexport_copyTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=null;
		String avd=null;
		String opd=null;
		String newAvd=null;
		String newSign=null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
	        		logger.info("####################################################");
        			logger.info("param Name : " + element + " value: " + value);
        			if(element.startsWith("originalAvd")){
        				avd = value;
        			}else if(element.startsWith("originalOpd")){
        				opd = value;
        			}else if(element.startsWith("newAvd")){
        				newAvd = value;
        			}else if(element.startsWith("newSign")){
        				newSign = value;
        			}else if(element.startsWith("action")){
        				action = value;
        			}
        		}
	    	}
	    
	
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//--------------------
			//STEP 1: COPY record
			//--------------------
			logger.info("starting COPY record transaction...");
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "?" + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonNctsExportTopicCopiedContainer = this.sadNctsExportSpecificTopicService.getNctsExportTopicCopiedContainer(jsonPayload);
	    		if(jsonNctsExportTopicCopiedContainer!=null){
	    			//Check for errors
	    			if(jsonNctsExportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonNctsExportTopicCopiedContainer.getErrMsg())){
	    				logger.error("[ERROR FATAL] errMsg containing: " + jsonNctsExportTopicCopiedContainer.getErrMsg());
	    				return fallbackOnErrorView;
	    			}
	    		}
	    	}else{
			logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
			return loginView;
			}
			
	    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
	    	//--------------------
			//STEP 2: FETCH record
			//--------------------
			logger.info("starting FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonNctsExportTopicCopiedContainer.getNewavd(), jsonNctsExportTopicCopiedContainer.getNewopd(), jsonNctsExportTopicCopiedContainer.getNewsign(), appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSadNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
		    		//add gui lists here
		    		this.setCodeDropDownMgr(appUser, model);	
		    		
		    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer);
		    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
				//put the doUpdate action since we are preparing the record for an update (when saving)
				successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
		    		
		    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			return successView;
		}
		
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadnctsexport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadnctsexport.do?action=doFind&sign=" + appUser.getTvinnSadSign());
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String newStatus = request.getParameter("selectedStatus");
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_STATUS_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForUpdateStatus(avd, opd, newStatus, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			
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
	    		//TODO ERROR HANDLING HERE... stay in the same page ?
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Status successfully updated [changed status], OK ");
	    		//put domain objects
	    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
	    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
	    	}
		}
		return successView;
	}
	
	/**
	 * 
	 * @param jsonNctsExportSpecificTopicContainer
	 * @param user
	 * @param avd
	 * @param opd
	 */
	private void fetchSecurityRecord(JsonSadNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer, String user, String avd, String opd){
		String method = "fetchSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL;
		String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd;   
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	JsonSadNctsExportSpecificTopicRecord sikkerhetRecord = null;
    	JsonSadNctsExportSpecificTopicRecord targetRecord = null;
    	
    	for (JsonSadNctsExportSpecificTopicRecord topicRecord : jsonNctsExportSpecificTopicContainer.getOneorder()){
    		targetRecord = topicRecord;
    	}
    	
    	//------------------------
    	//EXECUTE (Sikkerhet) here
    	//------------------------
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadNctsExportSpecificTopicContainer topicSikkerhetContainer = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
        	if(topicSikkerhetContainer!=null && topicSikkerhetContainer.getSecurityhead()!=null){
        		for (JsonSadNctsExportSpecificTopicRecord topicSikkerhetRecord : topicSikkerhetContainer.getSecurityhead()){
        			sikkerhetRecord = topicSikkerhetRecord;
        			//logger.info("A(thlosd):" + sikkerhetRecord.getThlosd());
            	}
        		//copy delta to target record
        		if(sikkerhetRecord!=null && targetRecord!=null){
	        		JavaReflectionUtil.setFields(sikkerhetRecord, targetRecord);
	        		//logger.info(method + "targetRecord(thnas):" + targetRecord.getThnas());
	        		//logger.info(method + "targetRecord(thlosd):" + targetRecord.getThlosd());
	        		Collection newList = new ArrayList();
	        		newList.add(targetRecord);
	        		jsonNctsExportSpecificTopicContainer.setOneorder(newList);
        		}
        	}
    	}
	}
	
	/**
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean existsSecurityRecord(String user, String avd, String opd){
		boolean retval = false;
		
		String method = "existsSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL;
		String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd;   
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	
    	//------------------------
    	//EXECUTE (Sikkerhet) here
    	//------------------------
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadNctsExportSpecificTopicContainer topicSikkerhetContainer = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
        	if(topicSikkerhetContainer!=null && topicSikkerhetContainer.getSecurityhead()!=null){
        		if(topicSikkerhetContainer.getSecurityhead().size()>0){
        			retval = true;
        		}
        	}
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param mode
	 * @param user
	 * @param avd
	 * @param opd
	 */
	private boolean updateSecurityRecord(JsonSadNctsExportSpecificTopicRecord recordToValidate, String mode, String user, String avd, String opd){
		boolean retval = true;
		String method = "updateSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//-------------
		//get BASE URL
        //-------------
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_URL;
		//-----------------------------------------------------
		//add URL-parameter "mode=U" (Update), (A)dd, (D)elete
		//-----------------------------------------------------
		String urlRequestParamsKeys = "user="+ user + "&mode=" + mode + "&thavd=" + avd + "&thtdn=" + opd;
		//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
		String urlRequestParamsTopicSikkerhet = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicSikkerhet;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	//--------------------
    	//EXECUTE the UPDATE 
    	//--------------------
    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		//Debug --> 
    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
    	//we must evaluate a return RPG code in order to know if the Update was OK or not
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    		
    	}else{
    		//Update successfully done!
    		logger.info("[INFO] Record SECURITY-SIKKERHET successfully updated, OK ");
    	}
    	
    	return retval;
	}
	
	/**
	 * Generates key seeds for an upcoming update (the generation of this keys creates also a new record ready to be updated)
	 * The method must be seen as STEP ONE in an upcoming update [same transaction].
	 * 
	 * @param session
	 * @param request
	 * @param user
	 * @param avd
	 * @param sign
	 * 
	 * @return 
	 */
	private JsonSadNctsExportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user, String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = new JsonSadNctsExportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL);
				
		String avdIdForCreate = avd;
		String signatureForCreate = sign;
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avdIdForCreate);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + signatureForCreate);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
		logger.info("BASE_URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new thtdn (opd) Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		logger.info("RpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### thtdn from RPG PROGRAM: " + rpgReturnResponseHandler.getThtdn());
		logger.info("### thtuid from RPG PROGRAM: " + rpgReturnResponseHandler.getThtuid());
		
		//we must complete the GUI-json thtdn
		if(rpgReturnResponseHandler.getThtdn()!=null){
			jsonNctsExportSpecificTopicRecord.setThtdn(rpgReturnResponseHandler.getThtdn().trim());
			//jsonNctsExportSpecificTopicRecord.setThtuid(rpgReturnResponseHandler.getThtuid().trim());
			//jsonNctsExportSpecificTopicRecord.setLrnNr(rpgReturnResponseHandler.getThtuid().trim());
			jsonNctsExportSpecificTopicRecord.setThavd(avdIdForCreate);
			jsonNctsExportSpecificTopicRecord.setThsg(signatureForCreate);
			
			
		}else{
			logger.info("[ERROR] No mandatory seeds (opd,avd,sg?) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonNctsExportSpecificTopicRecord = null;
		}
        
		return jsonNctsExportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsExportSpecificTopicRecord);
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
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_UPDATE);
			
		}else if(TvinnSadConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
			
		}else if(TvinnSadConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Print parameters
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrint(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param newAvd
	 * @param newSign
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);
		
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param newStatus
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForUpdateStatus(String avd, String opd, String newStatus, SystemaWebUser appUser){
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		if(newStatus != null & !"".equals(newStatus)){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + newStatus);
		}
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicContainer
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadNctsExportSpecificTopicRecord record : jsonNctsExportSpecificTopicContainer.getOneorder()){
			//Adjust dates
			this.adjustDatesOnFetch(record);
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNctsExportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		//Adjust dates
		this.adjustDatesOnFetch(record);
		
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
	private void setDomainObjectsInView(Map model, JsonSadNctsExportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		//Adjust dates
		this.adjustDatesOnFetch(record);
		
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
		errorMetaInformation.append(rpgReturnResponseHandler.getThtdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	/**
	 * 
	 * @param model
	 * @return
	 */
	private void populateHtmlDropDownsFromFile(Map model){
		//model.put(TodoConstants.RESOURCE_MODEL_KEY_LANGUAGE_LIST, this.dropDownListPopulationService.getLanguageList());
		//model.put(TodoConstants.URL_EXTERNAL_LANGUAGECODES_TARIC_CODE, new UrlISOLanguageObject());
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_EXPORT_IE = "X"; //Export
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_AVDELNINGAR_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadAvdelningContainer container = this.tvinnSadDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTvinnSadAvdelningRecord> list = new ArrayList();
			for(JsonTvinnSadAvdelningRecord record: container.getAvdelningar()){
				logger.info("Avd:" + record.getAvd());
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
		String NCTS_EXPORT_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IMPORT_IE);
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
	 * Population of codes (GUI drop-downs)
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_031_DEKLTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_V_CURRENCY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_4_TRANSPORTMATER, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_096_SPEC_OMST, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT, null, null);
		/*
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_302_STATUS_KODER, null, null);
		*/
	}
	
	/**
	 * 
	 * @param record
	 */
	private void adjustDatesOnFetch(JsonSadNctsExportSpecificTopicRecord record){
		String dateThtrdtNO = null;
		String dateThddtNO = null;
		String dateThdtaNO = null;
		
		if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
			dateThtrdtNO = this.dateFormatter.convertToDate_NO(record.getThtrdt());
			record.setThtrdt(dateThtrdtNO);
		}
		if(record.getThddt()!=null && !"".equals(record.getThddt())){
			dateThddtNO = this.dateFormatter.convertToDate_NO(record.getThddt());
			record.setThddt(dateThddtNO);
		}
		if(record.getThdta()!=null && !"".equals(record.getThdta())){
			//logger.info("A");
			dateThdtaNO = this.dateFormatter.convertToDate_NO(record.getThdta());
			record.setThdta(dateThdtaNO);
		}
		
	}
	/**
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request,JsonSadNctsExportSpecificTopicRecord record){
		String dateThtrdtISO = null;
		String dateThddtISO = null;
		String dateThdtaISO = null;
		
		if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
			dateThtrdtISO = this.dateFormatter.convertToDate_ISO(record.getThtrdt());
			record.setThtrdt(dateThtrdtISO);
		}
		if(record.getThddt()!=null && !"".equals(record.getThddt())){
			dateThddtISO = this.dateFormatter.convertToDate_ISO(record.getThddt());
			record.setThddt(dateThddtISO);
		}
		if(record.getThdta()!=null && !"".equals(record.getThdta())){
			//logger.info("A");
			dateThdtaISO = this.dateFormatter.convertToDate_ISO(record.getThdta());
			record.setThdta(dateThdtaISO);
		}
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("sadNctsExportSpecificTopicService")
	private SadNctsExportSpecificTopicService sadNctsExportSpecificTopicService;
	@Autowired
	@Required
	public void setSadNctsExportSpecificTopicService (SadNctsExportSpecificTopicService value){ this.sadNctsExportSpecificTopicService = value; }
	public SadNctsExportSpecificTopicService getSadNctsExportSpecificTopicService(){ return this.sadNctsExportSpecificTopicService; }
	
	
	@Qualifier ("sadNctsExportDropDownListPopulationService")
	private SadNctsExportDropDownListPopulationService sadNctsExportDropDownListPopulationService;
	@Autowired
	public void setSadNctsExportDropDownListPopulationService (SadNctsExportDropDownListPopulationService value){ this.sadNctsExportDropDownListPopulationService=value; }
	public SadNctsExportDropDownListPopulationService getSadNctsExportDropDownListPopulationService(){return this.sadNctsExportDropDownListPopulationService;}
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
}

