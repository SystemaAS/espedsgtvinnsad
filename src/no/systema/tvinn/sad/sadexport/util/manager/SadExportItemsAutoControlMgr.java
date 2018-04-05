package no.systema.tvinn.sad.sadexport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
/*
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.util.manager.TdsImportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
*/

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicItemService;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;


public class SadExportItemsAutoControlMgr {
	private static final Logger logger = Logger.getLogger(SadExportItemsAutoControlMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private SadExportSpecificTopicItemService sadExportSpecificTopicItemService = null;
	NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	//Should be set after the constructor call
	private JsonSadExportSpecificTopicItemRecord record = null;
	public void setRecord (JsonSadExportSpecificTopicItemRecord recordToValidate){ 
		this.record = recordToValidate;
		this.validRecord = true;
	}
	
	//this is the global validRecord that will decide if the control passes
	private boolean validRecord = true;
	public boolean isValidRecord (){return this.validRecord;}
	
	public SadExportItemsAutoControlMgr(UrlCgiProxyService urlCgiProxyService, SadExportSpecificTopicItemService sadExportSpecificTopicItemService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.sadExportSpecificTopicItemService = sadExportSpecificTopicItemService;
	}
	
	
	/**
	 * This method must comply with the section: ValidationUtils.rejectIfEmptyOrWhitespace in order to check the same mandatory fields as the normal validation method call 
	 *  
	 */
	
	public void checkValidMandatoryFields(){

		if(this.record.getSvfyl()==null || "".equals(this.record.getSvfyl())){ this.validRecord = false; }
		if(this.record.getSvvnt()==null || "".equals(this.record.getSvvnt())){ this.validRecord = false; }
		if(this.record.getSvvktb()==null || "".equals(this.record.getSvvktb())){ this.validRecord = false; }
		if(this.record.getSvvktn()==null || "".equals(this.record.getSvvktn())){ this.validRecord = false; }
	}

	/**
	 *
	 */
	public void checkCountryCode(){
		if("91".equals(record.getSvfyl())){
			if(!"".equals(record.getSvlk())){
				this.validRecord = false;
			}
		}
	}
	
	/**
	 *
	 */
	public void checkValidGrossAndNetWeight(){
		if(this.record.getSvvktb()!=null && !"".equals(this.record.getSvvktb())){
			if(this.record.getSvvktn()!=null && !"".equals(this.record.getSvvktn())){
				try{
					String grossFormatTmp = this.record.getSvvktb().replace(".", "");
					double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
					String netFormatTmp = this.record.getSvvktn().replace(".", "");
					double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
					
					//Net can not be > than Gross
					if (netWeight>grossWeight){
						this.validRecord = false;
					}
				}catch(Exception e){
					this.validRecord = false;
				}
			}
		}
	}
	/**
	 *
	 */
	public void checkForMengdeMustExist(){
		if("Y".equals(this.record.getExtraMangdEnhet())){
			if(this.record.getSvntm()!=null && !"".equals(this.record.getSvntm())){
				//valid
			}else{
				this.validRecord = false;
			}
		}
		
	}
	/**
	 *
	 */
	public void checkForMengdeMustNotExist(){
		if(!"Y".equals(this.record.getExtraMangdEnhet())){
			if(this.record.getSvntm()!=null && !"".equals(this.record.getSvntm())){
				this.validRecord = false;
			}
		}
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param errorFlag
	 */
	
	public void updateItemWithAutoControlError(String applicationUser, String errorFlag){
		String BASE_URL_UPDATE_ERR = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getSvavd());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getSvtdn());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getSvli());
		if(errorFlag!=null){
			urlRequestParamsKeysAutoControl.append("&sverr=" + errorFlag);
		}else{
			urlRequestParamsKeysAutoControl.append("&sverr=");
		}
		/*DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH item list... ");
    	logger.info("URL: " + BASE_URL_UPDATE_ERR);
    	logger.info("URL PARAMS: " + urlRequestParamsKeysAutoControl);
    	*/
		
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE_ERR, urlRequestParamsKeysAutoControl.toString());
		JsonSadAutoControlErrorContainer container = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
    	if(container!=null){
    		if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    			logger.info(container.getErrMsg());
    		}else{
    			logger.info("[OK] Update successfully done on AutoControl");
    		}
    	}
    }
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isNotNull(String value){
		boolean retval = false;
		if(value!=null && !"".equals(value)){
			retval = true;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	public boolean updateItemRecord(String applicationUser){
		boolean retval = true;
		UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String BASE_URL_UPDATE = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		logger.info("[INFO] UPDATE (light) to be done with lineNr [svli]:" + this.record.getSvli());
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + this.record.getSvavd());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + this.record.getSvtdn());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + this.record.getSvli());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=U");
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svligh=X"); //light update flag
		
		
		String urlRequestParamsItem = urlRequestParameterMapper.getUrlParameterValidString((this.record));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsItem;
		/*DEBUG 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL_UPDATE);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	*/
    	//----------------------------------------------------------------------------
    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
    	//----------------------------------------------------------------------------
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
		//Debug --> 
    	//logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
    	//we must evaluate a return RPG code in order to know if the Update was OK or not
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    	}else{
    		//Update succefully done!
    		logger.info("[INFO] Valid light Update -- Record successfully updated, OK ");    		
    	}
    	return retval;
	} 
	
	/**
	 * 
	 * @param headerRecord
	 * @param appUser
	 */
	public void calculateNetWeight(JsonSadExportSpecificTopicRecord headerRecord, SystemaWebUser appUser){
		  double grossNetFactor = 0.9; //default;
		  String grossWeight = this.record.getSvvktb();
		  String netWeight = this.record.getSvvktn();
		  try{
			  //Gross weight exists but not net weight
			  if(grossWeight!=null && !"".equals(grossWeight) && (netWeight==null || "".equals(netWeight))  ){
				  if(headerRecord.getSefvk()!=null && !"".equals(headerRecord.getSefvk())){
					  String tmp = headerRecord.getSefvk().replace("," , ".");
					  grossNetFactor = Double.parseDouble(tmp);
				  }
				  //operation
				  grossWeight = grossWeight.replace("," , ".");
				  double grossWeightDbl = Double.parseDouble(grossWeight);
				  double netWeightDbl = grossWeightDbl * grossNetFactor;
				  NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
				  netWeight = String.valueOf(formatter.getDoubleEuropeanFormat(netWeightDbl, 3, false));
				  //final result
				  this.record.setSvvktn(netWeight);
			  }
			  
		  }catch (Exception e){
			  logger.info("[ERROR] on Net weight calculation - Auto control:" + e.toString());
		  }
		
	}
	
}
