package no.systema.tvinn.sad.sadexport.controller;

import java.util.*;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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


import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicService;

import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerRecord;

import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.service.html.dropdown.SadExportDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.util.SadExportCalculator;
import no.systema.tvinn.sad.sadexport.util.manager.CodeDropDownMgr;

import no.systema.tvinn.sad.sadexport.validator.SadExportHeaderFinansOpplysningerValidator;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalRecord;

import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;


/**
 * SAD Export create items gateway
 * 
 * @author oscardelatorre
 * @date Jun 25, 2014
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadExportHeaderFinansOpplysningerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadExportHeaderFinansOpplysningerController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SadExportCalculator sadExportCalculator = new SadExportCalculator();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
		
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadexport_edit_finansopplysninger.do")
	public ModelAndView sadImportEditFinansOpplysninger(@ModelAttribute ("record") JsonSadExportTopicFinansOpplysningerRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: sadExportEditFinansOpplysninger");
		
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit_finansopplysninger");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadExportTopicFinansOpplysningerRecord jsonSadExportTopicFinansOpplysningerRecord = null;
		
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
			String omberegningFlag = request.getParameter("o2_sest");
			String omberegningDate = request.getParameter("o2_sedt");
			String omberegningType = request.getParameter("o2_semf");
			
			//this fragment gets some header fields needed for the validator
			JsonSadExportSpecificTopicRecord headerRecord = (JsonSadExportSpecificTopicRecord)session.getAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD);
			String invoiceTotalAmount = headerRecord.getSebel1();
			
			
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
			model.put("o2_sest", omberegningFlag);
			model.put("o2_sedt", omberegningDate);
			model.put("o2_semf", omberegningType);
			
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				//recordToValidate.setHeader_dkih_aart(headerRecord.getDkih_aart());
				//recordToValidate.setExtraMangdEnhet(this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate));
				
				SadExportHeaderFinansOpplysningerValidator validator = new SadExportHeaderFinansOpplysningerValidator();
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
						logger.info("UPDATE(only) FINANS ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonSadExportTopicFinansOpplysningerRecord = new JsonSadExportTopicFinansOpplysningerRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportTopicFinansOpplysningerRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on FINANS ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,sftxt) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSadExportTopicFinansOpplysningerRecord  = this.createNewItemKeySeeds(recordToValidate, session, request, appUser);
						if(jsonSadExportTopicFinansOpplysningerRecord!=null){
							String newLineId = jsonSadExportTopicFinansOpplysningerRecord.getSftxt();
							//take the rest from GUI.
							jsonSadExportTopicFinansOpplysningerRecord = new JsonSadExportTopicFinansOpplysningerRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportTopicFinansOpplysningerRecord);
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
						this.adjustFieldsAfterBind(request, jsonSadExportTopicFinansOpplysningerRecord);
						
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
						logger.info("[INFO] UPDATE to be done with invoiceId [sftxt]: " + jsonSadExportTopicFinansOpplysningerRecord.getSftxt());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadExportTopicFinansOpplysningerRecord.getSftxt(), avd, opd, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadExportTopicFinansOpplysningerRecord));
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
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportTopicFinansOpplysningerRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportTopicFinansOpplysningerRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("fak");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportTopicFinansOpplysningerRecord);
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL;
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
	    	logger.info(jsonPayloadFetch);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadExportTopicFinansOpplysningerContainer jsonSadExportTopicFinansOpplysningerContainer = this.sadExportSpecificTopicService.getSadExportTopicFinansOpplysningerContainer(jsonPayloadFetch);
	    	if(jsonSadExportTopicFinansOpplysningerContainer!=null){
	    		
	    		JsonSadExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
	    		jsonSadExportTopicFinansOpplysningerContainer.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vk28());
	    		jsonSadExportTopicFinansOpplysningerContainer.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_bl28());
	    		
	    		/*OBSOLETE SECTION. Has been repalced by service AS400 above: this.getInvoiceTotalFromInvoices...
	    		//Set the common currency code for all invoices (if more than one)
	    		jsonSadExportTopicFinansOpplysningerContainer.setCalculatedValidCurrency(this.sadExportCalculator.getFinalCurrency(jsonSadExportTopicFinansOpplysningerContainer));
	    		
	    		Double calculatedItemLinesTotalAmount = this.sadExportCalculator.getItemLinesTotalAmount(jsonSadExportTopicFinansOpplysningerContainer);
	    		Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.sadExportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
	    		logger.info("CalculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
	    		logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    		jsonSadExportTopicFinansOpplysningerContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    		jsonSadExportTopicFinansOpplysningerContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
				*/    
	    	}
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    		
    		//drop downs populated from a txt file
    		this.setDomainObjectsForListInView(model, jsonSadExportTopicFinansOpplysningerContainer);
			//this next step is necessary for the default values on "create new" record
    		if(bindingErrorsExist){
    			this.setDefaultDomainItemRecordInView(model, jsonSadExportTopicFinansOpplysningerContainer, recordToValidate);
    			model.put("lineId", lineId);
    			model.put("action", action);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonSadExportTopicFinansOpplysningerContainer, null);
    		}
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
	private void setDomainObjectsForListInView(Map model, JsonSadExportTopicFinansOpplysningerContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSadExportTopicFinansOpplysningerRecord record : container.getInvoicList()){
				this.adjustDatesOnFetch(record);
				list.add(record);
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
	private void setDomainObjectsInView(Map model, JsonSadExportTopicFinansOpplysningerRecord record){
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
	private void setDefaultDomainItemRecordInView(Map model, JsonSadExportTopicFinansOpplysningerContainer container, JsonSadExportTopicFinansOpplysningerRecord recordToValidate){
		List list = new ArrayList();
		JsonSadExportTopicFinansOpplysningerRecord defaultRecord = new JsonSadExportTopicFinansOpplysningerRecord();
		
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
			if(recordToValidate!=null){
				defaultRecord = recordToValidate;//in order to retain the original values before the validation errors
				model.put(TvinnSadConstants.DOMAIN_RECORD, defaultRecord);
				
			}else{
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
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadExportTopicFinansOpplysningerRecord record){
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
	private JsonSadExportTopicFinansOpplysningerRecord createNewItemKeySeeds(JsonSadExportTopicFinansOpplysningerRecord record, HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		/*String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
		*/	
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		JsonSadExportTopicFinansOpplysningerRecord jsonSadExportTopicFinansOpplysningerRecord = new JsonSadExportTopicFinansOpplysningerRecord();
		jsonSadExportTopicFinansOpplysningerRecord.setSfopdn(opd);
		jsonSadExportTopicFinansOpplysningerRecord.setSfavd(avd);
		jsonSadExportTopicFinansOpplysningerRecord.setSftxt(record.getSftxt());
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		//logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + jsonSadExportTopicFinansOpplysningerRecord.getSfavd());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + jsonSadExportTopicFinansOpplysningerRecord.getSfopdn());
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
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + jsonSadExportTopicFinansOpplysningerRecord.getSftxt());
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
			jsonSadExportTopicFinansOpplysningerRecord = null;
		}
        
		return jsonSadExportTopicFinansOpplysningerRecord;
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
	private void adjustDatesOnFetch(JsonSadExportTopicFinansOpplysningerRecord record){
		String dateSfdtNO = this.dateFormatter.convertToDate_NO(record.getSfdt());
		//fields
		record.setSfdt(dateSfdtNO);
	}
	/**
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadExportTopicFinansOpplysningerRecord record){
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
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model, JsonSadExportSpecificTopicRecord headerRecord){
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
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonSadExportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices(String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonSadExportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
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
    	logger.info(jsonPayload);
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonSadExportSpecificTopicFaktTotalContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonSadExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		return returnRecord;
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
	
	
	
	@Qualifier ("sadExportDropDownListPopulationService")
	private SadExportDropDownListPopulationService sadExportDropDownListPopulationService;
	@Autowired
	public void setSadExportDropDownListPopulationService (SadExportDropDownListPopulationService value){ this.sadExportDropDownListPopulationService=value; }
	public SadExportDropDownListPopulationService getSadImportDropDownListPopulationService(){return this.sadExportDropDownListPopulationService;}
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	@Qualifier ("sadExportSpecificTopicService")
	private SadExportSpecificTopicService sadExportSpecificTopicService;
	@Autowired
	@Required
	public void setSadExportSpecificTopicService (SadExportSpecificTopicService value){ this.sadExportSpecificTopicService = value; }
	public SadExportSpecificTopicService getSadExportSpecificTopicService(){ return this.sadExportSpecificTopicService; }
	
	
	 
}

