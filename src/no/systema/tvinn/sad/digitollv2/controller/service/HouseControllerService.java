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
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.ApiGenericDtoResponseService;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
public class HouseControllerService {
	private static final Logger logger = LoggerFactory.getLogger(HouseControllerService.class);
	private DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	/**
	 * 
	 * @param applicationUser
	 * @param houseRecord
	 */
	public void setTransportDto(String applicationUser, SadmohfRecord houseRecord) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + houseRecord.getEhlnrt();
		
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
    		List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null){
				for(SadmotfRecord record: outputList){
					//ETA datein NO-format
					if(record.getEtetad()!=null && record.getEtetad() > 0) {
						String tmpEtetatd = String.valueOf(record.getEtetad());
						if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEtetatd) && tmpEtetatd.length()==8) {
							int isoEtetad = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEtetatd, DateTimeManager.ISO_FORMAT));
							record.setEtetad(isoEtetad);
						}
					}
					houseRecord.setTransportDto(record);
				}
			}
			
    	}	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param houseRecord
	 */
	public void setMasterDto(String applicationUser, SadmohfRecord houseRecord) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&emlnrt=" + houseRecord.getEhlnrt() + "&emlnrm=" + houseRecord.getEhlnrm();
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		List<SadmomfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null){
				for(SadmomfRecord record: outputList){
					houseRecord.setMasterDto(record);
				}
			}
			
    	}	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int updateRecord(String applicationUser, SadmohfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_URL;
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
					}else {
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
							if(recordToValidate.getEhlnrt()==null || recordToValidate.getEhlnrt()==0){
								recordToValidate.setEhlnrt(record.getId());
							}
							if(recordToValidate.getEhlnrm()==null || recordToValidate.getEhlnrm()==0){
								recordToValidate.setEhlnrm(record.getId2());
							}
							if(recordToValidate.getEhlnrh()==null || recordToValidate.getEhlnrh()==0){
								recordToValidate.setEhlnrh(record.getId3());
							}
						}
					}
				}
			}
    	}
    	
    	return retval;
	}
	/**
	 * 
	 * @param orgNr
	 * @param sadmohfRecord
	 * @return
	 */
	public String getRandomDocumentId(String orgNr, SadmohfRecord sadmohfRecord) {
		final String HYPHEN = "-";
		String dokumentId = "";
		
		if(sadmohfRecord!=null) {
			//an extra random number som extra unique flag
			Random rand = new Random(); 
			int randomValue = rand.nextInt(100); 
			
			dokumentId = orgNr + HYPHEN + StringUtils.leftPad(String.valueOf(sadmohfRecord.getEhavd()),4,"0") + 
						 		 HYPHEN + StringUtils.leftPad(String.valueOf(sadmohfRecord.getEhtdn()),7,"0") + 
								 HYPHEN + StringUtils.leftPad(String.valueOf(randomValue),3,"0");
		}
		
		return dokumentId;
		
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