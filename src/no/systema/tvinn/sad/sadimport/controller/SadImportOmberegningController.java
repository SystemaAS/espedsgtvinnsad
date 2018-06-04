package no.systema.tvinn.sad.sadimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

import no.systema.tvinn.sad.sadimport.model.topic.SadImportSpecificTopicTotalItemLinesObject;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicContainer;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;

import no.systema.tvinn.sad.sadimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.service.html.dropdown.SadImportDropDownListPopulationService;
import no.systema.tvinn.sad.sadimport.validator.SadImportOmberegningHeaderValidator;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.sadimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadimport.util.SadImportCalculator;
import no.systema.tvinn.sad.sadimport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;

/**
 * 
 * Tvinn-Sad Import Topic Omberegning Controller 
 * 
 * @author oscardelatorre
 * @date Nov 11, 2016
 * 
 */

@Controller
@Scope("session")
public class SadImportOmberegningController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadImportOmberegningController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private SadImportCalculator sadImportCalculator = new SadImportCalculator();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	private String ACTIVE_INNSTIKK_CODE = "I";
	//Omberegning
	private String OMBEREGNING_TYPE_OMB_ORIGINAL_BACKEND = "OMB";
	private String OMBEREGNING_TYPE_NYO_ORIGINAL_BACKEND = "NYO";
	private String OMBEREGNING_TYPE_NYS_LATTER_BACKEND = "NYS";
	private String OMBEREGNING_TYPE_NYA_ANGRA_BACKEND = "NYA";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * New Omberegning
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_edit_omberegning.do")
	public ModelAndView doSadImportEditOmberegning(@ModelAttribute ("record") JsonSadImportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_omberegning");
		String method = "doSadImportEditOmberegning";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		SadImportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SadImportSpecificTopicTotalItemLinesObject();
		//---------------------------------
		//Crucial request parameters (Keys)
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String si0035 = request.getParameter("si0035"); //test indicator
		String innstikk = request.getParameter("simi"); //innstikk indicator
		String omberegningFlag = request.getParameter("o2_sist"); //omberegning indicator
		String omberegningDate = request.getParameter("o2_sidt"); //omberegning indicator
		String omberegningType = request.getParameter("o2_simf"); //omberegning indicator
		String selectedOmb = request.getParameter("selectedOmb"); //omberegning indicator from User Input dialog
		
		
		logger.info("TEST flag:<" + si0035 +">");
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Avd:" + avd);
		logger.info("Sign:" + sign);
		logger.info("OmberegFlag:" + omberegningFlag);
		
		logger.info("Fakturabelop (sibel3):" + recordToValidate.getSibel3());
		
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				
				//get some item lines total fields (∑)
				totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
				//get invoice totals from invoice list
				JsonSadImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
				totalItemLinesObject.setFinansOpplysningarTotValidCurrency(sumFaktTotalRecord.getTot_vk28());
				totalItemLinesObject.setFinansOpplysningarTotSum(sumFaktTotalRecord.getTot_bl28());
				totalItemLinesObject.setFinansOpplysningarTotKurs(sumFaktTotalRecord.getTot_kr28());
				
				//-------------
				//FETCH RECORD
				//-------------
				if(TvinnSadConstants.ACTION_FETCH.equals(action)){
					//Omberegning adjust prior to fetch
					String opdOmb = opd;
					
					//(A) BRANCH for omberegning ALREADY exists
					if(omberegningDate!=null && !"".equals(omberegningDate)){
						//at this point we do know that there IS ALREADY a previous omberegning
						if(omberegningType!=null && !"".equals(omberegningType)){
							//At this point we do know the user wants to clone or simply fetch upon a dialog interaction
							if(selectedOmb != null && !"".equals(selectedOmb)){
								if(!opdOmb.contains("-")){
									opdOmb = opdOmb + "-";
								}
								logger.info("Clone omberegning... upon user interaction");
								this.cloneOpdToOmberegning(appUser.getUser(), avd, opdOmb, sign, selectedOmb);
								
							}else{
								//Add a minus sign (to indicate omberegning on service back-end will be fetch)
								if(!opdOmb.contains("-")){
									opdOmb = opdOmb + "-"; 
								}
							}
						}else{
							//Add a minus sign (to indicate omberegning on service back-end will be fetched)
							logger.info("Show omberegning BRANCH (A)...");
							if(!opdOmb.contains("-")){
								opdOmb = opdOmb + "-"; 
							}
						}
					//(B) BRANCH for ombregning DOES NOT exist
					}else{
						if(!opdOmb.contains("-")){
							opdOmb = opdOmb + "-"; 
						}
						if(!this.omberegningExists(action, avd, opdOmb, sign, appUser)){
							logger.info("Create new omberegning automatically...");
							//at this point we do know that there IS NOT a previous omberegning
							//(1) create first omberegning (will be prepared for fetch)
							selectedOmb = this.OMBEREGNING_TYPE_OMB_ORIGINAL_BACKEND;
							this.cloneOpdToOmberegning(appUser.getUser(), avd, opdOmb, sign, selectedOmb);
						}else{
							//this will be true in 1% of cases (only when there is some corrupt data - backend)
							logger.info("Show omberegning BRANCH (B)...");
						}
					}
					logger.info("opdOmb:" + opdOmb);
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opdOmb, sign, appUser);
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
			    		JsonSadImportSpecificTopicContainer jsonSadImportSpecificTopicContainer = this.sadImportSpecificTopicService.getSadImportSpecificTopicContainer(jsonPayload);
			    		
			    		//populate gui elements
			    		//add gui lists here
						this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
						this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
						this.setCodeDropDownMgr(appUser, model);	
			    		this.setDomainObjectsInView(session, model, jsonSadImportSpecificTopicContainer, totalItemLinesObject, omberegningFlag, omberegningDate, omberegningType);	
				    	
			    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
						//put the doUpdate action since we are preparing the record for an update (when saving)
						successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
						
			    	}else{
			    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
			    		return loginView;
			    	}
			    	logger.info(Calendar.getInstance().getTime() +  "END of FETCH");
					
		    	//----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				}else if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
					boolean isValidCreatedRecordTransactionOnRPG = true;
					//-----------
					//Validation
					//-----------
					logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
					//this validation alternative is only used in a pure update.
					if(opd!=null && !"".equals(opd)){
						logger.info("PURE UPDATE validation...");
						totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
						//We do fetch this number here in order to update the recordToValidate (for validation purposes) 
						recordToValidate.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
						recordToValidate.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
						recordToValidate.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
						recordToValidate.setSumTotalBruttoViktItemLines(totalItemLinesObject.getSumTotalBruttoViktItemLines());
						
					}else{
						recordToValidate.setSiavd(avd);
						recordToValidate.setSisg(sign);
					}
					SadImportOmberegningHeaderValidator validator = new SadImportOmberegningHeaderValidator();
					validator.setSystemWebUser(appUser);
					validator.setSadImportSpecificTopicService(this.sadImportSpecificTopicService);
					validator.setUrlCgiProxyService(this.urlCgiProxyService);
					//fire validation
					validator.validate(recordToValidate, bindingResult);
					//test indicator in validation field
					recordToValidate.setSi0035(si0035);
					recordToValidate.setSimi(innstikk);
					//----------------------------
				    //check for validation ERRORS
					//----------------------------
					if(bindingResult.hasErrors()){
							logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setSiavd(avd);
					    	recordToValidate.setSitdn(opd);
					    	if(recordToValidate.getSisg()==null || "".equals(recordToValidate.getSisg()) ){
					    		recordToValidate.setSisg(sign);
					    	}
					    	this.setDomainObjectsInView(session, model, recordToValidate, totalItemLinesObject, omberegningFlag, omberegningDate, omberegningType);
					    	isValidCreatedRecordTransactionOnRPG = false;
				
				    }else{
			    		JsonSadImportSpecificTopicRecord jsonSadImportSpecificTopicRecord = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction..."); 
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonSadImportSpecificTopicRecord = new JsonSadImportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadImportSpecificTopicRecord);
				            //binder.registerCustomEditor(...); // if needed
							binder.bind(request);
				            this.adjustFieldsAfterBind(request, jsonSadImportSpecificTopicRecord);
				            //test indicator
				            jsonSadImportSpecificTopicRecord.setSi0035(si0035);
				            jsonSadImportSpecificTopicRecord.setSimi(innstikk);
				            
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//Set fall-back fields if applicable. Usually deklarant-info. When deklarant = null then it will be = mottagare (receiver)
							//We check this right here though the jsonRecord (if applicable)
							/////this.setDeklarantFieldsIfApplicable(jsonSadImportSpecificTopicRecord);
							
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadImportSpecificTopicRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update successfully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		//put domain objects
					    		this.setDomainObjectsInView(session, model, jsonSadImportSpecificTopicRecord, totalItemLinesObject, omberegningFlag, omberegningDate, omberegningType );
					    		if(totalItemLinesObject.getSumOfAntalItemLines()>0 || this.ACTIVE_INNSTIKK_CODE.equals(jsonSadImportSpecificTopicRecord.getSimi())){
					    			this.adjustValidUpdateFlag(model, jsonSadImportSpecificTopicRecord);
					    		}
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tolldeklnr generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }

					//add gui lists here
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					this.setCodeDropDownMgr(appUser, model);	

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
	 * @param action
	 * @param avd
	 * @param opdOmb
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private boolean omberegningExists(String action, String avd, String opdOmb, String sign, SystemaWebUser appUser){
		boolean recordExists = false;
		
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opdOmb, sign, appUser);
		//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadImportSpecificTopicContainer jsonSadImportSpecificTopicContainer = this.sadImportSpecificTopicService.getSadImportSpecificTopicContainer(jsonPayload);
    		if(jsonSadImportSpecificTopicContainer!=null && jsonSadImportSpecificTopicContainer.getOneorder()!=null){
    			if(jsonSadImportSpecificTopicContainer.getOneorder().size()>0){
    				recordExists = true;
    			}
    		}
    	}
    	return recordExists;
	}
	/**
	 * 
	 * Aux method to prevent an end-user for sending the declaration without having saved it first.
	 * The end-user must save a topic before issuing a further "send". Sort of a validation routine to ensure validity of all fields.
	 * 
	 * @param model
	 * @param record
	 * 
	 */
	private void adjustValidUpdateFlag(Map model, JsonSadImportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param selectedOmb
	 */
	private void cloneOpdToOmberegning(String appUser, String avd, String opd, String sign,String selectedOmb){
		//clone the original
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_CREATE_OMBEREGNING_FROM_ORIG_SPECIFIC_TOPIC_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sisg=" + sign);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "omtype=" + selectedOmb );
		//Debug --> 
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParamsKeys.toString());
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		if(jsonPayload!=null){
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		}else{
			logger.debug("FATAL ERROR-- jsonPayload is NULL? ..." + jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		}
		
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="tvinnsadimport_edit_omberegning_send.do")
	public ModelAndView doSadImportSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadimport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
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
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, appUser, request);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
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
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
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
	/*
	@RequestMapping(value="tvinnsadimport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSadImportEditPrintTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadimport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
		String method = "doSadImportEditPrintTopic [RequestMapping-->tvinnsadimport_edit_printTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
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
		    	logger.info("Method PRINT END - " + method);	
		}
		
		return successView;
	}
	*/
	
	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	/*
	@RequestMapping(value="tvinnsadimport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadimport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
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
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_STATUS_URL;
			
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
	*/
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private SadImportSpecificTopicTotalItemLinesObject getRequiredSumsInItemLines(String avd, String opd, SystemaWebUser appUser){
		SadImportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SadImportSpecificTopicTotalItemLinesObject();
		
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_FETCH));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadImportSpecificTopicItemContainer jsonSadImportSpecificTopicItemContainer = this.getSadImportSpecificTopicItemService().getSadImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	//now to the real logic
	    	int antalKolli = 0;
	    	int numberOfItemLines = 0;
	    	double totalAmount = 0.00D;
	    	double totalGrossWeight=0.00D;
	    	
	    	if(jsonSadImportSpecificTopicItemContainer!=null){
		    	for(JsonSadImportSpecificTopicItemRecord record : jsonSadImportSpecificTopicItemContainer.getOrderList()){
		    		numberOfItemLines++;
		    		
		    		if(record.getWb1()!=null && !"".equals(record.getWb1())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb1());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb2()!=null && !"".equals(record.getWb2())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb2());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb3()!=null && !"".equals(record.getWb3())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb3());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb4()!=null && !"".equals(record.getWb4())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb4());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb5()!=null && !"".equals(record.getWb5())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb5());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb6()!=null && !"".equals(record.getWb6())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb6());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getWb7()!=null && !"".equals(record.getWb7())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getWb7());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		//============
		    		//Varans pris
		    		//============
		    		//logger.info("(A)####### svbelt: " + record.getSvbelt());
		    		if(record.getSvbelt()!=null && !"".equals(record.getSvbelt())){
		    			try{
		    				totalAmount += Double.parseDouble(record.getSvbelt().replace(",", "."));
		    			}catch(Exception e){
		    				logger.info("[ERROR] on VARANS PRIS CATCH");
		    			}
		    		}
		    		//logger.info("(B)####### total amount: " + totalAmount);
		    		
		    		//============
		    		//Bruttovikt
		    		//============
		    		//logger.info("(C)####### svvktb: " + record.getSvvktb());
		    		if(record.getSvvktb()!=null && !"".equals(record.getSvvktb())){
		    			try{
		    				totalGrossWeight += Double.parseDouble(record.getSvvktb().replace(",", "."));
		    			}catch(Exception e){
		    				logger.info("[ERROR] on BRUTTOVIKT CATCH");
		    			}
		    		}
		    		//logger.info("(D)####### total gross weight: " + totalGrossWeight);
		    	}
	    	}
	    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
	    	//to be used in validation...
	    	if(numberOfItemLines>0 && antalKolli==0){
	    		antalKolli = -1;
	    	}
	    	totalItemLinesObject.setSumOfAntalItemLines(numberOfItemLines);
	    	totalItemLinesObject.setSumOfAntalKolliInItemLines(antalKolli);
	    	totalItemLinesObject.setSumTotalAmountItemLines(numberFormatter.getDouble(numberFormatter.getString(totalAmount, 3, false, "NO")));
	    	totalItemLinesObject.setSumTotalBruttoViktItemLines(numberFormatter.getDouble(numberFormatter.getString(totalGrossWeight, 3, false, "NO")));
	    	
	    	
	    	//DEBUG
	    	logger.info("AntalKolli: " + totalItemLinesObject.getSumOfAntalKolliInItemLines());
	    	logger.info("AntalItems: " + totalItemLinesObject.getSumOfAntalItemLines());
	    	logger.info("TotalAmountItems: " + totalItemLinesObject.getSumTotalAmountItemLines());
	    	logger.info("TotalGrossWeightItems: " + totalItemLinesObject.getSumTotalBruttoViktItemLines());
	    
	    	return totalItemLinesObject;
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
	private String getRequestUrlKeyParametersFinansOpplysningar(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadImportSpecificTopicRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
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
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_UPDATE);
			
		}else if(TvinnSadConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sisg=" + sign);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param action
	 * @param appUser
	 * @param request
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, SystemaWebUser appUser, HttpServletRequest request){
		//logger.info("Inside getRequestUrlKeyParameters for SEND...");
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		//logger.info("OPD:" + opd + "AVD:" + avd);
		//
		String m1N07 = request.getParameter("m1N07");
		String m3039e = request.getParameter("m3039e");
		String m2005b = request.getParameter("m2005b");
		String m5004d = request.getParameter("m5004d");
		String mven = request.getParameter("mven");
		String m0035 = request.getParameter("m0035");
		String m9n01 = request.getParameter("m9n01");
		//date special
		String m2005bISO = "";
		if(m2005b!=null){
			m2005bISO = this.dateFormatter.convertToDate_ISO(m2005b);
		}
		
		if(TvinnSadConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_SEND);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m1N07=" + m1N07);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m3039e=" + m3039e);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m2005b=" + m2005bISO);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m5004d=" + m5004d);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mven=" + mven);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m0035=" + m0035);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m9n01=" + m9n01);
			
		}
		return urlRequestParamsKeys.toString();
	}

	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSadImportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		this.adjustDatesOnFetch(record);
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @param totalItemLinesObject
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadImportSpecificTopicContainer container, SadImportSpecificTopicTotalItemLinesObject totalItemLinesObject , String omberegningFlag , String omberegningDate, String omberegningType){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadImportSpecificTopicRecord record : container.getOneorder()){
			record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
			record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
			record.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
			record.setSumTotalBruttoViktItemLines(totalItemLinesObject.getSumTotalBruttoViktItemLines());
			record.setFinansOpplysningarTotValidCurrency(totalItemLinesObject.getFinansOpplysningarTotValidCurrency());
			record.setFinansOpplysningarTotSum(totalItemLinesObject.getFinansOpplysningarTotSum());
			record.setFinansOpplysningarTotKurs(totalItemLinesObject.getFinansOpplysningarTotKurs());
			//Adjust dates
			this.adjustDatesOnFetch(record);
			//Omberegning flag
			record.setO2_sist(omberegningFlag);
			record.setO2_sidt(omberegningDate);
			record.setO2_simf(omberegningType);
			
			
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
		}
	}

	/**
	 * 
	 * @param session
	 * @param model
	 * @param record
	 * @param totalItemLinesObject
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadImportSpecificTopicRecord record, SadImportSpecificTopicTotalItemLinesObject totalItemLinesObject, String omberegningFlag , String omberegningDate, String omberegningType){
		//SET HEADER RECORDS  (from RPG)
		record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
		record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
		record.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
		record.setSumTotalBruttoViktItemLines(totalItemLinesObject.getSumTotalBruttoViktItemLines());
		record.setFinansOpplysningarTotValidCurrency(totalItemLinesObject.getFinansOpplysningarTotValidCurrency());
		record.setFinansOpplysningarTotSum(totalItemLinesObject.getFinansOpplysningarTotSum());
		record.setFinansOpplysningarTotKurs(totalItemLinesObject.getFinansOpplysningarTotKurs());
		//Adjust dates
		this.adjustDatesOnFetch(record);
		//Omberegning flag
		record.setO2_sist(omberegningFlag);
		record.setO2_sidt(omberegningDate);
		record.setO2_simf(omberegningType);
		
				
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param session
	 * 
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
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
				//logger.info("AVD:" + record.getAvd());
				//logger.info("TST:" + record.getTst());
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
	 * Tollværdi fields must be adjusted (radio buttons)
	 * 
	 * @param jsonSadImportSpecificTopicRecord
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadImportSpecificTopicRecord jsonSadImportSpecificTopicRecord){
		
		String dateSifidISO = null;
		String dateSidtgISO = null;
		String dateSidtISO =null;
		
		if(jsonSadImportSpecificTopicRecord.getSifid()!=null){
			dateSifidISO = this.dateFormatter.convertToDate_ISO(jsonSadImportSpecificTopicRecord.getSifid());
			jsonSadImportSpecificTopicRecord.setSifid(dateSifidISO);
		}
		if(jsonSadImportSpecificTopicRecord.getSidtg()!=null){
			dateSidtgISO = this.dateFormatter.convertToDate_ISO(jsonSadImportSpecificTopicRecord.getSidtg());
			jsonSadImportSpecificTopicRecord.setSidtg(dateSidtgISO);
		}
		
		if(jsonSadImportSpecificTopicRecord.getSidt()!=null && !"".equals(jsonSadImportSpecificTopicRecord.getSidt())){
			dateSidtISO = this.dateFormatter.convertToDate_ISO(jsonSadImportSpecificTopicRecord.getSidt());
			jsonSadImportSpecificTopicRecord.setSidt(dateSidtISO);
		}else{
			//this is used when there is a CREATE NEW event
			DateTimeManager dateMgr = new DateTimeManager();
			String now = dateMgr.getCurrentDate_ISO();
			jsonSadImportSpecificTopicRecord.setSidt(now);
		}

		logger.info("sidt:" + jsonSadImportSpecificTopicRecord.getSidt());		
		
	}
	
	/**
	 * 
	 * @param request
	 * @param jsonSadImportSpecificTopicRecord
	 */
	private void adjustDatesOnFetch(JsonSadImportSpecificTopicRecord jsonSadImportSpecificTopicRecord){
		String dateSifiNO = null;
		String dateSidtgNO = null;
		String dateSidtNO = null;
		
		if(jsonSadImportSpecificTopicRecord!=null){
			if(jsonSadImportSpecificTopicRecord.getSifid()!=null){
				dateSifiNO = this.dateFormatter.convertToDate_NO(jsonSadImportSpecificTopicRecord.getSifid());
				jsonSadImportSpecificTopicRecord.setSifid(dateSifiNO);
			}
			
			if(jsonSadImportSpecificTopicRecord.getSidtg()!=null){
				dateSidtgNO = this.dateFormatter.convertToDate_NO(jsonSadImportSpecificTopicRecord.getSidtg());
				jsonSadImportSpecificTopicRecord.setSidtg(dateSidtgNO);
			}
			
			if(jsonSadImportSpecificTopicRecord.getSidt()!=null){
				dateSidtNO = this.dateFormatter.convertToDate_NO(jsonSadImportSpecificTopicRecord.getSidt());
				jsonSadImportSpecificTopicRecord.setSidt(dateSidtNO);
			}
			
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
					 model,appUser,CodeDropDownMgr.CODE_1_EKSPEDISJONSTYPER_IMPORT, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_4_TRANSPORTMATER, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_D_LAGRINGSSTED, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_L_LEVERINGSBETINGELSER, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_V_CURRENCY, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_O_TYPETILFELLE, null, null);
			
	}
	
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("sadImportSpecificTopicItemService")
	private SadImportSpecificTopicItemService sadImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicItemService (SadImportSpecificTopicItemService value){ this.sadImportSpecificTopicItemService = value; }
	public SadImportSpecificTopicItemService getSadImportSpecificTopicItemService(){ return this.sadImportSpecificTopicItemService; }
	
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
	@Qualifier ("sadImportDropDownListPopulationService")
	private SadImportDropDownListPopulationService sadImportDropDownListPopulationService;
	@Autowired
	public void setSadImportDropDownListPopulationService (SadImportDropDownListPopulationService value){ this.sadImportDropDownListPopulationService=value; }
	public SadImportDropDownListPopulationService getSadImportDropDownListPopulationService(){return this.sadImportDropDownListPopulationService;}
	
	
	@Qualifier ("sadImportSpecificTopicService")
	private SadImportSpecificTopicService sadImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicService (SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	public SadImportSpecificTopicService getSadImportSpecificTopicService(){ return this.sadImportSpecificTopicService; }
	
	
}

