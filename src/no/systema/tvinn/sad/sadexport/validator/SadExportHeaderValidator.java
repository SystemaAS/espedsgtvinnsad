package no.systema.tvinn.sad.sadexport.validator;

import java.lang.reflect.Array;
import java.util.*;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;

/**
 * 
 * @author oscardelatorre
 * @date Sep 4, 2014
 *
 */
public class SadExportHeaderValidator implements Validator {
	
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
				if(!this.isValidProcedureTypeForAvsLand(record)){
					errors.rejectValue("selka", "systema.tvinn.sad.export.header.error.rule.avsLandNotNorwayAndBestLandNorway"); 
				}
				if(!this.isValidProcedureTypeForBestLand(record)){
					errors.rejectValue("selka", "systema.tvinn.sad.export.header.error.rule.avsLandNotNorwayAndBestLandNorway"); 
				}
				//if tullkredit = not exists
				if("".equals(record.getSekta()) || "".equals(record.getSektb()) ){
					if("".equals(record.getSeski())){
						errors.rejectValue("seski", "systema.tvinn.sad.export.header.error.rule.seski.tollMvaValueMandatory"); 
					}
				}
				//if tullkredit = exists
				if(!"".equals(record.getSekta()) && !"".equals(record.getSektb()) ){
					if("S".equals(record.getSeski()) || "I".equals(record.getSeski()) ){
						//Valid
					}else{
						errors.rejectValue("seski", "systema.tvinn.sad.export.header.error.rule.seski.tollMvaValueDiscreteMandatoryValues"); 
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
