package no.systema.tvinn.sad.digitollv2.controller;

import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
import no.systema.tvinn.sad.digitollv2.controller.service.ApiAsyncFacadeSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiTransportSendService;
import no.systema.tvinn.sad.digitollv2.filter.SearchFilterDigitollTransportList;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
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
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
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
            	if(StringUtils.isNotEmpty(searchFilter.getOpd()) && searchFilter.getOpd().length()<4){
            		model.put("errorMessage", "Opd må være minst 4 sifre...");
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
			    	//logger.debug(jsonPayload);
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
								this.adjustFieldsForFetch(record);
							}
							//logger.debug(outputList.toString());
						}
						
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
				//Validate
				TransportValidator validator = new TransportValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		this.setRecordAspects(appUser, recordToValidate);
		    		this.adjustOmbudCommunication(recordToValidate);
					//now we have all aspects in this transport
					model.put("record", recordToValidate);
					isValidForFetch = false;
		    		
			    }else{
			    	//adjust fields
					this.adjustFieldsForUpdate(recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(etlnrt) ){
						mode = SadDigitollConstants.DB_MODE_UPDATE;
					}else {
						mode = SadDigitollConstants.DB_MODE_INSERT;
					}
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
							//logger.debug(record.toString());
							
							//Only if ERROR
							if("M".equals(record.getEtst2())) {
								this.setLastErrorText(appUser, etlnrt, model);
							}
						}
						
					}
					
		    	}
		    	
		    	
		    	
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
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
    			}else if(element.startsWith("current_status")){
    				status = value;
    			}
    		}
    	}
	    logger.info("Id1:" + id1); logger.info("status:" + status);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2.do?action=doFind");
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
			if(StringUtils.isNotEmpty(id1) ) {
				
				recordToValidate.setEtlnrt(Integer.valueOf(id1));
				recordToValidate.setEtst(status);
				String mode = "US";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatusOnTransport(appUser.getUser(), recordToValidate, mode, errMsg);
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
	@RequestMapping(value="tvinnsaddigitollv2_api_send_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendTransport");
		
		String async = request.getParameter("async");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = null;;
		StringBuilder redirect = new StringBuilder();
		redirect.append("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + recordToValidate.getEtlnrt());
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEtlnrt() > 0 ) {
				if(StringUtils.isNotEmpty(async)) {
					//async if applicable
					this.apiAsynchFacadeSendService.sendTransport(appUser.getUser(), recordToValidate.getEtlnrt(), recordToValidate.getEtmid());
				}else {
					//normal synchronous default as a normal controller
					String redirectSuffix = apiTransportSendService.send(appUser.getUser(), recordToValidate.getEtlnrt(), recordToValidate.getEtmid());
					if(StringUtils.isNotEmpty(redirectSuffix)) {
						redirect.append(redirectSuffix);
					}
				}
	    		successView = new ModelAndView(redirect.toString());
	    		
			}else {
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendMaster -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
		}
		
		return successView;
		
	}
	
	/* OBSOLETE ... replaced with the above
	@RequestMapping(value="tvinnsaddigitollv2_api_send_transportOrig.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiSendTransportOrig(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("inside doApiSendTransport");
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = null;;
		StringBuilder redirect = new StringBuilder();
		redirect.append("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + recordToValidate.getEtlnrt());
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEtlnrt() > 0 ) {
		    	logger.info("Before send in Controller ...");
				
				StringBuilder url = new StringBuilder();
				StringBuilder urlRequestParamsKeys = new StringBuilder();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				
				url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
				//check if POST-CREATE or PUT-UPDATE
				if( StringUtils.isNotEmpty(recordToValidate.getEtmid()) ) {
					url.append("putTransport.do");
					urlRequestParamsKeys.append("&etlnrt=" + recordToValidate.getEtlnrt());
					urlRequestParamsKeys.append("&mrn=" + recordToValidate.getEtmid());
				}else {
					
					url.append("postTransport.do");
					urlRequestParamsKeys.append("&etlnrt=" + recordToValidate.getEtlnrt());
				}
				
				String BASE_URL = url.toString();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    		//Debug -->
		    	logger.info(jsonPayload);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		
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
				StringBuffer errMsg = new StringBuffer();
				errMsg.append("ERROR on doSendMaster -->detail: null ids? ...");
				model.put("errorMessage", errMsg.toString());

			}
		}
		
		return successView;
		
	}
	*/
	
	
	@RequestMapping(value="tvinnsaddigitollv2_api_delete_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiDeleteMaster(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<SadmomfRecord> outputList = new ArrayList<SadmomfRecord>();
		Map model = new HashMap();
		
		String action = "";
		String id1 = "";
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
    			}else if(element.startsWith("current_mrn")){
    				mrn = value;
    			}else if(element.startsWith("action")){
    				action = value;
    			}
    		}
    	}
	    logger.info("action:" + action);
	    logger.info("Id1:" + id1); logger.info("mrn:" + mrn);
	    
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2.do?action=doFind" );
		if(StringUtils.isNotEmpty(layer)) {
			//when delete is triggered within the layer (transport)
			successView = new ModelAndView("redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + Integer.parseInt(id1));
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
				url.append("deleteTransport.do");
				
				String BASE_URL = url.toString();
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&etlnrt=" + id1 + "&mrn=" + mrn;
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
	 * This method is the same that in the childWindow but refined by showing ONLY the last ERROR (if any)
	 * 
	 * @param appUser
	 * @param etlnrt
	 * @param model
	 */
	private void setLastErrorText(SystemaWebUser appUser, String etlnrt, Map model) {
		//fetch from error log 
    	StringBuilder url = new StringBuilder();
		url.append(SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_LOG_URL);
		StringBuilder urlRequestParamsKeys = new StringBuilder();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&ellnrt=" + etlnrt);
		
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
	private void setRecordAspects(SystemaWebUser appUser, SadmotfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all masters
		this.getMasters(appUser, record);
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateStatusOnTransport(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
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
		if(allEmpty(searchFilter))  {
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
			//Special (Opd from house) Deep search
			if(StringUtils.isNotEmpty(searchFilter.getOpd())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
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
	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownService(Map model) {
		List<GenericDropDownDto> dto = this.digitollDropDownListPopulationService.getContainerSizeAndType();
		model.put("containerSizeAndTypeDto", dto);
		//country
		dto = this.digitollDropDownListPopulationService.getCountryList(); model.put("countryDto", dto);
		//mode of transport
		dto = this.digitollDropDownListPopulationService.getModeOfTransportDto(); model.put("modeOfTransportDto", dto);
		//means of transport
		dto = this.digitollDropDownListPopulationService.getMeansOfTransportDto(); model.put("meansOfTransportDto", dto);
		//type of identification
		dto = this.digitollDropDownListPopulationService.getTypeOfIdentificationMeansOfTranportDto(); model.put("typeOfIdentificationMeansTransportDto", dto);
		
	}
	/**
	 * Special only for invalidation errors
	 * @param recordToValidate
	 */
	private void adjustOmbudCommunication(SadmotfRecord recordToValidate) {
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemr_email())){
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_email());
			recordToValidate.setEtemrt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_telephone());
			recordToValidate.setEtemrt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(SadmotfRecord recordToValidate){
		//Register date
		if(recordToValidate.getEtdtr()!=null && recordToValidate.getEtdtr() > 0) {
			String tmpEtdtr = String.valueOf(recordToValidate.getEtdtr());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtdtr) && tmpEtdtr.length()==8) {
				int isoEtdtr = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtdtr, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEtdtr(isoEtdtr);
			}
		}
		//ETA date
		if(recordToValidate.getEtetad()!=null && recordToValidate.getEtetad() > 0) {
			String tmpEtetatd = String.valueOf(recordToValidate.getEtetad());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtetatd) && tmpEtetatd.length()==8) {
				int isoEtetad = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtetatd, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEtetad(isoEtetad);
			}
		}
		//ETA time
		if(recordToValidate.getEtetat()!=null && recordToValidate.getEtetat() > 0) {
			String tmpEtetat = String.valueOf(recordToValidate.getEtetat());
			if(tmpEtetat.length()>4) {
				tmpEtetat = tmpEtetat.substring(0,4);
				recordToValidate.setEtetat(Integer.valueOf(tmpEtetat));
			}
			
		}
		
		//STA date
		if(recordToValidate.getEtshed()!=null && recordToValidate.getEtshed() > 0) {
			String tmpEtshed = String.valueOf(recordToValidate.getEtshed());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtshed) && tmpEtshed.length()==8) {
				int isoEtshed = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtshed, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEtshed(isoEtshed);
			}
		}
		//STA time
		if(recordToValidate.getEtshet()!=null && recordToValidate.getEtshet() > 0) {
			String tmpEtshet = String.valueOf(recordToValidate.getEtshet());
			if(tmpEtshet.length()>4) {
				tmpEtshet = tmpEtshet.substring(0,4);
				recordToValidate.setEtetat(Integer.valueOf(tmpEtshet));
			}
			
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEtpnt())) {
			if("NO".equals(recordToValidate.getEtlkt())) {
				recordToValidate.setEtpnt(StringUtils.leftPad(String.valueOf(recordToValidate.getEtpnt()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEtpnr())) {
			if("NO".equals(recordToValidate.getEtlkr())) {
				recordToValidate.setEtpnr(StringUtils.leftPad(String.valueOf(recordToValidate.getEtpnr()),4,"0"));
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
				recordToValidate.setEtemst(SadDigitollConstants.API_TYPE_EMAIL);
			}else {
				recordToValidate.setEtemst(SadDigitollConstants.API_TYPE_TELEPHONE);
			}
		}
		//Representative - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemr_email())){
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_email());
			recordToValidate.setEtemrt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEtemr(recordToValidate.getOwn_etemr_telephone());
			recordToValidate.setEtemrt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		//Carrier - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_email())){
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_email());
			recordToValidate.setEtemtt(SadDigitollConstants.API_TYPE_EMAIL);	
		}else {
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_telephone());
			recordToValidate.setEtemtt(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		
		//Register date
		if(recordToValidate.getEtdtr()!=null && recordToValidate.getEtdtr() > 0) {
			int regDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEtdtrStr(), DateTimeManager.NO_FORMAT));
			//logger.info("BBBB:" + regDate);
			recordToValidate.setEtdtr(regDate);
			
		}else {
			int regDate = Integer.valueOf(this.dateMgr.getCurrentDate_ISO());
			recordToValidate.setEtdtr(regDate);
		}
		
		//ETA - date to ISO
		if(recordToValidate.getEtetad()!=null && recordToValidate.getEtetad() > 0) {
			int isoEtetad = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEtetadStr(), DateTimeManager.NO_FORMAT));
			recordToValidate.setEtetad(isoEtetad);
		}else {
			recordToValidate.setEtetad(0);
		}
		//ETA - time to ISO
		if(recordToValidate.getEtetat()!=null && recordToValidate.getEtetat() > 0) {
			String tmp = String.valueOf(recordToValidate.getEtetat());
			String outStr = "";
			if(tmp.length()==3) {
				outStr = "0" + tmp;
				
			}else if(tmp.length()==4) {
				outStr = tmp ;
			}
			
			int okTimeDb = Integer.parseInt(outStr);
			recordToValidate.setEtetat(okTimeDb);
			
		}else {
			recordToValidate.setEtetat(0);
		}
		
		
		//STA - date to ISO
		if(recordToValidate.getEtshed()!=null && recordToValidate.getEtshed() > 0) {
			int isoEtshed = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEtshedStr(), DateTimeManager.NO_FORMAT));
			recordToValidate.setEtshed(isoEtshed);
		}else {
			recordToValidate.setEtshed(0);
		}
		//STA - time to ISO
		if(recordToValidate.getEtshet() !=null && recordToValidate.getEtshet() > 0) {
			String tmp = String.valueOf(recordToValidate.getEtshet());
			String outStr = "";
			if(tmp.length()==3) {
				outStr = "0" + tmp;
				
			}else if(tmp.length()==4) {
				outStr = tmp ;
			}
			
			int okTimeDb = Integer.parseInt(outStr);
			recordToValidate.setEtshet(okTimeDb);
			
		}else {
			recordToValidate.setEtshet(0);
		}
		
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEtpnt())) {
			if("NO".equals(recordToValidate.getEtlkt())) {
				recordToValidate.setEtpnt(StringUtils.leftPad(String.valueOf(recordToValidate.getEtpnt()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEtpnr())) {
			if("NO".equals(recordToValidate.getEtlkr())) {
				recordToValidate.setEtpnr(StringUtils.leftPad(String.valueOf(recordToValidate.getEtpnr()),4,"0"));
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
    		//now check if the transport is valid to be deleted or not.
    		//To be valid for deletion it is required to have masters and houses all deleted as well = without MRN att toll.no
    		//You are allow to delete a transport if and only if all children have been deleted from toll.no previously ... It has to do with the API since we must know which api (air or road)
    		//we are using...
    		List<SadmomfRecord> listChild = (List)jsonContainer.getList();
    		if(listChild!=null && !listChild.isEmpty()) {
    			for(SadmomfRecord child : listChild) {
	    			if(StringUtils.isNotEmpty(child.getEmmid())) {
	    				record.setOwn_okToDelete(false);
	    				break;
	    			}
	    		}
    			for(SadmomfRecord child : listChild) {
	    			//to heavy?: --> this is for GUI info
	    			this.getHouses(appUser, child);
	    		}
    		}else {
    			//OK
    		}
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
    		//this is in order to rise "red flag" on GUI 
    		List<SadmohfRecord>tmpList = (List)jsonContainer.getList();
    		for(SadmohfRecord house : tmpList) {
    			//to heavy?: --> this is for GUI info
    			if(house.getEhst2().equals("M")) {
    				record.setOwn_invalidHousesExist(true);
    				break;
    			}
    		}
    	}
    	
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidForApiSendGui(SadmomfRecord record) {
		boolean retval = true;
		if(StringUtils.isEmpty(record.getEmmid()) || StringUtils.isEmpty(record.getEmuuid())){
			if(record.getEmst().equals("S") ) { 
				//OK since this has been canceled(S)
			}else{
				if(record.getEmst2().equals("D") || record.getEmst2().equals("C")) {
					//OK since this has been deleted(D) or completed(C)
				}else {
					retval = false;
				}
			}
		}
		return retval;
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
	private SadmohfListService sadmohfListService;
	
	@Autowired
	private GeneralUpdateService generalUpdateService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	
	@Autowired
	private SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	
	@Autowired
	private ApiTransportSendService apiTransportSendService;
	@Autowired
	private ApiAsyncFacadeSendService apiAsynchFacadeSendService;
	
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;

}

