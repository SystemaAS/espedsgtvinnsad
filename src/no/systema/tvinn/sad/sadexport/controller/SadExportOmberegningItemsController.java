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
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicItemService;
import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.service.html.dropdown.SadExportDropDownListPopulationService;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.util.SadExportCalculator;
import no.systema.tvinn.sad.sadexport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.sadexport.util.manager.SadExportItemsContainernrMgr;
import no.systema.tvinn.sad.sadexport.validator.SadExportItemsValidator;

import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;




/**
 * SAD Export items omberegning gateway
 * 
 * @author oscardelatorre
 * @date Nov 15, 2016
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadExportOmberegningItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	private static final Logger logger = Logger.getLogger(SadExportOmberegningItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SadExportCalculator sadExportCalculator = new SadExportCalculator();
	//private SkatImportTweaker skatImportTweaker = new SkatImportTweaker();
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
	@RequestMapping(value="tvinnsadexport_edit_omberegning_items.do")
	public ModelAndView sadExportEditItemOmberegning(@ModelAttribute ("record") JsonSadExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: sadExportEditItemOmberegning");
		ModelAndView successView = new ModelAndView("tvinnsadexport_edit_omberegning_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadExportSpecificTopicItemRecord jsonSadExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_EXPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_UPDATE_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE);
			
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String refnr = request.getParameter("refnr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			String startItemLineNr = request.getParameter("startItemLineNr");
			String tariffNr = request.getParameter("tariffNr");
			//Omberegning
			String omberegningFlag = request.getParameter("o2_sest"); //omberegning indicator
			String omberegningDate = request.getParameter("o2_sedt"); //omberegning indicator
			String omberegningType = request.getParameter("o2_semf"); //omberegning indicator (DEB,DRE,DSO)
			String selectedOmb = request.getParameter("selectedOmb"); //omberegning indicator from User Input dialog
			
			//new line
			String renew = request.getParameter("renew");
			if(renew!=null && !"".equals(renew)){
				//clean
				session.removeAttribute("svli_SESSION");
				session.removeAttribute("svln_SESSION");
			}
			
			
			//this fragment gets some header fields needed for the validator
			JsonSadExportSpecificTopicRecord headerRecord = (JsonSadExportSpecificTopicRecord)session.getAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD);
			String invoiceTotalAmount = headerRecord.getSebel1();
			
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
			model.put("o2_sest", omberegningFlag);
			model.put("o2_sedt", omberegningDate);
			model.put("o2_semf", omberegningType);
			model.put("selectedOmb", selectedOmb );
			
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				SadExportItemsValidator validator = new SadExportItemsValidator();
				//we must validate towards the back-end here in order to avoid injection problems in Validator
				//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search routine
				boolean isBatch = false;
				this.backEndValidationOnTolltariff(appUser, headerRecord, recordToValidate, isBatch);
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
						jsonSadExportSpecificTopicItemRecord = new JsonSadExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            jsonSadExportSpecificTopicItemRecord.setSvli(lineSvli);
			            jsonSadExportSpecificTopicItemRecord.setSvtdn(opd);
			            jsonSadExportSpecificTopicItemRecord.setSvavd(avd);
			            
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
						jsonSadExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSadExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSadExportSpecificTopicItemRecord.getSvli();
							
							//take the rest from GUI.
							jsonSadExportSpecificTopicItemRecord = new JsonSadExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            logger.info("[INFO] populate the svli:" + newLineNr);
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonSadExportSpecificTopicItemRecord.setSvli(newLineNr);
				            //jsonSkatImportSpecificTopicItemRecord.setDkiv_32(newLineNr);
				            jsonSadExportSpecificTopicItemRecord.setSvtdn(opd);
				            jsonSadExportSpecificTopicItemRecord.setSvavd(avd);
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
						
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						logger.info("[INFO] UPDATE to be done with lineNr [svli]: " + jsonSadExportSpecificTopicItemRecord.getSvli());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadExportSpecificTopicItemRecord.getSvli(), avd, opd, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadExportSpecificTopicItemRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportSpecificTopicItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		
					    		//now create the container nr if applicable (child record)
					    		if(strMgr.isNotNull(jsonSadExportSpecificTopicItemRecord.getSvcnr())){
					    			//check if already exists
					    			boolean svcnrExists = false;
					    			SadExportItemsContainernrMgr containerMgr = new SadExportItemsContainernrMgr(this.getSadExportSpecificTopicItemService(),jsonSadExportSpecificTopicItemRecord.getSvavd(), jsonSadExportSpecificTopicItemRecord.getSvtdn(), jsonSadExportSpecificTopicItemRecord.getSvli(), jsonSadExportSpecificTopicItemRecord.getSvcnr());
					    			List<JsonSadExportSpecificTopicItemContainernrRecord> tmpList = containerMgr.getContainernrList(appUser.getUser());
					    			
					    			for(JsonSadExportSpecificTopicItemContainernrRecord record : tmpList){
					    				if(jsonSadExportSpecificTopicItemRecord.getSvcnr().equals(record.getSvcnr())){
					    					//do nothing
					    					logger.info("containernr EXISTS !!!!");
					    					svcnrExists = true;
					    					break;
					    				}
					    			}
					    			if(!svcnrExists){
					    				logger.info("containernr NOT EXISTS !!!!");
					    				containerMgr.updateContainernr(appUser.getUser(), TvinnSadConstants.MODE_ADD);
					    			}
					    		}
					    		
					    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadExportSpecificTopicItemRecord);
		    		
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}else if(TvinnSadConstants.ACTION_REVERSE.equals(action)){
				logger.info("[INFO] Angre Omberegning item line list start process... ");
				String BASE_URL_ANGRE_OMB = SadExportUrlDataStore.SAD_EXPORT_BASE_ANGRE_OMB_ITEMLIST_URL;
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
			String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
					" " + "(fetched item list):" + jsonPayloadFetch); 
			
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadExportSpecificTopicItemContainer jsonSadExportSpecificTopicItemContainer = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonSadExportSpecificTopicItemContainer!=null){
			    //DEBUG-->logger.info("###########" + jsonSadExportSpecificTopicItemContainer.getW2fyl());
	    		jsonSadExportSpecificTopicItemContainer.setStartItemLineNr(startItemLineNr);
	    		jsonSadExportSpecificTopicItemContainer.setTariffNr(tariffNr);
			    Double calculatedItemLinesTotalAmount = this.sadExportCalculator.getItemLinesTotalAmount(jsonSadExportSpecificTopicItemContainer);
			    Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.sadExportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
			    logger.info("CalculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
			    logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
			    jsonSadExportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
		    	jsonSadExportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
		    	//some aspects for GUI
		    	jsonSadExportSpecificTopicItemContainer.setLastSelectedItemLineNumber(lastSelectedItemLineNumber);
	    	}
	    	
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    	//drop downs populated from a txt file
    		model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST, this.sadExportDropDownListPopulationService.getBerakningsEnheterList());
    		this.setDomainObjectsForListInView(appUser, session, model, jsonSadExportSpecificTopicItemContainer, headerRecord);
    		if(bindingErrorsExist){
    			this.setDefaultDomainItemRecordInView(model, jsonSadExportSpecificTopicItemContainer, recordToValidate);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonSadExportSpecificTopicItemContainer, null);
    		}
    		
    		successView.addObject("model",model);
    		//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
		}
	}
	
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
	/*
	private String getMandatoryMangdEnhetDirective(String applicationUser, JsonTdsImportSpecificTopicItemRecord recordToValidate){
		String retval = "N";
		
		String BASE_URL_FETCH = TdsUrlDataStore.TDS_CHECK_EXTRA_MANGDENHET;
		
		String urlRequestParamsKeys = "user="+ applicationUser + "&ie=I&kod=" + recordToValidate.getSviv_vata() + "&lk=" + recordToValidate.getSviv_ulkd();

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av mangdenhet... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//-----------------
	    	//Json and execute 
	    	//-----------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		logger.info(jsonPayload);
		JsonTdsMangdEnhetContainer container = this.tdsImportSpecificTopicItemService.getTdsMangdEnhetContainer(jsonPayload);
		for(JsonTdsMangdEnhetRecord record: container.getXtramangdenhet()){
			if(record.getXtra()!=null && record.getXtra().toUpperCase().equals("Y")){
				retval = "Y";
			}
		}
		
		return retval;
	}
	*/
	/**
	 * 
	 * @param applicationUser
	 * @param varukod
	 * @return
	 */
	private JsonTvinnSadTolltariffVarukodRecord getTolltariffVarukod(String applicationUser, String taricVarukod, String selkbCountryCode) {
		logger.info("Inside getTolltariffVarukod()");
		JsonTvinnSadTolltariffVarukodRecord retval = null;
		
		String TYPE_IE = "E";
		try{
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod + "&selkb=" + selkbCountryCode;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonTvinnSadTolltariffVarukodContainer container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonTvinnSadTolltariffVarukodRecord record : container.getTariclist()){
				  //logger.info("MATCH on VAREKOD. !!!!: " + record.getTatanr());
				  //logger.info("MATCH on VAREBESKRIV. !!!!: " + record.getBeskr1());
				  retval = record;
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
		errorMetaInformation.append(rpgReturnResponseHandler.getSvtdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * 
	 * @param appUser
	 * @param session
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(SystemaWebUser appUser, HttpSession session, Map model, JsonSadExportSpecificTopicItemContainer container, JsonSadExportSpecificTopicRecord headerRecord){
		List<JsonSadExportSpecificTopicItemRecord> list = new ArrayList<JsonSadExportSpecificTopicItemRecord>();
		if(container!=null){
			for (JsonSadExportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
			}
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
		model.put(TvinnSadConstants.DOMAIN_RECORD_ITEM_CONTAINER_TOPIC, container);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, list);
				
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadExportSpecificTopicItemRecord record){
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
	private void setDefaultDomainItemRecordInView(Map model, JsonSadExportSpecificTopicItemContainer container, JsonSadExportSpecificTopicItemRecord recordToValidate){
		List list = new ArrayList();
		JsonSadExportSpecificTopicItemRecord defaultRecord = new JsonSadExportSpecificTopicItemRecord();
		
		if(container!=null){
			int lineNr = 0;
			for (JsonSadExportSpecificTopicItemRecord record : container.getOrderList()){
				lineNr++;
				if(lineNr==1){
					//defaultRecord.setDkiv_402a(this.skatImportTweaker.setSummariskAngivelse_402a(record));
					//defaultRecord.setDkiv_403a(record.getDkiv_403a());
					//logger.info("[DEBUG _402a] " +  record.getDkiv_402a());
					//logger.info("[DEBUG _403a] " +  record.getDkiv_403a());
					break;
				}
			}
			//meaning that there were validation errors
			if(recordToValidate!=null){
				//recordToValidate.setDkiv_402a(defaultRecord.getDkiv_402a());
				//recordToValidate.setDkiv_403a(defaultRecord.getDkiv_403a());
				
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
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadExportSpecificTopicItemRecord record){
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
	private JsonSadExportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSadExportSpecificTopicItemRecord jsonSadExportSpecificTopicItemRecord = new JsonSadExportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
			Integer numberOfItemLinesInTopic = -99;
			try{
				numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
				//add one
				numberOfItemLinesInTopic++;
				logger.info("New item line nr:" + numberOfItemLinesInTopic);
			}catch(Exception e){
				//nothing
			}
			urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
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
			logger.info("[ERROR] No mandatory seeds (svli, svtdn) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonSadExportSpecificTopicItemRecord = null;
			
		}else{
			jsonSadExportSpecificTopicItemRecord.setSvtdn(rpgReturnResponseHandler.getSvtdn());
			jsonSadExportSpecificTopicItemRecord.setSvli(rpgReturnResponseHandler.getLin().trim());
			//debug
			logger.info("Record seeds (svtdn):" + jsonSadExportSpecificTopicItemRecord.getSvtdn());
			logger.info("Record seeds (svli):" + jsonSadExportSpecificTopicItemRecord.getSvli());
		}
        
		return jsonSadExportSpecificTopicItemRecord;
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
	 * @param appUser
	 * @param model
	 * @param headerRecord
	 * 
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model, JsonSadExportSpecificTopicRecord headerRecord){
	    	//values for map 
		Map map = new HashMap();

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_E_FYLKESKODERKODER, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_A_ENHETSKODER, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_C_DOK_SERTIFIKAT_KODER_EXPORT, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_FF_AVGIFTSKODER_SEKV, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param isBatch
	 */
	private void backEndValidationOnTolltariff(SystemaWebUser appUser, JsonSadExportSpecificTopicRecord headerRecord, JsonSadExportSpecificTopicItemRecord record, boolean isBatch){
		//String ITEM_NR_SUFFIX_CHARACTERS_INVALID = "???";
		//we must validate towards the back-end here in order to avoid injection problems in Validator
		//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search-taric-number routine
		JsonTvinnSadTolltariffVarukodRecord jsonTvinnSadTaricVarukodRecord = this.getTolltariffVarukod(appUser.getUser(), record.getSvvnt(), headerRecord.getSelkb());
		if(jsonTvinnSadTaricVarukodRecord!=null){
			//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
			this.setValuesOnRecordToValidate(jsonTvinnSadTaricVarukodRecord, record, isBatch);
			
		}else{
			//only with validation of a specific record (no batch)
			if(!isBatch){
				if(record.getSvvnt()!=null){
					Integer length = record.getSvvnt().length();
					if(length>=3){
						/*String tmp = record.getSvvnt().substring(0,length-3);
						//put a suffix to indicate invalid number (in a single validation use case)
						record.setSvvnt(tmp + ITEM_NR_SUFFIX_CHARACTERS_INVALID);*/
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
	 * 
	 * @param jsonTvinnSadTaricVarukodRecord
	 * @param recordToValidate
	 * @param isBatch
	 */
	private void setValuesOnRecordToValidate(JsonTvinnSadTolltariffVarukodRecord jsonTvinnSadTolltariffVarukodRecord, JsonSadExportSpecificTopicItemRecord recordToValidate, boolean isBatch){
		//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
		if( "J".equals(jsonTvinnSadTolltariffVarukodRecord.getTastk()) ){
			recordToValidate.setExtraMangdEnhet("Y");
		}else{
			recordToValidate.setExtraMangdEnhet("N");
		}
		//fiskeavgift
		if(  (jsonTvinnSadTolltariffVarukodRecord.getFfsvavt()!=null && !"".equals(jsonTvinnSadTolltariffVarukodRecord.getFfsvavt())) &&
				 (jsonTvinnSadTolltariffVarukodRecord.getFfsvavtp()!=null && !"".equals(jsonTvinnSadTolltariffVarukodRecord.getFfsvavtp())) &&	
				 (jsonTvinnSadTolltariffVarukodRecord.getFfsvavts()!=null && !"".equals(jsonTvinnSadTolltariffVarukodRecord.getFfsvavts())) ){
			
				recordToValidate.setMandatoryFiskAvgift(true);
				
				//only when Autocontrol is used...
				if(isBatch){
					recordToValidate.setSvavt(jsonTvinnSadTolltariffVarukodRecord.getFfsvavt());
					recordToValidate.setSvavtp(jsonTvinnSadTolltariffVarukodRecord.getFfsvavtp());
					recordToValidate.setSvavts(jsonTvinnSadTolltariffVarukodRecord.getFfsvavts());
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
	
	
	
	@Qualifier ("sadExportDropDownListPopulationService")
	private SadExportDropDownListPopulationService sadExportDropDownListPopulationService;
	@Autowired
	public void setSadExportDropDownListPopulationService (SadExportDropDownListPopulationService value){ this.sadExportDropDownListPopulationService=value; }
	public SadExportDropDownListPopulationService getSadExportDropDownListPopulationService(){return this.sadExportDropDownListPopulationService;}
	
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	@Qualifier ("sadExportSpecificTopicItemService")
	private SadExportSpecificTopicItemService sadExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSadExportSpecificTopicItemService (SadExportSpecificTopicItemService value){ this.sadExportSpecificTopicItemService = value; }
	public SadExportSpecificTopicItemService getSadExportSpecificTopicItemService(){ return this.sadExportSpecificTopicItemService; }
	
	
	 
}

