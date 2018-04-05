/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.controller.ajax;

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
import no.systema.main.util.JavaReflectionUtil;

//SAD

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.service.TvinnSadTolltariffVarukodService;

import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityRecord;

import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicService;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicItemService;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;


/**
 * This Ajax handler is the class handling ajax request in SadNctsExport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Feb 19, 2016
 * 
 */
@Controller
public class SadNctsExportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SadNctsExportAjaxHandlerController.class.getName());
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_TvinnSadNctsExport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadNctsExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_TvinnSadNctsExport.do()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
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
				 //in the future by orderList... We do that here and now
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 logger.info(jsonPayload);
				 JsonSadNctsExportSpecificTopicItemContainer container = this.sadNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSadNctsExportSpecificTopicItemRecord  record : container.getOrderList()){
						 record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         //get security fields and add to master record
				         this.fetchSecurityRecord(record, applicationUser, avd, opd, lineNr);
				         //complete record including security
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
	   * @param avd
	   * @return
	   */
	  @RequestMapping(value = "initCreateNewTopic_TvinnSadNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {		 
		 	String method = "initCreateNewTopic";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForInitCreateNewTopic(applicationUser, avd);
			//for debug purposes in GUI
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
			JsonSadNctsExportSpecificTopicContainer container = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonSadNctsExportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Deklarantens plats via AJAX: " + record.getThdst() );
	    				result.add(record);
	    			}
	    		}
	    	}
		return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  @RequestMapping(value = "validateGuaranteeNrAndCode_TvinnSadNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadCustomerRecord> validateGuaranteeNrAndCode(@RequestParam String applicationUser, @RequestParam String guaranteeNumber, @RequestParam String guaranteeCode ) {
		 
		 	String method = "validateGuaranteeNrAndCode";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("VALIDATION of Guarantee...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(applicationUser,guaranteeNumber,guaranteeCode );
			//for debug purposes in GUI
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
				JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer container = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
				logger.info("errMsg on Guarantee: " + container.getErrMsg());
    			if(container!=null){
    				result.add(container);
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
	  
	  @RequestMapping(value = "searchTaricVarukod_TvinnSadNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTvinnSadTolltariffVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside getTaricVarukod");
		  Set result = new HashSet();
		  String IMPORT_IE = "E";//
		  
		  try{
			  String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonTvinnSadTolltariffVarukodContainer container = this.tvinnSadTolltariffVarukodService.getContainer(jsonPayload);
			  for(JsonTvinnSadTolltariffVarukodRecord record : container.getTariclist()){
				  /*logger.info("dktara02:" + record.getDktara02());
				  logger.info("dktara63:" + record.getDktara63());
				  logger.info("dktara64:" + record.getDktara64());
				  */
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
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "importSadExportAsNctsExportItemLine_SadNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSadNctsExportSpecificTopicContainer> importSadExportAsSkatNctsExportItemLine(@RequestParam String applicationUser, @RequestParam String requestParams) {
		 
		 	String method = "importSadExportAsNctsExportItemLine_SadNctsExport.do";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	if (requestParams!=null && !"".equals(requestParams)){
			 	String[] params = requestParams.split(";");
			 	List <String>list = Arrays.asList(params);
			 	
			 	for (String record : list){
				 	logger.info("update record transaction started");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
					//---------------------------
					String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_IMPORT_EXPORT_AS_ITEMLINE_URL;
					//url params
					String urlRequestParamsKeys = "user=" + applicationUser + record;
					//for debug purposes in GUI
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					logger.info("URL: " + BASE_URL);
					logger.info("URL PARAMS: " + urlRequestParamsKeys);
					//--------------------------------------
					//EXECUTE RPG program here
					//--------------------------------------
					logger.info("CB/OT todo TVINN-implementations pgm!!!");
					/*
					String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
					//Debug --> 
					logger.info(method + " --> jsonPayload:" + jsonPayload);
					logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
					if(jsonPayload!=null){
					JsonSadNctsExportSpecificTopicContainer container = this.sadNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
			    		if(container!=null){
			    			logger.info("container errMsg (if any): " + "avd:" + container.getAvd() + " opd:" + container.getOpd() + 
			    						" errMsg:" + container.getErrMsg() );
			    					result.add(container);
			    		}
			    	}
			    	*/
			 	}
		 	}
		return result;  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
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
	  
	  /**
	   * 
	   * @param jsonNctsExportSpecificTopicItemRecord
	   * @param user
	   * @param avd
	   * @param opd
	   * @param lin
	   */
		private void fetchSecurityRecord(JsonSadNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord, String user, String avd, String opd, String lin){
			String method = "fetchSecurityRecord";
			logger.info("starting " + method + " transaction...");
			
			String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL;
			String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd + "&lin=" + lin;   
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	JsonSadNctsExportSpecificTopicItemSecurityRecord securityRecord = null;
	    	JsonSadNctsExportSpecificTopicItemRecord targetRecord = jsonNctsExportSpecificTopicItemRecord;
	    	
	    	//------------------------
	    	//EXECUTE (Sikkerhet) here
	    	//------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	logger.info(method + " --> jsonPayload:" + jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSadNctsExportSpecificTopicItemSecurityContainer securityContainer = this.sadNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemSecurityContainer(jsonPayload);
	        	if(securityContainer!=null && securityContainer.getSecurityline()!=null){
	        		for (JsonSadNctsExportSpecificTopicItemSecurityRecord thisRecord : securityContainer.getSecurityline()){
	        			securityRecord = thisRecord;
	        			logger.info("Tvknss" + securityRecord.getTvknss());
	            	}
	        		//copy delta to target record
	        		if(securityRecord!=null && targetRecord!=null){
		        		JavaReflectionUtil.setFields(securityRecord, targetRecord);
		        		//Replace the orginal with the complete record (including security record, if any)
		        		jsonNctsExportSpecificTopicItemRecord = targetRecord;
	        		}else{
	        			/* TEST on Copy
	        			securityRecord = new JsonSadNctsExportSpecificTopicItemSecurityRecord();
		        		securityRecord.setTvnass("TARZAN");
		        		logger.info("SOURCE:" + securityRecord.getTvnass());
		        		JavaReflectionUtil.setFields(securityRecord, targetRecord);
		        		//Replace the orginal with the complete record (including security record, if any)
		        		jsonNctsExportSpecificTopicItemRecord = targetRecord;
		        		logger.info("TARGET:" + jsonNctsExportSpecificTopicItemRecord.getTvnass());
		        		*/
	        		}
	        	}
	    	}
		}
		
		
	  
	  
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier ("sadNctsExportSpecificTopicService")
	  private SadNctsExportSpecificTopicService sadNctsExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSadNctsExportSpecificTopicService (SadNctsExportSpecificTopicService value){ this.sadNctsExportSpecificTopicService = value; }
	  public SadNctsExportSpecificTopicService getSadNctsExportSpecificTopicService(){ return this.sadNctsExportSpecificTopicService; }
	  
	  
	  @Qualifier ("sadNctsExportSpecificTopicItemService")
	  private SadNctsExportSpecificTopicItemService sadNctsExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSadNctsExportSpecificTopicItemService(SadNctsExportSpecificTopicItemService value){this.sadNctsExportSpecificTopicItemService = value;}
	  public SadNctsExportSpecificTopicItemService getSadNctsExportSpecificTopicItemService(){ return this.sadNctsExportSpecificTopicItemService; }

	  
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
	  
	 
}
