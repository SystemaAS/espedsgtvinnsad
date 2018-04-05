package no.systema.tvinn.sad.nctsimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindingResult;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

//TVINN
import no.systema.tvinn.sad.nctsimport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicItemService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.nctsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.nctsimport.validator.SadNctsImportItemsValidator;

/**
 * TVINN-NCTS Import create items gateway
 * 
 * @author oscardelatorre
 * @date	  Mar 6, 2015
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadNctsImportItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadNctsImportItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TdsExportItemsValidator());
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
	@RequestMapping(value="tvinnsadnctsimport_edit_items.do")
	public ModelAndView nctsImportEditItem(@ModelAttribute ("record") JsonSadNctsImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: nctsImportEditItem");
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_edit_items");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSadNctsImportSpecificTopicItemRecord jsonSadNctsImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
		  
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS_IMPORT);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String mrnNr = request.getParameter("mrnNr");
			String godsNr = request.getParameter("godsNr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("lineNr");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			model.put("opd", opd);
			model.put("avd", avd);
			model.put("sign", sign);
			model.put("mrnNr", mrnNr);
			model.put("godsNr", godsNr);
			model.put("status", status);
			model.put("datum", datum);
			
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				SadNctsImportItemsValidator validator = new SadNctsImportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (manadatory), this is the only way
				//-------------------------------------------------------
				/*
				String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
				if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
					numberOfItemLinesInTopicStr = "0";
				}
				
				recordToValidate.setNumberOfItemLinesInTopicStr(numberOfItemLinesInTopicStr);
				//in order to flag an update one-line item
				if(lineNr!=null && !"".equals(lineNr)){
					if("1".equals(numberOfItemLinesInTopicStr)){
						recordToValidate.setNumberOfItemLinesInTopicStr("-99");
					}
				}*/
				validator.validate(recordToValidate, bindingResult);
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					recordToValidate.setTvli(null);
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("tvli_SESSION", lineNr);
				    		recordToValidate.setTvavd(avd);
					    	recordToValidate.setTvtdn(opd);
					    	recordToValidate.setTvli(lineNr);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("tvli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) in process...");
						//take the rest from GUI.
						jsonSadNctsImportSpecificTopicItemRecord = new JsonSadNctsImportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadNctsImportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonSadNctsImportSpecificTopicItemRecord.setTvavd(avd);
			            jsonSadNctsImportSpecificTopicItemRecord.setTvtdn(opd);
			            jsonSadNctsImportSpecificTopicItemRecord.setTvli(lineNr);
			            //always send ISO-date format
			            this.formatDates(jsonSadNctsImportSpecificTopicItemRecord);
			            //info fields
			            this.setInformationFields(jsonSadNctsImportSpecificTopicItemRecord);
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonSadNctsImportSpecificTopicItemRecord.getTvavd());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonSadNctsImportSpecificTopicItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonSadNctsImportSpecificTopicItemRecord.getTvli());
			            
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSadNctsImportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSadNctsImportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSadNctsImportSpecificTopicItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonSadNctsImportSpecificTopicItemRecord = new JsonSadNctsImportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadNctsImportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonSadNctsImportSpecificTopicItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonSadNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				            jsonSadNctsImportSpecificTopicItemRecord.setTvavd(avd);
				            //always send ISO-date format
				            this.formatDates(jsonSadNctsImportSpecificTopicItemRecord);
				            //info fields
				            this.setInformationFields(jsonSadNctsImportSpecificTopicItemRecord);
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
			            logger.info("[INFO] Valid previous step successfully processed, OK ");
			            logger.info("[INFO] Ready to move forward to do the UPDATE");
			            
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadNctsImportSpecificTopicItemRecord, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadNctsImportSpecificTopicItemRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsImportSpecificTopicItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		//---------------------------
					    		//REFRESH ON SOME VARIABLES
					    		//---------------------------
					    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
					    		//TO USE??? -->this.refreshHeaderVariables(appUser.getUser(), avd, opd);
					    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsImportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				jsonSadNctsImportSpecificTopicItemRecord = new JsonSadNctsImportSpecificTopicItemRecord();
				jsonSadNctsImportSpecificTopicItemRecord.setTvli(lineNrToDelete);
				jsonSadNctsImportSpecificTopicItemRecord.setTvavd(avd);
				jsonSadNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonSadNctsImportSpecificTopicItemRecord, appUser,TvinnSadConstants.MODE_DELETE );
				
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
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsImportSpecificTopicItemRecord);
			    		
			    	}else{
			    		//Delete successfully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    		//---------------------------
			    		//REFRESH ON SOME VARIABLES
			    		//---------------------------
			    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
			    		//TO USE? --> this.refreshHeaderVariables(appUser.getUser(), avd, opd);
			    	}
			
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()); 
			
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
		    	JsonSadNctsImportSpecificTopicItemContainer jsonNctsImportSpecificTopicItemContainer = this.sadNctsImportSpecificTopicItemService.getNctsImportSpecificTopicItemContainer(jsonPayloadFetch);
		    	
		    	//general code population
		    	this.setCodeDropDownMgr(appUser, model);
		    	this.setDomainObjectsForListInView(model, jsonNctsImportSpecificTopicItemContainer);
			
			successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
			
			return successView;
		}
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 */
	/*
	private void refreshHeaderVariables(String appUser, String avd, String opd){
		//---------------------------
		//REFRESH ON SOME VARIABLES
		//---------------------------
		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		logger.info("[INFO] REFRESH of topic ... starting now...");
		String BASE_URL_REFRESH = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL;
		String urlRequestParams = "user=" + appUser + "&avd=" + avd + "&opd=" + opd; 
		
		logger.info("URL: " + BASE_URL_REFRESH);
		logger.info("URL PARAMS: " + urlRequestParams);
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_REFRESH, urlRequestParams);
		logger.info(rpgReturnPayload);	
	}*/
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param itemContainer
	 */
	private void setDomainObjectsForListInView(Map model, JsonSadNctsImportSpecificTopicItemContainer itemContainer){
		List list = new ArrayList();
		if(itemContainer!=null){
			for (JsonSadNctsImportSpecificTopicItemRecord record : itemContainer.getOrderList()){
				list.add(record);
				logger.info(record.getTvst());
			}
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadNctsImportSpecificTopicItemRecord record){
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadNctsImportSpecificTopicItemRecord record){
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
	private JsonSadNctsImportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}else{
			numberOfItemLinesInTopicStr = numberOfItemLinesInTopicStr.trim();
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSadNctsImportSpecificTopicItemRecord jsonNctsImportSpecificTopicItemRecord = new JsonSadNctsImportSpecificTopicItemRecord();
		
		try{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			
			//-------------------------------------------------------------------------------------------
			// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
			//-------------------------------------------------------------------------------------------
			logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
			StringBuffer urlRequestParamsForSeed = new StringBuffer();
			urlRequestParamsForSeed.append("user=" + appUser.getUser());
			urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			Integer numberOfItemLinesInTopic = -99;
			
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
			
			urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
			urlRequestParamsForSeed.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
			logger.info("New SEEDs URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
					
			//Get the counter from RPG (new opd Id)
			String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
			// Map the JSON response to the new seeds (tvtdn,tvli)
			// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
			// the header fields. The RPG output should be changed in order to comply to the field specification...
			rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
			
			//we must complete the GUI-json with the value from a line nr seed here
			if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
				logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
				jsonNctsImportSpecificTopicItemRecord = null;
				
			}else{
				jsonNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				jsonNctsImportSpecificTopicItemRecord.setTvli(String.valueOf(numberOfItemLinesInTopic));
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return jsonNctsImportSpecificTopicItemRecord;
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
	 * @param numberOfItemLinesInTopic
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(JsonSadNctsImportSpecificTopicItemRecord record, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + record.getTvavd().trim());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + record.getTvtdn().trim());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + record.getTvli().trim());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode.trim());
		
		return urlRequestParamsKeys.toString();
	}
	
	
	/**
	 * 
	 * Populates all the information fields since there is only one field (text area) in the GUI
	 * 
	 * @param itemRecord
	 * 
	 */
	private void setInformationFields(JsonSadNctsImportSpecificTopicItemRecord itemRecord){
		int FIELD_LENGTH = 70;
		String tvinf = itemRecord.getTvinf();
		
		if(tvinf!=null && !"".equals(tvinf)){
			if(tvinf.trim().length()>=FIELD_LENGTH){
				itemRecord.setTvinf1(tvinf.substring(0,FIELD_LENGTH));
				itemRecord.setTvinf2(tvinf.substring(FIELD_LENGTH));
				logger.info("tvinf2:" + itemRecord.getTvinf2());
				
				if(itemRecord.getTvinf2().length()>=FIELD_LENGTH){
					//logger.info("B");
					String tmp2 = itemRecord.getTvinf2();
					itemRecord.setTvinf2(tmp2.substring(0,FIELD_LENGTH));
					itemRecord.setTvinf3(tmp2.substring(FIELD_LENGTH));
					
					if(itemRecord.getTvinf3().length()>=FIELD_LENGTH){
						//logger.info("C");
						String tmp3 = itemRecord.getTvinf3();
						itemRecord.setTvinf3(tmp3.substring(0,FIELD_LENGTH));
						itemRecord.setTvinf4(tmp3.substring(FIELD_LENGTH));
					}
				}
			}else{
				itemRecord.setTvinf1(tvinf);
			}
		}
		//clear the temp place holder.
		itemRecord.setTvinf(null);
		
		logger.debug("tvinf1:" + itemRecord.getTvinf1());
		logger.info("tvinf2:" + itemRecord.getTvinf2());
		logger.info("tvinf3:" + itemRecord.getTvinf3());
		logger.info("tvinf4:" + itemRecord.getTvinf4());
		
	}
	
	/**
	 * 
	 * @param jsonSadNctsImportSpecificTopicItemRecord
	 */
	private void formatDates(JsonSadNctsImportSpecificTopicItemRecord jsonSadNctsImportSpecificTopicItemRecord){
		//always send ISO-date format
        jsonSadNctsImportSpecificTopicItemRecord.setTvgdt(this.dateFormatter.convertToDate_ISO(jsonSadNctsImportSpecificTopicItemRecord.getTvgdt()));
        jsonSadNctsImportSpecificTopicItemRecord.setTvgodt(this.dateFormatter.convertToDate_ISO(jsonSadNctsImportSpecificTopicItemRecord.getTvgodt()));
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
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
	
	@Qualifier ("sadNctsImportSpecificTopicItemService")
	private SadNctsImportSpecificTopicItemService sadNctsImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSadNctsImportSpecificTopicItemService (SadNctsImportSpecificTopicItemService value){ this.sadNctsImportSpecificTopicItemService = value; }
	public SadNctsImportSpecificTopicItemService getSadNctsImportSpecificTopicItemService(){ return this.sadNctsImportSpecificTopicItemService; }
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService = value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	 
}

