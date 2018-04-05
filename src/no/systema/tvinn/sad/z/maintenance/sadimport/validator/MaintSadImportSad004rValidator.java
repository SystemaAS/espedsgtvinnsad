package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 31, 2016
 * 
 *
 */
public class MaintSadImportSad004rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad004rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadSadlRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadSadlRecord record = (JsonMaintSadSadlRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slknr", "", "Kundnr (SLKNR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slalfa", "", "Varenr. (SLALFA) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sloppl", "", "Kundnr (SLOPPL) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sltanr", "", "Kundnr (SLTANR) er obligatorisk"); 
		
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
		
		JsonMaintSadSadlRecord record = (JsonMaintSadSadlRecord)obj;
		//logger.info(record.getSltanr());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slknr", "", "Kundnr (SLKNR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slalfa", "", "Varenr. (SLALFA) er obligatorisk"); 
		
	}
}
