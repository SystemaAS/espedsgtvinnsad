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
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.validator.MasterValidator;
import no.systema.tvinn.sad.manifest.express.filter.SearchFilterManifestList;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
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
	private final String MODE_UPDATE = "U";
	private final String MODE_INSERT = "A";
	private final String TYPE_EMAIL = "EM";
	private final String TYPE_TELEPHONE = "TE";
	
	
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
		boolean isValid = true;
		
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
		    		this.adjustFieldsForFetch(recordToValidate);
					//get all masters
					this.getHouses(appUser, recordToValidate);
					//now we have all master consignments in this transport
					model.put("record", recordToValidate);
					isValid = false;
				
				}else{
			    	//adjust fields
					this.adjustFieldsForUpdate(recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm) ){
						mode = this.MODE_UPDATE;
						
					}else {
						mode = this.MODE_INSERT;
					}
					logger.info("MODE:" + mode + " before update in Controller ...");
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
					//this step is required for the FETCH-step since we want to get the newly created record for upcoming updates...
					if(mode.equals(this.MODE_INSERT)) {
						emlnrt = String.valueOf(recordToValidate.getEmlnrt());
						emlnrm = String.valueOf(recordToValidate.getEmlnrm());
					}
				}
			}
			

			//FETCH when applicable
			if((StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm)) && isValid ){
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
							this.adjustFieldsForFetch(record);
							//get all masters
							this.getHouses(appUser, record);
							//now we have all master consignments in this transport
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
	    	/*
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			*/
	    	
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
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
	private int updateRecord(String applicationUser, SadmomfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = -1;
		
		
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
					retval = 0;
					logger.warn(record.toString());
					if(mode.equals(this.MODE_INSERT)) {
						recordToValidate.setEmlnrt(record.getId());
						recordToValidate.setEmlnrm(record.getId2());
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
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFPR.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
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
			recordToValidate.setEmemst(this.TYPE_EMAIL);	
		}else {
			recordToValidate.setEmems(recordToValidate.getOwn_emems_telephone());
			recordToValidate.setEmemst(this.TYPE_TELEPHONE);
		}
		//Receiver - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_ememm_email())){
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_email());
			recordToValidate.setEmemmt(this.TYPE_EMAIL);	
		}else {
			recordToValidate.setEmemm(recordToValidate.getOwn_ememm_telephone());
			recordToValidate.setEmemmt(this.TYPE_TELEPHONE);
		}
		
		//Register date
		if(recordToValidate.getEmdtr() > 0) {
			int regDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEmdtr()), DateTimeManager.NO_FORMAT));
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
	private MaintMainKofastService maintMainKofastService;

}

