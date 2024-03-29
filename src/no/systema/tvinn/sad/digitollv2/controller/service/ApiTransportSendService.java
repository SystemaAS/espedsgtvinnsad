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
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmotfStatus3;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
public class ApiTransportSendService {
	private static final Logger logger = LoggerFactory.getLogger(ApiTransportSendService.class);
	
	/**
	 * Must be exaclty the same params as in the Controller
	 * @param applicationUser
	 * @param etlnrt
	 * @param etmid
	 * @param etuuid
	 * 
	 * @return
	 */
	public String send (String applicationUser, Integer etlnrt, String etmid) {
	//public String send (String applicationUser, Integer etlnrt, String etmid, String etuuid, String apiType) {
		String retval = "";
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
				/*
				Pseudo: IN case async in all sendButtons does not work well ... otherwise delete this comment and send always async
					
				(1) Om etuuid is not empty then call: getTransport.do?user=SYSTEMA&lrn=68d3e859-9d53-4fce-a1e0-4d72f2980294&apiType=
				(2) Om errMsg not empty då är det en POST annars får vi MRN och errMsg tomt varpå det får bli PUT (denna händer när 404 pga Kafka delay att toll.no)
				(2.1) Vi måste uppdatera etmid i db först innan vi anropar PUT
					
				String mrn = this.getMrnFromApi(applicationUser, etuuid, "t", apiType);
				if(StringUtils.isNotEmpty(mrn)) {
					logger.info("PUT on MRN:" + mrn);
					url.append("putTransport.do");
					urlRequestParamsKeys.append("&etlnrt=" + etlnrt);
					urlRequestParamsKeys.append("&mrn=" + etmid);
				}else {
					
					url.append("postTransport.do");
					urlRequestParamsKeys.append("&etlnrt=" + etlnrt);
				}
				*/
			}
			
			String BASE_URL = url.toString();
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys.toString());
    		
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
    		//Debug -->
	    	logger.info(jsonPayload);
    		
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
    			this.setSt3_Transport(applicationUser, etlnrt, EnumSadmotfStatus3.EMPTY.toString());
    		}
		}
		
		return retval;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param lnrt
	 * @param st3
	 */
	public void setSt3_Transport(String applicationUser, Integer lnrt, String st3) {
		
		try {
			final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_TRANSPORT_URL;
			//add URL-parameters
			String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + lnrt + "&etst3=" + st3 + "&mode=US3" ;
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
	private SadmotfListService sadmotfListService;
}