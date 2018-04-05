package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 *
 */
public class MaintSadImportSad001arValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad001arValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportSadvareRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportSadvareRecord record = (JsonMaintSadImportSadvareRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "", "Varenr. (VARENR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "", "Kundid (LEVENR) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSadImportSadvareRecord record = (JsonMaintSadImportSadvareRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "", "Varenr. (VARENR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "", "Kundid (LEVENR) er obligatorisk"); 
		

	}
}
