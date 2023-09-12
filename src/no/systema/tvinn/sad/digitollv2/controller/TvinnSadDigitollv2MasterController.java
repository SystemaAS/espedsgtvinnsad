package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.context.TdsAppContext;
import no.systema.main.model.SystemaWebUser;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.digitollv2.filter.SearchFilterDigitollTransportList;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadDigitollDropDownListPopulationService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.digitollv2.validator.MasterValidator;
import no.systema.tvinn.sad.manifest.express.filter.SearchFilterManifestList;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRpgContainer;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;




/**
 * Tvinn Sad Digitoll v2 Controller 
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
@Controller
public class TvinnSadDigitollv2MasterController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2MasterController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditManifest(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmomfRecord> outputList = new ArrayList<SadmomfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_master");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("action");
		String emlnrt = request.getParameter("emlnrt");
		String emlnrm = request.getParameter("emlnrm");
		boolean isValidForFetch = true;
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Submit button (Update/Insert)
			if(StringUtils.isNotEmpty(action) && action.equals("doUpdate")) {
				MasterValidator validator = new MasterValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		this.setRecordAspects(appUser, recordToValidate);
		    		//now we have all aspects in record
					model.put("record", recordToValidate);
					isValidForFetch = false;
				
				}else{
			    	//adjust fields
					this.adjustFieldsForUpdate(recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm) ){
						mode = SadDigitollConstants.DB_MODE_UPDATE;
						
					}else {
						mode = SadDigitollConstants.DB_MODE_INSERT;
					}
					logger.info("MODE:" + mode + " before update in Controller ...");
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
					if(dmlRetval < 0) {
						//error on update
						model.put("errorMessage", errMsg.toString());
						//put all aspects (sub-lists) only with update (not insert) error
						if(SadDigitollConstants.DB_MODE_UPDATE.equals(mode)){
							this.setRecordAspects(appUser, recordToValidate);
						}
						model.put("record", recordToValidate);
						isValidForFetch = false;
					}else {
						//this step is required for the FETCH-step since we want to get the newly created record for upcoming updates...
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
							emlnrt = String.valueOf(recordToValidate.getEmlnrt());
							emlnrm = String.valueOf(recordToValidate.getEmlnrm());
						}
					}
				}
			}
			

			//FETCH when applicable
			if((StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm)) && isValidForFetch ){
				//get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = "user=" + appUser.getUser() + "&emlnrt=" + emlnrt + "&emlnrm=" + emlnrm;
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.warn("URL: " + BASE_URL);
		    	logger.warn("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		    	//Debug --> 
		    	logger.info(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		
		    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
		    		//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
		    		outputList = jsonContainer.getList();
					if(outputList!=null){
						for(SadmomfRecord record: outputList){
							this.setRecordAspects(appUser, record);
							//now we have all aspects in this record
							model.put("record", record);
							logger.info(record.toString());
						}
						
					}
					
		    	}
			}
			if("doCreate".equals(action)) {
				//in order to grab emlnrt-parent
				model.put("record", recordToValidate);
			}
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
	    	this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			this.setDropDownService(model);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String status = "";
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		//logger.warn("####################################################");
    			//logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("current_id1")){
    				id1 = value;
    			}else if(element.startsWith("current_id2")){
    				id2 = value;
    			}else if(element.startsWith("current_status")){
    				status = value;
    			}
    		}
    	}
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("status:" + status);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + Integer.parseInt(id1) );
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//==========
			//Upd status
			//==========
		
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Update/Insert
			if(StringUtils.isNotEmpty(id1) && StringUtils.isNotEmpty(id2) ) {
				
				recordToValidate.setEmlnrt(Integer.valueOf(id1));
				recordToValidate.setEmlnrm(Integer.valueOf(id2));
				recordToValidate.setEmst(status);
				String mode = "US";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatusOnMaster(appUser.getUser(), recordToValidate, mode, errMsg);
				if(dmlRetval < 0) {
					//error on update
					model.put("errorMessage", errMsg.toString());
				}
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
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
	@RequestMapping(value="tvinnsaddigitollv2_api_delete_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiDeleteMaster(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmomfRecord> outputList = new ArrayList<SadmomfRecord>();
		Map model = new HashMap();
		
		String action = "";
		String id1 = "";
		String id2 = "";
		String mrn = "";
		
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		//logger.warn("####################################################");
    			//logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("current_id1")){
    				id1 = value;
    			}else if(element.startsWith("current_id2")){
    				id2 = value;
    			}else if(element.startsWith("current_mrn")){
    				mrn = value;
    			}else if(element.startsWith("action")){
    				action = value;
    			}
    		}
    	}
	    logger.info("action:" + action);
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("mrn:" + mrn);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + Integer.parseInt(id1) );
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//========
			//DELETE
			//========
			if(StringUtils.isNotEmpty(action) && action.equals("doDelete")) {
		    	logger.info("Before delete in Controller ...");
		    	//this.context = TdsAppContext.getApplicationContext();
				logger.info("Inside: doApiDeleteMaster");
				
				StringBuilder url = new StringBuilder();
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				url.append("deleteMasterConsignment.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&emlnrt=" + id1 + "&emlnrm=" + id2 + "&mrn=" + mrn;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.info(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		ApiGenericDtoResponse apiDtoResponse = this.apiGenericDtoResponseService.getReponse(jsonPayload);
	    		if(StringUtils.isNotEmpty(apiDtoResponse.getErrMsg())){
	    			logger.error("ERROR:" + apiDtoResponse.toString());
	    			model.put("errorMessage", apiDtoResponse.getErrMsg());	
				}
	    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
				
			}else {
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doDeleteMaster -->detail: action is null or other than doDelete...");
				//error on update
				model.put("errorMessage", errMsg.toString());
				//put all aspects (sub-lists) only with update (not insert) error
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
	@RequestMapping(value="tvinnsaddigitollv2_api_send_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendMaster(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendMaster");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = "redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + recordToValidate.getEmlnrt() + "&emlnrm=" + recordToValidate.getEmlnrm();
		ModelAndView successView = new ModelAndView(redirect);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEmlnrt() > 0 && recordToValidate.getEmlnrm() > 0) {
		    	logger.info("Before send in Controller ...");
				logger.info("Inside: doApiSendMaster");
				
				StringBuilder url = new StringBuilder();
				StringBuilder urlRequestParamsKeys = new StringBuilder();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				//check if POST-CREATE or PUT-UPDATE
				if( StringUtils.isNotEmpty(recordToValidate.getEmmid()) ) {
					url.append("putMasterConsignment.do");
					urlRequestParamsKeys.append("&emlnrt=" + recordToValidate.getEmlnrt());
					urlRequestParamsKeys.append("&emlnrm=" + recordToValidate.getEmlnrm());
					urlRequestParamsKeys.append("&mrn=" + recordToValidate.getEmmid());
				}else {
					
					url.append("postMasterConsignment.do");
					urlRequestParamsKeys.append("&emlnrt=" + recordToValidate.getEmlnrt());
					urlRequestParamsKeys.append("&emlnrm=" + recordToValidate.getEmlnrm());					
				}
				
				String BASE_URL = url.toString();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    		//Debug -->
		    	logger.info(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
	    		ApiGenericDtoResponse apiDtoResponse = this.apiGenericDtoResponseService.getReponse(jsonPayload);
	    		if(StringUtils.isNotEmpty(apiDtoResponse.getErrMsg())){
	    			logger.error("ERROR:" + apiDtoResponse.toString());
	    			model.put("errorMessage", apiDtoResponse.getErrMsg());	
				}
	    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
				
			}else {
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendMaster -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
			
			
		}
		
		return successView;
		
	}
	
	
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setRecordAspects(SystemaWebUser appUser, SadmomfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all masters
		this.getHouses(appUser, record);
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateStatusOnMaster(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		GeneralUpdateContainer container = this.generalUpdateService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<GeneralUpdateRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(GeneralUpdateRecord record : outputList ){
					logger.info(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}
				}
			}
    	}
    	
    	return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		GeneralUpdateContainer container = this.generalUpdateService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<GeneralUpdateRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(GeneralUpdateRecord record : outputList ){
					logger.info(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}else {
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
							recordToValidate.setEmlnrt(record.getId());
							recordToValidate.setEmlnrm(record.getId2());
						}
					}
				}
			}
    	}
    	
    	return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id1
	 * @param id2
	 * @param mrn
	 * @param errMsg
	 * @return
	 */
	private int deleteRecord(String applicationUser, String id1, String id2, String mrn, StringBuffer errMsg) {
		int retval = 0;
		String MODE_DELETE = "D";
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + MODE_DELETE);
		if(StringUtils.isNotEmpty(mrn)) {
			urlRequestParams.append("&emmid=" + mrn);
		}else {
			urlRequestParams.append("&emlnrt=" + id1 + "&emlnrm=" + id2);
		}
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	
    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		GeneralUpdateContainer container = this.generalUpdateService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<GeneralUpdateRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(GeneralUpdateRecord record : outputList ){
					logger.info(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}
				}
			}
    	}
    	
    	return retval;
	}
		
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        //this.setDomainObjectsInView(model, record);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
			
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //Import
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_AVDELNINGAR_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadAvdelningContainer container = this.tvinnSadDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTvinnSadAvdelningRecord> list = new ArrayList();
			for(JsonTvinnSadAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
				//logger.info("Avd-tst:" + record.getAvd() + "XX" + record.getTst());
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			session.setAttribute(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG, list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void populateCustomsOfficeOfFirstEntryHtmlDropDown(Map model) {
		List<JsonTvinnSadCodeRecord> list = new ArrayList();
		list.add(this.setRecordCustomsOffice("NO371001", "Svinesund N tollsted"));
		list.add(this.setRecordCustomsOffice("NO372001", "Ørje tollsted"));
		model.put("custOfficeList", list);
	}
	private JsonTvinnSadCodeRecord setRecordCustomsOffice(String code, String text) {
		JsonTvinnSadCodeRecord record = new JsonTvinnSadCodeRecord();
		record.setZkod(code); record.setZtxt(text);
		return record;
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //NCTS import: ie=N 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_NCTS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:"  + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadSignatureContainer container = this.tvinnSadDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTvinnSadSignatureRecord> list = new ArrayList();
			for(JsonTvinnSadSignatureRecord record: container.getSignaturer()){
				list.add(record);
				//logger.info("Sign-tst:" + record.getSign());
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownService(Map model) {
		//previous docs
		List<GenericDropDownDto> dto = this.digitollDropDownListPopulationService.getPreviousDocumentsList(); model.put("previousDocumentsDto", dto);
		//container sizeAnd type
		dto = this.digitollDropDownListPopulationService.getContainerSizeAndType(); model.put("containerSizeAndTypeDto", dto);
		//country
		dto = this.digitollDropDownListPopulationService.getCountryList(); model.put("countryDto", dto);
		
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(SadmomfRecord recordToValidate){
		//Registered date
		if(recordToValidate.getEmdtr() > 0) {
			String tmpEmdtr = String.valueOf(recordToValidate.getEmdtr());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtr) && tmpEmdtr.length()==8) {
				int isoEmdtr = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtr, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmdtr(isoEmdtr);
			}
		}
		//Sent date
		if(recordToValidate.getEmdtin() > 0) {
			String tmpEmdtin = String.valueOf(recordToValidate.getEmdtin());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtin) && tmpEmdtin.length()==8) {
				int isoEmdtin = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtin, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmdtin(isoEmdtin);
			}
		}
		//Date
		if(recordToValidate.getEmatdd() > 0) {
			String tmpEmatdd = String.valueOf(recordToValidate.getEmatdd());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmatdd) && tmpEmatdd.length()==8) {
				int isoEmatdd = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmatdd, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmatdd(isoEmatdd);
			}
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(SadmomfRecord recordToValidate){
		
		//Sender - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_emems_email())){
			recordToValidate.setEmems(recordToValidate.getOwn_emems_email());
			recordToValidate.setEmemst(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEmems(recordToValidate.getOwn_emems_telephone());
			recordToValidate.setEmemst(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		//Receiver - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ememm_email())){
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_email());
			recordToValidate.setEmemmt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_telephone());
			recordToValidate.setEmemmt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		
		//Register date
		if(recordToValidate.getEmdtr()!=null && recordToValidate.getEmdtr() > 0) {
			int regDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEmdtrStr(), DateTimeManager.NO_FORMAT));
			recordToValidate.setEmdtr(regDate);
		}else {
			int regDate = Integer.valueOf(this.dateMgr.getCurrentDate_ISO());
			recordToValidate.setEmdtr(regDate);
		}
		
		//Sent date
		if(recordToValidate.getEmdtin() > 0) {
			int sentDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEmdtin()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEmdtin(sentDate);
		}
		//Date
		if(recordToValidate.getEmatdd() > 0) {
			int date = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEmatdd()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEmdtin(date);
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getMasters(SystemaWebUser appUser, SadmotfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&emlnrt=" + record.getEtlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		record.setListMasters(jsonContainer.getList());
    	}
    	
	}
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getHouses(SystemaWebUser appUser, SadmomfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&ehlnrt=" + record.getEmlnrt() + "&ehlnrm=" + record.getEmlnrm();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		record.setListHouses(jsonContainer.getList());
    	}
    	
	}
	
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private GeneralUpdateService generalUpdateService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	
	@Autowired
	SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;

}

