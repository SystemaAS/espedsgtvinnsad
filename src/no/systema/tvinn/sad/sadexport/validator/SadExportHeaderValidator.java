package no.systema.tvinn.sad.sadexport.validator;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.util.StringManager;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;

/**
 * 
 * @author oscardelatorre
 * @date Sep 4, 2014
 *
 */
public class SadExportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadExportHeaderValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadExportSpecificTopicRecord record = (JsonSadExportSpecificTopicRecord)obj;
		String[] avsLandNotNorwayValidCodes = {"2","4","18","81","90"};
		//----------------------------
		//Check for Mandatory fields
		//----------------------------
		//General
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seavd", "systema.tvinn.sad.export.header.error.null.seavd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesg", "systema.tvinn.sad.export.header.error.null.sesg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sedty", "systema.tvinn.sad.export.header.error.null.sedty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sedp", "systema.tvinn.sad.export.header.error.null.sedp");
		//Parties
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serg", "systema.tvinn.sad.export.header.error.null.serg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senas", "systema.tvinn.sad.export.header.error.null.senas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senad", "systema.tvinn.sad.export.header.error.null.senad");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setlf", "systema.tvinn.sad.export.header.error.null.setlf");
		//Header
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selka", "systema.tvinn.sad.export.header.error.null.selka.avsLand");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selkb", "systema.tvinn.sad.export.header.error.null.selkb.bestLand");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sekdc", "systema.tvinn.sad.export.header.error.null.sekdc.container");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setrid", "systema.tvinn.sad.export.header.error.null.setrid.transpId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selkt", "systema.tvinn.sad.export.header.error.null.selkt.countryOnBoarder");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setrm", "systema.tvinn.sad.export.header.error.null.setrm.transMate");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "segn", "systema.tvinn.sad.export.header.error.null.segn.godsnr");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selv", "systema.tvinn.sad.export.header.error.null.selv.incotermsKod");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "selvt", "systema.tvinn.sad.export.header.error.null.selvt.incotermsSted");
		//Utapass.sted
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sekdh", "systema.tvinn.sad.export.header.error.null.sekdh.utpassSted");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sekdft", "systema.tvinn.sad.export.header.error.null.sekdh.utpassSted");
		//Kolli och vikt
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sentk", "systema.tvinn.sad.export.header.error.null.sentk.antKolli");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sevkb", "systema.tvinn.sad.export.header.error.null.sevkb.bruttoVekt");
		//Faktura
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sefif", "systema.tvinn.sad.export.header.error.null.sefif.fakturaNr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sefid", "systema.tvinn.sad.export.header.error.null.sefid.fakturaDato");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sebel1", "systema.tvinn.sad.export.header.error.null.sebel1.fakturaSum");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seval1", "systema.tvinn.sad.export.header.error.null.seval1.fakturaVal");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sevku", "systema.tvinn.sad.export.header.error.null.sevku.fakturaKurs");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setst", "systema.tvinn.sad.export.header.error.null.setst.fakturaTrType");
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//signaturkontroll mot CargoWise signatur vid EDI - integration
				if("CW1".equals (record.getSesg())){
					errors.rejectValue("sesg", "systema.tvinn.sad.export.header.error.rule.invalid.sesg");
				}
				if(!this.isValidProcedureTypeForAvsLand(record)){
					errors.rejectValue("selka", "systema.tvinn.sad.export.header.error.rule.avsLandNotNorwayAndBestLandNorway"); 
				}
				if(!this.isValidProcedureTypeForBestLand(record)){
					errors.rejectValue("selkb", "systema.tvinn.sad.export.header.error.rule.avsLandNotNorwayAndBestLandNorway"); 
				}
				
				
				//if tullkredit = not exists
				if(!this.isValidTollkreditValue(record.getSekta())){
					if("".equals(record.getSeski())){
						errors.rejectValue("seski", "systema.tvinn.sad.export.header.error.rule.seski.tollMvaValueMandatory"); 
					}
				}
				if(!this.isValidTollkreditValue(record.getSekta())){
					if("".equals(record.getSekddk())){
						errors.rejectValue("seski", "systema.tvinn.sad.export.header.error.rule.sekddk.tollMvaValueMandatory"); 
					}
				}
				
//				2018-09-04 Remarked due to not needed, according to CB. 
//				//if tullkredit = exists
//				if(!"".equals(record.getSekta()) && !"".equals(record.getSektb()) ){
//					if("S".equals(record.getSeski()) || "I".equals(record.getSeski()) ){
//						//Valid
//					}else{
//						errors.rejectValue("seski", "systema.tvinn.sad.export.header.error.rule.seski.tollMvaValueDiscreteMandatoryValues"); 
//					}
//				}
				
				//------
				//dates 
				//------
				if(strMgr.isNotNull(record.getSefid())  && !"999999".equals(record.getSefid())){
					if(!dateValidator.validateDate(record.getSefid(), DateValidator.DATE_MASK_NO)){
						errors.rejectValue("sefid", "systema.tvinn.sad.export.header.error.rule.invalidFaktDate"); 	
					}
				}
				
				//Avs.Land vs Dekl.typ (EU vs EX)
				if(strMgr.isNotNull(record.getSelka())){
					//if(!isValidCountryForDeklaration(record)){
					if(!"NO".equals(record.getSelka())){
						errors.rejectValue("selka", "systema.tvinn.sad.export.header.error.rule.invalidCountryDeklType"); 	
					}
				}
				if(strMgr.isNotNull(record.getSelkb())){
					if(!isValidCountryForDeklaration(record)){
						errors.rejectValue("selkb", "systema.tvinn.sad.export.header.error.rule.selkb.invalidCountryDeklType"); 	
					}
				}
				//Kostnader can not be equal or greater than the invoice sum
				if(strMgr.isNotNull(record.getSebel1()) && strMgr.isNotNull(record.getSebel2()) ){
					BigDecimal faktSum = new BigDecimal(Double.valueOf(record.getSebel1().replace(",", ".")));
					BigDecimal costs = new BigDecimal(Double.valueOf(record.getSebel2().replace(",", ".")));
					if(costs.compareTo(faktSum)>=0 ){
						errors.rejectValue("sebel2", "systema.tvinn.sad.export.header.error.rule.invalid.sebel2");
					}
					
				}
			}
		}
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidProcedureTypeForAvsLand(JsonSadExportSpecificTopicRecord record){
		String[] notNorwayOnAvsLandCodes = {"2","4","18","81","90"};
		boolean retval = true;
		
		if(record!=null){
			String avsenderLand = record.getSelka();
			String procedure = record.getSedp();
			
			List<String> list = Arrays.asList(notNorwayOnAvsLandCodes);
			//test avs.land
			if("NO".equals(avsenderLand)){
				for(String field: list){
					if(field.equals(procedure)){
						retval = false;
						break;
					}
				}
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidProcedureTypeForBestLand(JsonSadExportSpecificTopicRecord record){
		String[] norwayOnBestLandCodes = {"2","4","18","81","90"};
		boolean retval = true;
		
		if(record!=null){
			String bestLand = record.getSelkb();
			String procedure = record.getSedp();
			
			List<String> list = Arrays.asList(norwayOnBestLandCodes);
			//test avs.land
			for(String field: list){
				if(field.equals(procedure)){
					if("NO".equals(bestLand)){
						//Valid
					}else{
						retval = false;
						break;
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
	private boolean isValidTollkreditValue(String value){
		logger.info("Inside isValidTollkreditValue");
		boolean retval = false;
		Integer creditField = 0;
		if(strMgr.isNotNull(value)){
			creditField = Integer.valueOf(value);
			if(creditField>0){
				retval = true;
				//logger.info("€€€€€€€€€€€€€€€€:" + value);
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidCountryForDeklaration(JsonSadExportSpecificTopicRecord record){
		boolean retval = false;
		
		boolean matchEU_Country = false;
		
		for(String country:TvinnSadConstants.LIST_EU_COUNTRIES){
			if(country.equals(record.getSelkb())){
				matchEU_Country = true;
				break;
			}
		}
		//
		if(matchEU_Country){
			if("EU".equals(record.getSedty())){
				logger.info("MATCH EU");
				retval = true;
			}
		}else{
			if("EX".equals(record.getSedty())){
				logger.info("MATCH EX");
				retval = true;
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
