package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Record;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts5rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002Kodts5rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts5Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts5Record record = (JsonMaintSadImportKodts5Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks5tln", "", "Kode (KS5TLN) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks5ftx", "", "Fritekst (KS5FTX) er obligatorisk"); 
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
		
		JsonMaintSadImportKodts5Record record = (JsonMaintSadImportKodts5Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks5tln", "", "Kode (KS5TLN) er obligatorisk"); 
		
	}
	
}
