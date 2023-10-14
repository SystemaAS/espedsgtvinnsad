package no.systema.tvinn.sad.digitollv2.controller.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
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
    		}

		}
		
		return retval;
		
	}
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private ApiGenericDtoResponseService apiGenericDtoResponseService;
	
}