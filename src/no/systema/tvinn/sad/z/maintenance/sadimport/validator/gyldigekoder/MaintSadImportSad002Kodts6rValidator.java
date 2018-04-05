package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts6Record;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts6rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002Kodts6rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts6Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts6Record record = (JsonMaintSadImportKodts6Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks6pre", "", "Kode (KS6PRE) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks6ftx", "", "Fritekst (KS6FTX) er obligatorisk"); 
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//N/A
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSadImportKodts6Record record = (JsonMaintSadImportKodts6Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks6pre", "", "Kode (KS6PRE) er obligatorisk"); 
		
	}
	
}
