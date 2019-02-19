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
import no.systema.main.util.StringManager;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicService;

import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurRecord;

import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.service.html.dropdown.SadExportDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.util.SadExportCalculator;
import no.systema.tvinn.sad.sadexport.util.manager.CodeDropDownMgr;

import no.systema.tvinn.sad.sadexport.validator.SadExportEurValidator;



/**
 * SAD Export EUR controller
 * 
 * @author oscardelatorre
 * @date Feb 2019
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadExportHeaderEurController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadExportHeaderEurController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SadExportCalculator sadExportCalculator = new SadExportCalculator();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private StringManager strMgr = new StringManager();	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	
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
	@RequestMapping(value="tvinnsadexport_edit_eur.do")
	public ModelAndView sadExportEditEur(@ModelAttribute ("record") JsonSadExportTopicEurRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: sadExportEditEur");
		
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit_eur");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadExportTopicEurRecord jsonSadExportTopicEurRecord = new JsonSadExportTopicEurRecord();
		
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
			
			boolean isValidRecordTransactionOnRPG = true;
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
				
				SadExportEurValidator validator = new SadExportEurValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	bindingErrorsExist = true;
				    	isValidRecordTransactionOnRPG = false;
			    }else{
		    		
					//-------
					//UPDATE
					//-------
					logger.info("UPDATE(only) FINANS ITEM (existent item) on process...");
					//take the rest from GUI.
					/*TODO - COVI jsonSadExportTopicFinansOpplysningerRecord = new JsonSadExportTopicFinansOpplysningerRecord();
					ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportTopicFinansOpplysningerRecord);
		            //binder.registerCustomEditor(...); // if needed
		            binder.bind(request);
		            */
		
					
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidRecordTransactionOnRPG){
						/*
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
				    		//clear record id in order to apply default logic to the next new line
				    		recordToValidate.setSftxt(null);
				    	}*/
					}else{
						/*
						StringBuffer errorMessage = new StringBuffer();
						//errorMessage.append("[ERROR] FATAL on CREATE: ");
						if(rpgReturnResponseHandler!=null && strMgr.isNotNull(rpgReturnResponseHandler.getErrorMessage())){
							errorMessage.append(rpgReturnResponseHandler.getErrorMessage());
						}
						if(jsonSadExportTopicFinansOpplysningerRecord!=null && strMgr.isNotNull(jsonSadExportTopicFinansOpplysningerRecord.getErrMsg())){
							errorMessage.append(jsonSadExportTopicFinansOpplysningerRecord.getErrMsg());
						}
						rpgReturnResponseHandler.setErrorMessage(errorMessage.toString());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportTopicFinansOpplysningerRecord);
						*/
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				/*
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
				*/
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			
			String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_EUR_URL;
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
	    	JsonSadExportTopicEurContainer jsonSadExportTopicEurContainer = this.sadExportSpecificTopicService.getSadExportSpecificTopicEur(jsonPayloadFetch);
	    	if(jsonSadExportTopicEurContainer!=null){
	    		if(jsonSadExportTopicEurContainer.getGeteur1()!=null){
	    			for(JsonSadExportTopicEurRecord record_ : jsonSadExportTopicEurContainer.getGeteur1()){
	    				jsonSadExportTopicEurRecord = record_;
	    				logger.info("Record EXISTS...");
	    			}
	    		}
	    	}
	    	
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    	//
	    	this.setDomainObjectsInView(model, jsonSadExportTopicEurRecord);
	    	
	    	/*
    		//drop downs populated from a txt file
    		this.setDomainObjectsForListInView(model, jsonSadExportTopicFinansOpplysningerContainer, recordToValidate);
			//this next step is necessary for the default values on "create new" record
    		if(bindingErrorsExist || !isValidCreatedRecordTransactionOnRPG){
    			model.put("action", action);
    		}
    		this.setDefaultDomainItemRecordInView(model, jsonSadExportTopicFinansOpplysningerContainer, recordToValidate, bindingErrorsExist, isValidCreatedRecordTransactionOnRPG );
	    	*/
	    	
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
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadExportTopicEurRecord record){
		this.adjustDatesOnFetch(record);
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadExportTopicEurRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
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
	private void adjustDatesOnFetch(JsonSadExportTopicEurRecord record){
		String dateNO = this.dateFormatter.convertToDate_NO(record.getEur12b());
		//fields
		record.setEur12b(dateNO);
	}
	/**
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadExportTopicEurRecord record){
		String dateISO = this.dateFormatter.convertToDate_ISO(record.getEur12b());
		String factor = request.getParameter("factor");
		//fields
		record.setEur12b(dateISO);
		
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
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
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

