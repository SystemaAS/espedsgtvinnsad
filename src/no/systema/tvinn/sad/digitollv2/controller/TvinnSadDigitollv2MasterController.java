package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import no.systema.main.model.SystemaWebUser;

//tvinn
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiAsyncFacadeSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiMasterSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.AvdSignControllerService;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmologContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmologRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadDigitollDropDownListPopulationService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
import no.systema.tvinn.sad.digitollv2.service.SadmoafListService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.digitollv2.validator.MasterValidator;
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
				MasterValidator validator = new MasterValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		this.setRecordAspects(appUser, recordToValidate);
		    		this.adjustSenderReceiverCommunication(recordToValidate);
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
		    	//	logger.debug(jsonPayload);
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
							this.setTransportDto(appUser.getUser(), record);
							//put it on GUI
							model.put("record", record);
							//logger.debug(record.toString());
							//Only if ERROR
							if("M".equals(record.getEmst2())) {
								this.setLastErrorText(appUser, emlnrt, emlnrm, model);
							}
						}
						
					}
					
		    	}
			}
			if("doCreate".equals(action)) {
				this.setTransportDto(appUser.getUser(), recordToValidate);
				//(1)get default values from sadmoaf
				this.setDefaultValues(appUser, recordToValidate);
				//(2)get default values from tur to complete
				this.setDefaultValuesTur(appUser, recordToValidate);
				//(3)extra
				if(StringUtils.isEmpty(recordToValidate.getEmrgt())){
					if(recordToValidate.getTransportDto()!=null) {
						//logger.debug("setting carrier orgnr from TransportDto:" + recordToValidate.getTransportDto().getEtrgt());
						recordToValidate.setEmrgt(recordToValidate.getTransportDto().getEtrgt());
					}
				}
				//
				model.put("record", recordToValidate);
			}
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
	    	this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.avdSignControllerService.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			
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
	@RequestMapping(value="tvinnsaddigitollv2_delete_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDelete(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		String id1 = "";
		String id2 = "";
		String id3 = "";
		
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
    			}
    		}
    	}
	    logger.info("Id1:" + id1);
	    ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&ehlnrt=" + Integer.parseInt(id1) );
	    
	    
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
				
				String mode = "D";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.deleteMaster(appUser.getUser(), recordToValidate, mode, errMsg);
				
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus2_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus2(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String status2 = "";
		//from form
		id1= String.valueOf(recordToValidate.getEmlnrt());
		id2= String.valueOf(recordToValidate.getEmlnrm());
    	status2 = recordToValidate.getEmst2();
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("status2:" + status2);
	    
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
				
				recordToValidate.setEmlnrt(Integer.valueOf(id1));
				recordToValidate.setEmlnrm(Integer.valueOf(id2));
				recordToValidate.setEmst2(status2);
				String mode = "US2";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus2OnMaster(appUser.getUser(), recordToValidate, mode, errMsg);
				if(dmlRetval < 0) {
					//error on update
					model.put("errorMessage", errMsg.toString());
				}
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}	
	
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus3_master.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus3(@ModelAttribute ("record") SadmomfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String status3 = "";
		
		//from form
		id1= String.valueOf(recordToValidate.getEmlnrt());
		id2= String.valueOf(recordToValidate.getEmlnrm());
    	status3 = recordToValidate.getEmst3();
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("status3:" + status3);
	    
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
				
				recordToValidate.setEmlnrt(Integer.valueOf(id1));
				recordToValidate.setEmlnrm(Integer.valueOf(id2));
				recordToValidate.setEmst3(status3);
				String mode = "US3";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus3OnMaster(appUser.getUser(), recordToValidate, mode, errMsg);
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
		String layer = request.getParameter("layer");
		
		
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
	    //when delete is triggered from a list (transport list of masters)
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + Integer.parseInt(id1) );
		if(StringUtils.isNotEmpty(layer)) {
			//when delete is triggered within the layer (master)
			successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + Integer.parseInt(id1) + "&emlnrm=" + Integer.parseInt(id2) );
		}
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
		
		String async = request.getParameter("async");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = null;;
		StringBuilder redirect = new StringBuilder();
		redirect.append("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + recordToValidate.getEmlnrt() + "&emlnrm=" + recordToValidate.getEmlnrm());
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEmlnrt() > 0 && recordToValidate.getEmlnrm() > 0) {
				//check send behaviour (async or sync)
				if(StringUtils.isNotEmpty(async)) {
					//Master level is the only using EXECUTING status in status 1 instead for PENDING in status 3. The reason is the collision with SendAllHouses wich puts PENDING
					//look at sendAllHouses on House Controller
					this.apiMasterSendService.setSt_Master(appUser.getUser(), recordToValidate.getEmlnrt(),recordToValidate.getEmlnrm(),  EnumSadmomfStatus.EXECUTING.toString());
					//set st3 as pending in house to block the Send button until finished
					//this.apiMasterSendService.setSt3_Master(appUser.getUser(), recordToValidate.getEmlnrt(),recordToValidate.getEmlnrm(),  EnumSadmomfStatus3.PENDING.toString());
					//async if applicable
					this.apiAsynchFacadeSendService.sendMaster(appUser.getUser(), recordToValidate.getEmlnrt(),recordToValidate.getEmlnrm(), recordToValidate.getEmmid());
				}else {
					//normal synchronous default as a normal controller
					//set st3 as pending in house to block the Send button until finished
					this.apiMasterSendService.setSt_Master(appUser.getUser(), recordToValidate.getEmlnrt(),recordToValidate.getEmlnrm(),  EnumSadmomfStatus.EXECUTING.toString());
					//sync
					String redirectSuffix = apiMasterSendService.send(appUser.getUser(), recordToValidate.getEmlnrt(),recordToValidate.getEmlnrm(), recordToValidate.getEmmid());
					if(StringUtils.isNotEmpty(redirectSuffix)) {
						redirect.append(redirectSuffix);
					}
				}
			}else {
				//this will never populate a redirect but sheet the same ...:-(
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendMaster -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
			successView = new ModelAndView(redirect.toString());
			
		}
		
		return successView;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int deleteMaster(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	 * This method is the same that in the childWindow but refined by showing ONLY the last ERROR (if any)
	 * 
	 * @param appUser
	 * @param etlnrt
	 * @param model
	 */
	private void setLastErrorText(SystemaWebUser appUser, String emlnrt, String emlnrm, Map model) {
		//fetch from error log 
    	StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_LOG_URL);
		StringBuilder urlRequestParamsKeys = new StringBuilder();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&ellnrt=" + emlnrt);
		urlRequestParamsKeys.append("&ellnrm=" + emlnrm);
		
		String BASE_URL_LOG = url.toString();
    	logger.info("URL: " + BASE_URL_LOG);
    	logger.info("PARAMS: " + urlRequestParamsKeys.toString());
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_LOG, urlRequestParamsKeys.toString());
    	String washed = jsonPayload.replaceAll("'", "");
    	washed = washed.replaceAll("\t", "");
    	//logger.info(washed);
    	try {
    		SadmologContainer logContainer = new ObjectMapper().readValue(washed, SadmologContainer.class);
    		for (SadmologRecord record : logContainer.getList()) {
    			//always to the last element in the list which is the most recent
    			if("ERROR".equals(record.getEltyp())) {
    				model.put("logErrorText", "Dato:" + record.getEldate() + " " + "tid:" + record.getEltime() + " " + record.getElltxt());
    				//logger.info(record.getEltyp());
    				//logger.info(record.getElltxt());
    			}
    		}
    	}catch (Exception e) {
    		logger.error(e.toString());
    		e.printStackTrace();
    	}
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setDefaultValuesTur(SystemaWebUser appUser, SadmomfRecord recordToValidate) {
		  logger.info("Inside setTurStdValues");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TUR_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUser.getUser());
		  //urlRequestParamsKeys.append("&wtudt=" + fromDate);
		  urlRequestParamsKeys.append("&wsstur=" + recordToValidate.getTransportDto().getEtpro());
		  urlRequestParamsKeys.append("&wssavd=" + recordToValidate.getTransportDto().getEtavd());

		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadTurContainer container = this.sadTurService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			for(SadTurRecord  record : container.getWrktriplist()){
	    				//change only given attributes...
	    				this.overrideDefaultValuesWithTurValue(record, recordToValidate);
	    			}
	    		}
	    	}
		  
	}
	/**
	 * 
	 * @param record
	 * @param recordToValidate
	 */
	private void overrideDefaultValuesWithTurValue(SadTurRecord record, SadmomfRecord recordToValidate) {
		if(StringUtils.isNotEmpty(record.getTutvkt())) {
			logger.info("Brutto-Vekt: " + record.getTutvkt());
			recordToValidate.setEmvkb(Integer.valueOf(record.getTutvkt()));
		}
		if(StringUtils.isNotEmpty(record.getTutarf())) {
			logger.info("Ag-ref(tutarf-tariffør): " + record.getTutarf());
			recordToValidate.setEmdkm(record.getTutarf());
		}
		
		if(StringUtils.isNotEmpty(record.getTusdf())) {
			logger.info("Lastested: " + record.getTusdf());
			recordToValidate.setEmsdlt(record.getTusdf());
			recordToValidate.setEmlkl(record.getTulk());
		}
		if(StringUtils.isNotEmpty(record.getTusdt())) {
			logger.info("Lossested: " + record.getTusdt());
			recordToValidate.setEmsdut(record.getTusdt());
		}
		//Container code 1
		if(StringUtils.isNotEmpty(record.getTuckd1())) {
			logger.info("Container code 1: " + record.getTuckd1());
			recordToValidate.setEmc1id(record.getTuckd1());	
		}
		//Container code 2
		if(StringUtils.isNotEmpty(record.getTuckd2())) {
			logger.info("Container code 2: " + record.getTuckd2());
			recordToValidate.setEmc2id(record.getTuckd2());
			
		}
		
	}
	/**
	 * 
	 * @param applicationUser
	 * @param etavd
	 * @param recordToValidate
	 */
	private void setDefaultValues(SystemaWebUser appUser, SadmomfRecord recordToValidate) {
		Integer ZERO_AVD = 0;
		 Integer originalEmlnrt = recordToValidate.getEmlnrt();
		 String originalSign = recordToValidate.getEmsg();
		 Integer originalAvd = recordToValidate.getEmavd();
		 Integer originalPro = recordToValidate.getEmpro();
		 try {
			  	List<SadmoafRecord> tmpList = this.getListWithDefaultValues(appUser, recordToValidate.getEmavd());
				if(tmpList!=null && tmpList.size()>0) {
					for(SadmoafRecord  record : tmpList){
						logger.info("Ombud navn:" + record.getEtnar());
						logger.info("Mottaker master navn:" + record.getEmnam());
						//hand over
						//Now lend only those transport attributes to the recordToValidate GUI (only master attributes) to make the com
						BeanUtils.copyProperties(recordToValidate, record);
					}
				}else {
					//check with the lowest-hierarchy default value set (avd = 0)
					tmpList = this.getListWithDefaultValues(appUser, ZERO_AVD);
					if(tmpList!=null && tmpList.size()>0) {
						for(SadmoafRecord  record : tmpList){
							logger.info("Ombud navn:" + record.getEtnar());
							logger.info("Mottaker master navn:" + record.getEmnam());
							//hand over
							//Now lend only those transport attributes to the recordToValidate GUI (only master attributes) to make the com
							BeanUtils.copyProperties(recordToValidate, record);
						}
					}
				}
				//restore original ids
				recordToValidate.setEmlnrt(originalEmlnrt);
				recordToValidate.setEmavd(originalAvd);
				recordToValidate.setEmpro(originalPro);
				recordToValidate.setEmsg(originalSign);
				
		 }catch(Exception e) {
			 logger.error(e.toString());
		 }
	    	
	}
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @return
	 */
	private List getListWithDefaultValues(SystemaWebUser appUser, Integer avd) {
		List result = new ArrayList();
		try {
			  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
			  StringBuffer urlRequestParamsKeys = new StringBuffer();
			  urlRequestParamsKeys.append("user=" + appUser.getUser() + "&etavd=" + avd);
			    
			  logger.info("URL: " + BASE_URL);
			  logger.info("PARAMS: " + urlRequestParamsKeys);
			  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			
			  logger.info(jsonPayload);
			  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			  if(jsonPayload!=null){
				SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
				if(container!=null){
					if(container.getList()!=null && container.getList().size()>0) {
						result = (List)container.getList();
					}
				}
			  }
		 }catch(Exception e) {
			 logger.error(e.toString());
		 }
		
		return result;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param masterRecord
	 */
	private void setTransportDto(String applicationUser, SadmomfRecord masterRecord) {
		try {
			final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
			//add URL-parameters
			String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + masterRecord.getEmlnrt();
			
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
						masterRecord.setTransportDto(record);
					}
				}
				
	    	}
		}catch(Exception e) {
			 logger.error(e.toString());
		}
	}
	
	
	
	
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setRecordAspects(SystemaWebUser appUser, SadmomfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all houses
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
	private int updateStatus2OnMaster(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
						errMsg.append(record.getStatus2());
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
	private int updateStatus3OnMaster(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
						errMsg.append(record.getStatus3());
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
							if(recordToValidate.getEmlnrt()==null || recordToValidate.getEmlnrt()==0){
								recordToValidate.setEmlnrt(record.getId());
							}
							if(recordToValidate.getEmlnrm()==null || recordToValidate.getEmlnrm()==0){
								recordToValidate.setEmlnrm(record.getId2());
							}
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
		if(recordToValidate.getEmdtr()!=null && recordToValidate.getEmdtr() > 0) {
			String tmpEmdtr = String.valueOf(recordToValidate.getEmdtr());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtr) && tmpEmdtr.length()==8) {
				int isoEmdtr = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtr, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmdtr(isoEmdtr);
			}
		}
		//Sent date
		if(recordToValidate.getEmdtin()!=null && recordToValidate.getEmdtin() > 0) {
			String tmpEmdtin = String.valueOf(recordToValidate.getEmdtin());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtin) && tmpEmdtin.length()==8) {
				int isoEmdtin = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtin, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmdtin(isoEmdtin);
			}
		}
		//Date
		if(recordToValidate.getEmatdd()!=null && recordToValidate.getEmatdd() > 0) {
			String tmpEmatdd = String.valueOf(recordToValidate.getEmatdd());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmatdd) && tmpEmatdd.length()==8) {
				int isoEmatdd = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmatdd, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEmatdd(isoEmatdd);
			}
		}
		
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEmpns())) {
			if("NO".equals(recordToValidate.getEmlks())) {
				recordToValidate.setEmpns(StringUtils.leftPad(String.valueOf(recordToValidate.getEmpns()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEmpnm())) {
			if("NO".equals(recordToValidate.getEmlkm())) {
				recordToValidate.setEmpnm(StringUtils.leftPad(String.valueOf(recordToValidate.getEmpnm()),4,"0"));
			}
		}
		//doknr
		if(StringUtils.isNotEmpty(recordToValidate.getEmdkm())) {
			int index = recordToValidate.getEmdkm().lastIndexOf("-");
			if (index > -1) {
				String uniqueKey = recordToValidate.getEmdkm().substring(index);
				recordToValidate.setEmdkm(recordToValidate.getEmdkm().substring(0, index));
				recordToValidate.setOwn_emdkmUnique(uniqueKey);
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
		if(recordToValidate.getEmdtin()!=null && recordToValidate.getEmdtin() > 0) {
			int sentDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEmdtin()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEmdtin(sentDate);
		}
		//Date
		if(recordToValidate.getEmatdd()!=null && recordToValidate.getEmatdd() > 0) {
			int date = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEmatdd()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEmdtin(date);
		}
		
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEmpns())) {
			if("NO".equals(recordToValidate.getEmlks())) {
				recordToValidate.setEmpns(StringUtils.leftPad(String.valueOf(recordToValidate.getEmpns()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEmpnm())) {
			if("NO".equals(recordToValidate.getEmlkm())) {
				recordToValidate.setEmpnm(StringUtils.leftPad(String.valueOf(recordToValidate.getEmpnm()),4,"0"));
			}
		}
		//doknr
		if(StringUtils.isNotEmpty(recordToValidate.getEmdkm())) {
			String HYPHEN_SYS = "-SYS";
			if(recordToValidate.getEmlnrm()>0) {
				//only if this exists from before
				if(StringUtils.isNotEmpty(recordToValidate.getOwn_emdkmUnique())){
					String documentId = recordToValidate.getEmdkm() + recordToValidate.getOwn_emdkmUnique();
					recordToValidate.setEmdkm(documentId);
				}
			}else {
				
				int randomValue = new Random().nextInt(100000);
				String documentId = recordToValidate.getEmdkm() + HYPHEN_SYS + StringUtils.leftPad(String.valueOf(randomValue),6,"0");
				recordToValidate.setEmdkm(documentId);
			}
			
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
    		//now check if the master is valid to be deleted or not.
    		//To be valid for deletion it is required to have all houses deleted as well = without MRN att toll.no
    		//You are allow to delete a master if and only if all children have been deleted from toll.no previously ... It has to do with the API since we must know which api (air or road)
    		//we are using...
    		/*List<SadmohfRecord> listChild = (List)jsonContainer.getList();
    		if(listChild!=null && !listChild.isEmpty()) {
    			for(SadmohfRecord child : listChild) {
	    			if(StringUtils.isNotEmpty(child.getEhmid())) {
	    				record.setOwn_okToDelete(false);
	    				break;
	    			}
	    		}
    		}else {
    			//OK
    		}*/
    	}
    	
	}
	
	/**
	 * Special only for invalidation errors
	 * @param recordToValidate
	 */
	private void adjustSenderReceiverCommunication(SadmomfRecord recordToValidate) {
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_emems_email())){
			recordToValidate.setEmems(recordToValidate.getOwn_emems_email());
			recordToValidate.setEmemst(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEmems(recordToValidate.getOwn_emems_telephone());
			recordToValidate.setEmemst(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		//Receiver
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ememm_email())){
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_email());
			recordToValidate.setEmemmt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_telephone());
			recordToValidate.setEmemmt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
	}
	
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmoafListService sadmoafListService;
	
	@Autowired
	private GeneralUpdateService generalUpdateService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	
	@Autowired
	SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	@Autowired
	private SadTurService sadTurService;
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	private AvdSignControllerService avdSignControllerService;
	
	@Autowired
	private ApiMasterSendService apiMasterSendService;
	@Autowired
	private ApiAsyncFacadeSendService apiAsynchFacadeSendService;
	
}

