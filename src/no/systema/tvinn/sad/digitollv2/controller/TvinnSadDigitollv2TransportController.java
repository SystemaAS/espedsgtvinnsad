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
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
//Avd/Sign
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiAsyncFacadeSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.ApiTransportSendService;
import no.systema.tvinn.sad.digitollv2.controller.service.AvdSignControllerService;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus2;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus2;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmotfStatus3;
import no.systema.tvinn.sad.digitollv2.filter.SearchFilterDigitollTransportList;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
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
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.digitollv2.validator.TransportValidator;
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
public class TvinnSadDigitollv2TransportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2TransportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private DateTimeManager dateMgr = new DateTimeManager();
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
		String sadi = request.getParameter("sadi");
		
		
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
            		if(StringUtils.isNotEmpty(sadi)) {
            			sessionFilter = null;
            			session.removeAttribute(TvinnSadConstants.SESSION_SEARCH_FILTER_SADDIGITOLL_TRANSPORT_LIST);
            		}else {
            			//Use the session filter when applicable
            			searchFilter = sessionFilter;
            		}
            	}else{
            		if(StringUtils.isNotEmpty(searchFilter.getLnr()) && StringUtils.isNotEmpty(sadi) ) {
            			//no date since it is comming from outside the Digitoll-Tab (redirecting from SAD-Import to Digitoll via lnr)
            		}else {
            			//first time propose today
            			searchFilter.setEtaDatum(dateMgr.getNewDateFromNow(DateTimeManager.NO_FORMAT, -1));
            		}
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
			    	logger.info(jsonPayload);
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		
			    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
			    		//----------------------------------------------------------------
						//now filter the topic list with the search filter (if applicable)
						//----------------------------------------------------------------
						if(jsonContainer!=null) {
				    		outputList = jsonContainer.getList();
							Collection<SadmotfRecord> outputListWithRedFlags = jsonContainer.getList();
							if(outputList!=null && outputList.size() > SadmotfContainer.LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS){
								outputList = new ArrayList();
								model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, "Too many lines. Narrow your search please ...");
							}else{
								for(SadmotfRecord record: outputList){
									//to catch master id's on gui
									this.getMasterInfo(appUser, record);
									//
									this.adjustFieldsForFetch(record);
									//Special search for red flags/ yellow flags
									//this in order to get a redFlag/yellowFlag (defect masters or houses)
									if(StringUtils.isNotEmpty(request.getParameter("showErrorLayers"))) {
										if(outputList.size() < SadDigitollConstants.MAX_NUMBER_OF_LINES_FOR_DEEPSEARCH_REDFLAG_ON_TRANSPORT_MAINLIST) { //temporarily to test and avoid hang-ups
											this.getMasterHouseRedFlagMainList(appUser, record);
										}else {
											model.put("errorMessage", "For mange linjer for dyptsøk på -Vis error-flagg M/H nivå-");
										}
									}else {
										//for presentation purposes (master info as e.g MRN)
										//this.getMastersLightList(appUser, record);
									}
								}
								//logger.debug(outputList.toString());
							}
						}
						
			    	}
            	}
            }
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.avdSignControllerService.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			//this is necessary since not all customers have access to webservices without making firewall changes (take time...)
			model.put("eoriValidationActive",AppConstants.EORI_VALIDATION_ACTIVE);
			//
			model.put("routingId",this.getRoutingIdForChildWindow(appUser));
			
			//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
			session.setAttribute(session.getId() + TvinnSadConstants.SESSION_LIST, outputList);
			
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
				//======================================================================================================================================
				//Check for duplicate only with CREATE NEW and if turnr >0 (some customers may have to send a turnr=-1 dummyplace-holder
				// turnr = -1 should be allowed to be duplicated! (111 as an extra number since some customers have problems with -1 in their keeboards
				//======================================================================================================================================
				if(recordToValidate.getEtpro()>0 && StringUtils.isEmpty(etlnrt)) {
					if(recordToValidate.getEtpro()!=111) {
						Boolean isDuplicateTurnr = this.isDuplicateTurnumber(appUser, recordToValidate);
						logger.info(isDuplicateTurnr.toString());
						recordToValidate.setIsDuplicateTurnr(isDuplicateTurnr);
					}
				}
				
				//Validate
				TransportValidator validator = new TransportValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		if(recordToValidate.getEtlnrt()>0) {
		    			this.setRecordAspects(appUser, recordToValidate);
		    		}
		    		this.adjustOmbudCommunication(recordToValidate);
		    		this.adjustCarrierCommunication(recordToValidate);
					//now we have all aspects in this transport
					model.put("record", recordToValidate);
					isValidForFetch = false;
		    		
			    }else{
			    	StringBuffer errMsg = new StringBuffer();
			    	int dmlRetval = 0;
					
			    	// this is a little deviation only for the auto-generated records...
			    	if("Z".equals( recordToValidate.getEtst2())){
						//adjust fields in Master & house (cascade) before we eliminate Z-status in the parent transport (ETST2 = Z) AUTO-GEN
						this.updateAutoGenChildren(appUser.getUser(), recordToValidate, errMsg);
					}
					
					
			    	//adjust fields for transport
					this.adjustFieldsForUpdate(recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(etlnrt) ){
						mode = SadDigitollConstants.DB_MODE_UPDATE;
					}else {
						mode = SadDigitollConstants.DB_MODE_INSERT;
					}
					
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
						}else {
							this.setRecordAspects(appUser, recordToValidate);
							//only UPDATE --> when having already masters in order to update the emrgt (transp.orgNr on sadmomf...)
							//this case is usually present when the AUTO-GEN option has been carried out (integration with CB's TMS-systems and Digitoll)
							if(recordToValidate.getListMasters()!=null && !recordToValidate.getListMasters().isEmpty()) {
								this.updateTranspOrgNrOnMaster(appUser.getUser(), recordToValidate, errMsg);
							}
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
							model.put("routingId",this.getRoutingIdForChildWindow(appUser));
							//now we have all aspects in this record
							model.put("record", record);
							//logger.debug(record.toString());
							
							//This is done to block the Orgnr/EORI from carrier to be updated since master/houses depend on it
							//Only under certain circumstances ...
							if(this.masterMrnExist(appUser, record)) {
								model.put("masterMrnExists", "1");
							}
							
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
			this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.avdSignControllerService.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			this.setDropDownService(model);
			//this is necessary since not all customers have access to webservices without making firewall changes (take time...)
			model.put("eoriValidationActive",AppConstants.EORI_VALIDATION_ACTIVE);
			
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	
	@RequestMapping(value="tvinnsaddigitollv2_delete_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDelete(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		String id1 = "";
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		//logger.warn("####################################################");
    			//logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("current_id1")){
    				id1 = value;
    			}
    		}
    	}
	    logger.info("Id1:" + id1);
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
			if(StringUtils.isNotEmpty(id1)) {
				
				recordToValidate.setEtlnrt(Integer.valueOf(id1));
				
				String mode = "D";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.deleteTransport(appUser.getUser(), recordToValidate, mode, errMsg);
				
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
	 * It happens when the AUTO-GENERATED Transport with std-signature=SYS is assigned to another signature (from the list of transports (GUI)) 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_reassignSignatur_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateSignatur(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String signatur = "";
		boolean calledFromList = true; 
		//(1) call coming from the transport list
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		//logger.warn("####################################################");
    			//logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("current_id1")){
    				id1 = value;
    			}else if(element.startsWith("current_sg")){
    				signatur = value;
    			}
    		}
    	}
	    //N/A ??
	    /*
	    if(StringUtils.isEmpty(id1)) {
	    	//(2) this means that the call is not coming from the list of transport and instead coming from the transport-form
	    	id1= String.valueOf(recordToValidate.getEtlnrt());
	    	signatur = recordToValidate.getEtst();
	    	calledFromList = false;
	    }
	    */
	    logger.info("Id1:" + id1); logger.info("new sign:" + signatur);
	    String url = "redirect:tvinnsaddigitollv2.do?action=doFind";
	    if(!calledFromList) {
	    	//N/A ?
	    	//url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + id1;
	    }
	   
		ModelAndView successView = new ModelAndView(url);
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//==============
			//Upd signature
			//==============
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Update/Insert
			if(StringUtils.isNotEmpty(id1) ) {
				
				recordToValidate.setEtlnrt(Integer.valueOf(id1));
				recordToValidate.setEtsg(signatur);
				String mode = "REASSIGN";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.reassignSignaturOnTransport(appUser.getUser(), recordToValidate, mode, errMsg);
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
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String status = "";
		boolean calledFromList = true; 
		//(1) call coming from the transport list
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
	    if(StringUtils.isEmpty(id1)) {
	    	//(2) this means that the call is not coming from the list of transport and instead coming from the transport-form
	    	id1= String.valueOf(recordToValidate.getEtlnrt());
	    	status = recordToValidate.getEtst();
	    	calledFromList = false;
	    }
	    
	    logger.info("Id1:" + id1); logger.info("status:" + status);
	    String url = "redirect:tvinnsaddigitollv2.do?action=doFind";
	    if(!calledFromList) {
	    	url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + id1;
	    }
	   
		ModelAndView successView = new ModelAndView(url);
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
	
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus2_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus2(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String status2 = "";
		//from form
		id1= String.valueOf(recordToValidate.getEtlnrt());
    	status2 = recordToValidate.getEtst2();
    	
	    
	    logger.info("Id1:" + id1); logger.info("status2:" + status2);
	    String url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + id1;
	    
	   
		ModelAndView successView = new ModelAndView(url);
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
				recordToValidate.setEtst2(status2);
				String mode = "US2";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus2OnTransport(appUser.getUser(), recordToValidate, mode, errMsg);
				if(dmlRetval < 0) {
					//error on update
					model.put("errorMessage", errMsg.toString());
				}
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}	
	
	@RequestMapping(value="tvinnsaddigitollv2_updateInternalStatus3_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateStatus3(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
		String id1 = "";
		String status3 = "";
		//from form
		id1= String.valueOf(recordToValidate.getEtlnrt());
    	status3 = recordToValidate.getEtst3();
    	
	    
	    logger.info("Id1:" + id1); logger.info("status3:" + status3);
	    String url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + id1;
	    
	   
		ModelAndView successView = new ModelAndView(url);
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
				recordToValidate.setEtst3(status3);
				String mode = "US3";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.updateStatus3OnTransport(appUser.getUser(), recordToValidate, mode, errMsg);
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
	@RequestMapping(value="tvinnsaddigitollv2_resetMrn_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doResetMrn(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
	    logger.info("etlnrt:" + recordToValidate.getEtlnrt()); logger.info("etmid_own:" + recordToValidate.getEtmid_own());
	    String url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + recordToValidate.getEtlnrt();
	    
	   
		ModelAndView successView = new ModelAndView(url);
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//==========
			//Upd Mrn
			//==========
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Update/Insert
			if(recordToValidate.getEtlnrt() >0 ) {
				
				String mode = "RESET_MRN";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0; 
				dmlRetval = this.resetMrn(appUser.getUser(), recordToValidate, mode, errMsg);
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
	 * This is a very special case. When the etmid and the etmid_own where not created in the db despite the fact that the etmid was created at toll.no
	 * The user then will proceed to input the known-MRN value and save it (by updating the sadmotf-db-table)
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_resetMrnManually_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doResetMrnManually(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		
	    logger.info("etlnrt:" + recordToValidate.getEtlnrt()); 
	    logger.info("etmid_own:" + recordToValidate.getEtmid_own());
	    String url = "redirect:tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + recordToValidate.getEtlnrt();
	    
	   
		ModelAndView successView = new ModelAndView(url);
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//==========
			//Upd Mrn
			//==========
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Update/Insert
			if(recordToValidate.getEtlnrt() >0 ) {
				
				String mode = "RESET_MRN_MANUALLY";
				logger.info("MODE:" + mode + " before update in Controller ...");
				
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0; 
				dmlRetval = this.resetMrn(appUser.getUser(), recordToValidate, mode, errMsg);
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
			logger.info("ASYNC:" + async);
			//=================
			//SEND POST or PUT
			//=================
			if(recordToValidate.getEtlnrt() > 0 ) {
				if(StringUtils.isNotEmpty(async)) {
					//set st3 as pending in transport to block the Send button until finished
					this.apiTransportSendService.setSt3_Transport(appUser.getUser(), recordToValidate.getEtlnrt(), EnumSadmotfStatus3.PENDING.toString());
					//async if applicable
					this.apiAsynchFacadeSendService.sendTransport(appUser.getUser(), recordToValidate.getEtlnrt(), recordToValidate.getEtmid());
				}else {
					//normal synchronous default as a normal controller
					//set st3 as pending in transport to block the Send button until finished
					this.apiTransportSendService.setSt3_Transport(appUser.getUser(), recordToValidate.getEtlnrt(), EnumSadmotfStatus3.PENDING.toString());
					//sync
					String redirectSuffix = apiTransportSendService.send(appUser.getUser(), recordToValidate.getEtlnrt(), recordToValidate.getEtmid());
					if(StringUtils.isNotEmpty(redirectSuffix)) {
						redirect.append(redirectSuffix);
					}
				}
	    	
			}else {
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
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_api_delete_transport.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doApiDeleteTransport(@ModelAttribute ("record") SadmotfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
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
	
	
	private int deleteTransport(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	private int reassignSignaturOnTransport(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateStatus2OnTransport(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	private int updateStatus3OnTransport(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	private int resetMrn(String applicationUser, SadmotfRecord recordToValidate, String mode, StringBuffer errMsg) {
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
	
	private int updateTranspOrgNrOnMaster(String applicationUser, SadmotfRecord recordToValidate, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_TRANSP_ORGNR_ON_MASTER_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&emlnrt=" + recordToValidate.getEtlnrt() + "&emrgt=" + recordToValidate.getEtrgt());
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
					}
				}
			}
    	}
    	
    	return retval;
	}
	/**
	 * update some fields in the children tables (1) master: SADMOMF and (2) house: SADMOHF only with status Z
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int updateAutoGenChildren(String applicationUser, SadmotfRecord recordToValidate, StringBuffer errMsg) {
		int retval = 0;
		logger.info("Inside updateAutoGenChildren ...");
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_AUTOGEN_CHILDREN_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
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
						//OK
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
			if(StringUtils.isNotEmpty(searchFilter.getStatus())){
				//We must do this since we are mixing etst and etst2 in the same SQL-query
				if("K".equals(searchFilter.getStatus())){
					urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etst=" + "S");
				}else {
					urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etst2=" + searchFilter.getStatus());
				}
				
			}
			//FILTER multiple (DSV req. BENTEs mail 7.Nov.2024...must exclude some status or include variations
			if(StringUtils.isNotEmpty(searchFilter.getCb_C())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_C=" + searchFilter.getCb_C());	
			}
			if(StringUtils.isNotEmpty(searchFilter.getCb_N())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_N=" + searchFilter.getCb_N());
			}
			if(StringUtils.isNotEmpty(searchFilter.getCb_M())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_M=" + searchFilter.getCb_M());
			}
			
			if(StringUtils.isNotEmpty(searchFilter.getCb_EMPTY())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_EMPTY=" + searchFilter.getCb_EMPTY());
			}
			if(StringUtils.isNotEmpty(searchFilter.getCb_D())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_D=" + searchFilter.getCb_D());
			}
			if(StringUtils.isNotEmpty(searchFilter.getCb_S())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_S=" + searchFilter.getCb_S());
			}
			if(StringUtils.isNotEmpty(searchFilter.getCb_Z())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "cb_Z=" + searchFilter.getCb_Z());
			}
			
			
			//Special (Opd from house) Deep search
			if(StringUtils.isNotEmpty(searchFilter.getOpd())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
			}
			
			//Special (Extref from house) Deep search
			if(StringUtils.isNotEmpty(searchFilter.getExtref())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "extref=" + searchFilter.getExtref());
			}
			
			//Special (masterid from master) special search
			if(StringUtils.isNotEmpty(searchFilter.getMasterId())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "masterid=" + searchFilter.getMasterId());
			}
			
			//Bilnr
			if(StringUtils.isNotEmpty(searchFilter.getTranspId())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etkmrk=" + searchFilter.getTranspId());
			}
			//Lnr
			if(StringUtils.isNotEmpty(searchFilter.getLnr())){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "etlnrt=" + searchFilter.getLnr());
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
				StringUtils.isEmpty(searchFilter.getStatus()) && StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) 
				&& StringUtils.isEmpty(searchFilter.getEtaDatumt()) && StringUtils.isEmpty(searchFilter.getTranspId()) && StringUtils.isEmpty(searchFilter.getLnr()) ){
			   
			retval = true;
		
		} else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				StringUtils.isEmpty(searchFilter.getStatus()) && StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isNotEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) 
				&& StringUtils.isEmpty(searchFilter.getEtaDatumt()) && StringUtils.isEmpty(searchFilter.getTranspId()) && StringUtils.isEmpty(searchFilter.getLnr()) ) {
		   
		   searchFilter.setDatumt("");
		   retval = true;
		   
		} else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				StringUtils.isEmpty(searchFilter.getStatus()) && StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) 
				&& StringUtils.isNotEmpty(searchFilter.getEtaDatumt()) && StringUtils.isEmpty(searchFilter.getTranspId()) && StringUtils.isEmpty(searchFilter.getLnr()) ) {
		   
		   searchFilter.setEtaDatumt("");
		   retval = true;
		   
		} else if(StringUtils.isEmpty(searchFilter.getAvd()) && StringUtils.isEmpty(searchFilter.getOpd()) && StringUtils.isEmpty(searchFilter.getSign()) && StringUtils.isEmpty(searchFilter.getTurnr()) &&
				StringUtils.isEmpty(searchFilter.getStatus()) && StringUtils.isEmpty(searchFilter.getDatum()) && StringUtils.isNotEmpty(searchFilter.getDatumt()) && StringUtils.isEmpty(searchFilter.getEtaDatum()) 
				&& StringUtils.isNotEmpty(searchFilter.getEtaDatumt()) && StringUtils.isEmpty(searchFilter.getTranspId()) && StringUtils.isEmpty(searchFilter.getLnr()) ) {
		   
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
		}
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemr_telephone())){
			recordToValidate.setEtemrx(recordToValidate.getOwn_etemr_telephone());
			recordToValidate.setEtemrtx(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
	}
	private void adjustCarrierCommunication(SadmotfRecord recordToValidate) {
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_email())){
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_email());
			recordToValidate.setEtemtt(SadDigitollConstants.API_TYPE_EMAIL);	
		}
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_telephone())){
			recordToValidate.setEtemtx(recordToValidate.getOwn_etemt_telephone());
			recordToValidate.setEtemttx(SadDigitollConstants.API_TYPE_TELEPHONE);
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
		
		//Bil regnr ( no spaces )
		if(recordToValidate.getEtkmrk()!=null) {
			String str = recordToValidate.getEtkmrk();
			str = str.trim();
			str = str.replaceAll(" ", "");
			recordToValidate.setEtkmrk(str);
		}
		
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
		}
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemr_telephone())){
			recordToValidate.setEtemrx(recordToValidate.getOwn_etemr_telephone());
			recordToValidate.setEtemrtx(SadDigitollConstants.API_TYPE_TELEPHONE);
		}
		
		//Carrier - communication
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_email())){
			recordToValidate.setEtemt(recordToValidate.getOwn_etemt_email());
			recordToValidate.setEtemtt(SadDigitollConstants.API_TYPE_EMAIL);	
		}
		if(StringUtils.isNotEmpty(recordToValidate.getOwn_etemt_telephone())){
			recordToValidate.setEtemtx(recordToValidate.getOwn_etemt_telephone());
			recordToValidate.setEtemttx(SadDigitollConstants.API_TYPE_TELEPHONE);
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
			//Original --> recordToValidate.setEtshed(0);
			
			//copy from ETA
			recordToValidate.setEtshed(recordToValidate.getEtetad());
			
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
			//Original --> recordToValidate.setEtshet(0);
			//copy from ETA
			recordToValidate.setEtshet(recordToValidate.getEtetat());
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
		//AUTO-GENERATED transports (status = Z) must removed that state as soon as someone saves the transport manually
		if("Z".equals(recordToValidate.getEtst2())){
			recordToValidate.setEtst2("");
		}
		
	}
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getMasterHouseRedFlagMainList(SystemaWebUser appUser, SadmotfRecord record) {
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
    		//now check if the transport has errors on all layers below (master-layer and house-layer)
    		List<SadmomfRecord> listChild = (List)jsonContainer.getList();
    		if(listChild!=null && !listChild.isEmpty()) {
    			for(SadmomfRecord child : listChild) {
	    			if(child.getEmst2().equals(EnumSadmomfStatus2.M.toString())) {
	    				logger.info("RED!!!");
	    				record.setOwn_invalidMastersExist(true);
	    				break;
	    			}else {
	    				//this is in order to rise "red flag" on GUI
	    				this.getHouses(appUser, child, record);
	    			}
	    		}
    			//check for unsent masters ...
    			for(SadmomfRecord child : listChild) {
    				logger.info("status2:" + child.getEmst2());
	    			if(!child.getEmst2().equals(EnumSadmomfStatus2.S.toString())) {
	    				if(!child.getEmst2().equals(EnumSadmomfStatus2.C.toString())) {
	    					logger.info("YELLOW flag!!!");
	    					record.setOwn_unsentMastersExist(true);
	    					break;
	    				}
	    			}
	    		}
    			
    		}else {
    			//OK
    		}
    	}
    	
	}
	
	private void getMasterInfo(SystemaWebUser appUser, SadmotfRecord record) {
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
	private boolean masterMrnExist(SystemaWebUser appUser, SadmotfRecord record) {
		boolean retval = false;
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
    		//now check if the transport has errors on all layers below (master-layer and house-layer)
    		List<SadmomfRecord> listChild = (List)jsonContainer.getList();
    		if(listChild!=null && !listChild.isEmpty()) {
    			for(SadmomfRecord child : listChild) {
	    			if(child.getEmmid() != null && StringUtils.isNotEmpty(child.getEmmid())) {
	    				logger.info("MrnMasterExists!!!");
	    				retval = true;
	    				break;
	    			}
	    		}
    			
    		}else {
    			//OK
    		}
    	}
    	
    	return retval;
	}
	
	/**
	 * This is in order to present handy master information at the highest level. E.g MRN in order to search in Transport main list
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getMastersLightList(SystemaWebUser appUser, SadmotfRecord record) {
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
	    			this.getHouses(appUser, child, record);
	    		}
    			//this is in order to rise "red flag" on GUI 
        		List<SadmomfRecord>tmpList = listChild;
        		for(SadmomfRecord master : tmpList) {
        			//this is for GUI info
        			if(master.getEmst2().equals(EnumSadmomfStatus2.M.toString())) {
        				record.setOwn_invalidMastersExist(true);
        				break;
        			}
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
	private void getHouses(SystemaWebUser appUser, SadmomfRecord record, SadmotfRecord parent) {
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
    			if(house.getEhst2().equals(EnumSadmohfStatus2.M.toString())) {
    				record.setOwn_invalidHousesExist(true);
    				parent.setOwn_invalidHousesExist(true);
    				break;
    			}
    		}
    		//unsent houses check
    		for(SadmohfRecord house : tmpList) {
    			//to heavy?: --> this is for GUI info
    			if(!house.getEhst2().equals(EnumSadmohfStatus2.S.toString())) {
    				if(!house.getEhst2().equals(EnumSadmohfStatus2.C.toString())) {
    					//record.setOwn_invalidHousesExist(true);
    					parent.setOwn_unsentHousesExist(true);
    					break;
    				}
    			}
    		}
    		//check for invalid Sekv.nr on houses
    		for(SadmohfRecord house : tmpList) {
    			//only if the Mrn exists and if the status = SUBMITTED
    			if(house.getEhst2().equals(EnumSadmohfStatus2.S.toString()) && StringUtils.isNotEmpty(house.getEhmid())) {
	    			if(house.getIncltdoc()!=null && StringUtils.isNotEmpty(house.getIncltdoc())) {
	    				parent.setOwn_invalidSekvnrOnHouse(true);
	    				break;
	    			}
    			}
    		}
    		
    	}
    	
	}
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRoutingIdForChildWindow(SystemaWebUser appUser) {
		String retval = "";
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_ROUTINGID_MOVEMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
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
						break;
					}else {
						retval = record.getRid();
					}
				}
			}
    	}
    	return retval;
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
	
	//check for duplicates (not allowed)
	private boolean isDuplicateTurnumber(SystemaWebUser appUser, SadmotfRecord record) {
		boolean retval = false;
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&etpro=" + record.getEtpro();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		if(jsonContainer!=null && !jsonContainer.getList().isEmpty()) {
    			retval = true;
    		}
 
    	}
    	
    	return retval;
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	//@Autowired
	//private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmohfListService sadmohfListService;

	
	@Autowired
	private GeneralUpdateService generalUpdateService;

	@Autowired
	private AvdSignControllerService avdSignControllerService;
	
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

