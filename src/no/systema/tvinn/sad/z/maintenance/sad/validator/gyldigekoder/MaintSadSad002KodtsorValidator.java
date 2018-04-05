package no.systema.tvinn.sad.z.maintenance.sad.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 * 
 *
 */
public class MaintSadSad002KodtsorValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadSad002KodtsorValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadKodtsoRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadKodtsoRecord record = (JsonMaintSadKodtsoRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksokd", "", "Kode (KSOKD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksoft", "", "Fritekst (KSOFT) er obligatorisk"); 
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
		
		JsonMaintSadKodtsoRecord record = (JsonMaintSadKodtsoRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksokd", "", "Kode (KSOKD) er obligatorisk");  
		
	}
	
}
