package no.systema.tvinn.sad.digitollv2.controller.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmotfStatus3;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;

import java.util.Calendar;

import org.slf4j.Logger;


/**
 * This class executes all the logic that usually resides in the Controller directly
 * The reason is to decouple it from the controller in order to implement either the normal synchronous behavior of a response OR an asynchronous behavior
 * 
 * @author oscardelatorre
 * Oct 2023
 * 
 */
@Service
public class ApiMasterSendService {
	private static final Logger logger = LoggerFactory.getLogger(ApiMasterSendService.class);
	
	/**
	 * Must have same parameter as the Controller
	 * 
	 * @param applicationUser
	 * @param emlnrt
	 * @param emlnrm
	 * @param ehmid
	 * @return
	 */
	public String send (String applicationUser, Integer emlnrt, Integer emlnrm, String emmid) {
		String retval = "";
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//=================
		//SEND POST or PUT
		//=================
		if(emlnrt > 0 && emlnrt > 0) {
	    	logger.info("Before send in Controller ...");
			logger.info("Inside: doApiSendMaster");
			
			StringBuilder url = new StringBuilder();
			StringBuilder urlRequestParamsKeys = new StringBuilder();
			urlRequestParamsKeys.append("user=" + applicationUser);
			
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			//check if POST-CREATE or PUT-UPDATE
			if( StringUtils.isNotEmpty(emmid) ) {
				url.append("putMasterConsignment.do");
				urlRequestParamsKeys.append("&emlnrt=" + emlnrt);
				urlRequestParamsKeys.append("&emlnrm=" + emlnrm);
				urlRequestParamsKeys.append("&mrn=" + emmid);
			}else {
				
				url.append("postMasterConsignment.do");
				urlRequestParamsKeys.append("&emlnrt=" + emlnrt);
				urlRequestParamsKeys.append("&emlnrm=" + emlnrm);					
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
	    				retval = "&" + SadDigitollConstants.REDIRECT_ERRMSG + "=" + apiDtoResponse.getErrMsgClean();
	    			}
	    		}
			}catch(Exception e) {
    			e.printStackTrace();
    		
			}finally {
				//remove the (P)ENDING status that was set by the caller before the async call
				this.setSt3_Master(applicationUser, emlnrt, emlnrm, EnumSadmomfStatus3.EMPTY.toString());
    		}
		}
		
		return retval;
		
	}
	/**
	 * 
	 * @param applicationUser
	 * @param lnrt
	 * @param lnrm
	 * @param st3
	 */
	public void setSt3_Master(String applicationUser, Integer lnrt, Integer lnrm, String st3) {
		
		try {
			final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_URL;
			//add URL-parameters
			String urlRequestParams = "user=" + applicationUser + "&emlnrt=" + lnrt + "&emlnrm=" + lnrm + "&emst3=" + st3 + "&mode=US3" ;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
	    		if(jsonContainer!=null && !jsonContainer.getList().isEmpty()) {
	    			if(StringUtils.isNotEmpty(jsonContainer.getErrMsg())) {
	    				logger.error("ERROR on update st3 for SADMOMF:" + jsonContainer.getErrMsg());
	    			}
	    		}
	    	}
		}catch(Exception e) {
			logger.error(e.toString());
		}
    
		
	}
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	@Autowired
	private SadmomfListService sadmomfListService;
}