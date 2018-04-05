/**
 * 
 */
package no.systema.tvinn.sad.sadimport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.service.general.CurrencyRateService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
//TVINN SAD
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.util.SadImportCalculator;
import no.systema.tvinn.sad.sadimport.model.topic.SadImportSpecificTopicFinansOpplysningarAjaxObject;
import no.systema.tvinn.sad.sadimport.model.topic.items.SadImportSpecificTopicItemAvgiftDynamicObject;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord;


import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaRecord;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;

/**
 * This Ajax handler is the class handling ajax request in SkatImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 */
@Controller
public class SadImportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadImportAjaxHandlerController.class.getName());
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	private SadImportCalculator sadImportCalculator = new SadImportCalculator();
	
	/**
	 * Fetches the dep. information
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	
	 @RequestMapping(value = "getSpecificTopicAvdData_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadImportSpecificTopicRecord> getSpecificTopicAvdData
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 final String METHOD = "[DEBUG] method:getSpecificTopicAvdData ";
		 logger.info(METHOD + " Inside...");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_AVDDATA_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info(METHOD + "URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(METHOD + jsonPayload);
		 if(jsonPayload!=null){
				 JsonSadImportSpecificTopicAvdDataContainer container = this.sadImportSpecificTopicService.getSadImportSpecificTopicAvdDataContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadImportSpecificTopicRecord  record : container.getGetdepinf()){
						 //logger.info("#### DKIA_14a:" + record.getDkia_14a());
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
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SadImport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadImportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 final String METHOD = "[DEBUG] getSpecificTopicItemChosenFromHtmlList ";
		 logger.info(METHOD + "Inside...");
		 
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
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
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 logger.info(METHOD + jsonPayload);
				 JsonSadImportSpecificTopicItemContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadImportSpecificTopicItemRecord  record : container.getOrderList()){
						 record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         result.add(record);
				         //logger.info("svvf:" + record.getSvvf());
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
	@RequestMapping(value = "getSpecificTopicFinansOpplysningerItemChosenFromGuiElement_SadImport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadImportTopicFinansOpplysningerRecord> getSpecificTopicFinansOpplysningerItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 
		 final String METHOD = "[DEBUG] getSpecificTopicFinansOpplysningerItemChosenFromHtmlList ";
		 logger.info(METHOD + "Inside");
		 final String ID_RECORD_SEPARATOR = "__";
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL;
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
				 JsonSadImportTopicFinansOpplysningerContainer container = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainerOneInvoice(jsonPayload);
				 if(container!=null){
					 for(JsonSadImportTopicFinansOpplysningerRecord  record : container.getOneInvoice()){
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
	   * @return
	   */
	  
	  @RequestMapping(value = "searchTolltariffVarukod_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadTolltariffVarukodRecord> getTolltariffVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  
		  final String METHOD = "[DEBUG] method: getTolltariffVarukod ";
		  logger.info("Inside..." + METHOD);
		  Set result = new HashSet();
		  String IMPORT_IE = "I";//
		  try{
			  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  //logger.info(jsonPayload);
			  JsonTvinnSadTolltariffVarukodContainer container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
			  for(JsonTvinnSadTolltariffVarukodRecord record : container.getTariclist()){
				  result.add(record);
				  //logger.info(record.getBeskr1());
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param sidp
	   * @param sitst
	   * @param svvnt
	   * @param svlk
	   * @param svtn
	   * @return
	   */
	  @RequestMapping(value = "getPreferenceCode_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadImportSpecificTopicItemPreferenceRecord> getPreferenceCode(@RequestParam String applicationUser, 
			  @RequestParam String sidp, @RequestParam String sitst, @RequestParam String svvnt,@RequestParam String svlk, @RequestParam String svtn) {
		  
		  final String METHOD = "[DEBUG] method: getPreferenceCode ";
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  try{
			  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_GET_SPECIFIC_TOPIC_ITEM_PREFERENCE_CODE_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&sidp=" + sidp + "&sitst=" + sitst + "&svvnt=" + svvnt + "&svlk=" + svlk + "&svtn=" + svtn;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  //logger.info(jsonPayload);
			  JsonSadImportSpecificTopicItemPreferenceContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemPreferenceContainer(jsonPayload);
			  for(JsonSadImportSpecificTopicItemPreferenceRecord record : container.getGetpreferanse()){
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param svvnt
	   * @param svlk
	   * @param svpre
	   * @return
	   */
	  @RequestMapping(value = "getPvaCode_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadImportSpecificTopicItemPvaRecord> getPvaCode(@RequestParam String applicationUser, 
			  @RequestParam String svvnt,@RequestParam String svlk, @RequestParam String svpre) {
		  
		  final String METHOD = "[DEBUG] method: getPvaCode ";
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  try{
			  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_GET_SPECIFIC_TOPIC_ITEM_PVA_CODE_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&svvnt=" + svvnt + "&svlk=" + svlk + "&svpre=" + svpre;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  //logger.info(jsonPayload);
			  JsonSadImportSpecificTopicItemPvaContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemPvaContainer(jsonPayload);
			  for(JsonSadImportSpecificTopicItemPvaRecord record : container.getGetPVA()){
				  result.add(record);
			  }	

		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	  /**
	  * 
	  * @param applicationUser
	  * @param code
	  * @return
	  */
	 @RequestMapping(value = "getAvgiftSequence_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCodeRecord> getAvgiftSequence(@RequestParam String applicationUser, @RequestParam String code) {
		 
		  final String METHOD = "[DEBUG] getAvgiftSequence ";
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  String type = "8B";//
		  
		  try{
			  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&typ=" + type + "&kode=" + code;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonTvinnSadCodeContainer container = this.tvinnSadDropDownListPopulationService.getCodeContainer(jsonPayload);
			  for(JsonTvinnSadCodeRecord record : container.getKodlista()){
				  //logger.info("zskv:" + record.getZskv());
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	 
	  /**
	  * 
	  * @param applicationUser
	  * @param svvnt
	  * @return
	  */
	 @RequestMapping(value = "getAvgiftDataBeforeCalculation_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> getAvgiftDataBeforeCalculation(@RequestParam String applicationUser, @RequestParam String svvnt, @RequestParam String siknk_receiverId ) {
		  final String METHOD = "[DEBUG] getAvgiftDataBeforeCalculation ";
		  logger.info(METHOD + "Inside...");
		  List result = new ArrayList();
		  try{
			  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_AVGIFTER_BEFORE_CALCULATION_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&svvnt=" + svvnt + "&siknk=" + siknk_receiverId;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		  	  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  logger.info(jsonPayload);
			  
			  JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemAvgifterBeforeCalculationContainer(jsonPayload);
			  //we are returning a list instead of a Set since we must have an ordered list. TreeSet did not work...
			  for(JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord record : container.getGetavgifter()){
				  //logger.info("awa:" + record.getAwa());
				  result.add(record);
			  }	
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	 
	 /**
	  * 
	  * @param applicationUser
	  * @param svvnt
	  * @param omrakningsFaktor
	  * @param fakturaSum
	  * @param belCifSum
	  * @param bearbKost
	  * @param cifsum
	  * @param tollvardi
	  * @param bruttoVekt
	  * @param nettoVekt
	  * @param mengde
	  * @param kode0
	  * @param sekvensList_0
	  * @param sats0
	  * @param kode1
	  * @param sekvensList_1
	  * @param sats1
	  * @param kode2
	  * @param sekvensList_2
	  * @param sats2
	  * @param kode3
	  * @param sekvensList_3
	  * @param sats3
	  * @param kode4
	  * @param sekvensList_4
	  * @param sats4
	  * @param kode5
	  * @param sekvensList_5
	  * @param sats5
	  * @param kode6
	  * @param sekvensList_6
	  * @param sats6
	  * @param kode7
	  * @param sekvensList_7
	  * @param sats7
	  * @return
	  */
	 @RequestMapping(value = "setAvgiftDataAfterCalculation_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> setAvgiftDataAfterCalculation(@RequestParam String applicationUser, @RequestParam String svvnt,
			  @RequestParam String omrakningsFaktor, @RequestParam String fakturaSum, @RequestParam String belCifSum, @RequestParam String bearbKost, @RequestParam String cifsum,
			  @RequestParam String tollvardi, @RequestParam String bruttoVekt, @RequestParam String nettoVekt, @RequestParam String mengde, 
			  @RequestParam String kode0, @RequestParam String sekvensList_0, @RequestParam String sats0,
			  @RequestParam String kode1, @RequestParam String sekvensList_1, @RequestParam String sats1, 
			  @RequestParam String kode2, @RequestParam String sekvensList_2, @RequestParam String sats2, 
			  @RequestParam String kode3, @RequestParam String sekvensList_3, @RequestParam String sats3, 
			  @RequestParam String kode4, @RequestParam String sekvensList_4, @RequestParam String sats4, 
			  @RequestParam String kode5, @RequestParam String sekvensList_5, @RequestParam String sats5, 
			  @RequestParam String kode6, @RequestParam String sekvensList_6, @RequestParam String sats6, 
			  @RequestParam String kode7, @RequestParam String sekvensList_7, @RequestParam String sats7,
			  @RequestParam String kode8, @RequestParam String sekvensList_8, @RequestParam String sats8,
			  @RequestParam String kode9, @RequestParam String sekvensList_9, @RequestParam String sats9,
			  @RequestParam String kode10, @RequestParam String sekvensList_10, @RequestParam String sats10,
			  @RequestParam String kode11, @RequestParam String sekvensList_11, @RequestParam String sats11,
			  @RequestParam String kode12, @RequestParam String sekvensList_12, @RequestParam String sats12) {

		 final Integer NUMBER_OF_FIELDS = 13;
		 final String METHOD = "[DEBUG] setAvgiftDataAfterCalculation";
		  logger.info(METHOD + " Inside...");
		  List result = new ArrayList();
		  try{
			  Map<Integer, SadImportSpecificTopicItemAvgiftDynamicObject> recordDynObj = this.getDynamicObject(
					   kode0, sekvensList_0,  sats0, kode1, sekvensList_1,  sats1, kode2, sekvensList_2,  sats2,kode3, sekvensList_3,  sats3, 
					   kode4, sekvensList_4,  sats4, kode5, sekvensList_5,  sats5, kode6, sekvensList_6,  sats6,kode7, sekvensList_7,  sats7,
					   kode8, sekvensList_8,  sats8, kode9, sekvensList_9,  sats9, kode10, sekvensList_10,  sats10, kode11, sekvensList_11,  sats11,
					   kode12, sekvensList_12,  sats12);
			  
			  String urlRequestHeaderParamsKeys=null;
			  String urlRequestItemLineParamsKeys=null;
			  String urlRequestParamsKeys=null;
			  
			  for(int i=0;i<NUMBER_OF_FIELDS;i++){
				  
				  SadImportSpecificTopicItemAvgiftDynamicObject obj = (SadImportSpecificTopicItemAvgiftDynamicObject)recordDynObj.get(i);
				  if(this.isValidItemAvgift(obj)){
					  urlRequestHeaderParamsKeys = this.getRequestUrlHeaderKeyParametersForAvgifterAfterCalculation(applicationUser,omrakningsFaktor,fakturaSum,belCifSum,bearbKost,cifsum);
					  urlRequestItemLineParamsKeys = this.getRequestUrlItemKeyParametersForAvgifterAfterCalculation(obj.getKode(), obj.getSekvens(), obj.getSats(), tollvardi,bruttoVekt,nettoVekt,mengde );
					  urlRequestParamsKeys = urlRequestHeaderParamsKeys + urlRequestItemLineParamsKeys;
					  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_AVGIFTER_AFTER_CALCULATION_URL;
					  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
					  logger.info(METHOD + "URL:" + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
				  	  logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
				  	  
				  	  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				  	  logger.info(jsonPayload);
					  
				  	  JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemAvgifterAfterCalculationContainer(jsonPayload);
					  //we are returning a list instead of a Set since we must have an ordered list. TreeSet did not work...
					  for(JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord record : container.getCalcavgifter()){
						  //hand over from container to record;
						  record.setWg(container.getWg());record.setWh(container.getWh());record.setWk(container.getWk());
						  result.add(record);
					  }
					  
				  }
			  }
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	 
	 /**
	  * 
	  * @param kode0
	  * @param sekvensList_0
	  * @param sats0
	  * @param kode1
	  * @param sekvensList_1
	  * @param sats1
	  * @param kode2
	  * @param sekvensList_2
	  * @param sats2
	  * @param kode3
	  * @param sekvensList_3
	  * @param sats3
	  * @param kode4
	  * @param sekvensList_4
	  * @param sats4
	  * @param kode5
	  * @param sekvensList_5
	  * @param sats5
	  * @param kode6
	  * @param sekvensList_6
	  * @param sats6
	  * @param kode7
	  * @param sekvensList_7
	  * @param sats7
	  * @param kode8
	  * @param sekvensList_8
	  * @param sats8
	  * @param kode9
	  * @param sekvensList_9
	  * @param sats9
	  * @return
	  */
	 private Map<Integer, SadImportSpecificTopicItemAvgiftDynamicObject>getDynamicObject(
			 String kode0, String sekvensList_0, String sats0, String kode1, String sekvensList_1, String sats1,
			 String kode2, String sekvensList_2, String sats2, String kode3, String sekvensList_3, String sats3,
			 String kode4, String sekvensList_4, String sats4, String kode5, String sekvensList_5, String sats5,
			 String kode6, String sekvensList_6, String sats6, String kode7, String sekvensList_7, String sats7,
			 String kode8, String sekvensList_8, String sats8, String kode9, String sekvensList_9, String sats9,
			 String kode10, String sekvensList_10, String sats10, String kode11, String sekvensList_11, String sats11,
			 String kode12, String sekvensList_12, String sats12){
		 
		 Map retval = new HashMap();
		 
		 SadImportSpecificTopicItemAvgiftDynamicObject obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode0);obj.setSats(sats0);obj.setSekvens(sekvensList_0);
		 retval.put(0, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode1);obj.setSats(sats1);obj.setSekvens(sekvensList_1);
		 retval.put(1, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode2);obj.setSats(sats2);obj.setSekvens(sekvensList_2);
		 retval.put(2, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode3);obj.setSats(sats3);obj.setSekvens(sekvensList_3);
		 retval.put(3, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode4);obj.setSats(sats4);obj.setSekvens(sekvensList_4);
		 retval.put(4, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode5);obj.setSats(sats5);obj.setSekvens(sekvensList_5);
		 retval.put(5, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode6);obj.setSats(sats6);obj.setSekvens(sekvensList_6);
		 retval.put(6, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode7);obj.setSats(sats7);obj.setSekvens(sekvensList_7);
		 retval.put(7, obj);
		 
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode8);obj.setSats(sats8);obj.setSekvens(sekvensList_8);
		 retval.put(8, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode9);obj.setSats(sats9);obj.setSekvens(sekvensList_9);
		 retval.put(9, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode10);obj.setSats(sats10);obj.setSekvens(sekvensList_10);
		 retval.put(10, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode11);obj.setSats(sats11);obj.setSekvens(sekvensList_11);
		 retval.put(11, obj);
		 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
		 obj.setKode(kode12);obj.setSats(sats12);obj.setSekvens(sekvensList_12);
		 retval.put(12, obj);
		 
		 
		 return retval;
	 }
	 
	 /**
	  * Header parameters (meaning fields belonging to the Topic header form)
	  * 
	  * @param applicationUser
	  * @param omrakningsFaktor
	  * @param fakturaSum
	  * @param belCifSum
	  * @param bearbKost
	  * @param cifsum
	  * @return
	  */
	 private String getRequestUrlHeaderKeyParametersForAvgifterAfterCalculation(String applicationUser,
			 String omrakningsFaktor, String fakturaSum, String belCifSum, String bearbKost, String cifsum ){
		  
		 StringBuffer sb = new StringBuffer();
		 sb.append("user=" + applicationUser);
		 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ubvnv=" + omrakningsFaktor);
		 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibel3=" + fakturaSum);
		 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibel4=" + belCifSum);
		 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibelr=" + bearbKost);
		 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibels=" + cifsum);
		  
		 return sb.toString();
	  }

	 /**
	  * Item line parameters ...
	  * @param kode
	  * @param sekvensList
	  * @param sats
	  * @param tollvardi
	  * @param bruttoVekt
	  * @param nettoVekt
	  * @param mengde
	  * @return
	  */
	 private String getRequestUrlItemKeyParametersForAvgifterAfterCalculation(String kode, String sekvensList, String sats, 
			 String tollvardi, String bruttoVekt, String nettoVekt, String mengde ){
		 
		 StringBuffer sb = new StringBuffer();
		 if( (sekvensList!=null && !"".equals(sekvensList)) && (sats!=null && !"".equals(sats)) ){
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wg=" + kode );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wh=" + sekvensList );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wk=" + sats );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svbelt=" + tollvardi );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svvktb=" + bruttoVekt );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svvktn=" + nettoVekt );
			 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svntm=" + mengde );
		 }
		 return sb.toString();
	  }
	 /**
	  * 
	  * @param obj
	  * @return
	  */
	 private boolean isValidItemAvgift(SadImportSpecificTopicItemAvgiftDynamicObject obj){
		 boolean retval = false;
		 if( (obj.getSekvens()!=null && !"".equals(obj.getSekvens())) && (obj.getSats()!=null && !"".equals(obj.getSats()))){
			 retval = true;
		 }
		 
		 return retval;
	 }
	 
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @param isoDate
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, @RequestParam String isoDate) {
		  final String METHOD = "[DEBUG] method:getCurrencyRate_SadImport "; 
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
				  //logger.info(METHOD + "Currency RATE: " + record.getKvakrs() + " Currency FACTOR: " + record.getKvaomr() + " Currency DATE: " + validDate);
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  
	  /**
	   * get correct date formatted
	   * @param isoDate
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
			  logger.info("currencyDate <dagensDato>:" + retval);
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
	  @RequestMapping(value = "getFinansTotalSumAndRelatedFields_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getFinansTotalSumAndRelatedFields_SadImport(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		  final String METHOD = "[DEBUG] method:getFinansTotalSumAndRelatedFields_SadImport "; 
		  logger.info(METHOD + "Inside...");
		  Set result = new HashSet();
		  
		  String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL;
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
	    	  JsonSadImportTopicFinansOpplysningerContainer finansOpplysningerContainer = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainer(jsonPayloadFetch);
	    	  if(finansOpplysningerContainer!=null){
	    		  //update the topic record ONLY when the Finans Oppl. exists (at least one row in the list)
	    		  Collection<JsonSadImportTopicFinansOpplysningerRecord> list = finansOpplysningerContainer.getInvoicList();
	    		  if(list!=null & list.size()>0){
	    			  JsonSadImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, applicationUser);
    				  SadImportSpecificTopicFinansOpplysningarAjaxObject ajaxObject = new SadImportSpecificTopicFinansOpplysningarAjaxObject();
    				  ajaxObject.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_bl28());
    				  ajaxObject.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vk28());
    				  result.add(ajaxObject);
    				  
    				  /*
	    			  for(JsonSadImportTopicFinansOpplysningerRecord record : finansOpplysningerContainer.getInvoicList()){
	    				  //Set the common currency code for all invoices (if more than one)
	    				  finansOpplysningerContainer.setCalculatedValidCurrency(this.sadImportCalculator.getFinalCurrency(finansOpplysningerContainer));
	    				  Double calculatedItemLinesTotalAmount = this.sadImportCalculator.getItemLinesTotalAmount(finansOpplysningerContainer);
	    				  finansOpplysningerContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    				  logger.info("############FINANS OPP Exists-calculatedAmount:" + finansOpplysningerContainer.getCalculatedItemLinesTotalAmount());
	    				  logger.info("############FINANS OPP Exists-validCurrency:" + finansOpplysningerContainer.getCalculatedValidCurrency());
	    				  //lend to the GUI-object
	    				  SadImportSpecificTopicFinansOpplysningarAjaxObject ajaxObject = new SadImportSpecificTopicFinansOpplysningarAjaxObject();
	    				  ajaxObject.setCalculatedItemLinesTotalAmount(finansOpplysningerContainer.getCalculatedItemLinesTotalAmount());
	    				  ajaxObject.setCalculatedValidCurrency(finansOpplysningerContainer.getCalculatedValidCurrency());
	    				  result.add(ajaxObject);
	    				  break;
	    			  }*/
	    		  }
	    		  
	    	  }	
		  return result;
	  }
	  
	  /**
	   * 
	   * @param avd
	   * @param opd
	   * @param applicationUser
	   * @return
	   */
	  private JsonSadImportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices(String avd, String opd, String applicationUser){
			//--------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			JsonSadImportSpecificTopicFaktTotalRecord returnRecord = null;
			
			String BASE_URL_FETCH = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
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
	    	JsonSadImportSpecificTopicFaktTotalContainer container = this.sadImportSpecificTopicService.getSadImportSpecificTopicFaktTotalContainer(jsonPayload);
	    	if(container!=null){
		    	for(JsonSadImportSpecificTopicFaktTotalRecord record : container.getInvTot()){
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
	  @RequestMapping(value = "updateExternalInvoiceLine_SadImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer> updateExternalInvoiceLineExport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateExternalInvoiceLineExport");
		  
		  Set<JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer> result = new HashSet<JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer>();
		  
		  try{
			  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer container = this.sadImportSpecificTopicService.getSadImportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate(jsonPayload);
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

	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier 
	  private SadImportSpecificTopicItemService sadImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSadImportSpecificTopicItemService(SadImportSpecificTopicItemService value){this.sadImportSpecificTopicItemService = value;}
	  public SadImportSpecificTopicItemService getSadImportSpecificTopicItemService(){ return this.sadImportSpecificTopicItemService; }
	  
	  
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
		
	  
	  @Qualifier ("sadImportSpecificTopicService")
	  private SadImportSpecificTopicService sadImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSadImportSpecificTopicService (SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	  public SadImportSpecificTopicService getSadImportSpecificTopicService(){ return this.sadImportSpecificTopicService; }
	  
	  @Qualifier ("tvinnSadDropDownListPopulationService")
	  private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	  @Autowired
	  public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	  public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
		
	  @Qualifier ("notisblockService")
	  private NotisblockService notisblockService;
	  @Autowired
	  public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	  public NotisblockService getNotisblockService(){return this.notisblockService;}
		
		
}
