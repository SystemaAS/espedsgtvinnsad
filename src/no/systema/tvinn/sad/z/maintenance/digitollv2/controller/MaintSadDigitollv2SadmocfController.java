package no.systema.tvinn.sad.z.maintenance.digitollv2.controller;

import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.digitollv2.controller.service.AvdSignControllerService;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafOnlyTransportRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadDigitollDropDownListPopulationService;
import no.systema.tvinn.sad.digitollv2.service.SadmoafListService;
import no.systema.tvinn.sad.digitollv2.service.SadmocfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKofastService;

/**
 * TVINN Maintenance DigitollV2 Controller SADMOCF
 * 
 * @author oscardelatorre
 * @date Oct. 2025
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
//@Scope("session")
public class MaintSadDigitollv2SadmocfController {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadDigitollv2SadmocfController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmocf.do", method=RequestMethod.GET)
	public ModelAndView doSadmocf(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_digitollv2_sadmocf");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Inside doSadmoaf...");
		Map model = new HashMap();
		String action = request.getParameter("action");
		String orgnr = request.getParameter("orgnr");
		if(appUser==null){
			return this.loginView;
		}else{
			
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			
			if(StringUtils.isNotEmpty(action)) {
				if(action.equals("doFind")) {
					model.put("record", this.getRecord(appUser, orgnr, true));
				}
			}
			//drop downs
			this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.avdSignControllerService.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			this.setDropDownService(model);
			//errorMessage from a redirect...
			if(StringUtils.isNotEmpty(request.getParameter("errorMessage"))) {
				model.put("errorMessage", request.getParameter("errorMessage"));
			}
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
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmocf_edit.do", method=RequestMethod.POST)
	public ModelAndView doEditSadmocff(@ModelAttribute ("record") SadmoafRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info(String.valueOf(recordToValidate.getEtavd()));
		StringBuilder redirect =  new StringBuilder();
		redirect.append("redirect:tvinnsadmaintenance_digitollv2_sadmocf.do?action=doFind&etavd=" + recordToValidate.getEtavd());
		
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
		
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			 if(jsonPayload!=null){
				
				 SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				if(container!=null){
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, container.getErrMsg());
						redirect.append("&errorMessage=" + container.getErrMsg()); 
					}
				}
			 }
			//redirect
			ModelAndView successView = new ModelAndView(redirect.toString());
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
	@RequestMapping(value="tvinnsadmaintenance_digitollv2_sadmocf_delete.do", method={ RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doDeleteSadmocf(@ModelAttribute ("record") SadmoafRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		StringBuilder redirect =  new StringBuilder();
		redirect.append("redirect:tvinnsadmaintenance_digitollv2_sadmocf.do");
		
		String etavd = request.getParameter("etavd");
		String mode = "D";
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//prepare the access CGI with RPG back-end
			 String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_DEFAULT_VALUES_URL;
			 StringBuffer urlRequestParams = new StringBuffer();
			 urlRequestParams.append("user=" + appUser.getUser() + "&mode=" + mode + "&etavd=" + etavd );
			 
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
						redirect.append("&errorMessage=" + container.getErrMsg()); 
					}
				}
			 }
			
			ModelAndView successView = new ModelAndView(redirect.toString());
			
			
	    	return successView;
	    	
		}
	}
	
	
	/**
	 * 
	 * @param source
	 * @param recordToValidate
	 */
	private void adjustRecordToValidateWithTranportAttributes(SadmoafRecord source, SadmoafRecord recordToValidate) {
		SadmoafOnlyTransportRecord tmpBeanTransport = new SadmoafOnlyTransportRecord();
		try {
			//Now lend only those transport attributes to the recordToValidate GUI (only master attributes) to make the com
			BeanUtils.copyProperties(tmpBeanTransport, source);
			//This will only add the transport props and leave the master properties untouched
			BeanUtils.copyProperties(recordToValidate, tmpBeanTransport);
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private Collection<SadmocfRecord> populateList(SystemaWebUser appUser){
		Collection<SadmocfRecord> retval = new ArrayList<SadmocfRecord>();
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_URL;
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
    		
    		SadmocfContainer container = this.sadmocfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<SadmocfRecord> outputList = container.getList();	
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
	private SadmocfRecord getRecord(SystemaWebUser appUser, String orgnr, boolean adjust){
		SadmocfRecord retval = null;
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&orgnr=" + orgnr;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmocfContainer container = this.sadmocfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<SadmocfRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(SadmocfRecord record: outputList) {
					if(adjust) {
						this.adjustFieldsForFetch(record);
					}
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
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(SadmocfRecord recordToValidate){
		String NULL_STR = "null";
		//Ftp records
		if(recordToValidate.getFtpserver()!=null && recordToValidate.getFtpserver().equals(NULL_STR)) {
			recordToValidate.setFtpserver("");
		}
		if(recordToValidate.getFtpport()!=null && recordToValidate.getFtpport().equals(NULL_STR)) {
			recordToValidate.setFtpport("");
		}
		if(recordToValidate.getFtpuser()!=null && recordToValidate.getFtpuser().equals(NULL_STR)) {
			recordToValidate.setFtpuser("");
		}
		if(recordToValidate.getFtppwd()!=null && recordToValidate.getFtppwd().equals(NULL_STR)) {
			recordToValidate.setFtppwd("");
		}
		//Ftpdir and tmp on GUI 
		/*
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
		*/
		
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(SadmoafRecord recordToValidate){
		
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
		/*if(recordToValidate.getEtdtr()!=null && recordToValidate.getEtdtr() > 0) {
			int regDate = Integer.valueOf(this.dateMgr.getDateFormatted_ISO(recordToValidate.getEtdtrStr(), DateTimeManager.NO_FORMAT));
			//logger.info("BBBB:" + regDate);
			recordToValidate.setEtdtr(regDate);
			
		}else {
			int regDate = Integer.valueOf(this.dateMgr.getCurrentDate_ISO());
			recordToValidate.setEtdtr(regDate);
		}*/
		
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
	 * Adjust for master fields
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdateMaster(SadmoafRecord recordToValidate){
		
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
	}
	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownServiceMaster(Map model) {
		//previous docs
		List<GenericDropDownDto> dto = this.digitollDropDownListPopulationService.getPreviousDocumentsList(); model.put("previousDocumentsDto", dto);
		//container sizeAnd type
		dto = this.digitollDropDownListPopulationService.getContainerSizeAndType(); model.put("containerSizeAndTypeDto", dto);
		//country
		dto = this.digitollDropDownListPopulationService.getCountryList(); model.put("countryDto", dto);
		
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;

	@Autowired
	private SadmoafListService sadmoafListService;
	@Autowired
	private AvdSignControllerService avdSignControllerService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	private SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	
	@Autowired
	private SadmocfListService sadmocfListService;
	


}

