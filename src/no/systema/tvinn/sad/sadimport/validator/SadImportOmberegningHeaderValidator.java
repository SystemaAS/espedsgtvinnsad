package no.systema.tvinn.sad.sadimport.validator;

import java.lang.reflect.Array;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.sadimport.controller.SadImportHeaderController;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;


/**
 * 
 * @author oscardelatorre
 * @date Dec 9, 2016
 *
 */
public class SadImportOmberegningHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadImportHeaderController.class.getName());
	
	private UrlCgiProxyService urlCgiProxyService = null;
	private SadImportSpecificTopicService sadImportSpecificTopicService = null;
	private SystemaWebUser appUser = null;
	//setters
	public void setUrlCgiProxyService(UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public void setSadImportSpecificTopicService(SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	public void setSystemWebUser (SystemaWebUser value){ this.appUser=value; }  
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadImportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		String ACTIVE_INNSTIKK_CODE = "I";
		JsonSadImportSpecificTopicRecord record = (JsonSadImportSpecificTopicRecord)obj;
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siavd", "systema.tvinn.sad.import.header.error.null.siavd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sisg", "systema.tvinn.sad.import.header.error.null.sisg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sidty", "systema.tvinn.sad.import.header.error.null.sidty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sidp", "systema.tvinn.sad.import.header.error.null.sidp");

		//Afsender
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sinas", "systema.tvinn.sad.import.header.error.null.sinas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siads1", "systema.tvinn.sad.import.header.error.null.siads1");
		
		//Modtager
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sinak", "systema.tvinn.sad.import.header.error.null.sinak");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sirg", "systema.tvinn.sad.import.header.error.null.sirg");
		//Deklarant
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sinad", "systema.tvinn.sad.import.header.error.null.sinad");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sitlf", "systema.tvinn.sad.import.header.error.null.sitlf");
		//Afsend.land
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "silka", "systema.tvinn.sad.import.header.error.null.silka");
		//Antall kolli
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sintk", "systema.tvinn.sad.import.header.error.null.sintk");
		//Brutto vekt
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sivkb", "systema.tvinn.sad.import.header.error.null.sivkb");
		//Godsnr
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sign", "systema.tvinn.sad.import.header.error.null.sign");
		//Leveringsvilkår
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "silv", "systema.tvinn.sad.import.header.error.null.silv");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "silvt", "systema.tvinn.sad.import.header.error.null.silvt");
		//Faktura total and related fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sibel3", "systema.tvinn.sad.import.header.error.null.sibel3");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sival3", "systema.tvinn.sad.import.header.error.null.sival3");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sivku", "systema.tvinn.sad.import.header.error.null.sivku");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sitst", "systema.tvinn.sad.import.header.error.null.sitst");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sifif", "systema.tvinn.sad.import.header.error.null.sifif");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sifid", "systema.tvinn.sad.import.header.error.null.sifid");
		//OMBEREGNING
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "om_sitype", "systema.tvinn.sad.import.header.error.null.om_sitype.typetilfelle");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "om_sift01", "systema.tvinn.sad.import.header.error.null.om_sift01.ombArsak");
		
		
		//Extra validation for innstikk (if applicable)
		if(record!=null && ACTIVE_INNSTIKK_CODE.equals(record.getSimi())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sift1", "systema.tvinn.sad.import.header.error.null.sift1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sift3", "systema.tvinn.sad.import.header.error.null.sift3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siski", "systema.tvinn.sad.import.header.error.null.siski");
			//21 och 25
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "silkt", "systema.tvinn.sad.import.header.error.null.silkt");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sitrm", "systema.tvinn.sad.import.header.error.null.sitrm");
			//30.1
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sikdls", "systema.tvinn.sad.import.header.error.null.sikdls");
			//48
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siktc", "systema.tvinn.sad.import.header.error.null.siktc");
		}
	
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			
			if(record!=null){
				//------------------------
				//Tollkredit vs Dagsoppjør
				//------------------------
				if(this.tollkreditExists(record)){
					if(!"".equals(record.getSikddk())){
						errors.rejectValue("sikddk", "systema.tvinn.sad.import.header.error.rule.tollkreditOrDagsopp"); 
					}
				}
				//Leveransvillkor validation towards R.12 (freight)
				if(!this.isValidLeveransvillkorTowardsFreight_Rubrik12(record)){
					errors.rejectValue("sibel1", "systema.tvinn.sad.import.header.error.rule.invalidFrakt"); 
				}
				
				//------
				//dates 
				//------
				/*
				if( (record.getDkih_dtm1()!=null && !"".equals(record.getDkih_dtm1()) ) && (record.getDkih_dtm2()!=null && !"".equals(record.getDkih_dtm2()) ) ){ 
					errors.rejectValue("dkih_dtm1", "systema.skat.import.header.error.rule.onlyOneDate"); 
				}
				
				//Check for valid dates (when applicable)
				if(record.getDkih_dtm1()!=null && !"".equals(record.getDkih_dtm1())){
					boolean isValid = new DateTimeManager().isValidForwardTime(record.getDkih_dtm1(), "yyyyMMddHHmm");
					if(!isValid){
						errors.rejectValue("dkih_dtm1", "systema.skat.import.header.error.rule.ankomstTidDtm1NotValid"); 
					}
				}else if (record.getDkih_dtm2()!=null && !"".equals(record.getDkih_dtm2())){
					boolean isValid = new DateTimeManager().isValidForwardTime(record.getDkih_dtm2(), "yyyyMMddHHmm");
					if(isValid){
						//This dtm2 date MUST be back in time so the valid date will through an Error
						errors.rejectValue("dkih_dtm2", "systema.skat.import.header.error.rule.ankomstTidDtm2NotValid"); 
					}
				}
				//Faktiskt ankomstdato ej tillåtet vid Vägtransport (3)
				if(record.getDkih_dtm2()!=null && !"".equals(record.getDkih_dtm2()) ){ 
					if(record.getDkih_26()!=null && "3".equals(record.getDkih_26()) ){
						errors.rejectValue("dkih_dtm2", "systema.skat.import.header.error.rule.faktisktAnkomstDatoEmpty"); 
					}
				}
				
				//Angivelsestyp
				if( record.getDkih_15()!=null && !"".equals(record.getDkih_15()) ){ 
					if(!this.isValidAngivelseTypeForEftaCountry(record)){
						errors.rejectValue("dkih_15", "systema.skat.import.header.error.rule.eftaCountriesAngType"); 
					}
					if(!this.isValidAngivelseTypeForNoneEftaCountry(record)){
						errors.rejectValue("dkih_15", "systema.skat.import.header.error.rule.noneEftaCountriesAngType"); 
					}
				}
				
				//CVR/SE-nr Prefix (DK) requirement
				if( record.getDkih_08a()!=null && !"".equals(record.getDkih_08a()) ){ 
					if(!record.getDkih_08a().startsWith("DK")){
						errors.rejectValue("dkih_08a", "systema.skat.import.header.error.rule.cvrNrPrefixModtager"); 
					}
				}
				if( record.getDkih_14a()!=null && !"".equals(record.getDkih_14a()) ){ 
					if(!record.getDkih_14a().startsWith("DK")){
						errors.rejectValue("dkih_14a", "systema.skat.import.header.error.rule.cvrNrPrefixRepresentat"); 
					}
				}
				*/
			}
		}
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean tollkreditExists(JsonSadImportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getSiktc()) || !"".equals(record.getSikta()) || !"".equals(record.getSiktb()) ){
			retval = true;
		}
		
		return retval;
	}
	
	
	/**
	 * 
	 * @param record
	 * @return
	 * 
	 */
	private boolean isValidLeveransvillkorTowardsFreight_Rubrik12(JsonSadImportSpecificTopicRecord record){
		boolean retval = true;
		String FLAG_REQUIRED = "J";
		String FLAG_NOT_REQUIRED = "N";
		String FLAG_FREE = "F";
		if(this.urlCgiProxyService!=null && this.sadImportSpecificTopicService!=null){
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_INCOTERMS_ATTRIBUTES_FOR_VALIDATION_DATA_URL;
			String urlRequestParamsKeys = "user=" + this.appUser.getUser() + "&kode=" + record.getSilv();
			
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			logger.info(jsonPayload);
			if(jsonPayload!=null){
				JsonSadImportTopicIncotermsAttributesContainer incotermsContainer = this.sadImportSpecificTopicService.getSadImportTopicIncotermsAttributesContainer(jsonPayload);
				for(JsonSadImportTopicIncotermsAttributesRecord incotermsRecord : incotermsContainer.getLevvillkar()){
					String freightFlag = incotermsRecord.getKlbfrk();
					if(FLAG_REQUIRED.equals(freightFlag)){
						if( "".equals(record.getSibel1()) ){
							retval = false;
						}
					}else if(FLAG_NOT_REQUIRED.equals(freightFlag)){
						if( !"".equals(record.getSibel1()) ){
							retval = false;
						}
					}else{
						//nothing = always true
					}
				}
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	/*
	private boolean isValidAngivelseTypeForEftaCountry(JsonSkatImportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		
		if(record!=null){
			String afsenderLand = record.getDkih_15();
			
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				if(!"EU".equals(record.getDkih_r011())){
					if(field.equals(afsenderLand)){
						retval = false;
						break;
					}
				}
			}
		}
		return retval;
	}
	*/
	/**
	 * 
	 * @param value
	 * @return
	 */
	/*
	private boolean isValidAngivelseTypeForNoneEftaCountry(JsonSkatImportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		boolean eftaMatch = false;
		
		if(record!=null){
			String afsenderLand = record.getDkih_15();
			//Catch the possible Efta country (if any)
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				//if("EU".equals(record.getDkih_r011())){
					if(field.equals(afsenderLand)){
						eftaMatch = true;
						break;
					}
				}
			}
			//Now check if valid
			if("EU".equals(record.getDkih_r011()) && !eftaMatch){
				retval = false;
			}
		
		return retval;
	}
	*/
	
}
