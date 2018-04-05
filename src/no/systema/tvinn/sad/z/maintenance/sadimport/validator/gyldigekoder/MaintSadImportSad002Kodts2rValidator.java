package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Record;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts2rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002Kodts2rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts2Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts2Record record = (JsonMaintSadImportKodts2Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks2lk", "", "Kode (KS2LK) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks2ftx", "", "Fritekst (KS2FTX) er obligatorisk"); 
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
		
		JsonMaintSadImportKodts2Record record = (JsonMaintSadImportKodts2Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks2lk", "", "Kode (KS2LK) er obligatorisk"); 
		
	}
	
}
