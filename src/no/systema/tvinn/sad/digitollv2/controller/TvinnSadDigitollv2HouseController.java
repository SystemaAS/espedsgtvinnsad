package no.systema.tvinn.sad.digitollv2.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import no.systema.tvinn.sad.util.manager.ArchiveGoogleCloudManager;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiAsyncFacadeSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiHouseSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.AvdSignControllerService;
import no.systema.tvinn.sad.digitollv2.controller.service.HouseControllerService;
import no.systema.tvinn.sad.digitollv2.controller.service.HouseDocLogControllerService;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmodoclgRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmologContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmologRecord;
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
import no.systema.tvinn.sad.digitollv2.validator.HouseValidator;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsRecord;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;


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
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="createHousesFromOppdrag_Digitoll_TEST.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCreateHousesFromOppdrag_Digitoll(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String lnrt = request.getParameter("lnrt");
		String lnrm = request.getParameter("lnrm");
		
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + lnrt + "&emlnrm=" + lnrm);
		
		//take the whole string av choices, separated by "#"
		
		
		
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
		    		this.adjustSenderReceiverCommunication(recordToValidate);
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
					dmlRetval = this.houseControllerService.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
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
							this.setRecordAspects(appUser, record);
							this.setTransportDto(appUser.getUser(), record);
							this.setMasterDto(appUser.getUser(), record);
							//now we have all item lines in this house
							model.put("record", record);
							//put some other aspects
							model.put("ceilHouse", this.getHousesListSize(appUser, record.getMasterDto()));
							model.put("zhdoc_automatic_send", Integer.valueOf(AppConstants.ZHDOC_API_AUTOMATIC_SEND_ACTIVE));
							//logger.debug(record.toString());
							//Only if ERROR
							if("M".equals(record.getEhst2())) {
								this.setLastErrorText(appUser, ehlnrt, ehlnrm, ehlnrh, model);
							}
						}
						//logger.debug(outputList.toString());
					}
					
		    	}
			}
			if("doCreate".equals(action)) {
				logger.info("Inside doCreate...");
				//in order to grab ehlnrt-parent
				this.setTransportDto(appUser.getUser(), recordToValidate);
				this.setMasterDto(appUser.getUser(), recordToValidate);
				//some default values
				recordToValidate.setEhdkht("N730");
				model.put("record", recordToValidate);
			}	
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			
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
	@RequestMapping(value="tvinnsaddigitollv2_delete_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDelete(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
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
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3);
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
				String mode = "D";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.deleteHouse(appUser.getUser(), recordToValidate, mode, errMsg);
				
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus2_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus2(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String id3 = "";
		String status2 = "";
		//from form
		id1= String.valueOf(recordToValidate.getEhlnrt());
		id2= String.valueOf(recordToValidate.getEhlnrm());
		id3= String.valueOf(recordToValidate.getEhlnrh());
    	status2 = recordToValidate.getEhst2();
		
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3); logger.info("status2:" + status2);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=" + Integer.parseInt(id1) + "&ehlnrm=" + Integer.parseInt(id2)  + "&ehlnrh=" + Integer.parseInt(id3) );
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
				recordToValidate.setEhst2(status2);
				String mode = "US2";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus2OnHouse(appUser.getUser(), recordToValidate, mode, errMsg);
				if(dmlRetval < 0) {
					//error on update
					model.put("errorMessage", errMsg.toString());
				}
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus3_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus3(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String id2 = "";
		String id3 = "";
		String status3 = "";
		//from form
		id1= String.valueOf(recordToValidate.getEhlnrt());
		id2= String.valueOf(recordToValidate.getEhlnrm());
		id3= String.valueOf(recordToValidate.getEhlnrh());
    	status3 = recordToValidate.getEhst3();
		
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3); logger.info("status3:" + status3);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=" + Integer.parseInt(id1) + "&ehlnrm=" + Integer.parseInt(id2)  + "&ehlnrh=" + Integer.parseInt(id3) );
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
				recordToValidate.setEhst3(status3);
				String mode = "US3";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus3OnHouse(appUser.getUser(), recordToValidate, mode, errMsg);
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
	@RequestMapping(value="tvinnsaddigitollv2_api_send_house.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendHouse(@ModelAttribute ("record") SadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendHouse");
		
		String async = request.getParameter("async");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = null;
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
				//set st3 as pending in house to block the Send button until finished
				this.apiHouseSendService.setSt3_House(appUser.getUser(), recordToValidate.getEhlnrt(),recordToValidate.getEhlnrm(), recordToValidate.getEhlnrh(), EnumSadmohfStatus3.PENDING.toString());
				//check send behaviour (async or sync)
				if(StringUtils.isNotEmpty(async)) {
					//async if applicable
					this.apiAsynchFacadeSendService.sendHouse(appUser.getUser(), recordToValidate.getEhlnrt(),recordToValidate.getEhlnrm(), recordToValidate.getEhlnrh(), recordToValidate.getEhmid());
				}else {
					//normal synchronous default as a normal controller
					String redirectSuffix = apiHouseSendService.send(appUser.getUser(), recordToValidate.getEhlnrt(),recordToValidate.getEhlnrm(), recordToValidate.getEhlnrh(), recordToValidate.getEhmid());
					if(StringUtils.isNotEmpty(redirectSuffix)) {
						redirect.append(redirectSuffix);
					}
				}
				//----------------------------------------------------------
				//Send ZH-doc-Handlesfaktura automatically. When applicable...
				//----------------------------------------------------------
				if(this.isValidZH_tosend(recordToValidate)) {
					logger.info("#######################################");
					logger.info("START API ZH-DOC send ... house [ehlnrh]:" + recordToValidate.getEhlnrh());
					logger.info("#######################################");
					//(1) send doc (ZH = Handelsfaktura) unconditionally. This has been done manually in the GUI but now we will reinforce that behavior with this automation...
					//UC driven by DSV in order to always send the doc (if applicable)
					//(1.1) get the document link and type (ZH)
					JsonTvinnSadManifestArchivedDocsRecord zhRecord = this.getZHdoc(appUser, recordToValidate);
					if(zhRecord!=null) {
						//at this point we are sure that there are Handelsfakturor-ZH-PDFs (otherwise just do not send anything)
						logger.info("Doctyp:" + zhRecord.getDoctyp());
						logger.info("Doclnk:" + zhRecord.getDoclnk());
						logger.info("ehrg:" + recordToValidate.getEhrg());
						//String eh0068a_ISO = dateFormatter.convertToDate_ISO(recordToValidate.getEh0068a().toString());
						//logger.info("eh0068a_ISO:" + eh0068a_ISO);
						logger.info("eh0068a:" + recordToValidate.getEh0068a().toString());
						logger.info("eh0068b:" + recordToValidate.getEh0068b().toString());
					
						//(2) send it to the service as in the child-window, namely: 
						//example (manually): no.systema.tvinn.sad.manifest.express.controller.ajax.TvinnSadManifestAjaxHandlerController.sendFileToToll_TvinnSadManifest.do
						Set result = this.sendZHDocViaAPi(appUser, zhRecord, recordToValidate);
						logger.info("result-Set ZHDoc-Api:" + result.toString());
						
					}
					logger.info("####################################################");
					logger.info("END API ZH-DOC send ...house [ehlnrh]:" + recordToValidate.getEhlnrh());
					logger.info("####################################################");
				}
				
				
			}else {
				//this will never populate a redirect but anyways ... :-(
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendHouse -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
			successView = new ModelAndView(redirect.toString());
		}
		
		return successView;
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param zhRecord
	 * @param recordToValidate
	 * @return
	 */
	private Set sendZHDocViaAPi(SystemaWebUser appUser, JsonTvinnSadManifestArchivedDocsRecord zhRecord, SadmohfRecord sadmohfRecord) {
		Set result = new HashSet();
		
		try {
			String declDateFormatted = dateMgr.getDateFormatted_ISO(sadmohfRecord.getEh0068a().toString(), DateTimeManager.NO_FORMAT, DateTimeManager.ISO_FORMAT_REVERSED);
			String declId = sadmohfRecord.getEhrg() + "-" + declDateFormatted + "-" + sadmohfRecord.getEh0068b().toString();
			String docPath = zhRecord.getDoclnk();
			String docType = zhRecord.getDoctyp();
			String docTypeFormatted = this.getDocumentType(docType, docPath);
			
			if(docPath.startsWith("http")) {
				//Download to local directory from Google Cloud
				if(appUser.getUser() .equalsIgnoreCase("OSCAR")) {
					logger.warn("####### -->Using downloadPdfFromGoogleCloudTest (hardcoded A12 + .pdf just for testing ...");
					docPath = new ArchiveGoogleCloudManager().downloadPdfFromGoogleCloudTestSimple(docPath);
				}else {
					docPath = new ArchiveGoogleCloudManager().downloadPdfFromGoogleCloudSimple(docPath);
				}
			}
			//
			String url = TvinnSadManifestUrlDataStore.TVINN_SAD_SEND_DOCUMENT_TO_TOLL_URL_V2;
			String BASE_URL = url;
	 		StringBuffer urlRequestParamsKeys = new StringBuffer();
	 		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&declId=" + declId + "&docType=" + docTypeFormatted );
	 		urlRequestParamsKeys.append("&docPath=" + docPath );
	 		logger.warn("URL: " + BASE_URL);
	 		logger.warn("PARAMS: " + urlRequestParamsKeys.toString());
	 		
	 		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	 		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	 		//Debug -->
		    logger.warn("Return code docAPI:" + jsonPayload);
	 		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	 		
	 		result.add(jsonPayload);
	 		
	 		//log in new db-table SADMODOCLG
			SadmodoclgRecord sadmodoclgRecord = new SadmodoclgRecord();
			if(StringUtils.isNotEmpty(jsonPayload) && jsonPayload.length()>200) {
				jsonPayload = jsonPayload.substring(0,199);
			}
			sadmodoclgRecord.setResultapi(jsonPayload);
			sadmodoclgRecord.setDocId(sadmohfRecord.getEhdkh());
			sadmodoclgRecord.setDeklid(declId);
			sadmodoclgRecord.setDoctyp(docTypeFormatted);
			sadmodoclgRecord.setDoclnk(zhRecord.getDoclnk());
			sadmodoclgRecord.setDeklnr(sadmohfRecord.getEhrg());
			sadmodoclgRecord.setDekldate(dateMgr.getDateFormatted_ISO(sadmohfRecord.getEh0068a().toString(), DateTimeManager.NO_FORMAT));
			sadmodoclgRecord.setDeklsekv(sadmohfRecord.getEh0068b().toString());
			StringBuffer errMsg = new StringBuffer();
	 		this.houseDocLogControllerServiceLogger.insertRecord(appUser.getUser(), sadmodoclgRecord, "A", errMsg);
	 	 	
	 		
	 		
		}catch(Exception e) {
			logger.error(e.toString());
			e.toString();
		}
		
		return result;
	}
	
	private String getDocumentType(String docType, String docPath){
		//docType is too bad to elucidate something. We use the docPath since this carries information about type
		String retval = "faktura";
		if(docPath!=null){
			if(docPath.contains("/ZH") || docPath.contains("/ZO")){
				retval = "faktura";
			}else{
				//other types here (tillatelser, fraktregning, opprinnelsesdokumentasjon, fraktbrev)
			}
		}
		
		return retval;
	}
	/**
	 * Check for validity for automatic sending ZH-doc
	 * @param recordToValidate
	 * @return
	 */
	private boolean isValidZH_tosend(SadmohfRecord sadmohfRecord ) {
		boolean retval = false;
		try {
			if(AppConstants.ZHDOC_API_AUTOMATIC_SEND_ACTIVE!=null && Integer.valueOf(AppConstants.ZHDOC_API_AUTOMATIC_SEND_ACTIVE) >0) {
				if(StringUtils.isNotEmpty(sadmohfRecord.getEhrg()) && 
					StringUtils.isNotEmpty(String.valueOf(sadmohfRecord.getEh0068a())) && StringUtils.isNotEmpty(String.valueOf(sadmohfRecord.getEh0068b())) ){
					retval = true;
				}
			}
		}catch(Exception e) {
			e.toString();
			logger.error(e.toString());
		}
		
		return retval;
	}
	/**
	 * filters the list to get the ZH-docs (Handelsfaktura SAD)
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private JsonTvinnSadManifestArchivedDocsRecord getZHdoc(SystemaWebUser appUser, SadmohfRecord sadmohfRecord ) {
		JsonTvinnSadManifestArchivedDocsRecord result = null;
		try {
			String wsavd = String.valueOf(sadmohfRecord.getEhavd()); 
			String wsopd = String.valueOf(sadmohfRecord.getEhtdn());
			if(StringUtils.isNotEmpty(wsavd) && StringUtils.isNotEmpty(wsopd)) {
				Collection<JsonTvinnSadManifestArchivedDocsRecord> list = manifestExpressMgr.fetchArchiveDocs(appUser, wsavd, wsopd);
				logger.info(list.toString());
				for(JsonTvinnSadManifestArchivedDocsRecord record: list) {
					logger.trace("Doctyp:" + record.getDoctyp());
					logger.trace("Doclnk:" + record.getDoclnk());
					if(record.getDoclnk()!=null && record.getDoclnk().contains("ZH20")) {
						result = new JsonTvinnSadManifestArchivedDocsRecord();
						result.setDoctyp(record.getDoctyp());
						result.setDoclnk(record.getDoclnk());
						break;
					}
				}
			}
		}catch(Exception e) {
			e.toString();
			logger.error(e.toString());
			
		}
		return result;
	}
	/**
	 * Always async
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_api_send_allHouses.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendAllHouses(HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendAllHouses");
		
		String lnrtStr = request.getParameter("lnrt");
		String lnrmStr = request.getParameter("lnrm");
		String level = request.getParameter("level");
		
		Integer lnrt = 0;
		Integer lnrm = 0;
		if(StringUtils.isNotEmpty(lnrtStr)) { lnrt = Integer.valueOf(lnrtStr); }
		if(StringUtils.isNotEmpty(lnrtStr)) { lnrm = Integer.valueOf(lnrmStr); }
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = null;
		StringBuilder redirect = new StringBuilder();
		
		//if it triggered from master level
		if(StringUtils.isNotEmpty(level) && level.equals("m")) {
			redirect.append("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + lnrtStr + "&emlnrm=" + lnrmStr);
		}else {
			redirect.append("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + lnrtStr );
		}
		logger.info(redirect.toString());
		
		if(appUser==null){
			return loginView;
		
		}else{
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//=================
			//SEND POST or PUT
			//=================
			//send from master GUI implementation
			if(lnrt > 0 && lnrm > 0 ) {
				//set st3 as pending in master to block the Send all houses- button until finished
				this.apiHouseSendService.setSt3_Master(appUser.getUser(), lnrt, lnrm, EnumSadmomfStatus3.PENDING.toString());
				//always async
				this.apiAsynchFacadeSendService.sendAllHouses(appUser.getUser(), lnrt, lnrm );
				
				//remove the (P)ENDING status that was set by the caller before the async call
				//this.apiHouseSendService.setSt3_Master(appUser.getUser(), lnrt, lnrm, EnumSadmomfStatus3.EMPTY.toString());
			}else {
				//this will never populate a redirect but shit the same ...:-(
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendHouse -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());
			}
			
			//----------------------------------------------------------
			//Send ZH-doc-Handlesfaktura automatically. When applicable...
			//----------------------------------------------------------
			List<SadmohfRecord> listSadmohRecord = this.getHouses(appUser.getUser(), lnrtStr, lnrmStr);
			if(listSadmohRecord!=null && listSadmohRecord.size()>0) {
				for (SadmohfRecord sadmohfRecord : listSadmohRecord) {
					if(this.isValidZH_tosend(sadmohfRecord)) {
						logger.info("##############################################################");
						logger.info("START API ZH-DOC send ... house [ehdkh]:" + sadmohfRecord.getEhdkh());
						logger.info("##############################################################");
						//(1) send doc (ZH = Handelsfaktura) unconditionally. This has been done manually in the GUI but now we will reinforce that behavior with this automation...
						//UC driven by DSV in order to always send the doc (if applicable)
						//(1.1) get the document link and type (ZH)
						JsonTvinnSadManifestArchivedDocsRecord zhRecord = this.getZHdoc(appUser, sadmohfRecord);
						if(zhRecord!=null) {
							//at this point we are sure that there are Handelsfakturor-ZH-PDFs (otherwise just do not send anything)
							logger.info("Doctyp:" + zhRecord.getDoctyp());
							logger.info("Doclnk:" + zhRecord.getDoclnk());
							logger.info("ehrg:" + sadmohfRecord.getEhrg());
							//String eh0068a_ISO = dateFormatter.convertToDate_ISO(recordToValidate.getEh0068a().toString());
							//logger.info("eh0068a_ISO:" + eh0068a_ISO);
							logger.info("eh0068a:" + sadmohfRecord.getEh0068a().toString());
							logger.info("eh0068b:" + sadmohfRecord.getEh0068b().toString());
						
							//(2) send it to the service as in the child-window, namely: 
							//example (manually): no.systema.tvinn.sad.manifest.express.controller.ajax.TvinnSadManifestAjaxHandlerController.sendFileToToll_TvinnSadManifest.do
							Set result = this.sendZHDocViaAPi(appUser, zhRecord, sadmohfRecord);
							logger.info("result-Set ZHDoc-Api:" + result.toString());
							
						}
						logger.info("##########################################################");
						logger.info("END API ZH-DOC send ... house [ehdkh]:" + sadmohfRecord.getEhdkh());
						logger.info("##########################################################");
					}
				
				}
			}
			
			
			
			successView = new ModelAndView(redirect.toString());
			
		}
		
		return successView;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param emlnrt
	 * @param emlnrm
	 * @return
	 */
	private List<SadmohfRecord> getHouses(String applicationUser, String lnrt, String lnrm) {
		List <SadmohfRecord> resultList = new ArrayList<SadmohfRecord>();
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&ehlnrt=" + lnrt + "&ehlnrm=" + lnrm;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		if(jsonContainer!=null && !jsonContainer.getList().isEmpty()) {
    			resultList = (List)jsonContainer.getList();
    		}
    	}
    	return resultList;
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
	    //when delete is triggered from a list (master list of houses)
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + Integer.parseInt(id1) + "&emlnrm=" + Integer.parseInt(id2) );
		if(StringUtils.isNotEmpty(layer)) {
			//when delete is triggered within the layer (house)
			successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=" + Integer.parseInt(id1) + "&ehlnrm=" + Integer.parseInt(id2) + "&ehlnrh=" + Integer.parseInt(id3) );
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
	 * Special only for invalidation errors
	 * @param recordToValidate
	 */
	private void adjustSenderReceiverCommunication(SadmohfRecord recordToValidate) {
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ehems_email())){
			recordToValidate.setEhems(recordToValidate.getOwn_ehems_email());
			recordToValidate.setEhemst(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEhems(recordToValidate.getOwn_ehems_telephone());
			recordToValidate.setEhemst(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		//Receiver
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ehemm_email())){
			recordToValidate.setEhemm(recordToValidate.getOwn_ehemm_email());
			recordToValidate.setEhemmt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEhemm(recordToValidate.getOwn_ehemm_telephone());
			recordToValidate.setEhemmt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param ehlnrt
	 * @param ehlnrm
	 * @param ehlnrm
	 * @param model
	 */
	private void setLastErrorText(SystemaWebUser appUser, String ehlnrt, String ehlnrm, String ehlnrh, Map model) {
		//fetch from error log 
    	StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_LOG_URL);
		StringBuilder urlRequestParamsKeys = new StringBuilder();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&ellnrt=" + ehlnrt);
		urlRequestParamsKeys.append("&ellnrm=" + ehlnrm);
		urlRequestParamsKeys.append("&ellnrh=" + ehlnrh);
		
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
	
	private int updateStatus2OnHouse(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	
	private int updateStatus3OnHouse(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	private int deleteHouse(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
		//Decl.date
		if(recordToValidate.getEh0068a()!=null && recordToValidate.getEh0068a() > 0) {
			
			String isoDeclDate = String.valueOf(recordToValidate.getEh0068a());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(isoDeclDate) && isoDeclDate.length()==8) {
				int declDate = Integer.parseInt(this.dateMgr.getDateFormatted_NO(isoDeclDate, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEh0068a(declDate);
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpns())) {
			if("NO".equals(recordToValidate.getEhlks())) {
				recordToValidate.setEhpns(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpns()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpnm())) {
			if("NO".equals(recordToValidate.getEhlkm())) {
				recordToValidate.setEhpnm(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpnm()),4,"0"));
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
			//do nothing (already in place)	as in: 123456789-0001-0000003-099
		}else {
			//House Document number and type (under fraktbrev for house nr API)
			this.getTransportDto(user, recordToValidate);
			logger.info("!!!!!!!! getting orgNr for EHDKH from Transport DTO:" + recordToValidate.getTransportDto().toString());
			//get the first part of the string, namely the OrnNr from the representative
			String orgNr = recordToValidate.getTransportDto().getEtrgr();
			if(StringUtils.isEmpty(orgNr)) {
				orgNr = recordToValidate.getTransportDto().getEtrgt(); //Carrier's OrgNr
			}
			recordToValidate.setEhdkh(this.houseControllerService.getRandomDocumentId(orgNr, recordToValidate));
			
			
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
			//AS400 typiskt för minus. We must invert so that the minus char is in the beginning
			if(tmp.endsWith("-")) {
				tmp =  tmp.replace("-", "");
				tmp = "-" + tmp;
				
			}
			BigDecimal bd = new BigDecimal(tmp).setScale(2, RoundingMode.HALF_UP);
			recordToValidate.setEhvkb(bd.toString());	
		}
		//Sent date
		if(recordToValidate.getEhdts()!=null && recordToValidate.getEhdts() > 0) {
			int sentDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEhdts()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEhdts(sentDate);
		}
		
		//Decl.date on eh0068a
		if(recordToValidate.getEh0068a()!=null && recordToValidate.getEh0068a() > 0) {
			
			int declDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEh0068aStr(), DateTimeManager.NO_FORMAT));
			logger.info("#############" + declDate);
			recordToValidate.setEh0068a(declDate);
			
		}
		
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpns())) {
			if("NO".equals(recordToValidate.getEhlks())) {
				recordToValidate.setEhpns(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpns()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpnm())) {
			if("NO".equals(recordToValidate.getEhlkm())) {
				recordToValidate.setEhpnm(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpnm()),4,"0"));
			}
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
		logger.info("getItemLines --->" + Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
    		record.setListItemLines(jsonContainer.getList());
    	}
    	
	}
	
	
	private int getHousesListSize(SystemaWebUser appUser, SadmomfRecord record) {
		int retval = 0;
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
    		
    		if(!record.getListHouses().isEmpty()) {
    			retval = record.getListHouses().size();
    		}
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
    	return retval;
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
	private AvdSignControllerService avdSignControllerService;
	
	@Autowired
	private ApiHouseSendService apiHouseSendService;
	@Autowired
	private ApiAsyncFacadeSendService apiAsynchFacadeSendService;
	
	@Autowired
	private SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	
	@Autowired
	private HouseControllerService houseControllerService;
	
	@Autowired
	private HouseDocLogControllerService houseDocLogControllerServiceLogger;
	
	@Autowired
	private ManifestExpressMgr manifestExpressMgr;
}

