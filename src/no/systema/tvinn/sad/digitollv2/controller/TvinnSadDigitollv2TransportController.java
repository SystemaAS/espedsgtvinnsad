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
import no.systema.tvinn.sad.digitollv2.validator.TransportValidator;
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
public class TvinnSadDigitollv2TransportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2TransportController.class.getName());
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
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doListTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmotfRecord> outputList = new ArrayList<SadmotfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String gate = request.getParameter("gate");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			SearchFilterDigitollTransportList searchFilter = new SearchFilterDigitollTransportList();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
            //binder.registerCustomEditor(...); // if needed
            binder.bind(request);
            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            	session.setAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST, searchFilter);
            }else{
            	SearchFilterDigitollTransportList sessionFilter = (SearchFilterDigitollTransportList)session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST);
            	if(sessionFilter!=null){
            		//Use the session filter when applicable
            		searchFilter = sessionFilter;
            		
            	}else{
            		//first time propose today
            		searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            	}
            }
            
            if(StringUtils.isNotEmpty(gate)) {
            	//nothing
            	//The first search from the Digitoll meny is the gate and it should not fire any query...
            	//with no gate as parameter will be normal-behavior in the search GUI (with filter)
            	
            }else {
            	
	            //get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
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
					outputList = jsonContainer.getList();
					if(outputList!=null && outputList.size() > SadmotfContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS){
						outputList = new ArrayList();
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, "Too many lines. Narrow your search please ...");
					}else{
						for(SadmotfRecord record: outputList){
							/*//check if the manifest cargo lines are valid
							if(!manifestExpressMgr.isValidManifest(appUser, record.getEfpro())){
								record.setOwn_valid(-1);
							}
							//check it the manifest is editable
							if(!manifestExpressMgr.isEditableManifest(appUser, record)){
								record.setOwn_editable(-1);
							}
							*/
							//dates
							this.adjustFieldsForFetch(record);
							
						}
						logger.info(outputList.toString());
					}
					
		    	}	
            }
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			
			//domain and search filter
			successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
			successView.addObject(TvinnSadConstants.DOMAIN_LIST_SIZE, outputList.size());	
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
    			
			if (session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST) == null || session.getAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST).equals("")){
				successView.addObject(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST, searchFilter);
			}
	    	
			//this.populateCustomsOfficeOfFirstEntryHtmlDropDown(model);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmotfRecord> outputList = new ArrayList<SadmotfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_transport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//logger.debug(recordToValidate.toString());
		
		String action = request.getParameter("action");
		String etlnrt = request.getParameter("etlnrt");
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
				//Validate
				TransportValidator validator = new TransportValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		this.setRecordAspects(appUser, recordToValidate);
					//now we have all aspects in this transport
					model.put("record", recordToValidate);
					isValidForFetch = false;
		    		
			    }else{
			    	//adjust fields
					this.adjustFieldsForUpdate(recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(etlnrt) ){
						mode = this.MODE_UPDATE;
					}else {
						mode = this.MODE_INSERT;
					}
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
					if(dmlRetval < 0) {
						//error on update
						model.put("errorMessage", errMsg.toString());
						//put all aspects (sub-lists) only with update (not insert) error
						if(this.MODE_UPDATE.equals(mode)){
							this.setRecordAspects(appUser, recordToValidate);
						}
						model.put("record", recordToValidate);
						isValidForFetch = false;
					}else {
						//this step is required for the FETCH-step since we want to get the newly created record for upcoming updates...
						if(mode.equals(this.MODE_INSERT)) {
							etlnrt = String.valueOf(recordToValidate.getEtlnrt());
						}
					}
			    }
			}
			
			//FETCH when applicable
			if(StringUtils.isNotEmpty(etlnrt) && isValidForFetch ){
				//FETCH record
	            //get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = "user=" + appUser.getUser() + "&etlnrt=" + etlnrt;
	    		
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
		    		outputList = jsonContainer.getList();
					if(outputList!=null){
						for(SadmotfRecord record: outputList){
							this.setRecordAspects(appUser, record);
							//now we have all aspects in this record
							model.put("record", record);
							logger.info(record.toString());
						}
						
					}
					
		    	}	
			}
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 */
	private void setRecordAspects(SystemaWebUser appUser, SadmotfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all masters
		this.getMasters(appUser, record);
	}
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
	private int updateRecord(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_TRANSPORT_URL;
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
					logger.warn(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}else {
						if(mode.equals(this.MODE_INSERT)) {
							recordToValidate.setEtlnrt(record.getId());
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
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterDigitollTransportList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(allEmpty(searchFilter)) {
			int DAYS_BACK = -3;
			String tmpISO = this.dateMgr.getSpecificDayFrom_CurrentDate_ISO(DAYS_BACK);
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etdtr=" + tmpISO );
			//put the value now
			searchFilter.setDatum(this.dateFormatter.convertToDate_NO(tmpISO));
			
		}else {
			if(StringUtils.isNotEmpty(searchFilter.getAvd())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etavd=" + searchFilter.getAvd());
			}
			if(StringUtils.isNotEmpty(searchFilter.getTurnr())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etpro=" + searchFilter.getTurnr());
			}
			if(StringUtils.isNotEmpty(searchFilter.getSign())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etsg=" + searchFilter.getSign());
			}
			
			//Reg date
			if(StringUtils.isNotEmpty(searchFilter.getDatum())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etdtr=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatum()));
			}
			if(StringUtils.isNotEmpty(searchFilter.getDatumt())){
				//fromDate must be in place for this to work
				if(StringUtils.isNotEmpty(searchFilter.getDatum())){
					urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etdtr_to=" + this.dateFormatter.convertToDate_ISO(searchFilter.getDatumt()));
				}else {
					searchFilter.setDatumt("");
				}
			}
			
			//ETA date
			if(StringUtils.isNotEmpty(searchFilter.getEtaDatum())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etetad=" + this.dateFormatter.convertToDate_ISO(searchFilter.getEtaDatum()));
			}
			
			if(StringUtils.isNotEmpty(searchFilter.getEtaDatumt())){
				//fromDate must be in place for this to work
				if(StringUtils.isNotEmpty(searchFilter.getEtaDatum())){
					urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etetad_to=" + this.dateFormatter.convertToDate_ISO(searchFilter.getEtaDatumt()));
				}else {
					searchFilter.setEtaDatumt("");
				}
				
			}
		}
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * Limit the search in case of non-filter
	 * @param searchFilter
	 * @return
	 */
	private boolean allEmpty(SearchFilterDigitollTransportList searchFilter) {
		boolean retval = false;
		if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
		   StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) && StringUtils.isEmpty(searchFilter.getEtaDatumt()) ){
			   retval = true;
		   } else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				   StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isNotEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) && StringUtils.isEmpty(searchFilter.getEtaDatumt())) {
			   
			   searchFilter.setDatumt("");
			   retval = true;
			   
		   } else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				   StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) && StringUtils.isNotEmpty(searchFilter.getEtaDatumt())) {
			   
			   searchFilter.setEtaDatumt("");
			   retval = true;
			   
		   } else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				   StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isNotEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) && StringUtils.isNotEmpty(searchFilter.getEtaDatumt())) {
			   
			   searchFilter.setDatumt("");
			   searchFilter.setEtaDatumt("");
			   retval = true;
			   
		   }
		return retval;
		
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
	
	private void adjustFieldsForFetch(SadmotfRecord recordToValidate){
		//Register date
		if(recordToValidate.getEtdtr() > 0) {
			String tmpEtdtr = String.valueOf(recordToValidate.getEtdtr());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtdtr) && tmpEtdtr.length()==8) {
				int isoEtdtr = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtdtr, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEtdtr(isoEtdtr);
			}
		}
		//ETA date
		if(recordToValidate.getEtetad() > 0) {
			String tmpEtetatd = String.valueOf(recordToValidate.getEtetad());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtetatd) && tmpEtetatd.length()==8) {
				int isoEtetad = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtetatd, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEtetad(isoEtetad);
			}
		}
		//ETA time
		if(recordToValidate.getEtetat() > 0) {
			String tmpEtetat = String.valueOf(recordToValidate.getEtetat());
			if(tmpEtetat.length()>4) {
				tmpEtetat = tmpEtetat.substring(0,4);
				recordToValidate.setEtetat(Integer.valueOf(tmpEtetat));
			}
			
		}
		
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(SadmotfRecord recordToValidate){
		
		//Driver - communication
		if(StringUtils.isNotEmpty(recordToValidate.getEtems())){
			if(recordToValidate.getEtems().contains("@")) {
				recordToValidate.setEtemst(this.TYPE_EMAIL);
			}else {
				recordToValidate.setEtemst(this.TYPE_TELEPHONE);
			}
		}
		//Representative - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemr_email())){
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_email());
			recordToValidate.setEtemrt(this.TYPE_EMAIL);	
		}else {
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_telephone());
			recordToValidate.setEtemrt(this.TYPE_TELEPHONE);
		}
		//Carrier - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_email())){
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_email());
			recordToValidate.setEtemtt(this.TYPE_EMAIL);	
		}else {
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_telephone());
			recordToValidate.setEtemtt(this.TYPE_TELEPHONE);
		}
		
		//Register date
		if(recordToValidate.getEtdtr() > 0) {
			int regDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEtdtr()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEtdtr(regDate);
		}else {
			int regDate = Integer.valueOf(this.dateMgr.getCurrentDate_ISO());
			recordToValidate.setEtdtr(regDate);
		}
		
		//ETA - date to ISO
		if(recordToValidate.getEtetad() > 0) {
			int isoEtetad = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(String.valueOf(recordToValidate.getEtetad()), DateTimeManager.NO_FORMAT));
			recordToValidate.setEtetad(isoEtetad);
		}
		//ETA - time to ISO
		if(recordToValidate.getEtetat() > 0) {
			String tmp = String.valueOf(recordToValidate.getEtetat());
			String outStr = "";
			if(tmp.length()==3) {
				outStr = "0" + tmp;
				
			}else if(tmp.length()==4) {
				outStr = tmp ;
			}
			
			int okTimeDb = Integer.parseInt(outStr);
			recordToValidate.setEtetat(okTimeDb);
			
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
	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	
	@Autowired
	private GeneralUpdateService generalUpdateService;
	
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;

}
