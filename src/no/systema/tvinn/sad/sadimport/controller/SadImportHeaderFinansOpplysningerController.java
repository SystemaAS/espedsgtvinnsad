package no.systema.tvinn.sad.sadimport.controller;

import java.util.*;


 
import org.apache.logging.log4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;

import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;

import no.systema.tvinn.sad.sadimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;

import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.sadimport.service.html.dropdown.SadImportDropDownListPopulationService;
import no.systema.tvinn.sad.sadimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadimport.util.SadImportCalculator;
import no.systema.tvinn.sad.sadimport.util.manager.CodeDropDownMgr;

import no.systema.tvinn.sad.sadimport.validator.SadImportHeaderFinansOpplysningerValidator;

import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;


/**
 * SAD Import create items gateway
 * 
 * @author oscardelatorre
 * @date Sep 24, 2014
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportHeaderFinansOpplysningerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(SadImportHeaderFinansOpplysningerController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SadImportCalculator sadImportCalculator = new SadImportCalculator();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private StringManager strMgr = new StringManager();	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_finansopplysninger.do")
	public ModelAndView sadImportEditFinansOpplysninger(@ModelAttribute ("record") JsonSadImportTopicFinansOpplysningerRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: sadImportEditFinansOpplysninger");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_finansopplysninger");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadImportTopicFinansOpplysningerRecord jsonSadImportTopicFinansOpplysningerRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_UPDATE_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			String omberegningFlag = request.getParameter("o2_sist");
			String omberegningDate = request.getParameter("o2_sidt");
			String omberegningType = request.getParameter("o2_simf");
			
			
			//this fragment gets some header fields needed for the validator
			JsonSadImportSpecificTopicRecord headerRecord = (JsonSadImportSpecificTopicRecord)session.getAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD);
			String invoiceTotalAmount = headerRecord.getSibel3();
			
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineId = request.getParameter("lineId");
			logger.info("[INFO lineId] " + lineId);
			if(lineId!=null && !"".equals(lineId)){
				//nothing
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("status", status);
			model.put("datum", datum);
			model.put("o2_sist", omberegningFlag);
			model.put("o2_sidt", omberegningDate);
			model.put("o2_simf", omberegningType);
			
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				//recordToValidate.setHeader_dkih_aart(headerRecord.getDkih_aart());
				//recordToValidate.setExtraMangdEnhet(this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate));
				
				SadImportHeaderFinansOpplysningerValidator validator = new SadImportHeaderFinansOpplysningerValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	logger.info("[INFO lineId] " + lineId);
				    	bindingErrorsExist = true;
				    	//model.put("record", recordToValidate);
				    	if(lineId!=null && !"".equals(lineId)){
				    		recordToValidate.setSfopdn(opd);
				    		recordToValidate.setSfavd(avd);
				    	}
			    }else{
			    		if(lineId!=null && !"".equals(lineId)){
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonSadImportTopicFinansOpplysningerRecord = new JsonSadImportTopicFinansOpplysningerRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadImportTopicFinansOpplysningerRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,sftxt) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSadImportTopicFinansOpplysningerRecord  = this.createNewItemKeySeeds(recordToValidate, session, request, appUser);
						if(jsonSadImportTopicFinansOpplysningerRecord!=null && strMgr.isNull(jsonSadImportTopicFinansOpplysningerRecord.getErrMsg()) ){
							String newLineId = jsonSadImportTopicFinansOpplysningerRecord.getSftxt();
							//take the rest from GUI.
							jsonSadImportTopicFinansOpplysningerRecord = new JsonSadImportTopicFinansOpplysningerRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadImportTopicFinansOpplysningerRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            logger.info("[INFO] populate the sftx:" + newLineId);
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//adjust after bind (both UPDATE or CREATE)
						this.adjustFieldsAfterBind(request, jsonSadImportTopicFinansOpplysningerRecord);
						
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
						logger.info("[INFO] UPDATE to be done with invoiceId [sftxt]: " + jsonSadImportTopicFinansOpplysningerRecord.getSftxt());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadImportTopicFinansOpplysningerRecord.getSftxt(), avd, opd, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadImportTopicFinansOpplysningerRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_UPDATE_TVINN_SAD, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_UPDATE));
				    	logger.info("URL PARAMS: " + urlRequestParams);
				    	//----------------------------------------------------------------------------
				    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
				    	//----------------------------------------------------------------------------
						String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
					    	
						//Debug --> 
				    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				    	//we must evaluate a return RPG code in order to know if the Update was OK or not
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportTopicFinansOpplysningerRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//clear record id in order to apply default logic to the next new line
				    		recordToValidate.setSftxt(null);
				    	}
					}else{
						StringBuffer errorMessage = new StringBuffer();
						//errorMessage.append("[ERROR] FATAL on CREATE: ");
						if(rpgReturnResponseHandler!=null && strMgr.isNotNull(rpgReturnResponseHandler.getErrorMessage())){
							errorMessage.append(rpgReturnResponseHandler.getErrorMessage());
						}
						if(jsonSadImportTopicFinansOpplysningerRecord!=null && strMgr.isNotNull(jsonSadImportTopicFinansOpplysningerRecord.getErrMsg())){
							errorMessage.append(jsonSadImportTopicFinansOpplysningerRecord.getErrMsg());
						}
						rpgReturnResponseHandler.setErrorMessage(errorMessage.toString());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportTopicFinansOpplysningerRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("fak");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(lineNrToDelete, avd, opd, appUser,TvinnSadConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_DELETE));
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_DELETE, urlRequestParams);
			    	
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportTopicFinansOpplysningerRecord);
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_FETCH));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + 
					" " + "(fetched list):" + jsonPayloadFetch); 
			
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadImportTopicFinansOpplysningerContainer jsonSadImportTopicFinansOpplysningerContainer = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainer(jsonPayloadFetch);
	    	if(jsonSadImportTopicFinansOpplysningerContainer!=null){
	    		
	    		JsonSadImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
	    		jsonSadImportTopicFinansOpplysningerContainer.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vk28());
	    		jsonSadImportTopicFinansOpplysningerContainer.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_bl28());
	    		
	    		
	    	}
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    		
    		//drop downs populated from a txt file
    		this.setDomainObjectsForListInView(model, jsonSadImportTopicFinansOpplysningerContainer, recordToValidate);
			//this next step is necessary for the default values on "create new" record
    		if(bindingErrorsExist || !isValidCreatedRecordTransactionOnRPG){
    			model.put("lineId", lineId);
    			model.put("action", action);
    		}
    		this.setDefaultDomainItemRecordInView(model, jsonSadImportTopicFinansOpplysningerContainer, recordToValidate, bindingErrorsExist, isValidCreatedRecordTransactionOnRPG );
	    	successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
			}
	}
	
	
	
	/**
	 * Set aspects  objects
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(Map model, JsonSadImportTopicFinansOpplysningerContainer container, JsonSadImportTopicFinansOpplysningerRecord recordToValidate){
		List list = new ArrayList();
		if(container!=null){
			int counter = 1;
			for (JsonSadImportTopicFinansOpplysningerRecord record : container.getInvoicList()){
				this.adjustDatesOnFetch(record);
				list.add(record);
				//fill in default values with first item line values since it is mostly the norm for the rest of lines (to help end-user)
				if(counter==1){
					recordToValidate.setSfdt(record.getSfdt());
					recordToValidate.setSfvk28(record.getSfvk28());
					recordToValidate.setSfkr28(record.getSfkr28());
				}
				counter++;
			}
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
		model.put(TvinnSadConstants.DOMAIN_RECORD_ITEM_CONTAINER_FINANS_OPPLYSNINGER_TOPIC, container);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadImportTopicFinansOpplysningerRecord record){
		this.adjustDatesOnFetch(record);
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * The method populates a default record (including original records before a validation error)
	 * The requirement here is to propose some predefined default values to the end-user.
	 *  
	 * @param model
	 * @param container
	 * @param recordToValidate
	 * 
	 */
	private void setDefaultDomainItemRecordInView(Map model, JsonSadImportTopicFinansOpplysningerContainer container, JsonSadImportTopicFinansOpplysningerRecord recordToValidate, boolean bindingErrorsExist, boolean isValidCreatedRecordTransactionOnRPG){
		List list = new ArrayList();
		JsonSadImportTopicFinansOpplysningerRecord defaultRecord = new JsonSadImportTopicFinansOpplysningerRecord();
		logger.info(recordToValidate.getSfvk28());
		if(container!=null){
			/*	
			int lineNr = 0;
			for (JsonSadImportTopicFinansOpplysningerRecord record : container.getOrderList()){
				lineNr++;
				if(lineNr==1){
					break;
				}
			}
			*/
			//meaning that there were validation errors
			if(bindingErrorsExist || !isValidCreatedRecordTransactionOnRPG){
				defaultRecord = recordToValidate;//in order to retain the original values before the validation errors
				model.put(TvinnSadConstants.DOMAIN_RECORD, defaultRecord);
				
			}else{
				//this means that there is at least one-item line with values to user as default values
				if(strMgr.isNull(recordToValidate.getSftxt()) && strMgr.isNotNull(recordToValidate.getSfdt())){
					defaultRecord.setSfdt(recordToValidate.getSfdt());
					defaultRecord.setSfvk28(recordToValidate.getSfvk28());
					defaultRecord.setSfkr28(recordToValidate.getSfkr28());
				}
				//
				model.put(TvinnSadConstants.DOMAIN_RECORD, defaultRecord);				
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadImportTopicFinansOpplysningerRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonSadImportTopicFinansOpplysningerRecord createNewItemKeySeeds(JsonSadImportTopicFinansOpplysningerRecord record, HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		/*String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
		*/	
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		JsonSadImportTopicFinansOpplysningerRecord jsonSadImportTopicFinansOpplysningerRecord = new JsonSadImportTopicFinansOpplysningerRecord();
		jsonSadImportTopicFinansOpplysningerRecord.setSfopdn(opd);
		jsonSadImportTopicFinansOpplysningerRecord.setSfavd(avd);
		jsonSadImportTopicFinansOpplysningerRecord.setSftxt(record.getSftxt());
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		//logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + jsonSadImportTopicFinansOpplysningerRecord.getSfavd());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + jsonSadImportTopicFinansOpplysningerRecord.getSfopdn());
		/*
		Integer numberOfItemLinesInTopic = -99;
		try{
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
		}catch(Exception e){
			//nothing
		}
		*/
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + jsonSadImportTopicFinansOpplysningerRecord.getSftxt());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
		logger.info("URL for SEED: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		//for debug purposes in GUI
		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
				
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		
		// Map the JSON response to the new seeds (syop,syli)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
		
		//we must complete the GUI-json with the value from a line nr seed here
		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
			logger.info("[ERROR] No mandatory seeds (syli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonSadImportTopicFinansOpplysningerRecord.setErrMsg(rpgReturnResponseHandler.getErrorMessage());
		}
        
		return jsonSadImportTopicFinansOpplysningerRecord;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonSadImportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices(String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonSadImportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonSadImportSpecificTopicFaktTotalContainer container = this.sadImportSpecificTopicService.getSadImportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonSadImportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	/**
	 * 
	 * @param request
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(HttpServletRequest request, String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Parameters for a creation of a "next" item line
	 * 
	 * @param lineId
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String invoiceId, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + invoiceId);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * We must adjust some fields that require it (presentation requirements)
	 * @param record
	 */
	private void adjustDatesOnFetch(JsonSadImportTopicFinansOpplysningerRecord record){
		if(record!=null){
			String dateSfdtNO = this.dateFormatter.convertToDate_NO(record.getSfdt());
			//fields
			record.setSfdt(dateSfdtNO);
		}
	}
	/**
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadImportTopicFinansOpplysningerRecord record){
		String dateSfdtISO = this.dateFormatter.convertToDate_ISO(record.getSfdt());
		String factor = request.getParameter("factor");
		//fields
		record.setSfdt(dateSfdtISO);
		record.setSfom28(factor);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 * @param headerRecord
	 * 
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model, JsonSadImportSpecificTopicRecord headerRecord){
	    	//values for map 
		Map map = new HashMap();
		/*
		Map map = new HashMap();
		map.put("dkih_25", headerRecord.getDkih_25());
		map.put("dkih_26", headerRecord.getDkih_26());
		*/
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_V_CURRENCY, null, null);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private TvinnSadTolltariffVarukodService tvinnSadTaricVarukodService;
	@Autowired
	@Required	
	public void setTvinnSadTaricVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTaricVarukodService = value;}
	public TvinnSadTolltariffVarukodService getTvinnSadTaricVarukodService(){ return this.tvinnSadTaricVarukodService; }
	
	
	
	@Qualifier ("sadImportDropDownListPopulationService")
	private SadImportDropDownListPopulationService sadImportDropDownListPopulationService;
	@Autowired
	public void setSadImportDropDownListPopulationService (SadImportDropDownListPopulationService value){ this.sadImportDropDownListPopulationService=value; }
	public SadImportDropDownListPopulationService getSadImportDropDownListPopulationService(){return this.sadImportDropDownListPopulationService;}
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	@Qualifier ("sadImportSpecificTopicService")
	private SadImportSpecificTopicService sadImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicService (SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	public SadImportSpecificTopicService getSadImportSpecificTopicService(){ return this.sadImportSpecificTopicService; }
	
	
	 
}

