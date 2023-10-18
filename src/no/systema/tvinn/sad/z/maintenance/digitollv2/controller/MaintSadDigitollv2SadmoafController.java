package no.systema.tvinn.sad.z.maintenance.digitollv2.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafRecord;
import no.systema.tvinn.sad.digitollv2.service.SadDigitollDropDownListPopulationService;
import no.systema.tvinn.sad.digitollv2.service.SadmoafListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKofastService;

/**
 * TVINN Maintenance DigitollV2 Controller SADMOAF
 * 
 * @author oscardelatorre
 * @date Oct. 2023
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
//@Scope("session")
public class MaintSadDigitollv2SadmoafController {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadDigitollv2SadmoafController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmoaf.do", method=RequestMethod.GET)
	public ModelAndView doSadmoaf(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_digitollv2_sadmoaf");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Inside doSadmoaf...");
		Map model = new HashMap();
		String action = request.getParameter("action");
		if(appUser==null){
			return this.loginView;
		}else{
			
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			if(StringUtils.isNotEmpty(action)) {
				if(action.equals("doFind")) {
					model.put("record", this.getRecord(appUser, action));
				}
			}
			//drop downs
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			this.setDropDownService(model);
			
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmoaf_delete.do", method={ RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doDeleteSadefdef(@ModelAttribute ("record") JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_digitollv2_sadmoaf");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String efavd = null;
		String mode = null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentEfavd")){
    				efavd = value;
    			}else if(element.startsWith("selectedStatus")){
    				mode = value;
    			}
    			
    		}
    	}
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//prepare the access CGI with RPG back-end
			 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_SADEFDEF_URL;
			 StringBuffer urlRequestParams = new StringBuffer();
			 urlRequestParams.append("user=" + appUser.getUser() + "&mode=" + mode + "&efavd=" + efavd );
			 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			 if(jsonPayload!=null){
				
				SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				if(container!=null){
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, container.getErrMsg());
					}
				}
			 }
			
			
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
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
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmoaf_edit.do", method=RequestMethod.POST)
	public ModelAndView doEditSadefdef(@ModelAttribute ("record") SadmoafRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_digitollv2_sadmoaf");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String mode = "U";
		
		Map model = new HashMap();
		boolean validUpdate = true;
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			if(!this.avdExists(appUser, String.valueOf(recordToValidate.getEtavd())) ){
				mode = "A";	
			}
			
			 this.adjustFieldsForUpdate(recordToValidate);
			 //prepare the access CGI with RPG back-end
			 String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_DEFAULT_VALUES_URL;
			 StringBuffer urlRequestParams = new StringBuffer();
			 urlRequestParams.append("user=" + appUser.getUser() + "&mode=" + mode );
			 urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
			 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 /*
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			 if(jsonPayload!=null){
				
				 SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				if(container!=null){
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, container.getErrMsg());
					}
				}
			 }
			*/
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			
			//drop downs
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			this.setDropDownService(model);
			
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private Collection<SadmoafRecord> populateList(SystemaWebUser appUser){
		Collection<SadmoafRecord> retval = new ArrayList<SadmoafRecord>();
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
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
    		
    		SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<SadmoafRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				retval = outputList;
			}
    	}
    	return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param etavd
	 * @return
	 */
	private SadmoafRecord getRecord(SystemaWebUser appUser, String etavd){
		SadmoafRecord retval = null;
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&etavd=" + etavd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<SadmoafRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(SadmoafRecord record: outputList) {
					retval = record;
				}
			}
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param efavd
	 * @return
	 */
	private boolean avdExists(SystemaWebUser appUser, String etavd){
		boolean retval = false;
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&etavd=" + etavd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
    		//the list will exists with match
    		if(container.getList()!=null && container.getList().size()>0){
    			logger.warn("Avd exists");
    			retval = true;
    		}
			
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(SadmoafRecord recordToValidate){
		/*if(StringUtils.isEmpty(recordToValidate.getEfdtr())){ recordToValidate.setEfdtr("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfeta())){ recordToValidate.setEfeta("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfsjadt())){ recordToValidate.setEfsjadt("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEftsd())){ recordToValidate.setEftsd("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfdtin())){ recordToValidate.setEfdtin("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfetm())){ recordToValidate.setEfetm("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEfata())){ recordToValidate.setEfata("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfatm())){ recordToValidate.setEfatm("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEf3039e())){ recordToValidate.setEf3039e("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEfknd())){ recordToValidate.setEfknd("0"); }
		
		
		if(!"0".equals(recordToValidate.getEfeta())){
			recordToValidate.setEfeta(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfeta()));
		}
		if(!"0".equals(recordToValidate.getEfsjadt())){
			recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfsjadt()));
		}*/
	}
	
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
			logger.info("AVD BASE_URL:" + BASE_URL);
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
			logger.info("SIGN BASE_URL:"  + BASE_URL);
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
	
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFPR.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;

	@Autowired
	private SadmoafListService sadmoafListService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	private SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	


}

