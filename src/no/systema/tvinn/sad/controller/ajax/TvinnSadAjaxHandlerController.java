/**
 * 
 */
package no.systema.tvinn.sad.controller.ajax;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.CurrencyRateService;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextRecord;
import no.systema.tvinn.sad.service.TvinnSadTullkontorService;
import no.systema.tvinn.sad.service.TvinnSadCustomerService;

/**
 * This Ajax handler is the class handling ajax request in Tvinn-Sad (general)
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jun 11, 2014
 * 
 */

@Controller

public class TvinnSadAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TvinnSadAjaxHandlerController.class.getName());
	 
	
	  /**
	   * Populates the GUI element with a list of customers fulfilling the search condition
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	 
	  @RequestMapping(value = "searchCustomer_TvinnSad.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside searchCustomer");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	    		JsonTvinnSadCustomerContainer container = this.tvinnSadCustomerService.getTvinnSadCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTvinnSadCustomerRecord  record : container.getCustomerlist()){
	    				logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
	    				logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  @RequestMapping(value = "getCustomerInfoFreeText_TvinnSad.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> getCustomerInfoFreeText(@RequestParam String applicationUser, @RequestParam String customerNumber, @RequestParam String delsystem) {
		  logger.info("Inside getCustomerInfoFreeText");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_CUSTOMER_INFO_FREETEXT_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCustomerFreeText(applicationUser, customerNumber, delsystem);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonTvinnSadCustomerInfoFreeTextContainer container = this.tvinnSadCustomerService.getTvinnSadCustomerInfoFreeTextContainer(jsonPayload);
	    		if(container!=null){
	    			StringBuffer sb = new StringBuffer("");
	    			for(JsonTvinnSadCustomerInfoFreeTextRecord  record : container.getDspCustFreetxt()){
	    				//logger.info("FIELD: " + record.getFxtxt());
	    				sb.append(record.getFxtxt() + "\n");
	    				//result.add(record);
	    			}
	    			//This part puts the string buffer into the record to send to caller
	    			JsonTvinnSadCustomerInfoFreeTextRecord recordWithAppendedTexts = new JsonTvinnSadCustomerInfoFreeTextRecord();
	    			recordWithAppendedTexts.setFxtxt(sb.toString());
	    			result.add(recordWithAppendedTexts);
	    		}
	    	}
		  return result;
		  
	  }

	  /**
	   * Populates the GUI element with a list of tullkontor-offices fulfilling the search condition
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  
	  @RequestMapping(value = "searchUtfartsTullkontor_TvinnSad.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTvinnSadTullkontorRecord> searchUtfartsTullkontor(@RequestParam String applicationUser, 
			  @RequestParam String tullkontorName, @RequestParam String tullkontorCode, @RequestParam String kontorType) {
		  logger.info("Inside searchUtfartsTullkontor...");
		  logger.info("kontorType:" + kontorType);
		  List result = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_UTFARTS_TULLKONTOR_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchUtfartsTullkontor(applicationUser, tullkontorName, tullkontorCode, kontorType);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			  JsonTvinnSadTullkontorContainer container = this.tvinnSadTullkontorService.getTvinnSadTullkontorContainer(jsonPayload);
			  if(container!=null){
				  for(JsonTvinnSadTullkontorRecord  record : container.getCustomslist()){
					  //logger.info("Kontorsnamn via AJAX: " + record.getTktxtn() + " Code:" + record.getTkkode());
					  result.add(record);
				  }
			  }
		  }
		  return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @return
	   */
	  /*
	  @RequestMapping(value = "getCurrencyRate_TvinnSad.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode) {
		  logger.info("Inside getCurrencyRate_Skat");
		  Set result = new HashSet();
		  
		  String BASE_URL_CURRENCY_RATE = TvinnSadUrlDataStore.TVINN_SAD_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info("CURRENCY URL:" + BASE_URL_CURRENCY_RATE);
		  logger.info("CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info("Currency RATE: " + record.getSvvk_krs() + " " + record.getDkvk_krs());
				  logger.info("Currency FACTOR: " + record.getSvvs_omr() + " " + record.getDkvs_omr());
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  */
	  
	  /**
	   * Forms the correct parameter list according to a correct html POST
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param customerNumber
	   * @param delsystem (K=SADI ...)
	   * @return
	   */
	  private String getRequestUrlKeyParametersForCustomerFreeText(String applicationUser, String customerNumber, String delsystem){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kundn=" + customerNumber );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "delsystem=" + delsystem );
		  }
		  
		  return sb.toString();
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchUtfartsTullkontor(String applicationUser, String soName, String code, String kontorType){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(soName!=null && !"".equals(soName) && code!=null && !"".equals(code)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }else if (soName!=null && !"".equals(soName)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
		  }else if (code!=null && !"".equals(code)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }
		//append the type when applicable
		  if (kontorType!=null && !"".equals(kontorType)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST);
			  if("avg".equals(kontorType)){
				  sb.append("avg=J");
			  }else if ("ank".equals(kontorType)){
				  sb.append("ank=J");
			  }else if ("trs".equals(kontorType)){
				  sb.append("trs=J");
			  }
		  }
		  return sb.toString();
	  }
	  
	  
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier ("tvinnSadTullkontorService")
	  private TvinnSadTullkontorService tvinnSadTullkontorService;
	  @Autowired
	  @Required
	  public void setTvinnSadTullkontorService (TvinnSadTullkontorService value){ this.tvinnSadTullkontorService = value; }
	  public TvinnSadTullkontorService getTvinnSadTullkontorService(){ return this.tvinnSadTullkontorService; }
	  
	  
	  @Qualifier 
	  private TvinnSadCustomerService tvinnSadCustomerService;
	  @Autowired
	  @Required	
	  public void setTvinnSadCustomerService(TvinnSadCustomerService value){this.tvinnSadCustomerService = value;}
	  public TvinnSadCustomerService getTvinnSadCustomerService(){ return this.tvinnSadCustomerService; }
	  
	  
	  @Qualifier ("currencyRateService")
	  private CurrencyRateService currencyRateService;
	  @Autowired
	  public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	  public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  
	   
  	  /*
	  @Qualifier 
	  private TdsSignatureNameService tdsSignatureNameService;
	  @Autowired
	  @Required	
	  public void setTdsSignatureNameService(TdsSignatureNameService value){this.tdsSignatureNameService = value;}
	  public TdsSignatureNameService getTdsSignatureNameService(){ return this.tdsSignatureNameService; }
	  */
		
}
