package no.systema.tvinn.sad.digitollv2.controller.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadAvdSignContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadAvdSignRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadAvdSignService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.util.TvinnSadConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;


/**
 * This class executes all the logic that usually resides in the Controller directly
 * The reason is to decouple it from the controller in order to implement the same method for different consumers (AjaxController or normal Controller)
 * 
 * @author oscardelatorre
 * Oct 2023
 * 
 */
@Service
public class AvdSignControllerService {
	private static final Logger logger = LoggerFactory.getLogger(AvdSignControllerService.class);
	private DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param session
	 */
	public void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		try{
			String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_AVD_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			SadAvdSignContainer container = this.sadAvdSignService.getListContainer(url);
			List<SadAvdSignRecord> list = new ArrayList();
			for(SadAvdSignRecord record: container.getAvdelningar()){
				list.add(record);
				//logger.info("Avd-tst:" + record.getAvd() + "XX" + record.getTst());
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			session.setAttribute(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG, list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	public void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		try{
			String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_SIGN_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			SadAvdSignContainer container = this.sadAvdSignService.getListContainer(url);
			List<SadAvdSignRecord> list = new ArrayList();
			for(SadAvdSignRecord record: container.getSignaturer()){
				list.add(record);
				//logger.info("Avd-tst:" + record.getAvd() + "XX" + record.getTst());
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private SadAvdSignService sadAvdSignService;
	
	
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmotfListService sadmotfListService;
	
	@Autowired
	private GeneralUpdateService generalUpdateService;
	
}