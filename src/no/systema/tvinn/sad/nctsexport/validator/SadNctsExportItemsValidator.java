package no.systema.tvinn.sad.nctsexport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.validation.JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicItemService;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicItemServiceImpl;
import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 *
 */
public class SadNctsExportItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsExportItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SadNctsExportSpecificTopicItemService nctsExportSpecificTopicItemService = new SadNctsExportSpecificTopicItemServiceImpl();
		
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNctsExportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNctsExportSpecificTopicItemRecord record = (JsonSadNctsExportSpecificTopicItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvvt", "systema.tvinn.sad.ncts.export.header.error.null.item.itemdesc.tvvt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tveh", "systema.tvinn.sad.ncts.export.header.error.null.item.packageType.tveh");
				
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh
				//-----------------------------------------------------
				if("NE".equals(record.getTveh()) || "NF".equals(record.getTveh())){
					if(record.getTvnteh()==null && "".equals(record.getTvnteh())){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh()) || "VG".equals(record.getTveh()) ||
					"VL".equals(record.getTveh()) || "VY".equals(record.getTveh()) ||
					"VR".equals(record.getTveh()) || "VO".equals(record.getTveh())){
					
					if( record.getTvnt()!=null && !"".equals(record.getTvnt()) && record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt()!=null && !"".equals(record.getTvnt()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if( record.getTvnt()!=null && !"".equals(record.getTvnt()) && record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
					}else if ( record.getTvnt()==null || "".equals(record.getTvnt())){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnt.mustExist");
					}
				}
				
				/*
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh2
				//-----------------------------------------------------
				if("NE".equals(record.getTveh2()) || "NF".equals(record.getTveh2())){
					if(record.getTvnteh2()==null && "".equals(record.getTvnteh2())){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh2().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh2()) || "VG".equals(record.getTveh2()) ||
					"VL".equals(record.getTveh2()) || "VY".equals(record.getTveh2()) ||
					"VR".equals(record.getTveh2()) || "VO".equals(record.getTveh2())){
					
					if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) && record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh2())){
						if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) && record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt2()==null || "".equals(record.getTvnt2())){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}
				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh3
				//-----------------------------------------------------
				if("NE".equals(record.getTveh3()) || "NF".equals(record.getTveh3())){
					if(record.getTvnteh3()==null && "".equals(record.getTvnteh3())){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh3().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh3()) || "VG".equals(record.getTveh3()) ||
					"VL".equals(record.getTveh3()) || "VY".equals(record.getTveh3()) ||
					"VR".equals(record.getTveh3()) || "VO".equals(record.getTveh3())){
					
					if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) && record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh3())){
						if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) && record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt3()==null || "".equals(record.getTvnt3())){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}

				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh4
				//-----------------------------------------------------
				if("NE".equals(record.getTveh4()) || "NF".equals(record.getTveh4())){
					if(record.getTvnteh4()==null && "".equals(record.getTvnteh4())){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh4().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh4()) || "VG".equals(record.getTveh4()) ||
					"VL".equals(record.getTveh4()) || "VY".equals(record.getTveh4()) ||
					"VR".equals(record.getTveh4()) || "VO".equals(record.getTveh4())){
					
					if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) && record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh4())){
						if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) && record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt4()==null || "".equals(record.getTvnt4())){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}

				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh5
				//-----------------------------------------------------
				if("NE".equals(record.getTveh5()) || "NF".equals(record.getTveh5())){
					if(record.getTvnteh5()==null && "".equals(record.getTvnteh5())){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh5().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh5()) || "VG".equals(record.getTveh5()) ||
					"VL".equals(record.getTveh5()) || "VY".equals(record.getTveh5()) ||
					"VR".equals(record.getTveh5()) || "VO".equals(record.getTveh5())){
					
					if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) && record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) ){
						errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
						errors.rejectValue("tvnteh", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					
					if(!"".equals(record.getTveh5())){
						if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) && record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt5()==null || "".equals(record.getTvnt5())){
							errors.rejectValue("tvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}
					
				}
				*/
				
				/*
				//------------------------------------------------------------
				//Känsliga varor must always check for mandatory input or none
				//-------------------------------------------------------------
				if(record.getTvvnt()!=null && !"".equals(record.getTvvnt()) ){
					if(this.isVarukodWithSensitiveGoodsFlag(record)){
						if(record.getTvfvnt()!=null && !"".equals(record.getTvfvnt())){
							//nothing since it has been filled in
						}else{
							errors.rejectValue("tvfvnt", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvfvnt.mustExist");
						}
					}else{
						//back to default values
						record.setTvfv(null);
						record.setTvfvnt(null);
					}
				}*/
				
				//-----------------------------------------------
				//Gross weight only mandatory on first item line
				//-----------------------------------------------
				/*
				if("0".equals(record.getNumberOfItemLinesInTopicStr()) ){
					if(record.getTvvktb()==null || "".equals(record.getTvvktb()) ){
						errors.rejectValue("tvfvnt", "systema.tvinn.sad.ncts.export.header.error.null.item.grossweight.tvvktb");
					}
				}else if("-99".equals(record.getNumberOfItemLinesInTopicStr())){ //this is an update of the only item line in a topic
					if(record.getTvvktb()==null || "".equals(record.getTvvktb()) ){
						errors.rejectValue("tvfvnt", "systema.tvinn.sad.ncts.export.header.error.null.item.grossweight.tvvktb");
					}
				}
				*/
				
				//---------------------
				//Gross and Net weight
				//---------------------
				if(record.getTvvktb()!=null && !"".equals(record.getTvvktb())){
					if(record.getTvvktn()!=null && !"".equals(record.getTvvktn())){
						try{
							String grossFormatTmp = record.getTvvktb().replace(".", "");
							double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
							String netFormatTmp = record.getTvvktn().replace(".", "");
							double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
							
							//Net can not be > than Gross
							if (netWeight>grossWeight){
								errors.rejectValue("tvvktb", "systema.tvinn.sad.ncts.export.header.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a fantom hit here 
						}
					}
				}
				
				//Check valid Oppdrag reference
				if(!record.isValidOppdragRef()){
					errors.rejectValue("tvtdn2", "systema.tvinn.sad.ncts.export.header.error.rule.item.tvtdn2.mustExist");
				}
				
			}
			
		}
			
	}
	
	/**
	 * Validate towards sensitive goods register
	 * 
	 * @param record
	 * @return
	 */
	private boolean isVarukodWithSensitiveGoodsFlag(JsonSadNctsExportSpecificTopicItemRecord record){
		String method = "isVarukodWithSensitiveGoodsFlag";
	 	logger.info("Inside " + method);
	 	boolean retval = false;
	 	
	 	logger.info("VALIDATION of Sensitive Goods...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_ITEM_SENSITIVE_GOODS_URL;
		//url params
		String urlRequestParamsKeys = "user=" + record.getApplicationUser() + "&tftanr=" + record.getTvvnt();
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
			JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = this.nctsExportSpecificTopicItemService.getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(jsonPayload);
			if(container!=null){
				if("Y".equals(container.getExists()) ){
					logger.info("##########################################################");
					logger.info("[MANDATORY value required] on Sensitive goods for: " + container.getTftanr());
					logger.info("##########################################################");
					//set the code no matter if <null> or <not null>
					record.setTvfv(container.getTfkode());
					logger.info("Sensitive goods code:" + record.getTvfv());
					
					retval = true;
				}else{
					record.setTvfv(null);
					record.setTvfvnt(null);
					
				}
			}
		}
		return retval;
	}

	
}
