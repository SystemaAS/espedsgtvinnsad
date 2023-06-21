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
import no.systema.tvinn.sad.nctsexport.validator.SadNctsExportItemsValidator;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicCopiedContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment.JsonSadNcts5ExportHouseConsignmentContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment.JsonSadNcts5ExportHouseConsignmentRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemRecord;
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
 * SAD-NCTS-5 Export House Consignment Controller 
 * 
 * Db-table --> NCTSEH (Header)
 * Db-table --> NCTSEC (House Consignment)
 * Db-table --> NCTSEI (Item lines)
 * 
 * @author oscardelatorre
 * @date Maj 10, 2023
 * 
 */

@Controller
@Scope("session")
public class SadNcts5ExportHouseConsignmentController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadNcts5ExportHouseConsignmentController.class.getName());
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
	 * Creates or Updates a new House consignment
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadncts5export_edit_houseconsignment.do")
	public ModelAndView tvinnsadNcts5ExportEditHouseConsignment(@ModelAttribute ("record") JsonSadNctsExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		
		logger.info("Method: doPrepareCreate [RequestMapping-->tvinnsadncts5export_edit_houseconsignment.do]");
		ModelAndView successView = new ModelAndView("tvinnsadncts5export_edit_houseconsignment");
		String method = "doNctsExportEdit [RequestMapping-->tvinnsadncts5export_edit.do]";
		logger.info("Method: " + method);
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_NCTS5_EXPORT);
			boolean isValidCreatedRecordTransactionOnRPG = true;
			
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String tullId = request.getParameter("tullId");
			String mrnNr = request.getParameter("mrnNr");
			
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("tvli");
			
			
			
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("tullId", tullId);
			model.put("status", status);
			model.put("datum", datum);
			model.put("mrnNr", mrnNr);
			//logger.info("AA" + recordToValidate.getTvdref());
		    
			/*
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				SadNctsExportItemsValidator validator = new SadNctsExportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (mandatory), this is the only way
				//-------------------------------------------------------
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
				}
				//Check if oppdrag ref (if any) is valid
				if(!"".equals(recordToValidate.getTvtdn2())){
					recordToValidate.setValidOppdragRef(this.isValidOppdragRef(appUser, recordToValidate));
				}
				validator.validate(recordToValidate, bindingResult);
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					recordToValidate.setTvli(null);
				    	logger.info("[ERROR] Validation Item Record does not validate)");
				    	logger.info("[INFO] lineNr " + lineNr);

				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO] lineNr ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("tvli_SESSION", lineNr);
				    		recordToValidate.setTvli(lineNr);
					    	recordToValidate.setTvtdn(opd);
				    		recordToValidate.setTvavd(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("tvli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) HOUSE (existent item) in process...");
						//take the rest from GUI.
						jsonSadNctsExportSpecificTopicItemRecord = new JsonSadNctsExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadNctsExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonSadNctsExportSpecificTopicItemRecord.setTvavd(avd);
			            jsonSadNctsExportSpecificTopicItemRecord.setTvtdn(opd);
			            //put back the values of sensitive goods
			            jsonSadNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
			            jsonSadNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
			            
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonSadNctsExportSpecificTopicItemRecord.getTvli().trim());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonSadNctsExportSpecificTopicItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonSadNctsExportSpecificTopicItemRecord.getTvavd());
			            logger.info("[DEBUG] UPDATE on tvtdsk: " + jsonSadNctsExportSpecificTopicItemRecord.getTvtdsk());
			            logger.info("[DEBUG] UPDATE on tvtdo: " + jsonSadNctsExportSpecificTopicItemRecord.getTvtdo());
						
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
						jsonSadNctsExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSadNctsExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSadNctsExportSpecificTopicItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonSadNctsExportSpecificTopicItemRecord = new JsonSadNctsExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSadNctsExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonSadNctsExportSpecificTopicItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonSadNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				            jsonSadNctsExportSpecificTopicItemRecord.setTvavd(avd);
				            logger.info("[INFO] Varubeskrivning (on item record):" + jsonSadNctsExportSpecificTopicItemRecord.getTvvt());
				            logger.info("[INFO] New line number (on item record):" + jsonSadNctsExportSpecificTopicItemRecord.getTvli());
				            logger.info("[INFO] Yderligare oplys. (on item record):" + jsonSadNctsExportSpecificTopicItemRecord.getTvdty());
				            logger.info("[INFO] Doc.ref (on item record):" + jsonSadNctsExportSpecificTopicItemRecord.getTvdref());
				            //put back the values of sensitive goods
				            jsonSadNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
				            jsonSadNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
				            
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
						String BASE_URL_UPDATE = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSadNctsExportSpecificTopicItemRecord, appUser, TvinnSadConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSadNctsExportSpecificTopicItemRecord));
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
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsExportSpecificTopicItemRecord);
				    		
				    	}else{
				    		//Update successfully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//------------------------------------
				    		//Update/Create now Security-Sikkerhet record
				    		//------------------------------------
				    		//Check if there is a record minimum of information in order to go on with security record - update
				    		if(jsonSadNctsExportSpecificTopicItemRecord.getTvtkbm()!=null && !"".equals(jsonSadNctsExportSpecificTopicItemRecord.getTvtkbm())){
					    		String mode = TvinnSadConstants.MODE_ADD; //Add - default
					    		if(this.existsSecurityRecord(appUser.getUser(), avd, opd, jsonSadNctsExportSpecificTopicItemRecord.getTvli())){
					    			mode = TvinnSadConstants.MODE_UPDATE; //Update
					    		}
				    			if(!this.updateSecurityRecord(jsonSadNctsExportSpecificTopicItemRecord, mode, appUser.getUser(), avd, opd)){
					    			this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsExportSpecificTopicItemRecord);
					    		}
				    		}
				    		//---------------------------
				    		//REFRESH ON SOME VARIABLES
				    		//---------------------------
				    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
				    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TvinnSadConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				jsonSadNctsExportSpecificTopicItemRecord = new JsonSadNctsExportSpecificTopicItemRecord();
				jsonSadNctsExportSpecificTopicItemRecord.setTvli(lineNrToDelete);
				jsonSadNctsExportSpecificTopicItemRecord.setTvavd(avd);
				jsonSadNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonSadNctsExportSpecificTopicItemRecord, appUser,TvinnSadConstants.MODE_DELETE );
				
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSadNctsExportSpecificTopicItemRecord);
		    		
		    	}else{
		    		//Delete successfully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    		//---------------------------
		    		//REFRESH ON SOME VARIABLES
		    		//---------------------------
		    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
		    	}
			
			}
			*/
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SadNctsExportUrlDataStore.NCTS5_EXPORT_BASE_FETCH_TOPIC_HOUSECONFIGN_LIST_URL;
			
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " JavaServices-start timestamp");
			logger.info("FETCH av houseConsign list... ");
	     	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadNcts5ExportHouseConsignmentContainer container = this.sadNctsExportSpecificTopicService.getNcts5ExportSpecificTopicHouseConsignmentContainer(jsonPayloadFetch);
	    	//add gui lists here
    		this.setCodeDropDownMgr(appUser, model);	
    		//domain objects
	    	this.setDomainObjectsInView(model, container);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL,model);
			 
			
			
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
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadNcts5ExportHouseConsignmentRecord record){
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
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tcavd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tctdn=" + opd);
			
		}else if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tcavd=" + avd); //thavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tctdn=" + opd); //thtdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_UPDATE);
			
		}else if(TvinnSadConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tcavd=" + avd); //thavd
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tctdn=" + opd); //thtdn
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_ADD);
			
		}else if(TvinnSadConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tcavd=" + avd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TvinnSadConstants.MODE_SEND);
			
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNcts5ExportHouseConsignmentContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadNcts5ExportHouseConsignmentRecord record : container.getList()){
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSadNcts5ExportHouseConsignmentRecord record){
		//SET HEADER RECORDS  (from RPG)
		//Adjust dates
		this.adjustDatesOnFetch(record);
		
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TvinnSadConstants.DOMAIN_RECORD_TOPIC_TVINN_SAD, record);
	}
	
	private void setDomainObjectsInView(Map model, JsonSadNcts5ExportHouseConsignmentRecord record){
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		
	}
	private void setDomainObjectsInView(Map model, JsonSadNcts5ExportHouseConsignmentContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSadNcts5ExportHouseConsignmentRecord record : container.getList()){
				list.add(record);
				logger.info("tcalk: " + record.getTcalk());
				
			}
		}
		model.put(TvinnSadConstants.DOMAIN_LIST, list);
		
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
	private void adjustDatesOnFetch(JsonSadNcts5ExportHouseConsignmentRecord record){
		String dateThtrdtNO = null;
		String dateThddtNO = null;
		String dateThdtaNO = null;
		/*
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
		*/
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
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("sadNctsExportSpecificTopicService")
	private SadNctsExportSpecificTopicService sadNctsExportSpecificTopicService;
	@Autowired
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

