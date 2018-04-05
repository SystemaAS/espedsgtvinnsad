/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.controller.ajax;

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

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.JsonDebugger;

//Tvinn
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicService;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicItemService;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemContainer;

import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicItemService;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicUnloadingItemService;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;
/**
 * This Ajax handler is the class handling ajax request in Sad-NctsImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Mar 09, 2015
 * 
 */

@Controller
public class SadNctsImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SadNctsImportAjaxHandlerController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(); 
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	
	@RequestMapping(value = "getSpecificTopicUnloadingItemChosenFromGuiElement_TvinnNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadNctsImportSpecificTopicUnloadingItemRecord> getSpecificTopicUnloadingItemRecord
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicUnloadingItemChosenFromGuiElement_TvinnSadNctsImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineCounter = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineCounter = fields[1];
			 lineNr = fields[2];
			 
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonSadNctsImportSpecificTopicUnloadingItemContainer container = this.sadNctsImportSpecificTopicUnloadingItemService.getNctsImportSpecificTopicUnloadingItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadNctsImportSpecificTopicUnloadingItemRecord  record : container.getOrderList()){
						 logger.info("Item Description: " + record.getNvvt());
						 logger.info("Item line nr: " + record.getTvli());
						 //this.concatenateInformationFields(record);
						 result.add(record);
					 }
				 }
			 }
			 
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }	
	 

	/**
	  * 
	  * @param applicationUser
	  * @param elementValue
	  * @param user
	  * @param avd
	  * @param opd
	  * @return
	  */
	 @RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_TvinnSadNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadNctsImportSpecificTopicItemRecord> getSpecificTopicItemChosenFromGuiElement
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_TvinnSadNctsImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String varupostNr = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineNr = fields[1];
			 varupostNr = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonSadNctsImportSpecificTopicItemContainer container = this.sadNctsImportSpecificTopicItemService.getNctsImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadNctsImportSpecificTopicItemRecord  record : container.getOrderList()){
						 logger.info("Linjenr.: " + record.getTvli());
						 logger.info("Plats: " + record.getTvst());
						 this.concatenateInformationFields(record);
						 result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }
	 
	 
	 /**
	  * The method is used for fetching default values on "Create new"
	  * It is triggered when the end user chooses a department (Avdelning)
	  * 
	  * @param applicationUser
	  * @param customerName
	  * @param customerNumber
	  * @return
	  */
	 
	 @RequestMapping(value = "initCreateNewTopic_TvinnSadNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {
		 
		 	String method = "initCreateNewTopic_TvinnSadNctsImport()";
		 	logger.info("Inside:" + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForInitCreateNewTopic(applicationUser, avd);
			//for debug purposes in GUI
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
	    		JsonSadNctsImportSpecificTopicContainer container = this.sadNctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonSadNctsImportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Registreringsdatum: " + record.getTidt() );
	    				result.add(record);
	    			}
	    		}
	    	}
			return result;
		  
	  }
	 
	 /**
	   * 
	   * @param applicationUser
	   * @param taricVarukod
	   * @return
	   */
	  /*TODO
	  @RequestMapping(value = "searchTaricVarukodNcts_SkatNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> getTaricVarukodNcts(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside method: getTaricVarukodNcts()");
		  Set result = new HashSet();
		  String EXPORT_IE = "I";//always I since we are in IMPORT mode
		  
		  try{
			  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + EXPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  //logger.info("02:" + record.getDktara02());
				  //logger.info("63:" + record.getDktara63());
				  //logger.info("64:" + record.getDktara64());
				  
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	 */ 
	
	 /**
	  * 
	  * @param record
	  */
	 private void concatenateInformationFields(JsonSadNctsImportSpecificTopicItemRecord record){
		 String inf = null;
		 
		 //record.setTvinf(value)
		 if(record.getTvinf1()!=null && !"".equals(record.getTvinf1())){
			 inf = record.getTvinf1();
		 }
		 if(record.getTvinf2()!=null && !"".equals(record.getTvinf2())){
			 inf += " " + record.getTvinf2();
		 }
		 if(record.getTvinf3()!=null && !"".equals(record.getTvinf3())){
			 inf += " " + record.getTvinf3();
		 }
		 if(record.getTvinf4()!=null && !"".equals(record.getTvinf4())){
			 inf += " " + record.getTvinf4();
		 }
		 
		 record.setTvinf(inf);
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
	   * @return
	   */
	  private String getRequestUrlKeyParametersForInitCreateNewTopic(String applicationUser, String avd){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
		  return sb.toString();
	  }
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  @Qualifier ("sadNctsImportSpecificTopicItemService")
	  private SadNctsImportSpecificTopicItemService sadNctsImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSadNctsImportSpecificTopicItemService(SadNctsImportSpecificTopicItemService value){this.sadNctsImportSpecificTopicItemService = value;}
	  public SadNctsImportSpecificTopicItemService getSadNctsImportSpecificTopicItemService(){ return this.sadNctsImportSpecificTopicItemService; }
	    
	  @Qualifier ("sadNctsImportSpecificTopicService")
	  private SadNctsImportSpecificTopicService sadNctsImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSadNctsImportSpecificTopicService (SadNctsImportSpecificTopicService value){ this.sadNctsImportSpecificTopicService = value; }
	  public SadNctsImportSpecificTopicService getSadNctsImportSpecificTopicService(){ return this.sadNctsImportSpecificTopicService; }
	  
	  
	   
	  
	  @Qualifier ("sadNctsImportSpecificTopicUnloadingItemService")
	  private SadNctsImportSpecificTopicUnloadingItemService sadNctsImportSpecificTopicUnloadingItemService;
	  @Autowired
	  @Required	
	  public void setSadNctsImportSpecificTopicUnloadingItemService(SadNctsImportSpecificTopicUnloadingItemService value){this.sadNctsImportSpecificTopicUnloadingItemService = value;}
	  public SadNctsImportSpecificTopicUnloadingItemService getSadNctsImportSpecificTopicUnloadingItemService(){ return this.sadNctsImportSpecificTopicUnloadingItemService; }
	  

	  
	  @Qualifier ("tvinnSadTaricVarukodService")
	  private TvinnSadTolltariffVarukodService tvinnSadTaricVarukodService;
	  @Autowired
	  @Required	
	  public void setTvinnSadTaricVarukodService(TvinnSadTolltariffVarukodService value){this.tvinnSadTaricVarukodService = value;}
	  public TvinnSadTolltariffVarukodService getTvinnSadTaricVarukodService(){ return this.tvinnSadTaricVarukodService; }
	  
		
}
