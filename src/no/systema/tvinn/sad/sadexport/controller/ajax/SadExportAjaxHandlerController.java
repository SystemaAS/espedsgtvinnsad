/**
 * 
 */
package no.systema.tvinn.sad.sadexport.controller.ajax;

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
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.service.general.CurrencyRateService;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;

import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataRecord;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;

//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicService;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicItemService;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.sadexport.model.topic.SadExportSpecificTopicFinansOpplysningarAjaxObject;
import no.systema.tvinn.sad.sadexport.util.SadExportCalculator;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;

/**
 * This Ajax handler is the class handling ajax request in SkatImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Nov 6, 2014
 * 
 */
@Controller
public class SadExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadExportAjaxHandlerController.class.getName());
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private SadExportCalculator sadExportCalculator = new SadExportCalculator();
	
	
	/**
	 * Fetches the dep. information
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	 
	@RequestMapping(value = "getSpecificTopicAvdData_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadExportSpecificTopicRecord> getSpecificTopicAvdData
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 final String METHOD = "[DEBUG] method:getSpecificTopicAvdData ";
		 logger.info(METHOD + " Inside...");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_AVDDATA_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info(METHOD + "URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(METHOD + jsonPayload);
		 if(jsonPayload!=null){
				 JsonSadExportSpecificTopicAvdDataContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicAvdDataContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadExportSpecificTopicAvdDataRecord  record : container.getGetdepinf()){
						 logger.info("#### senad (Deklarantnavn):" + record.getSenad());
						 result.add(record);
					 }
				 }
			 }
		 return result;
	 }	
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SadExport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 final String METHOD = "[DEBUG] method: getSpecificTopicItemChosenFromHtmlList ";
		 logger.info(METHOD + "Inside");
		 logger.info(METHOD + "elementValue: " + elementValue);
		 
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 //String lineNr = null;
		 String lineId = null;
		 if(fields!=null && fields.length==2){
			 logger.info(METHOD + "FIELD: " + fields[0] + " " + fields[1]);
			 lineId = fields[1];
			 logger.info(METHOD + "user:" + applicationUser + "-" + "elementValue:" + elementValue + "-" + "avd:" + avd + "-" + "opd:" + opd + "- linenr:" + lineId);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineId);
			 logger.info(METHOD + "URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 logger.info(METHOD + jsonPayload);
				 JsonSadExportSpecificTopicItemContainer container = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadExportSpecificTopicItemRecord  record : container.getOrderList()){
						 //we must tweak this in order to present the correct value at the GUI
						 //record.setDkiv_402a(this.skatImportTweaker.setSummariskAngivelse_402a(record));
						 record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error(METHOD + "[ERROR] on fields[]...null or incorrect length???...");
		 }
		 return result;
	 }	 

	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicFinansOpplysningerItemChosenFromGuiElement_SadExport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadExportTopicFinansOpplysningerRecord> getSpecificTopicFinansOpplysningerItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 
		 final String METHOD = "[DEBUG] getSpecificTopicFinansOpplysningerItemChosenFromHtmlList ";
		 logger.info(METHOD + "Inside");
		 final String ID_RECORD_SEPARATOR = "__";
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
		 String[] fields = elementValue.split(ID_RECORD_SEPARATOR);
		 //String lineNr = null;
		 String fakNr = null;
		 String fakDate = null;
		 if(fields!=null && fields.length==3){
			 logger.info(METHOD + "FIELDs: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 fakNr = fields[1];
			 fakDate = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- fakNr:" + fakNr + "-fakDate:" + fakDate);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForFinansOpplysningerItem(applicationUser, avd, opd, fakNr, fakDate);
			 logger.info(METHOD + "URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 logger.info(METHOD + jsonPayload);
				 JsonSadExportTopicFinansOpplysningerContainer container = this.sadExportSpecificTopicService.getSadExportTopicFinansOpplysningerContainerOneInvoice(jsonPayload);
				 if(container!=null){
					 for(JsonSadExportTopicFinansOpplysningerRecord  record : container.getOneInvoice()){
						 record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         //adjust presentation fields (date, etc)
				         String dateSfdtNO = this.dateFormatter.convertToDate_NO(record.getSfdt());
				         record.setSfdt(dateSfdtNO);
				         //add to list
				         result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error(METHOD + "[ERROR] on fields[]...null or incorrect length???...");
		 }
		 return result;
	 }	 

	 
	
	  /**
	   * 
	   * @param applicationUser
	   * @param taricVarukod
	   * @param selkbCountryCode
	   * @return
	   */
	  @RequestMapping(value = "searchTolltariffVarukod_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadTolltariffVarukodRecord> getTolltariffVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod, @RequestParam String selkbCountryCode) {
		  final String METHOD = "[DEBUG] method: getTolltariffVarukod ";
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  String IMPORT_IE = "E";//
		  try{
			  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod + "&selkb=" + selkbCountryCode;;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  //logger.info(jsonPayload);
			  JsonTvinnSadTolltariffVarukodContainer container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
			  for(JsonTvinnSadTolltariffVarukodRecord record : container.getTariclist()){
				  //logger.info(record.getBeskr1());
				  result.add(record);
			  }	
			  logger.info(METHOD + "OK [executed without errors]");
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @param isoDate
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, @RequestParam String isoDate) {
		  final String METHOD = "[DEBUG] method:getCurrencyRate_SadExport "; 
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  String validDate = this.getValidCurrencyDate(isoDate);
		  //build
		  String BASE_URL_CURRENCY_RATE = TvinnSadUrlDataStore.TVINN_SAD_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  urlRequestParamsCurrencyRate.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + validDate);
		  //execute
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + "CURRENCY URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_CURRENCY_RATE));
		  logger.info(METHOD + "CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info(METHOD + "Currency RATE: " + record.getKvakrs() + " Currency FACTOR: " + record.getKvaomr() + " Currency DATE: " + validDate);
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  
	  /**
	   * 
	   * @param dateNORWAY
	   * @return
	   */
	  private String getValidCurrencyDate (String dateNORWAY){
		  final String DATE_MASK_NORWAY = "ddMMyy";
		  DateTimeManager mgr = new DateTimeManager();
		  String retval = null;
		  if (dateNORWAY!=null && !"".equals(dateNORWAY)){
			  if(dateNORWAY.length()==6){
				  retval = mgr.getDateFormatted_ISO(dateNORWAY, DATE_MASK_NORWAY);
			  }
		  }else{
			  retval = mgr.getCurrentDate_ISO();
		  }
		  return retval;
		  
	  }
	
	  /**
	   * Gets the total amount (as a proposal) from Finans Oppl. in case the InvoiceAmount = null and there are more than 0-elements
	   * in the extra Finans-Opplysningar division
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getFinansTotalSumAndRelatedFields_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getFinansTotalSumAndRelatedFields_SadExport(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		  final String METHOD = "[DEBUG] method:getFinansTotalSumAndRelatedFields_SadExport "; 
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  
		  String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersFinansOpplysningar(avd, opd, applicationUser);
		  logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		  //logger.info("FETCH av item list... ");
		  //logger.info("URL: " + BASE_URL_FETCH);
		  //logger.info("URL PARAMS: " + urlRequestParamsKeys);
		  //--------------------------------------
		  //EXECUTE the FETCH (RPG program) here
		  //--------------------------------------
		  String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		  //Debug --> 
	    	  logger.info(jsonPayloadFetch);
	    	  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	  JsonSadExportTopicFinansOpplysningerContainer finansOpplysningerContainer = this.sadExportSpecificTopicService.getSadExportTopicFinansOpplysningerContainer(jsonPayloadFetch);
	    	  if(finansOpplysningerContainer!=null){
	    		  //update the topic record ONLY when the Finans Oppl. exists (at least one row in the list)
	    		  Collection<JsonSadExportTopicFinansOpplysningerRecord> list = finansOpplysningerContainer.getInvoicList();
	    		  if(list!=null & list.size()>0){
	    			  JsonSadExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, applicationUser);
	    			  SadExportSpecificTopicFinansOpplysningarAjaxObject ajaxObject = new SadExportSpecificTopicFinansOpplysningarAjaxObject();
    				  ajaxObject.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_bl28());
    				  ajaxObject.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vk28());
    				  result.add(ajaxObject);
    				  
	    			  /*
	    			  for(JsonSadExportTopicFinansOpplysningerRecord record : finansOpplysningerContainer.getInvoicList()){
	    				  //Set the common currency code for all invoices (if more than one)
	    				  finansOpplysningerContainer.setCalculatedValidCurrency(this.sadExportCalculator.getFinalCurrency(finansOpplysningerContainer));
	    				  Double calculatedItemLinesTotalAmount = this.sadExportCalculator.getItemLinesTotalAmount(finansOpplysningerContainer);
	    				  finansOpplysningerContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    				  logger.info("############FINANS OPP Exists-calculatedAmount:" + finansOpplysningerContainer.getCalculatedItemLinesTotalAmount());
	    				  logger.info("############FINANS OPP Exists-validCurrency:" + finansOpplysningerContainer.getCalculatedValidCurrency());
	    				  //lend to the GUI-object
	    				  SadExportSpecificTopicFinansOpplysningarAjaxObject ajaxObject = new SadExportSpecificTopicFinansOpplysningarAjaxObject();
	    				  ajaxObject.setCalculatedItemLinesTotalAmount(finansOpplysningerContainer.getCalculatedItemLinesTotalAmount());
	    				  ajaxObject.setCalculatedValidCurrency(finansOpplysningerContainer.getCalculatedValidCurrency());
	    				  result.add(ajaxObject);
	    				  break;
	    			  }*/
	    		  }
	    	  }	
		  return result;
	  }
	  
	  private JsonSadExportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices(String avd, String opd, String applicationUser){
			//--------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			JsonSadExportSpecificTopicFaktTotalRecord returnRecord = null;
			
			String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//Debug --> 
	    	logger.info(jsonPayload);
			
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSadExportSpecificTopicFaktTotalContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicFaktTotalContainer(jsonPayload);
	    	if(container!=null){
		    	for(JsonSadExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
					 returnRecord = record;
		    	}
	    	}
			
			return returnRecord;
		}
	  /**
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateExternalInvoiceLine_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer> updateExternalInvoiceLineExport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateExternalInvoiceLineExport");
		  
		  Set<JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer> result = new HashSet<JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer>();
		  
		  try{
			  String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer container = this.sadExportSpecificTopicService.getSadExportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate(jsonPayload);
			  if(container!=null && ( container.getErrmsg()!=null && !"".equals(container.getErrmsg())) ){
				  logger.info("[ERROR] " + container.getErrmsg());
			  }else{
				  logger.info("[INFO]" + " Update successfully done!");
				  result.add(container);
				  
			  }
			  	
		  }catch(Exception e){
			  //e.printStackTrace();
		  }
		  
		  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getSpecificTopic_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadExportSpecificTopicRecord> getSpecificTopic (@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		 logger.info("Inside: getSpecificTopic_SadExport.do");
		 Set result = new HashSet();
		 String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		 //url params
	 	 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd;
		 //for debug purposes in GUI
		 
		 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	 logger.info("URL: " + BASE_URL);
    	 logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	 //--------------------------------------
    	 //EXECUTE the FETCH (RPG program) here
    	 //--------------------------------------
    	 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 //Debug --> 
    	 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	 if(jsonPayload!=null){
    		JsonSadExportSpecificTopicContainer container = this.sadExportSpecificTopicService.getSadExportSpecificTopicContainer(jsonPayload);
    		if(container!=null && container.getOneorder()!=null){
    			for(JsonSadExportSpecificTopicRecord record : container.getOneorder()){
  				  //Debug
  				  logger.info("Avs:" + record.getSenas());
  				  result.add(record);
  			  	}
    		}
    	 }
		 return result;
	 }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getItemLinesTopic_SadExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadExportSpecificTopicRecord> getItemLinesTopic (@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		 logger.info("Inside: getItemLinesTopic_SadExport.do");
		 
		 Set result = new HashSet();
		 String BASE_URL_FETCH = SadExportUrlDataStore.SAD_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
		 StringBuffer urlRequestParamsKeys = new StringBuffer();
			
		 urlRequestParamsKeys.append("user=" + applicationUser);
		 urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		 urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		 logger.info("FETCH av item list... ");
		 logger.info("URL: " + BASE_URL_FETCH);
		 logger.info("URL PARAMS: " + urlRequestParamsKeys);
		 //--------------------------------------
		 //EXECUTE the FETCH (RPG program) here
		 //--------------------------------------
		 String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys.toString());
		 //Debug --> 
		 logger.info(jsonPayloadFetch);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 if(jsonPayloadFetch!=null){
			 JsonSadExportSpecificTopicItemContainer container = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemContainer(jsonPayloadFetch);
			 if(container!=null){
				 for(JsonSadExportSpecificTopicItemRecord record : container.getOrderList()){
					 //Debug
					 logger.info("Varenr:" + record.getSvvnt());
					 logger.info("Beskr:" + record.getWd1());
					 
	  				 result.add(record);
				 }
			 }
		 }	
		 return result;
	 } 
  	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param lineNr
	   * @return
	   */
	  private String getRequestUrlKeyParametersForItem(String applicationUser, String avd, String opd,  String lineNr){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && lineNr!=null && !"".equals(lineNr)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNr );
		  }
		  return sb.toString();
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param lineNr
	   * @return
	   */
	  private String getRequestUrlKeyParametersForFinansOpplysningerItem(String applicationUser, String avd, String opd,  String lineId){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && lineId!=null && !"".equals(lineId)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + lineId );
		  }
		  return sb.toString();
	  }

	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param fakNr
	   * @return
	   */
	  private String getRequestUrlKeyParametersForFinansOpplysningerItem(String applicationUser, String avd, String opd,  String fakNr, String fakDate){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && fakNr!=null && !"".equals(fakNr)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + fakNr );
			  if(fakDate!=null && !"".equals(fakDate)){
				  String isoDate = this.dateFormatter.convertToDate_ISO(fakDate);
				  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "todoXXX=" + isoDate ); 
			  }
		  }
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param avd
	   * @param opd
	   * @param appUserString
	   * @return
	   */
	  private String getRequestUrlKeyParametersFinansOpplysningar(String avd, String opd, String appUserString){
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + appUserString);
		  urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		  urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		  return urlRequestParamsKeys.toString();
	  }	
	  
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier 
	  private SadExportSpecificTopicItemService sadExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSadExportSpecificTopicItemService(SadExportSpecificTopicItemService value){this.sadExportSpecificTopicItemService = value;}
	  public SadExportSpecificTopicItemService getSadExportSpecificTopicItemService(){ return this.sadExportSpecificTopicItemService; }
	  
	  
	  @Qualifier ("currencyRateService")
	  private CurrencyRateService currencyRateService;
	  @Autowired
	  public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	  public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  
	  @Qualifier 
	  private TvinnSadTolltariffVarukodService tvinnSadTolltariffVarukodService;
	  @Autowired
	  @Required	
	  public void setTvinnSadTolltariffVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTolltariffVarukodService = value;}
	  public TvinnSadTolltariffVarukodService getTvinnSadTolltariffVarukodService(){ return this.tvinnSadTolltariffVarukodService; }
		
	  
	  @Qualifier ("sadExportSpecificTopicService")
	  private SadExportSpecificTopicService sadExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSadExportSpecificTopicService (SadExportSpecificTopicService value){ this.sadExportSpecificTopicService = value; }
	  public SadExportSpecificTopicService getSadExportSpecificTopicService(){ return this.sadExportSpecificTopicService; }
	  
	  @Qualifier ("tvinnSadDropDownListPopulationService")
	  private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	  @Autowired
	  public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	  public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
		
}
