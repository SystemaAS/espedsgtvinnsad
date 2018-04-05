package no.systema.tvinn.sad.sadimport.controller;

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
import org.springframework.validation.BindingResult;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.sadimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.sadimport.service.html.dropdown.SadImportDropDownListPopulationService;
import no.systema.tvinn.sad.sadimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadimport.util.SadImportCalculator;
import no.systema.tvinn.sad.sadimport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.sadimport.util.manager.SadImportAvgiftsberakningenMgr;
import no.systema.tvinn.sad.sadimport.validator.SadImportItemsValidator;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;




/**
 * SAD Import items omberegning gateway
 * 
 * @author oscardelatorre
 * @date Nov 11, 2016
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportOmberegningItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadImportOmberegningItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SadImportCalculator sadImportCalculator = new SadImportCalculator();
	private SadImportAvgiftsberakningenMgr avgiftsberakningenMgr = null;
	//private SkatImportTweaker skatImportTweaker = new SkatImportTweaker();
	
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
	@RequestMapping(value="tvinnsadimport_edit_omberegning_items.do")
	public ModelAndView sadImportEditItemOmberegning(@ModelAttribute ("record") JsonSadImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: sadImportEditItemOmberegning");
		
		ModelAndView successView = new ModelAndView("tvinnsadimport_edit_omberegning_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		this.avgiftsberakningenMgr = new SadImportAvgiftsberakningenMgr(this.sadImportSpecificTopicItemService, this.urlCgiProxyService);
		
		JsonSadImportSpecificTopicItemRecord jsonSadImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_UPDATE_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String refnr = request.getParameter("refnr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//String invoiceTotalAmount = request.getParameter("fabl"); //Changed to getSibel3() from header...
			String startItemLineNr = request.getParameter("startItemLineNr");
			String tariffNr = request.getParameter("tariffNr");
			//Omberegning
			String omberegningFlag = request.getParameter("o2_sist"); //omberegning indicator
			String omberegningDate = request.getParameter("o2_sidt"); //omberegning indicator
			String omberegningType = request.getParameter("o2_simf"); //omberegning indicator (DFU,DTK,DEB, etc)
			//new line
			String renew = request.getParameter("renew");
			if(renew!=null && !"".equals(renew)){
				//clean
				session.removeAttribute("svli_SESSION");
				session.removeAttribute("svln_SESSION");
			}
			
			//this fragment gets some header fields needed for the validator
			JsonSadImportSpecificTopicRecord headerRecord = (JsonSadImportSpecificTopicRecord)session.getAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD);
			String invoiceTotalAmount = headerRecord.getSibel3();
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineSvli = request.getParameter("lineSvli");
			String lineSvln = request.getParameter("lineSvln");
			logger.info("[INFO lineSvli] " + lineSvli);
			if(lineSvli!=null && !"".equals(lineSvli)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineSvli = (String)session.getAttribute("svli_SESSION");
				lineSvln = (String)session.getAttribute("svln_SESSION");
			}
			
			//this row counter(lastSelectedItemLineNumber) is only used to add aspects/behavior to the list of rows (color, scroll(top/down)etc
			String lastSelectedItemLineNumber = request.getParameter("lastSelectedItemLineNumber");
			
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("refnr(tullid)", refnr);
			model.put("status", status);
			model.put("datum", datum);
			//Omberegning flag
			model.put("o2_sist", omberegningFlag);
			model.put("o2_sidt", omberegningDate);
			model.put("o2_simf", omberegningType);
			
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//-----------
				//Validation
				//-----------
				SadImportItemsValidator validator = new SadImportItemsValidator();
				//we must validate towards the back-end here in order to avoid injection problems in Validator
				//The validation routine for Tolltariff pinpoints those input values in which the user HAVE NOT used the search routine
				boolean isBatch = false;
				logger.info("A#############" + recordToValidate.getSvpva());
				this.backEndValidationOnTolltariff(appUser, recordToValidate, isBatch);
				//check avgifter (is not mandatory for TVINN but the user must get a warning)
				//DEBUG--> logger.info("A2#############" + recordToValidate.getSvpva());
				List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> avgList = this.avgiftsberakningenMgr.getAvgiftDataBeforeCalculation(appUser.getUser(), recordToValidate.getSvvnt(), headerRecord.getSiknk());
				if((avgList!=null && avgList.size()>0)){
					//We must check if this avgift data could be filled in automatically (single choices) or not (multiple choice = end-user must filled in, if applicable)
					if(this.avgiftsberakningenMgr.isMultipleChoiceAvgiftMatrix(avgList)){
						if(!this.avgiftsberakningenMgr.avgiftValuesExist(recordToValidate)){
							//logger.info("WARNING.. Avg. missing ????");
							recordToValidate.setMultipleChoiceAvgifter(true);
						}
					}else{
						//this is set in order to automatically set all existent single avgifter in the up-coming update without end-user intervention
						//recordToValidate.setSingleChoiceAvgifter(true);
						//this.avgiftsberakningenMgr.calculateSingleAvgifter(appUser.getUser(), avgList, headerRecord, recordToValidate);
						
					}
				}
				validator.validate(recordToValidate, bindingResult);
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineSvli] " + lineSvli);
				    	bindingErrorsExist = true;
				    	//model.put("record", recordToValidate);
				    	if(lineSvli!=null && !"".equals(lineSvli)){
				    		logger.info("[INFO lineNr] ... filling old value: lineSvli:" + lineSvli);
				    		session.setAttribute("svli_SESSION", lineSvli);
				    		session.setAttribute("svln_SESSION", lineSvln);
				    		
					    	recordToValidate.setSvtdn(opd);
				    		recordToValidate.setSvavd(avd);
				    	}
			    }else{
					if(lineSvli!=null && !"".equals(lineSvli)){
						//clean
						session.removeAttribute("svli_SESSION");
						session.removeAttribute("svln_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonSadImportSpecificTopicItemRecord = new JsonSadImportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadImportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            jsonSadImportSpecificTopicItemRecord.setSvli(lineSvli);
			            jsonSadImportSpecificTopicItemRecord.setSvtdn(opd);
			            jsonSadImportSpecificTopicItemRecord.setSvavd(avd);
			            
					}else{
						//-------
						//CREATE
						//-------
						//logger.info("STATION ONE - dkiv_42:" + recordToValidate.getDkiv_42());
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,svli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSadImportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSadImportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSadImportSpecificTopicItemRecord.getSvli();
							
							//take the rest from GUI.
							jsonSadImportSpecificTopicItemRecord = new JsonSadImportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadImportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            logger.info("[INFO] populate the svli:" + newLineNr);
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonSadImportSpecificTopicItemRecord.setSvli(newLineNr);
				            //jsonSkatImportSpecificTopicItemRecord.setDkiv_32(newLineNr);
				            jsonSadImportSpecificTopicItemRecord.setSvtdn(opd);
				            jsonSadImportSpecificTopicItemRecord.setSvavd(avd);
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						//handover Avgift records (if any)
						this.handoverAvgiftCalculatedValues(jsonSadImportSpecificTopicItemRecord, recordToValidate);
						
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						logger.info("[INFO] UPDATE to be done with svli [svli]: " + jsonSadImportSpecificTopicItemRecord.getSvli());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadImportSpecificTopicItemRecord.getSvli(), avd, opd, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadImportSpecificTopicItemRecord));
						
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
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportSpecificTopicItemRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadImportSpecificTopicItemRecord);
		    		
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}else if(TvinnSadConstants.ACTION_REVERSE.equals(action)){
				logger.info("[INFO] Angre Omberegning item line list start process... ");
				String BASE_URL_ANGRE_OMB = SadImportUrlDataStore.SAD_IMPORT_BASE_ANGRE_OMB_ITEMLIST_URL;
				StringBuffer urlRequestParamsKeysReverse = new StringBuffer();//this.getRequestUrlKeyParameters(request, avd, opd, appUser);
				urlRequestParamsKeysReverse.append("user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd + "&mode=R");
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_ANGRE_OMB));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeysReverse);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_ANGRE_OMB, urlRequestParamsKeysReverse.toString());
		    	if(jsonPayload!=null){
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	}else{
		    		logger.info("FATAL ERROR in ANGRE OMB...jsonPayload = <null> ???" + Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	}
		    	
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("BASE_URL:" + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + 
					" " + "(fetched item list):" + jsonPayloadFetch); 
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadImportSpecificTopicItemContainer jsonSadImportSpecificTopicItemContainer = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonSadImportSpecificTopicItemContainer!=null){
	    		jsonSadImportSpecificTopicItemContainer.setStartItemLineNr(startItemLineNr);
	    		jsonSadImportSpecificTopicItemContainer.setTariffNr(tariffNr);
		    	//set calculated amounts	
	    		Double calculatedItemLinesTotalAmount = this.sadImportCalculator.getItemLinesTotalAmount(jsonSadImportSpecificTopicItemContainer);
	    		Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.sadImportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
	    		logger.info("CalculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
	    		logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    		jsonSadImportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    		jsonSadImportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    		//some aspects for GUI
	    		jsonSadImportSpecificTopicItemContainer.setLastSelectedItemLineNumber(lastSelectedItemLineNumber);

	    	}
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    	//drop downs populated from a txt file
    		model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST, this.sadImportDropDownListPopulationService.getBerakningsEnheterList());
    		this.setDomainObjectsForListInView(appUser, session, model, jsonSadImportSpecificTopicItemContainer);
			
    		if(bindingErrorsExist){
    			this.setDefaultDomainItemRecordInView(model, jsonSadImportSpecificTopicItemContainer, recordToValidate);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonSadImportSpecificTopicItemContainer, null);
    		}
	    	successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
		}
	}
	/**
	 * 
	 * @param jsonSadImportSpecificTopicItemRecord
	 * @param recordToValidate
	 */
	private void handoverAvgiftCalculatedValues(JsonSadImportSpecificTopicItemRecord jsonSadImportSpecificTopicItemRecord, JsonSadImportSpecificTopicItemRecord recordToValidate){
		//1
		jsonSadImportSpecificTopicItemRecord.setWg1(recordToValidate.getWg1()); jsonSadImportSpecificTopicItemRecord.setWh1(recordToValidate.getWh1()); jsonSadImportSpecificTopicItemRecord.setWk1(recordToValidate.getWk1()); 
		jsonSadImportSpecificTopicItemRecord.setWj1(recordToValidate.getWj1()); jsonSadImportSpecificTopicItemRecord.setWi1(recordToValidate.getWi1());
		//2
		jsonSadImportSpecificTopicItemRecord.setWg2(recordToValidate.getWg2()); jsonSadImportSpecificTopicItemRecord.setWh2(recordToValidate.getWh2()); jsonSadImportSpecificTopicItemRecord.setWk2(recordToValidate.getWk2()); 
		jsonSadImportSpecificTopicItemRecord.setWj2(recordToValidate.getWj2()); jsonSadImportSpecificTopicItemRecord.setWi2(recordToValidate.getWi2());
		//3
		jsonSadImportSpecificTopicItemRecord.setWg3(recordToValidate.getWg3()); jsonSadImportSpecificTopicItemRecord.setWh3(recordToValidate.getWh3()); jsonSadImportSpecificTopicItemRecord.setWk3(recordToValidate.getWk3()); 
		jsonSadImportSpecificTopicItemRecord.setWj3(recordToValidate.getWj3()); jsonSadImportSpecificTopicItemRecord.setWi3(recordToValidate.getWi3());
		//4
		jsonSadImportSpecificTopicItemRecord.setWg4(recordToValidate.getWg4()); jsonSadImportSpecificTopicItemRecord.setWh4(recordToValidate.getWh4()); jsonSadImportSpecificTopicItemRecord.setWk4(recordToValidate.getWk4()); 
		jsonSadImportSpecificTopicItemRecord.setWj4(recordToValidate.getWj4()); jsonSadImportSpecificTopicItemRecord.setWi4(recordToValidate.getWi4());
		//5
		jsonSadImportSpecificTopicItemRecord.setWg5(recordToValidate.getWg5()); jsonSadImportSpecificTopicItemRecord.setWh5(recordToValidate.getWh5()); jsonSadImportSpecificTopicItemRecord.setWk5(recordToValidate.getWk5()); 
		jsonSadImportSpecificTopicItemRecord.setWj5(recordToValidate.getWj5()); jsonSadImportSpecificTopicItemRecord.setWi5(recordToValidate.getWi5());
		//6
		jsonSadImportSpecificTopicItemRecord.setWg6(recordToValidate.getWg6()); jsonSadImportSpecificTopicItemRecord.setWh6(recordToValidate.getWh6()); jsonSadImportSpecificTopicItemRecord.setWk6(recordToValidate.getWk6()); 
		jsonSadImportSpecificTopicItemRecord.setWj6(recordToValidate.getWj6()); jsonSadImportSpecificTopicItemRecord.setWi6(recordToValidate.getWi6());
		//7
		jsonSadImportSpecificTopicItemRecord.setWg7(recordToValidate.getWg7()); jsonSadImportSpecificTopicItemRecord.setWh7(recordToValidate.getWh7()); jsonSadImportSpecificTopicItemRecord.setWk7(recordToValidate.getWk7()); 
		jsonSadImportSpecificTopicItemRecord.setWj7(recordToValidate.getWj7()); jsonSadImportSpecificTopicItemRecord.setWi7(recordToValidate.getWi7());
		//8
		jsonSadImportSpecificTopicItemRecord.setWg8(recordToValidate.getWg8()); jsonSadImportSpecificTopicItemRecord.setWh8(recordToValidate.getWh8()); jsonSadImportSpecificTopicItemRecord.setWk8(recordToValidate.getWk8()); 
		jsonSadImportSpecificTopicItemRecord.setWj8(recordToValidate.getWj8()); jsonSadImportSpecificTopicItemRecord.setWi8(recordToValidate.getWi8());		
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param taricVarukod
	 * @return
	 */
	private JsonTvinnSadTolltariffVarukodRecord getTolltariffVarukod(String applicationUser, String taricVarukod) {
		//logger.info("Inside getTaricVarukod()");
		JsonTvinnSadTolltariffVarukodRecord retval = null;
		
		String TYPE_IE = "I";
		try{
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		  JsonTvinnSadTolltariffVarukodContainer container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  if(container.getTariclist()!=null){
				  for(JsonTvinnSadTolltariffVarukodRecord record : container.getTariclist()){
					  if( taricVarukod.equals(record.getTatanr()) ){
						  //logger.info("MATCH on VAREKOD. !!!!: " + record.getTatanr());
						  //logger.info("MATCH on VAREBESKRIV. !!!!: " + record.getBeskr1());
					  }	  
					  retval = record;
				  }	
			  }
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	  
		return retval;
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
	 * 
	 * @param appUser
	 * @param session
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(SystemaWebUser appUser, HttpSession session, Map model, JsonSadImportSpecificTopicItemContainer container){
		List<JsonSadImportSpecificTopicItemRecord> list = new ArrayList<JsonSadImportSpecificTopicItemRecord>();
		if(container!=null){
			for (JsonSadImportSpecificTopicItemRecord record : container.getOrderList()){
				//STEP (1)
				//boolean isBatch = true;
				//this.backEndValidationOnTolltariff(appUser, record, isBatch);
				list.add(record);
			}
			
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
		model.put(TvinnSadConstants.DOMAIN_RECORD_ITEM_CONTAINER_TOPIC, container);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, list);
				
	}
	/**
	 * Taric back-end validation
	 * 
	 * @param appUser
	 * @param record
	 * @param isBatch
	 * 
	 */
	private void backEndValidationOnTolltariff(SystemaWebUser appUser, JsonSadImportSpecificTopicItemRecord record, boolean isBatch){
		
		//we must validate towards the back-end here in order to avoid injection problems in Validator
		//The validation routine for Tolltarif Varukod pinpoints those input values in which the user HAVE NOT used the search-taric-number routine
		JsonTvinnSadTolltariffVarukodRecord jsonTvinnSadTaricVarukodRecord = this.getTolltariffVarukod(appUser.getUser(), record.getSvvnt());
		if(jsonTvinnSadTaricVarukodRecord!=null){
			//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
			this.setValuesOnRecordToValidate(jsonTvinnSadTaricVarukodRecord, record, isBatch);
		}else{
			//only with validation of a specific record (no batch)
			if(!isBatch){
				if(record.getSvvnt()!=null){
					Integer length = record.getSvvnt().length();
					if(length>=3){
						/*
						String tmp = record.getSvvnt().substring(0,length-3);
						//put a suffix to indicate invalid number (in a single validation use case)
						record.setSvvnt(tmp + ITEM_NR_SUFFIX_CHARACTERS_INVALID);
						*/
						record.setSvvntValid(false);
					}else{
						//put a suffix to indicate invalid number (in a single validation use case)
						//record.setSvvnt(record.getSvvnt() + ITEM_NR_SUFFIX_CHARACTERS_INVALID);
						record.setSvvntValid(false);
					}
					
				}
			}else{
				record.setValidTolltariff(false);
			}
		}
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadImportSpecificTopicItemRecord record){
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
	private void setDefaultDomainItemRecordInView(Map model, JsonSadImportSpecificTopicItemContainer container, JsonSadImportSpecificTopicItemRecord recordToValidate){
		List list = new ArrayList();
		JsonSadImportSpecificTopicItemRecord defaultRecord = new JsonSadImportSpecificTopicItemRecord();
		
		if(container!=null){
			int lineNr = 0;
			for (JsonSadImportSpecificTopicItemRecord record : container.getOrderList()){
				lineNr++;
				if(lineNr==1){
					/*
					defaultRecord.setDkiv_402a(this.skatImportTweaker.setSummariskAngivelse_402a(record));
					defaultRecord.setDkiv_403a(record.getDkiv_403a());
					*/
					//logger.info("[DEBUG _402a] " +  record.getDkiv_402a());
					//logger.info("[DEBUG _403a] " +  record.getDkiv_403a());
					break;
				}
			}
			//meaning that there were validation errors
			if(recordToValidate!=null){
				/*
				recordToValidate.setDkiv_402a(defaultRecord.getDkiv_402a());
				recordToValidate.setDkiv_403a(defaultRecord.getDkiv_403a());
				*/
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
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadImportSpecificTopicItemRecord record){
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
	private JsonSadImportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSadImportSpecificTopicItemRecord jsonSadImportSpecificTopicItemRecord = new JsonSadImportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		/*DEPRECATED. AS400 deals with new item line no.
			Integer numberOfItemLinesInTopic = -99;
			try{
				numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
				//add one
				numberOfItemLinesInTopic++;
				logger.info("New item line nr: " + numberOfItemLinesInTopic);
			}catch(Exception e){
				//nothing
			}
			//urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
		 */
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
		logger.info("URL for SEED: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		//for debug purposes in GUI
		session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
				
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		//logger.info("Rpg-responseHandler: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,syli)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
		
		//we must complete the GUI-json with the value from a line nr seed here
		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
			logger.info("[ERROR] No mandatory seeds (svli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonSadImportSpecificTopicItemRecord = null;
			
		}else{
			jsonSadImportSpecificTopicItemRecord.setSvtdn((rpgReturnResponseHandler.getOpd()));
			jsonSadImportSpecificTopicItemRecord.setSvli((rpgReturnResponseHandler.getLin().trim()));
			//debug
			logger.info("Record seeds (svtdn): " + jsonSadImportSpecificTopicItemRecord.getSvtdn());
			logger.info("Record seeds (svli): " + jsonSadImportSpecificTopicItemRecord.getSvli());
		}
        
		return jsonSadImportSpecificTopicItemRecord;
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
		String startItemLineNr = request.getParameter("startItemLineNr");
		String tariffNr = request.getParameter("tariffNr");
		logger.info("startItemLineNr:" + startItemLineNr);
		logger.info("tariffNr:" + tariffNr);
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		//This parameter is used in order to set a start item line in a given interval.
		if(startItemLineNr!=null && !"".equals(startItemLineNr)){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + startItemLineNr);
		}
		//This parameter is used in order to get an item line with a given tariff nr (varekod)
		if(tariffNr!=null && !"".equals(tariffNr)){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "varenr=" + tariffNr);
		}
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Parameters for a creation of a "next" item line
	 * 
	 * @param lineNumber
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String lineNumber, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNumber);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param dkiv_42
	 * @param appUser
	 * @param request
	 * @return
	 */
	private String getRequestUrlKeyParametersForTollvaerdiTransportCosts(String dkiv_42, SystemaWebUser appUser, HttpServletRequest request ){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		//Toldværdideklaration fields necessary only after the create-new line (not on successive updates)
		String session_dkih_12 = request.getParameter("session_dkih_12");
		String session_dkih_12e = request.getParameter("session_dkih_12e");
		String session_dkih_222 = request.getParameter("session_dkih_222");

		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkiv_42=" + dkiv_42);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_12=" + session_dkih_12);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_12e=" + session_dkih_12e);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_222=" + session_dkih_222);
		
		return urlRequestParamsKeys.toString();
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
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_8_AVGIFTSKODER, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_8B_AVGIFTSKODER_SEKV, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_A_ENHETSKODER, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_B_DOK_SERTIFIKAT_KODER_IMPORT, null, null);
		
	}
	
	/**
	 * Fill in complementary values before we validate.
	 * 
	 * @param jsonTvinnSadTaricVarukodRecord
	 * @param recordToValidate
	 */
	private void setValuesOnRecordToValidate(JsonTvinnSadTolltariffVarukodRecord jsonTvinnSadTaricVarukodRecord, JsonSadImportSpecificTopicItemRecord recordToValidate, boolean isBatch){
		//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
		if( "J".equals(jsonTvinnSadTaricVarukodRecord.getTastk()) ||  "A".equals(recordToValidate.getSvpva()) ){
			recordToValidate.setExtraMangdEnhet("Y");
		}else{
			recordToValidate.setExtraMangdEnhet("N");
		}
		//help the user with some values
		if("".equals(recordToValidate.getSvpva())){
			//only with autokontroll (batch)
			if(isBatch){
				recordToValidate.setSvpva(jsonTvinnSadTaricVarukodRecord.getTaordk());
			}else{
				//Nothing: will be caught in validator for all manual updates. The field is mandatory
			}
		}
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier 
	private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	@Autowired
	@Required	
	public void setTvinnSadTolltariffVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTolltariffVarukodService = value;}
	public TvinnSadTolltariffVarukodService getTvinnSadTolltariffVarukodService(){ return this.tvinnSadTolltariffVarukodService; }
	
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
	
	@Qualifier ("sadImportSpecificTopicItemService")
	private SadImportSpecificTopicItemService sadImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicItemService (SadImportSpecificTopicItemService value){ this.sadImportSpecificTopicItemService = value; }
	public SadImportSpecificTopicItemService getSadImportSpecificTopicItemService(){ return this.sadImportSpecificTopicItemService; }
	
	
	 
}

