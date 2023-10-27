package no.systema.tvinn.sad.digitollv2.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadOppdragService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
import no.systema.tvinn.sad.digitollv2.service.SadmoafListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.sadimport.controller.ajax.SadImportAjaxHandlerController;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

@RestController
public class TvinnSadDigitollAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollAjaxController.class.getName());
	private DateTimeManager dateMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificGoodsItemVoec_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadImportTopicFinansOpplysningerRecord> getSpecificGoodsItemVoec_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String eili, 
	  						 @RequestParam String eilnrt, @RequestParam String eilnrm , @RequestParam String eilnrh) {
		 
		 final String METHOD = "[DEBUG] getSpecificGoodsItemVoec_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		 String urlRequestParams = "user=" + applicationUser + "&eili=" + eili + "&eilnrt=" + eilnrt + "&eilnrm=" + eilnrm + "&eilnrh=" + eilnrh;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
	    		List<SadmoifRecord> list = (List)jsonContainer.getList();
	    		for(SadmoifRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param targetTransportId
	 * @param fromEmlnrt
	 * @param fromEmlnrm
	 * @param fromEtktyp
	 * @return
	 */
	@RequestMapping(value = "changeTransport_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmotfRecord> changeTransport_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String targetTransportId, 
	  						 @RequestParam String fromEmlnrt, @RequestParam String fromEmlnrm , @RequestParam String fromEtktyp) {
		 
		 final String METHOD = "[DEBUG] changeTransport_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_CHANGE_TRANSPORT_URL;
		 String urlRequestParams = "user=" + applicationUser + "&mode=U" + "&emlnrt=" + fromEmlnrt + "&emlnrm=" + fromEmlnrm + "&toEmlnrt=" + targetTransportId;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		result.add(jsonContainer);
	    		/*List<SadmotfRecord> list = (List)jsonContainer.getList();
	    		for(SadmotfRecord fakeRecord : list) {
	    			result.add(fakeRecord);
	    		}*/
	    		logger.info("result Set:" + result.toString());
	    		
	    	}
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param tur
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificOppdrag_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> getSpecificOppdrag_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String tur, 
	  						 @RequestParam String avd, @RequestParam String opd) {
		 
		final String METHOD = "[DEBUG] getSpecificOppdrag_Digitoll ";
		logger.info(METHOD + "Inside");
		Set result = new HashSet();
		SadOppdragRecord tmp = this.getOppdrag(applicationUser, tur, opd);
		if(tmp!=null) {
			result.add(tmp);
		}
		/*final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&tur=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && !container.getOrderList().isEmpty()) {
    			for(SadOppdragRecord record: container.getOrderList()) {
    				if(record.getSitdn().equals(opd)) {
    					//Dekl.dato format to NO
    					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
							if (record.getWeh0068a().length()==8) {
								record.setWeh0068a(this.dateMgr.getDateFormatted_NO(record.getWeh0068a(), DateTimeManager.ISO_FORMAT));
							}
						}
    					logger.info(record.getWeh0068a());
    					result.add(record);
    					break;
    				}
    			}
    		}
    		
		}
		*/
    	return result;
    	
	 }
	
	/**
	 * 
	 * @param applicationUser
	 * @param tur
	 * @param opd
	 */
	private SadOppdragRecord getOppdrag(String applicationUser, String tur, String opd) {
		SadOppdragRecord retval = null;
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&tur=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && !container.getOrderList().isEmpty()) {
    			for(SadOppdragRecord record: container.getOrderList()) {
    				if(record.getSitdn().equals(opd)) {
    					//Dekl.dato format to NO
    					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
							if (record.getWeh0068a().length()==8) {
								record.setWeh0068a(this.dateMgr.getDateFormatted_NO(record.getWeh0068a(), DateTimeManager.ISO_FORMAT));
							}
						}
    					logger.info(record.getWeh0068a());
    					retval = record;
    					break;
    				}
    			}
    		}
    		
		}
    	
    	return retval;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param etlnrt
	 * @return
	 */
	@RequestMapping(value = "createHousesFromOppdrag_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmomfRecord> createHousesFromOppdrag_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd, @RequestParam String mode ) {
		 
		 logger.info("avd:" + avd);
		 logger.info("opd:" + opd);
		 logger.info("mode:" + mode);
		 
		 Set result = new HashSet();
		 SadmomfRecord fejk = new SadmomfRecord();
		 fejk.setEmavd(Integer.valueOf(avd));
		 result.add(fejk);
		 
		 //prepare the access CGI with RPG back-end
		 /*
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		 String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + etlnrt;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		List<SadmotfRecord> list = (List)jsonContainer.getList();
	    		for(SadmotfRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 */
		 return result;
	 }
	
	/**
	 * 
	 * @param applicationUser
	 * @param ehlnrt
	 * @param ehlnrm
	 * @param ehlnrh
	 * @return
	 */
	@RequestMapping(value = "getDocumentNrHouse_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmotfRecord> updateDocumentNrHouse_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String etlnrt) {
		 
		 final String METHOD = "[DEBUG] updateDocumentNrHouse_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		 String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + etlnrt;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		List<SadmotfRecord> list = (List)jsonContainer.getList();
	    		for(SadmotfRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
		
	/**
	 * 
	 * @param request
	 * @param applicationUser
	 * @param turNr
	 * @param fromDate
	 * @return
	 */
	@RequestMapping(value = "searchTur_Digitoll.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadTurRecord> searchTur(HttpServletRequest request, 	@RequestParam String applicationUser, @RequestParam(value = "avd", required = true) String avd, 
			  																	   	@RequestParam(value = "turNr", required = true) String turNr, 
			  																		@RequestParam(value = "fromDate", required = true) String fromDate) {

		  logger.info("Inside searchTur");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TUR_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser);
		  urlRequestParamsKeys.append("&wtudt=" + fromDate);
		  if(StringUtils.isNotEmpty(turNr)) {
			  urlRequestParamsKeys.append("&wsstur=" + turNr);
		  }
		  if(StringUtils.isNotEmpty(avd)) {
			  urlRequestParamsKeys.append("&wssavd=" + avd);
		  }
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadTurContainer container = this.sadTurService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
		    			for(SadTurRecord  record : container.getWrktriplist()){
		    				logger.info("Bilnr: " + record.getTubiln());
		    				logger.info("Tollsted(a): " + record.getTuto1a());
		    				logger.info("Fører: " + record.getTusjn1());
		    				//transport måte
		    				if(StringUtils.isNotEmpty(record.getTutrma())) { this.washTranspMate(record); }
		    				//eta (NO format)
		    				if(StringUtils.isNotEmpty(record.getTueta()) && record.getTueta().equals("0") && record.getTueta().length()==8 ){
		    					record.setTueta(this.dateMgr.getDateFormatted_NO(record.getTueta(), DateTimeManager.ISO_FORMAT));
		    				}
		    				//
		    				result.add(record);
		    			}
	    			}else {
	    				SadTurRecord record = new SadTurRecord();
	    				record.setOwn_ErrMsg(container.getErrMsg());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	/**
	 * fetch default values from SADMOAF
	 * @param request
	 * @param applicationUser
	 * @return
	 */
	@RequestMapping(value = "searchDefaultValuesTransport_Digitoll.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadmoafRecord> searchDefaultValues(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam String avd) {
		  String DEFAULT_AVD = "0";
		  
		  logger.info("Inside searchDefaultValues (SADMOAF)");
		  logger.info("avd:" + avd);
		  Set result = new HashSet();
		  //with some etavd
		  SadmoafRecord record = this.getDefaultValues(applicationUser, avd);
		  if(record!=null) {
			  result.add(record);
		  }else {
			  //if the avd above does not exist take the default etavd = 0
			  record = this.getDefaultValues(applicationUser, DEFAULT_AVD);
			  if(record!=null) {
				  result.add(record);
			  }
		  }
	    	
		  return result;
		  
	  }
	
	private SadmoafRecord getDefaultValues(String applicationUser, String etavd) {
		  SadmoafRecord result = null;
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser + "&etavd=" + etavd);
		  
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
			if(container!=null){
				for(SadmoafRecord  record : container.getList()){
					logger.info("Ombud navn:" + record.getEtnar());
					result = record;
				}
			}
		  }
	    	
		  return result ;
	}
	/**
	 * 
	 * @param record
	 */
	private void washTranspMate(SadTurRecord record) {
		//supported by Digitoll
		if(record.getTutrma().equals("10") || record.getTutrma().equals("20") || record.getTutrma().equals("21") || record.getTutrma().equals("30") ||
				record.getTutrma().equals("31") || record.getTutrma().equals("41") || record.getTutrma().equals("80") ){
		}else {
			record.setTutrma("");
		}
	}
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmoafListService sadmoafListService;
	@Autowired
	private SadOppdragService sadOppdragService;
	@Autowired
	private SadTurService sadTurService;
	
}
