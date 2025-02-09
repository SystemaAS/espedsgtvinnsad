package no.systema.tvinn.sad.digitollv2.controller.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmodoclgRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;

import java.util.*;

import org.slf4j.Logger;


/**
 * This class executes all the logic that usually resides in the Controller directly
 * The reason is to decouple it from the controller in order to implement the same method for different consumers (AjaxController or normal Controller)
 * 
 * @author oscardelatorre
 * Feb 2025
 * 
 */
@Service
public class HouseDocLogControllerService {
	private static final Logger logger = LoggerFactory.getLogger(HouseDocLogControllerService.class);
	private DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int insertRecord(String applicationUser, SadmodoclgRecord logRecord, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_DOC_API_LOG_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((logRecord)));
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
					}else {
						//OK
					}
				}
			}
    	}
    	
    	return retval;
	}
	
	
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
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