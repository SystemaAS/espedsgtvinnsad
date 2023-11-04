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
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus3;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus3;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
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
public class ApiHouseSendService {
	private static final Logger logger = LoggerFactory.getLogger(ApiHouseSendService.class);
	
	/**
	 * Must be exaclty the same params as in the Controller
	 * @param applicationUser
	 * @param etlnrt
	 * @param etmid
	 * @return
	 */
	public String send (String applicationUser, Integer ehlnrt, Integer ehlnrm, Integer ehlnrh, String ehmid) {
		String retval = "";
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//=================
		//SEND POST or PUT
		//=================
		if(ehlnrt > 0 && ehlnrm > 0 && ehlnrh > 0) {
	    	
			StringBuilder url = new StringBuilder();
			StringBuilder urlRequestParamsKeys = new StringBuilder();
			urlRequestParamsKeys.append("user=" + applicationUser);
			
			url.append(SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL);
			//check if POST-CREATE or PUT-UPDATE
			if( StringUtils.isNotEmpty(ehmid) ) {
				url.append("putHouseConsignment.do");
				urlRequestParamsKeys.append("&ehlnrt=" + ehlnrt);
				urlRequestParamsKeys.append("&ehlnrm=" + ehlnrm);
				urlRequestParamsKeys.append("&ehlnrh=" + ehlnrh);
				urlRequestParamsKeys.append("&mrn=" + ehmid);
			}else {
				
				url.append("postHouseConsignment.do");
				urlRequestParamsKeys.append("&ehlnrt=" + ehlnrt);
				urlRequestParamsKeys.append("&ehlnrm=" + ehlnrm);	
				urlRequestParamsKeys.append("&ehlnrh=" + ehlnrh);
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
    			this.setSt3_House(applicationUser, ehlnrt, ehlnrm, ehlnrh, EnumSadmohfStatus3.EMPTY.toString());
    		}
		}
		
		return retval;
		
	}
	/**
	 * Send all houses as a batch (call by either master or transport)
	 * 
	 * @param applicationUser
	 * @param transportId
	 * @param masterId
	 * @return
	 */
	
	public String sendAll(String applicationUser, Integer lnrt, Integer lnrm) {
		String retval = "";
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//=================
		//SEND POST or PUT
		//=================
		if(lnrt > 0 && lnrm > 0) {
			List<SadmohfRecord> listOfHouses = this.getHouses(applicationUser, String.valueOf(lnrt), String.valueOf(lnrm));
			for(SadmohfRecord record: listOfHouses) {
				this.send(applicationUser, record.getEhlnrt(), record.getEhlnrm(), record.getEhlnrh(), record.getEhmid());
			}
			//remove the (P)ENDING status that was set by the caller before the async call
			this.setSt3_Master(applicationUser, lnrt, lnrm, EnumSadmomfStatus3.EMPTY.toString());
			
		}else if(lnrt > 0 ) {
			/* not implemented in the caller...
			List<SadmomfRecord> listOfMasters = this.getMasters(applicationUser, String.valueOf(lnrt));
			for(SadmomfRecord masterRecord: listOfMasters) {
				List<SadmohfRecord> listOfHouses = this.getHouses(applicationUser, String.valueOf(masterRecord.getEmlnrt()), String.valueOf(masterRecord.getEmlnrm()));
				for(SadmohfRecord houseRecord: listOfHouses) {
					this.send(applicationUser, houseRecord.getEhlnrt(), houseRecord.getEhlnrm(), houseRecord.getEhlnrh(), houseRecord.getEhmid());
				}
			}
			//remove the (P)ENDING status that was set by the caller before the async call
			this.setSt3_TransportToPending(applicationUser, lnrt, lnrm, EnumSadmomfStatus3.EMPTY.toString());
			*/
		}
		
		return retval;
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param emlnrt
	 * @param emlnrm
	 */
	private List<SadmohfRecord> getHouses(String applicationUser, String emlnrt, String emlnrm) {
		List <SadmohfRecord> resultList = new ArrayList<SadmohfRecord>();
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&ehlnrt=" + emlnrt + "&ehlnrm=" + emlnrm;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		if(jsonContainer!=null && !jsonContainer.getList().isEmpty()) {
    			resultList = (List)jsonContainer.getList();
    		}
    	}
    	return resultList;
	}
	
	
	private List<SadmomfRecord> getMasters(String applicationUser, String lnrt) {
		List <SadmomfRecord> resultList = new ArrayList<SadmomfRecord>();
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&emlnrt=" + lnrt;
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
    			resultList = (List)jsonContainer.getList();
    		}
    	}
    	return resultList;
	}
	
	/**
	 * The method changes the parent-caller st3 in order to block GUI elements until the st3 is empty (set by the async. process elsewhere...)
	 * @param applicationUser
	 * @param lnrt
	 * @param lnrm
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
	/**
	 * Apply to the SEND house button 
	 * @param applicationUser
	 * @param lnrt
	 * @param lnrm
	 * @param lnrh
	 * @param st3
	 */
	public void setSt3_House(String applicationUser, Integer lnrt, Integer lnrm, Integer lnrh, String st3) {
		
		try {
			final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_URL;
			//add URL-parameters
			String urlRequestParams = "user=" + applicationUser + "&ehlnrt=" + lnrt + "&ehlnrm=" + lnrm + "&ehlnrh=" + lnrh + "&ehst3=" + st3 + "&mode=US3" ;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
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
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	
	
}