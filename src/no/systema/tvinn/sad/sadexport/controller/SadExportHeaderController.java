package no.systema.tvinn.sad.sadexport.controller;

import java.util.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.*;
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
import no.systema.main.service.general.CurrencyRateService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicSendParametersContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicSendParametersRecord;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicService;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedContainer;
import no.systema.tvinn.sad.sadexport.model.topic.SadExportSpecificTopicTotalItemLinesObject;
import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadexport.service.html.dropdown.SadExportDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.validator.SadExportHeaderValidator;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicItemService;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.service.TvinnSadCustomerService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;


/**
 * 
 * Tvinn-Sad Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Oct 1, 2014
 * 
 */

@Controller
@Scope("session")
public class SadExportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadExportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();	
	private DecimalFormat decimalFormatter = new DecimalFormat("#.##"); 
	private StringManager strMgr = new StringManager();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private String ACTIVE_INNSTIKK_CODE = "I";
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
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
	@RequestMapping(value="tvinnsadexport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->tvinnsadexport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
	        //add gui lists here
			this.setCodeDropDownMgr(appUser, model);	
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
	    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
	    		//Mandatory fields from caller
	    		String avd = request.getParameter("avd");
	    		String sign = request.getParameter("sign");
	    		
	    		//domain
	    		JsonSadExportSpecificTopicRecord jsonSadExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
	    		//at this point we have a new record on db with only avd/sign. All 
	    		if(jsonSadExportSpecificTopicRecord!=null && strMgr.isNotNull(jsonSadExportSpecificTopicRecord.getSetdn()) ){
	    			StringBuffer redirectViewStr = new StringBuffer();
		    		redirectViewStr.append("redirect:tvinnsadexport_edit.do?action=doFetch&avd=" + jsonSadExportSpecificTopicRecord.getSeavd());
		    		redirectViewStr.append("&opd=" + jsonSadExportSpecificTopicRecord.getSetdn());
		    		redirectViewStr.append("&sysg=" + jsonSadExportSpecificTopicRecord.getSesg());
		    		//TODO redirectBfr.append("&sitll=121212&syst=&sydt=310518&o2_sist=&o2_sidt=20180612&o2_simf=2");
		    		successView = new ModelAndView(redirectViewStr.toString());
	    		}
	    		
	    		//logger.info("#######" + request.getAttribute("errorMessageOnCopyFromTransportOppdrag"));
	    		successView.addObject("model", model);
	    		successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_CREATE);
		}
		return successView;
	}
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadexport_edit.do")
	public ModelAndView doSadExportEdit(@ModelAttribute ("record") JsonSadExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit");
		String method = "doSadExportEdit [RequestMapping-->tvinnsadexport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		SadExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SadExportSpecificTopicTotalItemLinesObject();

		//---------------------------------
		//Crucial request parameters (Keys)
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sesg");
		String se0035 = request.getParameter("se0035"); //test indicator
		String innstikk = request.getParameter("semi"); //innstikk indicator
		String omberegningFlag = request.getParameter("o2_sest"); //omberegning indicator
		String omberegningDate = request.getParameter("o2_sedt"); //omberegning indicator
		String omberegningType = request.getParameter("o2_semf"); //omberegning indicator
		
		
		logger.info("TEST flag:<" + se0035 +">");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Avd:" + avd);
		logger.info("Sign:" + sign);
		logger.info("Fakturabelop (sebel1):" + recordToValidate.getSebel1());
		//logger.info("Sedt:" + sedt);
		
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//get some item lines total fields (∑)
				totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
				//get invoice totals from invoice list
				JsonSadExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
				totalItemLinesObject.setFinansOpplysningarTotValidCurrency(sumFaktTotalRecord.getTot_vk28());
				totalItemLinesObject.setFinansOpplysningarTotSum(sumFaktTotalRecord.getTot_bl28());
				totalItemLinesObject.setFinansOpplysningarTotKurs(sumFaktTotalRecord.getTot_kr28());
				
				//-------------
				//FETCH RECORD
				//-------------
				if(TvinnSadConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
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
			    		JsonSadExportSpecificTopicContainer jsonSadExportSpecificTopicContainer = this.sadExportSpecificTopicService.getSadExportSpecificTopicContainer(jsonPayload);
			    		for (JsonSadExportSpecificTopicRecord rr: jsonSadExportSpecificTopicContainer.getOneorder()){
			    			//Dekl.type (sedty), Avs.navn (senak)
			    			if(rr!=null && (strMgr.isNotNull(rr.getSedty()) && strMgr.isNotNull(rr.getSenak()) )){
			    				//Tolldekl. has been saved at least once OR has been imported from external systems (e.g. CargoWise- DHL)
			    			}else{
			    				logger.info("Fetching default values starting ...");
			    				rr = this.setDefaultValuesOnGui(appUser.getUser(), rr);
			    				Collection<JsonSadExportSpecificTopicRecord> list = new ArrayList<JsonSadExportSpecificTopicRecord>();
			    				list.add(rr);
			    				jsonSadExportSpecificTopicContainer.setOneorder(list);
			    			}
			    		}
			    		//populate gui elements
			    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			    		this.setCodeDropDownMgr(appUser, model);	
			    		this.setDomainObjectsInView(session, model, jsonSadExportSpecificTopicContainer, totalItemLinesObject, omberegningFlag, omberegningDate, omberegningType);
			    		
			    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
			    		//put the doUpdate action since we are preparing the record for an update (when saving)
			    		successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
			    		
			    	}else{
			    		logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
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
						//get invoice totals from invoice list
						sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
						totalItemLinesObject.setFinansOpplysningarTotValidCurrency(sumFaktTotalRecord.getTot_vk28());
						totalItemLinesObject.setFinansOpplysningarTotSum(sumFaktTotalRecord.getTot_bl28());
						totalItemLinesObject.setFinansOpplysningarTotKurs(sumFaktTotalRecord.getTot_kr28());
						
					}else{
						recordToValidate.setSeavd(avd);
						recordToValidate.setSesg(sign);
					}
					//Fill up tollkreditnr if applicable
					this.adjustTollkredit(appUser, recordToValidate);
					//adjust extra fields before validations
					this.adjustFieldsBeforeValidation(appUser, recordToValidate);
					
					SadExportHeaderValidator validator = new SadExportHeaderValidator(this.urlCgiProxyService, appUser.getUser(), currencyRateService);
					validator.validate(recordToValidate, bindingResult);
					//test indicator in validation field
					recordToValidate.setSe0035(se0035);
					recordToValidate.setSemi(innstikk);
					//----------------------------
				    //check for validation ERRORS
					//----------------------------
					if(bindingResult.hasErrors()){
						logger.info("[ERROR Validation] Record does not validate)");
					    //put domain objects and do go back to the original view...
						recordToValidate.setSetdn(opd);
						recordToValidate.setSeavd(avd);
						recordToValidate.setSesg(sign);
					    	this.setDomainObjectsInView(session, model, recordToValidate, totalItemLinesObject );
					    	
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = TvinnSadConstants.ACTION_CREATE;
					    	}

				    }else{
				    	JsonSadExportSpecificTopicRecord jsonSadExportSpecificTopicRecord = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction..."); 
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonSadExportSpecificTopicRecord = new JsonSadExportSpecificTopicRecord();
    						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportSpecificTopicRecord);
				            //binder.registerCustomEditor(...); // if needed
							binder.bind(request);
				            this.adjustFieldsAfterBind(request, jsonSadExportSpecificTopicRecord);
				            //test indicator
				            jsonSadExportSpecificTopicRecord.setSe0035(se0035);
				            jsonSadExportSpecificTopicRecord.setSemi(innstikk);
				            //set tollkredit
				            if(strMgr.isNotNull(recordToValidate.getSekta())){
				            	jsonSadExportSpecificTopicRecord.setSektc(recordToValidate.getSektc());
				            	jsonSadExportSpecificTopicRecord.setSekta(recordToValidate.getSekta());
				            	jsonSadExportSpecificTopicRecord.setSektb(recordToValidate.getSektb());
				            }
							
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonSadExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonSadExportSpecificTopicRecord!=null){
								opd = jsonSadExportSpecificTopicRecord.getSetdn();
								jsonSadExportSpecificTopicRecord.setSeavd(avd);
								jsonSadExportSpecificTopicRecord.setSesg(sign);
					            
								//take the rest from GUI.
								jsonSadExportSpecificTopicRecord = new JsonSadExportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
								//clean up the register date since there is some bug here
					            jsonSadExportSpecificTopicRecord.setSedt(null);

					            //adjust fields in order to comply to the back-end requirements
					            this.adjustFieldsAfterBind(request, jsonSadExportSpecificTopicRecord);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonSadExportSpecificTopicRecord.setSeavd(avd);
					            jsonSadExportSpecificTopicRecord.setSetdn(opd);
					            jsonSadExportSpecificTopicRecord.setSesg(sign);
					            //more completions
					            jsonSadExportSpecificTopicRecord.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
					            jsonSadExportSpecificTopicRecord.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
					            jsonSadExportSpecificTopicRecord.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
					            jsonSadExportSpecificTopicRecord.setSumTotalBruttoViktItemLines(totalItemLinesObject.getSumTotalBruttoViktItemLines());
					            //test indicator
					            jsonSadExportSpecificTopicRecord.setSe0035(se0035);
					            jsonSadExportSpecificTopicRecord.setSemi(innstikk);
					            //set tollkredit
					            if(strMgr.isNotNull(recordToValidate.getSekta())){
					            	jsonSadExportSpecificTopicRecord.setSektc(recordToValidate.getSektc());
					            	jsonSadExportSpecificTopicRecord.setSekta(recordToValidate.getSekta());
					            	jsonSadExportSpecificTopicRecord.setSektb(recordToValidate.getSektb());
					            }
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
							String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//Set fall-back fields if applicable. Usually deklarant-info. When deklarant = null then it will be = mottagare (receiver)
							//We check this right here though the jsonRecord (if applicable)
							//this.setDeklarantFieldsIfApplicable(jsonSkatExportSpecificTopicRecord);
							
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadExportSpecificTopicRecord));
							//put the final valid param. string
							String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
							//for debug purposes in GUI
							session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL); 
					    	
							logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
					    	logger.warn("URL PARAMS: " + urlRequestParams);
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update successfully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		//get SEND-parameters
					    		this.fetchSendParameters(appUser, jsonSadExportSpecificTopicRecord);
					    		//put domain objects
					    		this.setDomainObjectsInView(session, model, jsonSadExportSpecificTopicRecord, totalItemLinesObject);
					    		if(totalItemLinesObject.getSumOfAntalItemLines()>0 || this.ACTIVE_INNSTIKK_CODE.equals(jsonSadExportSpecificTopicRecord.getSemi())){
					    			this.adjustValidUpdateFlag(model, jsonSadExportSpecificTopicRecord);
					    		}   		
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at setdn generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportSpecificTopicRecord);
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
	 * @param jsonSadExportSpecificTopicRecord
	 * @return
	 */
	private void adjustTollkredit(SystemaWebUser appUser, JsonSadExportSpecificTopicRecord recordToValidate){
		//Conditions: Avgifter (seski)=empty, 48.kontonr.tollkredit(sekta) and a valid customer nr (seknk)
		if(strMgr.isNull(recordToValidate.getSeski()) && strMgr.isNull(recordToValidate.getSekta()) ){
			if(strMgr.isNotNull(recordToValidate.getSeknk())){
				this.getTollKredit(appUser, recordToValidate);
			}
		}
		
		
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param session
	 * @param request
	 */
	private void getTollKredit(SystemaWebUser appUser, JsonSadExportSpecificTopicRecord recordToValidate){
		logger.info("Inside getTollKredit");
		String customerNr = recordToValidate.getSeknk();
		
		Collection<JsonTvinnSadCustomerRecord> list = new ArrayList<JsonTvinnSadCustomerRecord>();
		//prepare the access CGI with RPG back-end
		if( strMgr.isNotNull(customerNr)){
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_CUSTOMER_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&knr=" + customerNr;
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			  
			if(jsonPayload!=null){
		  		JsonTvinnSadCustomerContainer container = this.tvinnSadCustomerService.getTvinnSadCustomerContainer(jsonPayload);
		  		if(container!=null){
		  			list = container.getCustomerlist();
		  			for(JsonTvinnSadCustomerRecord  record : container.getCustomerlist()){
		  				recordToValidate.setSektc(record.getWsktc());
		  				recordToValidate.setSekta(record.getWskta());
		  				recordToValidate.setSektb(record.getWsktb());
		  				logger.info("CUSTOMER: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
		  				logger.info("TOLLKREDITnr: " + recordToValidate.getSektc() + "_" + recordToValidate.getSekta() + "_" + recordToValidate.getSektb());  
		  			}
		  		}
		  	}
		}
			
	}
		
	/**
	 * 
	 * @param applicationUser
	 * @param jsonSadExportSpecificTopicRecord
	 * @return
	 */
	private JsonSadExportSpecificTopicRecord setDefaultValuesOnGui(String applicationUser, JsonSadExportSpecificTopicRecord jsonSadExportSpecificTopicRecord){
		 logger.info("FETCHING DEFAULT values for Tolldekl. " + jsonSadExportSpecificTopicRecord.getSetdn());
		 JsonSadExportSpecificTopicRecord targetRecord = null;
		 String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_AVDDATA_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + jsonSadExportSpecificTopicRecord.getSeavd();
		 logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 //logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 //logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 JsonSadExportSpecificTopicAvdDataContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicAvdDataContainer(jsonPayload);
			 if(container!=null){
				 for(JsonSadExportSpecificTopicRecord  record : container.getGetdepinf()){
					 targetRecord = record;
					 //these values should not be changed upon default-values
					 targetRecord.setSeavd(jsonSadExportSpecificTopicRecord.getSeavd());
					 targetRecord.setSetdn(jsonSadExportSpecificTopicRecord.getSetdn());
					 targetRecord.setSesg(jsonSadExportSpecificTopicRecord.getSesg());
					 targetRecord.setSedt(jsonSadExportSpecificTopicRecord.getSedt());
				 }
			 }
		 }
		 return targetRecord;
	 
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
	private void adjustValidUpdateFlag(Map model, JsonSadExportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="tvinnsadexport_send.do")
	public ModelAndView doSadExportSend(HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadexport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = TvinnSadConstants.ACTION_SEND;
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
	
	@RequestMapping(value="tvinnsadexport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSadExportEditPrintTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadexport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
		String method = "doSadExportEditPrintTopic [RequestMapping-->tvinnsadexport_edit_printTopic.do]";
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
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
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
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadexport_edit_printSkilleArkTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSadExportEditPrintSkilleArkTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadexport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
		String method = "doSadExportEditPrintSkilleArkTopic [RequestMapping-->tvinnsadexport_edit_printSkilleArkTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String archiveType = request.getParameter("selectedType");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrintSkilleArk( avd, opd, appUser, archiveType);
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

	/**
	 * Copies one topic(angivelse) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="tvinnsadexport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("tvinnsadexport");
		
		JsonSadExportTopicCopiedContainer jsonSadExportTopicCopiedContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "doCopyTopic";
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
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
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
		    		jsonSadExportTopicCopiedContainer = this.sadExportSpecificTopicService.getSadExportTopicCopiedContainer(jsonPayload);
		    		if(jsonSadExportTopicCopiedContainer!=null){
		    			//Check for errors
		    			if(jsonSadExportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonSadExportTopicCopiedContainer.getErrMsg())){
		    				logger.error("[ERROR FATAL] errMsg containing: " + jsonSadExportTopicCopiedContainer.getErrMsg());
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
			BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonSadExportTopicCopiedContainer.getNewavd(), jsonSadExportTopicCopiedContainer.getNewopd(), jsonSadExportTopicCopiedContainer.getNewsign(), appUser);
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
		    		JsonSadExportSpecificTopicContainer jsonSadExportSpecificTopicContainer = this.sadExportSpecificTopicService.getSadExportSpecificTopicContainer(jsonPayload);
		    		//add gui lists here
		    		
		    		this.setCodeDropDownMgr(appUser, model);
		    		this.setDomainObjectsInView(session, model, jsonSadExportSpecificTopicContainer);
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
	 * Copies one topic(Angivelse) to a new one, from (1) a Norsk Export or (2) Transport Uppdrag (order)
	 * STEP 1: Copy
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list (Update mode)
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadexport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST, RequestMethod.GET} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonSadExportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);

		ModelAndView successView = new ModelAndView("tvinnsadexport_edit");
		
		String method = "doFetchTopicFromTransportUppdrag";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");;
		String avd=request.getParameter("selectedAvd");
		String sign=request.getParameter("selectedSign");
		String opd=request.getParameter("selectedOpd");
		String extRefNr=request.getParameter("selectedExtRefNr"); //Domino ref in Dachser Norway AS
		//fallback in case no transport uppdrag is applicable
		ModelAndView fallbackView = new ModelAndView("redirect:tvinnsadexport_edit.do?action=doPrepareCreate&avd=" + avd + "&sign=" + sign );
				
		logger.info("Method: " + action + avd + opd);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			if( (extRefNr!=null && !"".equals(extRefNr)) || ( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))) ){
				//--------------------
				//STEP 1: COPY record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(avd, opd, extRefNr, appUser);
				//for debug purposes in GUI
				session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
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
			    		jsonContainer = this.sadExportSpecificTopicService.getSadExportTopicCopiedFromTransportUppdragContainer(jsonPayload);
			    		if(jsonContainer!=null){
			    			//Check for errors
			    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
			    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
			    				logger.info("[WARN] redirecting to error fallback jsp");
			    				//Send the error message to the redirect view.
			    				//request.setAttribute("errorMessageOnCopyFromTransportOppdrag", jsonContainer.getErrMsg());
			    				model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, jsonContainer.getErrMsg());
			    				model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, "Vid kopiering av TransportUppdrag...");
			    				fallbackView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
			    				
			    				return fallbackView;
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
				BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getSeavd(), jsonContainer.getSetdn(), jsonContainer.getSesg(), appUser);
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
			    		JsonSadExportSpecificTopicContainer jsonSadExportSpecificTopicContainer = this.sadExportSpecificTopicService.getSadExportSpecificTopicContainer(jsonPayload);
		    			//populate gui
			    		this.setCodeDropDownMgr(appUser, model);	
			    		this.setDomainObjectsInView(session, model, jsonSadExportSpecificTopicContainer);
			    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
			    		//put the doUpdate action since we are preparing the record for an update (when saving)
			    		successView.addObject(TvinnSadConstants.EDIT_ACTION_ON_TOPIC, TvinnSadConstants.ACTION_UPDATE);
			    		
			    	}else{
					logger.error("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
			}else if(strMgr.isNotNull(avd)){
					logger.warn("[INFO] Redirecting to: tvinnsadexport_edit.do?action=doPrepareCreate... to CREATE-NEW ");
					//this view is when the end user choose not to copy at all. He/She will start from scratch (empty form (header))
					return fallbackView;
			}
			
			return successView;
		}
		
	}
	
	

	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadexport_updateStatus_deleteWithNotis.do")
	public ModelAndView doUpdateStatusDeleteWithNotis(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadexport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//-------------------------
		//Request parameters
		//-------------------------
		String opd = null; 
		String avd = null;
		String newStatus = null; 
		String notisText = null; 
		String today = new DateTimeManager().getCurrentDate_ISO();
		String notisPart = "A"; //?
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.info("####################################################");
    			logger.info("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentAvd")){
    				avd = value;
    			}else if(element.startsWith("currentOpd")){
    				opd = value;
    			}else if(element.startsWith("selectedStatus")){
    				newStatus = value;
    			}else if(element.startsWith("currentText")){
    				notisText = value;
    			}
    		}
    	}
			    
			    
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_STATUS_URL;
			
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
	    		//now create a notisblock text for this (if applicable)
	    		String itemLineNumber = this.getNotisBlockNextItemLineNumber(appUser.getUser(), avd, opd) ;
	    		if(strMgr.isNotNull(notisText)){
	    			this.executeUpdateNotisBlock(appUser.getUser(), avd, opd, today, itemLineNumber, notisPart, notisText);
	    		}
	    	}
		}
		return successView;
	}
	
	@RequestMapping(value="tvinnsadexport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tvinnsadexport.do?action=doFind&sg=" + appUser.getTvinnSadSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//-------------------------
		//Request parameters
		//-------------------------
		String opd = null; //request.getParameter("currentOpd");
		String avd = null; //request.getParameter("currentAvd");
		String newStatus = null; //request.getParameter("selectedStatus");
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.info("####################################################");
    			logger.info("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentAvd")){
    				avd = value;
    			}else if(element.startsWith("currentOpd")){
    				opd = value;
    			}else if(element.startsWith("selectedStatus")){
    				newStatus = value;
    			}
    		}
    	}
			    
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_STATUS_URL;
			
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
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private String getNotisBlockNextItemLineNumber(String user, String avd, String opd){
		String retval = "0";
		
		String BASE_URL_FETCH = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_FETCH_LIST_URL;
		StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
		urlRequestParamsKeysBuffer.append("user=" + user);
		urlRequestParamsKeysBuffer.append("&avd=" + avd);
		urlRequestParamsKeysBuffer.append("&opd=" + opd);
		
		String urlRequestParamsKeys = urlRequestParamsKeysBuffer.toString();
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
    	String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
    	logger.info(jsonPayloadFetch);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayloadFetch);
    	if(jsonNotisblockContainer!=null){
    		Collection<JsonNotisblockRecord> list = new ArrayList<JsonNotisblockRecord>();
    		int lineNr = 0;
    		for(JsonNotisblockRecord notisRecord : jsonNotisblockContainer.getFreetextlist()){
    			//DEBUG 
    			logger.info("Notisblock free text:" + notisRecord.getFrtli());
    			lineNr = Integer.parseInt(notisRecord.getFrtli());
    			
    		}
    		lineNr++;
    		retval = String.valueOf(lineNr);
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param user
	 * @param avd
	 * @param opd
	 * @param notisDate
	 * @param lineNr
	 * @param notisPart
	 * @param notisText
	 */
	private void executeUpdateNotisBlock(String user, String avd, String opd, String notisDate, String lineNr, String notisPart, String notisText){
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
		StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
		urlRequestParamsKeysBuffer.append("user=" + user);
		urlRequestParamsKeysBuffer.append("&frtavd=" + avd);
		urlRequestParamsKeysBuffer.append("&frtopd=" + opd);
		urlRequestParamsKeysBuffer.append("&frtdt=" + notisDate);
		urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
		urlRequestParamsKeysBuffer.append("&mode=" + "A");
		urlRequestParamsKeysBuffer.append("&frttxt=" + notisText);
		urlRequestParamsKeysBuffer.append("&frtkod=" + notisPart);		 
		
		String urlRequestParamsKeys = urlRequestParamsKeysBuffer.toString();
		logger.info("URL: " + BASE_URL_UPDATE);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParamsKeys);
		logger.info(jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 
		JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
		logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
		if(jsonNotisblockContainer!=null){
			//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
			if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
				//Debug
				logger.info("[ERROR]:" + jsonNotisblockContainer.getErrMsg());
			}
		}
	}
	
	/**
	 * Sum of all item lines kolliantal
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	
	private SadExportSpecificTopicTotalItemLinesObject getRequiredSumsInItemLines(String avd, String opd, SystemaWebUser appUser){
		SadExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SadExportSpecificTopicTotalItemLinesObject();
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
	    	JsonSadExportSpecificTopicItemContainer jsonSadExportSpecificTopicItemContainer = this.getSadExportSpecificTopicItemService().getSadExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	//now to the real logic
	    	int antalKolli = 0;
	    	int numberOfItemLines = 0;
	    	double totalAmount = 0.00D;
	    	double totalGrossWeight=0.00D;
	    	
	    	if(jsonSadExportSpecificTopicItemContainer!=null){
		    	for(JsonSadExportSpecificTopicItemRecord record : jsonSadExportSpecificTopicItemContainer.getOrderList()){
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
		    		logger.info("svbelt:" + record.getSvbelt());
		    		if(record.getSvbelt()!=null && !"".equals(record.getSvbelt())){
		    			try{
		    				totalAmount += Double.parseDouble(record.getSvbelt().replace(",","."));
		    			}catch(Exception e){
		    				logger.info("[ERROR] on VARANS PRIS CATCH");
		    			}
		    		}
		    		
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
	    	totalItemLinesObject.setSumOfAntalKolliInItemLines(antalKolli);
	    	totalItemLinesObject.setSumOfAntalItemLines(numberOfItemLines);
	    	totalItemLinesObject.setSumTotalAmountItemLines(numberFormatter.formatBigDecimal(2,new BigDecimal(totalAmount)).doubleValue());
	    	totalItemLinesObject.setSumTotalBruttoViktItemLines(numberFormatter.formatBigDecimal(2,new BigDecimal(totalGrossWeight)).doubleValue());
	    	//totalItemLinesObject.setSumTotalAmountItemLines(numberFormatter.getDouble(numberFormatter.getString(totalAmount, 3, false, "NO")));
	    	//totalItemLinesObject.setSumTotalBruttoViktItemLines(numberFormatter.getDouble(numberFormatter.getString(totalGrossWeight, 3, false, "NO")));
	    	//DEBUG
	    	logger.info("AntalKolli: " + totalItemLinesObject.getSumOfAntalKolliInItemLines());
	    	logger.info("AntalItems: " + totalItemLinesObject.getSumOfAntalItemLines());
	    	logger.info("TotalAmountItems: " + totalItemLinesObject.getSumTotalAmountItemLines());
	    	logger.info("TotalGrossWeightItems: " + totalItemLinesObject.getSumTotalBruttoViktItemLines());
	    
	    	return totalItemLinesObject;
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
	
	private JsonSadExportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user,
																		 String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadExportSpecificTopicRecord record = new JsonSadExportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL);
				
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sesg=" + sign);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
		logger.info("URL for SEED: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		logger.info("### rpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### setdn from RPG PROGRAM: " + rpgReturnResponseHandler.getSetdn());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getSetdn()!=null && !"".equals(rpgReturnResponseHandler.getSetdn())){
			record.setSeavd(avd);
			record.setSesg(sign);
			record.setSetdn(rpgReturnResponseHandler.getSetdn().trim());
			
		}else{
			logger.info("[ERROR] No mandatory seeds (sitdn) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			record = null;
		}
		return record;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param headerRecord
	 */
	
	public void fetchSendParameters(SystemaWebUser appUser, JsonSadExportSpecificTopicRecord headerRecord){
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_SEND_PARAMS_URL;
		//-------------------
		//add URL-parameter 
		//-------------------
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&avd=" + headerRecord.getSeavd() + "&opd=" + headerRecord.getSetdn());
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParamsKeys.toString());
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		
    	if(jsonPayload!=null){
    		JsonSadExportSpecificTopicSendParametersContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicSendParametersContainer(jsonPayload);
    		if(container!=null){
    			for (JsonSadExportSpecificTopicSendParametersRecord record : container.getGetcmn()){
    				headerRecord.setSendParametersRecord(record);
    			}
    		}
    	}
	
	}
	
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadExportSpecificTopicRecord record){
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
	 * @param appUser
	 * @param type
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrintSkilleArk(String avd, String opd, SystemaWebUser appUser, String type){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "type=" + type);
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param extRefNr
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, String extRefNr, SystemaWebUser appUser){
		//user=OSCAR&avd=1&opd=53452&sign=CB&mode=GS 
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		if(opd!=null && !"".equals(opd)){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		}else if (extRefNr!=null && !"".equals(extRefNr)){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "h_xref=" + extRefNr);
		}
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sesg=" + appUser.getTvinnSadSign());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();
	}

	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsesg=" + newSign);
		
		
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
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sesg=" + sign);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
			
		}else if(TvinnSadConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_SEND);
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
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
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
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + opd);
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
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadExportSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadExportSpecificTopicRecord record : container.getOneorder()){
			
			this.adjustDatesOnFetch(record);
			if(record.getSeval1()!=null){
				//logger.info("###########seval1:" + record.getSeval1());
			}
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
		}

	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @param sumOfAntalKolliInItemLines
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadExportSpecificTopicContainer container, SadExportSpecificTopicTotalItemLinesObject totalItemLinesObject, 
			String omberegningFlag, String omberegningDate, String omberegningType){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadExportSpecificTopicRecord record : container.getOneorder()){
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
			record.setO2_sest(omberegningFlag);
			record.setO2_sedt(omberegningDate);
			record.setO2_semf(omberegningType);
			logger.info("setll:" + record.getSetll());
			logger.info("sest:" + record.getSest());
			logger.info("o2_sest:" + record.getO2_sest());
			
			
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadExportSpecificTopicRecord record, SadExportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		//SET HEADER RECORDS  (from RPG)
		record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
		record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
		record.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
		record.setSumTotalBruttoViktItemLines(totalItemLinesObject.getSumTotalBruttoViktItemLines());
		//Fakt.fields
		if(strMgr.isNotNull(totalItemLinesObject.getFinansOpplysningarTotValidCurrency())){
			record.setFinansOpplysningarTotValidCurrency(totalItemLinesObject.getFinansOpplysningarTotValidCurrency());
		}
		logger.info("#########:" + totalItemLinesObject.getFinansOpplysningarTotSum());
		if(strMgr.isNotNull(totalItemLinesObject.getFinansOpplysningarTotSum())){
			record.setFinansOpplysningarTotSum(totalItemLinesObject.getFinansOpplysningarTotSum());
		}
		if(strMgr.isNotNull(totalItemLinesObject.getFinansOpplysningarTotKurs())){
			record.setFinansOpplysningarTotKurs(totalItemLinesObject.getFinansOpplysningarTotKurs());
		}
		//Adjust dates
		this.adjustDatesOnFetch(record);
		logger.info("sekdh:" + record.getSekdh());
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadExportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
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
		errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String TYPE_IE = "E"; //Export
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
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustDatesOnFetch(JsonSadExportSpecificTopicRecord record){
		String dateSefiNO = null;
		String dateSedtgNO = null;
		String dateSedtNO = null;
		
		if(record!=null){
			if("999999".equals(record.getSefid()) || "99999999".equals(record.getSefid())){
				record.setSefid("999999");
			}else{
				dateSefiNO = this.dateFormatter.convertToDate_NO(record.getSefid());
				record.setSefid(dateSefiNO);
			}

			if(record.getSedtg()!=null){
				dateSedtgNO = this.dateFormatter.convertToDate_NO(record.getSedtg());
				record.setSedtg(dateSedtgNO);
			}

			if(record.getSedt()!=null){
				dateSedtNO = this.dateFormatter.convertToDate_NO(record.getSedt());
				record.setSedt(dateSedtNO);
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param request
	 * @param record
	 */
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSadExportSpecificTopicRecord record){
		//(1) SEKDH --> adjust the sekdh-field since it is coming as compound field (needed for a jQuery functionality in the GUI)
		String rawSekdh = record.getSekdh();
		if(rawSekdh!=null && !"".equals(rawSekdh)){
			int index = rawSekdh.indexOf("@");
			if(index>0){
				String adjustedSekdh = rawSekdh.substring(0,index);
				record.setSekdh(adjustedSekdh);
				logger.info("###########:" + record.getSekdh());
			}
		}
		String dateSefidISO = null;
		String dateSedtgISO = null;
		String dateSedtISO = null;
		
		if(record.getSefid()!=null){
			if(!"999999".equals(record.getSefid())){
				dateSefidISO = this.dateFormatter.convertToDate_ISO(record.getSefid());
				record.setSefid(dateSefidISO);
			}else{
				record.setSefid("99999999");
			}
			
		}
		if(record.getSedtg()!=null){
			dateSedtgISO = this.dateFormatter.convertToDate_ISO(record.getSedtg());
			record.setSedtg(dateSedtgISO);
		}
		if(record.getSedt()!=null && !"".equals(record.getSedt())){
			dateSedtISO = this.dateFormatter.convertToDate_ISO(record.getSedt());
			record.setSedt(dateSedtISO);
		}else{
			//this is used when there is a CREATE NEW event
			DateTimeManager dateMgr = new DateTimeManager();
			String now = dateMgr.getCurrentDate_ISO();
			record.setSedt(now);
		}
		//upper case
		if(strMgr.isNotNull(record.getSeftg2())){
			record.setSeftg2(record.getSeftg2().toUpperCase());
		}
		logger.info("sedt:" + record.getSedt());
		logger.info("seftg2" + record.getSeftg2());
		
		
		
		if(StringUtils.isNotEmpty(record.getOwn_sedp())){
			record.setSedp(record.getOwn_sedp().substring(0,2));
			record.setSedp2(record.getOwn_sedp().substring(2));
		}
		logger.warn("sedp:" + record.getSedp());
		logger.warn("sedp2:" + record.getSedp2());
		
	}
	
	/**
	 * Population of codes (GUI drop-downs)
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_9_EKSPEDISJONSTYPER_EXPORT, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_V_CURRENCY, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_4_TRANSPORTMATER, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_L_LEVERINGSBETINGELSER, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_D_LAGRINGSSTED, null, null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_HA_HAVNKODER, null, null);
			
			//drop down to print skilleark (must be Z type)
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(urlCgiProxyService, tvinnSadDropDownListPopulationService, model, appUser, "Z");
			
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
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonSadExportSpecificTopicFaktTotalContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonSadExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	/**
	 * Adjusts some fields before the validation
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	public void adjustFieldsBeforeValidation(SystemaWebUser appUser, JsonSadExportSpecificTopicRecord recordToValidate){
		
		try{
			//(1) ADJUST field: validTranspmateAndCity (Checks combination between: 25.Tranportmåte && 29.Utpass.sted).	
			String CODES_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + this.codeDropDownMgr.CODE_HA_HAVNKODER);
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			JsonTvinnSadCodeContainer codeContainer = tvinnSadDropDownListPopulationService.getCodeContainer(utfPayload);
			List<JsonTvinnSadCodeRecord> list = new ArrayList();
			//get the postal code alone
			logger.info(recordToValidate.getSekdh());
			String[] tmp = recordToValidate.getSekdh().split("@");
			String postalCode = tmp[0];
			//start the logic
			for(JsonTvinnSadCodeRecord codeRecord: codeContainer.getKodlista()){
				//find the city chosen by the end user
				if(codeRecord.getZkod().equals(postalCode)){
					logger.info(codeRecord.getZtxt2());
					//pick the list of Ztxt2 values
					if(StringUtils.isNotEmpty(codeRecord.getZtxt2())){
						logger.info(Arrays.toString(codeRecord.getZtxt2Array()));
						for (String i : codeRecord.getZtxt2Array()){
							//look for a match with Transport means field. If it matches then the combintation is not valid. Look further on into Validator
				        	if(i.equals(recordToValidate.getSetrm())){
				        		logger.info("FALSE!!!!!");
				        		recordToValidate.setValidTranspmateAndCity(false);
				        	}
				        }
					}
				}
			}
			
			//(2) ADJUST field: next field in the future
			//here ...
			if(StringUtils.isNotEmpty(recordToValidate.getOwn_sedp())){
				recordToValidate.setSedp(recordToValidate.getOwn_sedp().substring(0,2));
				recordToValidate.setSedp2(recordToValidate.getOwn_sedp().substring(2));
			}
			
		}catch (Exception e){
			logger.error(e.toString());
		}
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("currencyRateService")
	private CurrencyRateService currencyRateService;
	@Autowired
	public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  
	
	@Qualifier ("sadExportSpecificTopicItemService")
	private SadExportSpecificTopicItemService sadExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSadExportSpecificTopicItemService (SadExportSpecificTopicItemService value){ this.sadExportSpecificTopicItemService = value; }
	public SadExportSpecificTopicItemService getSadExportSpecificTopicItemService(){ return this.sadExportSpecificTopicItemService; }
	
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	
	@Qualifier ("sadExportDropDownListPopulationService")
	private SadExportDropDownListPopulationService sadExportDropDownListPopulationService;
	@Autowired
	public void setSadExportDropDownListPopulationService (SadExportDropDownListPopulationService value){ this.sadExportDropDownListPopulationService=value; }
	public SadExportDropDownListPopulationService getSadExportDropDownListPopulationService(){return this.sadExportDropDownListPopulationService;}
	
	
	@Qualifier ("sadExportSpecificTopicService")
	private SadExportSpecificTopicService sadExportSpecificTopicService;
	@Autowired
	@Required
	public void setSadExportSpecificTopicService (SadExportSpecificTopicService value){ this.sadExportSpecificTopicService = value; }
	public SadExportSpecificTopicService getSadExportSpecificTopicService(){ return this.sadExportSpecificTopicService; }
	
	@Qualifier ("notisblockService")
	private NotisblockService notisblockService;
	@Autowired
	public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	@Qualifier 
	private TvinnSadCustomerService tvinnSadCustomerService;
	@Autowired
	@Required	
	public void setTvinnSadCustomerService(TvinnSadCustomerService value){this.tvinnSadCustomerService = value;}
	public TvinnSadCustomerService getTvinnSadCustomerService(){ return this.tvinnSadCustomerService; }
	 
	
}

