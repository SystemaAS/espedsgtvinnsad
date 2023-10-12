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
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.SadOppdragService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
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
		 List<SadOppdragRecord> resultList = new ArrayList();
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
	    					result.add(record);
	    					break;
	    				}
	    			}
	    		}
	    		
	    	}
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
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> searchTur(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam(value = "turNr", required = false) String turNr, 
			  																							   @RequestParam(value = "fromDate", required = true) String fromDate) {

		  logger.info("Inside searchCustomer");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TUR_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser);
		  urlRequestParamsKeys.append("&wtudt=" + fromDate);
		  if(StringUtils.isNotEmpty(turNr)) {
			  urlRequestParamsKeys.append("&wsstur=" + turNr);
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
	    			for(SadTurRecord  record : container.getWrktriplist()){
	    				logger.info("Bilnr: " + record.getTubiln());
	    				logger.info("Tollsted(a): " + record.getTuto1a());
	    				logger.info("Fører: " + record.getTusjn1());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadOppdragService sadOppdragService;
	@Autowired
	private SadTurService sadTurService;
	
}
