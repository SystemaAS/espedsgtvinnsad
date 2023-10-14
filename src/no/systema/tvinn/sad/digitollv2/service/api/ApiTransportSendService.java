package no.systema.tvinn.sad.digitollv2.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;

import java.util.Calendar;

import org.slf4j.Logger;



@Service
@EnableAsync
public class AsynchTransportService {
	private static final Logger logger = LoggerFactory.getLogger(AsynchTransportService.class);
	
	@Async
	public void xxxTest (String applicationUser, Integer etlnrt, String etmid) {
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//=================
		//SEND POST or PUT
		//=================
		if(etlnrt > 0 ) {
	    	logger.info("Before send in Controller ...");
			
			StringBuilder url = new StringBuilder();
			StringBuilder urlRequestParamsKeys = new StringBuilder();
			urlRequestParamsKeys.append("user=" + applicationUser);
			
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			//check if POST-CREATE or PUT-UPDATE
			if( StringUtils.isNotEmpty(etmid) ) {
				url.append("putTransport.do");
				urlRequestParamsKeys.append("&etlnrt=" + etlnrt);
				urlRequestParamsKeys.append("&mrn=" + etmid);
			}else {
				
				url.append("postTransport.do");
				urlRequestParamsKeys.append("&etlnrt=" + etlnrt);
			}
			
			String BASE_URL = url.toString();
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
    		//Debug -->
	    	logger.info(jsonPayload);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		/*
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
			*/
    		//successView = new ModelAndView(redirect.toString());
    		
		}
		
	}
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
}