package no.systema.tvinn.sad.manifest.express.controller;

import java.util.*;
import java.util.regex.*;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;


import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRpgContainer;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.manifest.express.util.TvinnSadManifestConstants;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.express.validator.TvinnSadManifestHeaderValidator;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;

/**
 * Sad Manifest Header Controller
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */

@Controller
public class TvinnSadManifestHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = LoggerFactory.getLogger(TvinnSadManifestHeaderController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private LoginValidator loginValidator = new LoginValidator();
	private StringManager strMgr = new StringManager();
	private DateTimeManager dateMgr = new DateTimeManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private final String MODE_UPDATE_MANIFEST_STATUS = "UMS";
	private final String MODE_UPDATE_INTERNAL_STATUS = "US";
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	
	/**
	 * Update the manifest status (SUBMITTED, DELETED, REOPENED/DRAFT) - COMPLETED is done in toll.no not here
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_updateManifestStatus.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestUpdateManifestStatus(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.warn("Inside: doManifestUpdateManifestStatus");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = "redirect:tvinnsadmanifest_edit.do?action=doFetch&user=" + appUser.getUser() + "&efuuid=" + recordToValidate.getEfuuid();
		ModelAndView successView = new ModelAndView(redirect);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//Execute
			StringBuffer errMsg = new StringBuffer();
			int dmlRetval = 0;
			if(StringUtils.isNotEmpty(recordToValidate.getEfuuid()) ){
				dmlRetval = this.updateStatus(appUser.getUser(), this.MODE_UPDATE_MANIFEST_STATUS, recordToValidate, errMsg);
			}
			//Result
			if(dmlRetval<0){
				model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
				successView.addObject(model);
				logger.error("ERROR!!!!!!!!!!ERROR!!!!!!!!!!!!!ERROR!:" + errMsg.toString());
			}	
		}
		return successView;
	}	
	
	/**
	 * Updates the internal status (SYSPED) M,blank,P,Z,etc
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_updateInternalStatus.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestUpdateInternalStatus(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.warn("Inside: doManifestUpdateInternalStatus");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String uuid = null;
		String status = null;
		String sign = null;
		String redirect = null;
		
		if(StringUtils.isEmpty(recordToValidate.getEfuuid())){
			Enumeration requestParameters = request.getParameterNames();
		    while (requestParameters.hasMoreElements()) {
		        String element = (String) requestParameters.nextElement();
		        String value = request.getParameter(element);
		        if (element != null && value != null) {
	        		logger.warn("####################################################");
	    			logger.warn("param Name : " + element + " value: " + value);
	    			if(element.startsWith("currentUuid")){
	    				uuid = value;
	    			}else if(element.startsWith("currentSign")){
	    				sign = value;
	    			}else if(element.startsWith("selectedStatus")){
	    				status = value;
	    			}
	    			
	    		}
	    	}
		    //we are overriding here in case the call came from a list (usually "Kanseller")
		    recordToValidate.setEfuuid(uuid);
		    recordToValidate.setEfst(status);
		    redirect = "redirect:tvinnsadmanifest.do?action=doFind&sign=" + sign;
		}else{
			redirect = "redirect:tvinnsadmanifest_edit.do?action=doFetch&user=" + appUser.getUser() + "&efuuid=" + recordToValidate.getEfuuid();
		}
		ModelAndView successView = new ModelAndView(redirect);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//Execute
			StringBuffer errMsg = new StringBuffer();
			int dmlRetval = 0;
			if(StringUtils.isNotEmpty(recordToValidate.getEfuuid())){
				dmlRetval = this.updateStatus(appUser.getUser(), this.MODE_UPDATE_INTERNAL_STATUS, recordToValidate, errMsg);
			}
			//Result
			if(dmlRetval<0){
				model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
				successView.addObject(model);
				logger.error("ERROR!!!!!!!!!!ERROR!!!!!!!!!!!!!ERROR!:" + errMsg.toString());
			}	
		}
		return successView;
	}	
	
	/**
	 * Sends the manifest to a queue
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_send.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestSend(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.warn("Inside: doManifestSend");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = "redirect:tvinnsadmanifest_edit.do?action=doFetch&user=" + appUser.getUser() + "&efuuid=" + recordToValidate.getEfuuid();
		ModelAndView successView = new ModelAndView(redirect);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			StringBuffer errMsg = new StringBuffer();
			int dmlRetval = 0;
			if(StringUtils.isNotEmpty(recordToValidate.getEfuuid()) ){
				dmlRetval = this.updateStatus(appUser.getUser(), this.MODE_UPDATE_MANIFEST_STATUS, recordToValidate, errMsg);
			}
			
			
			//execute RPG last
			if(StringUtils.isNotEmpty(recordToValidate.getEfpro())){
				JsonTvinnSadManifestRpgContainer rpgContainer = this.executeRpgSADEFJSONW(appUser, recordToValidate.getEfpro());
				if(rpgContainer!=null){
					//check for errors
					if(StringUtils.isNotEmpty(rpgContainer.getErrMsg()) || StringUtils.isNotEmpty(rpgContainer.getErrMsgT()) ){
						String errorMessage = "SERVER_ERROR:" + rpgContainer.getErrMsg() + rpgContainer.getErrMsgT();
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errorMessage);
						logger.error(errorMessage);
					}
				}
			}
			
			
		}
		
		return successView;
	}	  
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestEdit(@ModelAttribute ("record") JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_edit");
		logger.warn("Inside: doManifestEdit");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("actionU");
		logger.warn("actionU:" + action);
		logger.warn("uuid:" + recordToValidate.getEfuuid());
		boolean isValidRecord = true;
		
		if(appUser==null){
			return loginView;
		
		}else{
			logger.warn("ASAVD:" + appUser.getAsavd());
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//validator
				TvinnSadManifestHeaderValidator validator = new TvinnSadManifestHeaderValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		isValidRecord = false;
		    		
			    }else{
			    	//adjust some db-fields
			    	this.adjustFieldsForUpdate(appUser.getUser(), recordToValidate);
			    	
			    	//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					logger.warn("step 2");
					if(strMgr.isNotNull( recordToValidate.getEfuuid()) ){
						
						logger.warn("doUpdate:" + recordToValidate.getEfuuid());
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_UPDATE, errMsg);
							
					}else{
						//set manifest-uuid
						try{recordToValidate.setEfuuid(this.manifestExpressMgr.getUuid());logger.info(recordToValidate.getEfuuid()); }
						catch(Exception e){ logger.error("#####################:" + e.getMessage()); }
						
						logger.warn("doCreate branch starting...");
						logger.warn("doCreate");
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_ADD, errMsg);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					if(dmlRetval<0){
						isValidRecord = false;
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					}else{
						//Create OK. Prepare for upcoming Update
						//model.addAttribute("updateFlag", "1");
					}
			    }
			}
			//--------------
			//Fetch record
			//--------------
			//dropdowns
			this.setCodeDropDownMgr(appUser, model);
			
			if(strMgr.isNotNull(recordToValidate.getEfuuid()) ){
				if(isValidRecord){
					JsonTvinnSadManifestRecord record = this.getRecord(appUser, recordToValidate.getEfuuid());
					this.adjustFieldsForFetch(record);
					//check if the manifest cargo lines are valid
					if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
						model.put("invalidManifest", "-1");
					}
					//check it the manifest is editable
					if(!manifestExpressMgr.isEditableManifest(appUser, record)){
						record.setOwn_editable(-1);
						//logger.warn(record.getOwn_editable());
						
					}
					model.put(TvinnSadConstants.DOMAIN_RECORD, record);
					session.setAttribute(TvinnSadConstants.SESSION_HEADER_RECORD_SADMANIFEST, record);
					
				}else{
					//in case of validation errors
					//this.adjustFieldsForFetch(recordToValidate);
					model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
					session.setAttribute(TvinnSadConstants.SESSION_HEADER_RECORD_SADMANIFEST, recordToValidate);
				}
				
			}else{
				if(isValidRecord){
					//get standard values from default avd (proposed)
					JsonTvinnSadManifestRecord tmp = this.getDefaultValuesRecord(appUser);
					if(tmp!=null){ recordToValidate = tmp; }
					this.adjustFieldsForFetch(recordToValidate);
					model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				}
			}
			
			if(action==null || "".equals(action)){ 
				action = "doUpdate";	
			}else if (action.equals(TvinnSadConstants.ACTION_CREATE)){
				action = "doUpdate";
			}
			
			model.put("action", action);
			//model.addAttribute("avd", avd);
			//logger.info("AVD:" + avd);
			logger.info("END of method");
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
			return successView;
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit_delete.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDelete(JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		ModelAndView successView = new ModelAndView("redirect:tvinnsadmanifest.do?action=doFind&sign=" + appUser.getSignatur());
		
		logger.warn("Inside: doDelete");
		String uuid = null;
		String notisText = null;
		String status = null;
		String pro = null;
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentUuid")){
    				uuid = value;
    			}else if(element.startsWith("selectedStatus")){
    				status = value;
    			}else if(element.startsWith("selectedPro")){
    				pro = value;
    			}else if(element.startsWith("currentText")){
    				notisText = value;
    			}
    		}
    	}
	    
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//---------
			//Delete 
			//---------
			if(strMgr.isNotNull( uuid )){
				recordToValidate.setEfuuid(uuid);
				recordToValidate.setEfst2(status);
				recordToValidate.setEfpro(pro);
				
				int dmlRetval = 0;
				StringBuffer errMsg = new StringBuffer();
				//fetch record
				logger.warn("doDelete");
				//Update with delete flag
				dmlRetval = this.deleteRecord(appUser.getUser(), recordToValidate, errMsg);
				
				if(dmlRetval<0){
					String errorMessage = "ERROR on delete ... ??? check your code";
					logger.error(errorMessage);
					model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errorMessage);
					
				}else{
					logger.info("doDelete = OK");
					//execute RPG last
					if(StringUtils.isNotEmpty(recordToValidate.getEfpro())){
						JsonTvinnSadManifestRpgContainer rpgContainer = this.executeRpgSADEFJSONW(appUser, recordToValidate.getEfpro());
						if(rpgContainer!=null){
							//check for errors
							if(StringUtils.isNotEmpty(rpgContainer.getErrMsg()) || StringUtils.isNotEmpty(rpgContainer.getErrMsgT()) ){
								String errorMessage = "SERVER_ERROR:" + rpgContainer.getErrMsg() + rpgContainer.getErrMsgT();
								model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errorMessage);
								logger.error(errorMessage);
							}else{
								logger.warn("Send after delete = OK");
							}
						}
					}
				}
			}
			return successView;
		}
	}
	
	
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private JsonTvinnSadManifestRecord getDefaultValuesRecord(SystemaWebUser appUser){
		JsonTvinnSadManifestRecord retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_DEFAULT_VALUES_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				int counter = 1;
				for(JsonTvinnSadManifestRecord record : outputList ){
					//Default first one
					if(counter==1){
						retval = record;
					}
					//user's default avd on global level ASAVD (go esped --> 8)
					if(record.getEfavd().equals(appUser.getAsavd())){
						retval = record;
						break;
					}
					counter++;
				}
				
			}
    	}
    	return retval;
	}
	/**
	 * Get a specific manifest
	 * @param appUser
	 * @param efuuid
	 * @return
	 */
	private JsonTvinnSadManifestRecord getRecord(SystemaWebUser appUser, String efuuid){
		JsonTvinnSadManifestRecord retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&efuuid=" + efuuid;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = record;
					//logger.info(retval.toString());
				}
			}
    	}
    	return retval;
	}
	
	/**
	 * Update record
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonTvinnSadManifestRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = 0;
					logger.warn(record.toString());
				}
			}
    	}
    	
    	return retval;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int deleteRecord(String applicationUser, JsonTvinnSadManifestRecord recordToValidate, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=D" + "&efuuid=" + recordToValidate.getEfuuid() + "&efst2=" + recordToValidate.getEfst2());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = 0;
					//logger.warn(record.toString());
				}
			}
    	}
    	
    	return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param mode
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int updateStatus(String applicationUser, String mode, JsonTvinnSadManifestRecord recordToValidate, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		//http://localhost:8080/syjservicestn/syjsSADEFFR_U.do?user=OSCAR&mode=UMS&efuuid=cb9717e2-e55a-4ff0-9931-60e0a7c5fbe7&efst2=S
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode  + "&efuuid=" + recordToValidate.getEfuuid());
		if("UMS".equals(mode)){
			urlRequestParams.append("&efst2=" + recordToValidate.getEfst2());
		}else if("US".equals(mode)){
			urlRequestParams.append("&efst=" + recordToValidate.getEfst());
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestRecord> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestRecord record : outputList ){
					retval = 0;
					logger.warn(record.toString());
				}
			}
    	}
    	
    	return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(String applicationUser, JsonTvinnSadManifestRecord recordToValidate){
		recordToValidate.setEfdtr(new DateTimeManager().getCurrentDate_ISO());
		recordToValidate.setEfeta(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfeta()));
		recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfsjadt()));
		//time must send seconds (RPG-GUI)
		if(StringUtils.isNotEmpty(recordToValidate.getEfetm())){
			if(recordToValidate.getEfetm().length()==4){
				recordToValidate.setEfetm(recordToValidate.getEfetm() + "00");
			}
		}
		//get efktypt (text) for efktyp(comming from a drop-down)
		recordToValidate.setEfktypt(this.getText(applicationUser, recordToValidate.getEfktyp()));
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(JsonTvinnSadManifestRecord recordToValidate){
		recordToValidate.setEfeta(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfeta()));
		recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_NO(recordToValidate.getEfsjadt()));
		//time must be presented without seconds (WEB-GUI
		if(StringUtils.isNotEmpty(recordToValidate.getEfetm())){
			if(recordToValidate.getEfetm().length()>4){
				recordToValidate.setEfetm(recordToValidate.getEfetm().substring(0,4));
			}
		}
	}
	/**
	 * Completes an important text field not present in GUI
	 * @param applicationUser
	 * @param efktyp
	 * @return
	 */
	private String getText(String applicationUser, String efktyp){
		String retval = "";
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kftyp=" + FasteKoder.SADEFBKODE.toString());
		urlRequestParams.append("&kfkod=" + efktyp);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());

		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonMaintMainChildWindowKofastContainer container = null;
		Collection <JsonMaintMainChildWindowKofastRecord> list = new ArrayList<JsonMaintMainChildWindowKofastRecord>();
		try {
			if (jsonPayload != null) {
				container = maintMainKofastService.getContainer(jsonPayload);
				if (container != null) {
					for (JsonMaintMainChildWindowKofastRecord record :  container.getDtoList()) {
						if (!"DEFN".equals(record.getKfkod())) {
							retval = record.getKftxt(); 
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("Error: ",e);
		}
		
		return retval;
	}
	
	/**
	 * Sends the manifest
	 * 
	 * @param appUser
	 * @param tur
	 * @return
	 */
	private JsonTvinnSadManifestRpgContainer executeRpgSADEFJSONW(SystemaWebUser appUser, String tur){
		JsonTvinnSadManifestRpgContainer retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_EXECUTE_RPG_SEND_MANIFEST_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&pro=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonTvinnSadManifestRpgContainer container = this.tvinnSadManifestListService.getContainerRpgResult(jsonPayload);
    		retval = container;
    	}
    	return retval;
	}

	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFBKODE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private ManifestExpressMgr manifestExpressMgr;

}

