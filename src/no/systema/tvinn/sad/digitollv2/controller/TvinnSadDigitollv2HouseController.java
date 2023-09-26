package no.systema.tvinn.sad.digitollv2.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
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
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.digitollv2.validator.HouseValidator;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;


/**
 * Tvinn Sad Digitoll v2 Controller 
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
@Controller
public class TvinnSadDigitollv2HouseController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2HouseController.class.getName());
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
	@RequestMapping(value="tvinnsaddigitollv2_edit_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditHouse(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmohfRecord> outputList = new ArrayList<SadmohfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_house");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("action");
		String ehlnrt = request.getParameter("ehlnrt");
		String ehlnrm = request.getParameter("ehlnrm");
		String ehlnrh = request.getParameter("ehlnrh");
		//in case the call comes from a redirect view (typically sending to the api digitoll and redirect to here) ...
		String redirect_errMsg = request.getParameter(SadDigitollConstants.REDIRECT_ERRMSG);
		if(StringUtils.isNotEmpty(redirect_errMsg)) {
			model.put("errorMessage", redirect_errMsg);
		}
		
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
				HouseValidator validator = new HouseValidator();
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
					this.adjustFieldsForUpdate(appUser.getUser(), recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(ehlnrt) && StringUtils.isNotEmpty(ehlnrm) && StringUtils.isNotEmpty(ehlnrh) ){
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
							
							ehlnrt = String.valueOf(recordToValidate.getEhlnrt());
							ehlnrm = String.valueOf(recordToValidate.getEhlnrm());
							ehlnrh = String.valueOf(recordToValidate.getEhlnrh());
						}
					}
				}
			}
			
			
			//FETCH when applicable
			if((StringUtils.isNotEmpty(ehlnrt) && StringUtils.isNotEmpty(ehlnrm))&& StringUtils.isNotEmpty(ehlnrh) && isValidForFetch ){
				//get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = "user=" + appUser.getUser() + "&ehlnrt=" + ehlnrt + "&ehlnrm=" + ehlnrm + "&ehlnrh=" + ehlnrh;
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.warn("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		
		    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
		    		
		    		//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
					outputList = jsonContainer.getList();
					if(outputList!=null){
						for(SadmohfRecord record: outputList){
							//get
							this.getItemLines(appUser, record);
							this.setRecordAspects(appUser, record);
							this.setTransportDto(appUser.getUser(), record);
							this.setMasterDto(appUser.getUser(), record);
							//now we have all item lines in this house
							model.put("record", record);
							logger.info(record.toString());
						}
						logger.info(outputList.toString());
					}
					
		    	}
			}
			if("doCreate".equals(action)) {
				//in order to grab ehlnrt-parent
				this.setTransportDto(appUser.getUser(), recordToValidate);
				this.setMasterDto(appUser.getUser(), recordToValidate);
				model.put("record", recordToValidate);
			}	
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			/*this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			*/
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String id3 = "";
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
    			}else if(element.startsWith("current_id3")){
    				id3 = value;
    			}else if(element.startsWith("current_status")){
    				status = value;
    			}
    		}
    	}
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3); logger.info("status:" + status);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + Integer.parseInt(id1) + "&emlnrm=" + Integer.parseInt(id2) );
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
				
				recordToValidate.setEhlnrt(Integer.valueOf(id1));
				recordToValidate.setEhlnrm(Integer.valueOf(id2));
				recordToValidate.setEhlnrh(Integer.valueOf(id3));
				recordToValidate.setEhst(status);
				String mode = "US";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatusOnHouse(appUser.getUser(), recordToValidate, mode, errMsg);
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
	@RequestMapping(value="tvinnsaddigitollv2_api_delete_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiDeleteHouse(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmohfRecord> outputList = new ArrayList<SadmohfRecord>();
		Map model = new HashMap();
		
		String action = "";
		String id1 = "";
		String id2 = "";
		String id3 = "";
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
    			}else if(element.startsWith("current_id3")){
    				id3 = value;
    			}else if(element.startsWith("current_mrn")){
    				mrn = value;
    			}else if(element.startsWith("action")){
    				action = value;
    			}
    		}
    	}
	    logger.info("action:" + action);
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3); logger.info("mrn:" + mrn);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + Integer.parseInt(id1) + "&emlnrm=" + Integer.parseInt(id2) );
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
				logger.info("Inside: doApiDeleteHouse");
				
				StringBuilder url = new StringBuilder();
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				url.append("deleteHouseConsignment.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&ehlnrt=" + id1 + "&ehlnrm=" + id2 + "&ehlnrh=" + id3 + "&mrn=" + mrn;
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
				errMsg.append("ERROR on doDeleteHouse -->detail: action is null or other than doDelete...");
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
	@RequestMapping(value="tvinnsaddigitollv2_api_send_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendHouse(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendHouse");
		ModelAndView successView = null;
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		StringBuilder redirect = new StringBuilder();
		redirect.append("redirect:tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=" + recordToValidate.getEhlnrt() + "&ehlnrm=" + recordToValidate.getEhlnrm()+ "&ehlnrh=" + recordToValidate.getEhlnrh());
		
		//check user (should be in session already)
		logger.info(recordToValidate.toString());
		
		if(appUser==null){
			return loginView;
		
		}else{
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEhlnrt() > 0 && recordToValidate.getEhlnrm() > 0 && recordToValidate.getEhlnrh() > 0) {
		    	logger.info("Before send in Controller ...");
				logger.info("Inside: doApiSendHouse");
				
				StringBuilder url = new StringBuilder();
				StringBuilder urlRequestParamsKeys = new StringBuilder();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				//check if POST-CREATE or PUT-UPDATE
				if( StringUtils.isNotEmpty(recordToValidate.getEhmid()) ) {
					url.append("putHouseConsignment.do");
					urlRequestParamsKeys.append("&ehlnrt=" + recordToValidate.getEhlnrt());
					urlRequestParamsKeys.append("&ehlnrm=" + recordToValidate.getEhlnrm());
					urlRequestParamsKeys.append("&ehlnrh=" + recordToValidate.getEhlnrh());
					urlRequestParamsKeys.append("&mrn=" + recordToValidate.getEhmid());
				}else {
					
					url.append("postHouseConsignment.do");
					urlRequestParamsKeys.append("&ehlnrt=" + recordToValidate.getEhlnrt());
					urlRequestParamsKeys.append("&ehlnrm=" + recordToValidate.getEhlnrm());	
					urlRequestParamsKeys.append("&ehlnrh=" + recordToValidate.getEhlnrh());
				}
				
				String BASE_URL = url.toString();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    		//Debug -->
		    	logger.info(jsonPayload);
		    	
	    		try {
		    		ApiGenericDtoResponse apiDtoResponse = this.apiGenericDtoResponseService.getReponse(jsonPayload);
		    		if(StringUtils.isNotEmpty(apiDtoResponse.getErrMsg())){
		    			new RedirectCleaner().doIt(apiDtoResponse);
		    			//in order to catch it after the redirect as a parameter...if applicable
		    			if(StringUtils.isNotEmpty(apiDtoResponse.getErrMsgClean())) {
		    				redirect.append("&" + SadDigitollConstants.REDIRECT_ERRMSG + "=" + apiDtoResponse.getErrMsgClean());
		    			}
					}
	    		}catch(Exception e) {
	    			e.printStackTrace();
	    			
	    		}finally {
	    			successView = new ModelAndView(redirect.toString());
	    		}
				
			}else {
				//this will never populate a redirect but sheet the same ...:-(
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendHouse -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
		}
		
		return successView;
		
	}
	
	
	private void setTransportDto(String applicationUser, SadmohfRecord houseRecord) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + houseRecord.getEhlnrt();
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null){
				for(SadmotfRecord record: outputList){
					//ETA datein NO-format
					if(record.getEtetad()!=null && record.getEtetad() > 0) {
						String tmpEtetatd = String.valueOf(record.getEtetad());
						if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtetatd) && tmpEtetatd.length()==8) {
							int isoEtetad = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtetatd, DateTimeManager.ISO_FORMAT));
							record.setEtetad(isoEtetad);
						}
					}
					houseRecord.setTransportDto(record);
				}
			}
			
    	}	
	}
	
	private void setMasterDto(String applicationUser, SadmohfRecord houseRecord) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&emlnrt=" + houseRecord.getEhlnrt() + "&emlnrm=" + houseRecord.getEhlnrm();
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		List<SadmomfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null){
				for(SadmomfRecord record: outputList){
					houseRecord.setMasterDto(record);
				}
			}
			
    	}	
	}
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setRecordAspects(SystemaWebUser appUser, SadmohfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all items
		this.getItemLines(appUser, record);
	}
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateStatusOnHouse(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_URL;
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
	private int updateRecord(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_URL;
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
							if(recordToValidate.getEhlnrt()==null || recordToValidate.getEhlnrt()==0){
								recordToValidate.setEhlnrt(record.getId());
							}
							if(recordToValidate.getEhlnrm()==null || recordToValidate.getEhlnrm()==0){
								recordToValidate.setEhlnrm(record.getId2());
							}
							if(recordToValidate.getEhlnrh()==null || recordToValidate.getEhlnrh()==0){
								recordToValidate.setEhlnrh(record.getId3());
							}
						}
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
		//country
		dto = this.digitollDropDownListPopulationService.getCountryList(); model.put("countryDto", dto);
		//export types
		dto = this.digitollDropDownListPopulationService.getExportTypesDto(); model.put("exportTypesDto", dto);
	}
	
	private void adjustFieldsForFetch(SadmohfRecord recordToValidate){
		//Sent date
		if(recordToValidate.getEhdts()!=null && recordToValidate.getEhdts() > 0) {
			String tmpEmdtin = String.valueOf(recordToValidate.getEhdts());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtin) && tmpEmdtin.length()==8) {
				int isoEhdts = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtin, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEhdts(isoEhdts);
			}
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(String user, SadmohfRecord recordToValidate){
		String HYPHEN = "-";
		String dokumentId = recordToValidate.getEhdkh();
		
		if(StringUtils.isNotEmpty(dokumentId) && !dokumentId.startsWith(HYPHEN)){
			//logger.info("##############" + dokumentId);
			//do nothing (already in place)	as in: 123456789-0001-0000003
		}else {
			//House Document number and type (under fraktbrev for house nr API)
			this.getTransportDto(user, recordToValidate);
			logger.info("!!!!!!!! getting orgNr for EHDKH from Transport DTO:" + recordToValidate.getTransportDto().toString());
			//get the first part of the string, namely the OrnNr from the representative
			String orgNr = recordToValidate.getTransportDto().getEtrgr();
			if(StringUtils.isEmpty(orgNr)) {
				orgNr = recordToValidate.getTransportDto().getEtrgt(); //Carrier's OrgNr
			}
			dokumentId = orgNr + HYPHEN + StringUtils.leftPad(String.valueOf(recordToValidate.getEhavd()),4,"0") + 
						 		 HYPHEN + StringUtils.leftPad(String.valueOf(recordToValidate.getEhtdn()),7,"0");
			
			recordToValidate.setEhdkh(dokumentId);
			
			
		}
		
		//Sender - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ehems_email())){
			recordToValidate.setEhems(recordToValidate.getOwn_ehems_email());
			recordToValidate.setEhemst(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEhems(recordToValidate.getOwn_ehems_telephone());
			recordToValidate.setEhemst(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		//Receiver - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ehemm_email())){
			recordToValidate.setEhemm(recordToValidate.getOwn_ehemm_email());
			recordToValidate.setEhemmt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEhemm(recordToValidate.getOwn_ehemm_telephone());
			recordToValidate.setEhemmt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		
		//Bruttovikt
		//adjust BigDecimal eibl (sonet 13,2)
		if(StringUtils.isNotEmpty(recordToValidate.getEhvkb())){
			String tmp = recordToValidate.getEhvkb().replace(",", ".");
			BigDecimal bd = new BigDecimal(tmp).setScale(2, RoundingMode.HALF_UP);
			recordToValidate.setEhvkb(bd.toString());	
		}
		//Sent date
		if(recordToValidate.getEhdts()!=null && recordToValidate.getEhdts() > 0) {
			int sentDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEhdts()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEhdts(sentDate);
		}
		
		
	}
	/**
	 * 
	 * @return
	 */
	private void getTransportDto(String user, SadmohfRecord recordToValidate) {
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + user + "&etlnrt=" + recordToValidate.getEhlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
			List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null && outputList.size() > 0){
				for (SadmotfRecord record : outputList) {
					recordToValidate.setTransportDto(record);
				}
			}
			
    	}	
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getItemLines(SystemaWebUser appUser, SadmohfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&eilnrt=" + record.getEhlnrt() + "&eilnrm=" + record.getEhlnrm() + "&eilnrh=" + record.getEhlnrh();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
    		record.setListItemLines(jsonContainer.getList());
    	}
    	
	}
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private GeneralUpdateService generalUpdateService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private MaintMainKofastService maintMainKofastService;

	@Autowired
	SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
}

