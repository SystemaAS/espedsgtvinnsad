package no.systema.tvinn.sad.z.maintenance.sad.validator.gyldigekoder;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 * 
 *
 */
public class MaintSadSad002KodtsdrValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadSad002KodtsdrValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadKodtsdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadKodtsdRecord record = (JsonMaintSadKodtsdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksdls", "", "Lag.sted (KSDLS) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksdtxt", "", "Fritekst (KSDTXT) er obligatorisk"); 
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
		
		JsonMaintSadKodtsdRecord record = (JsonMaintSadKodtsdRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksdls", "", "Lag.sted (KSDLS) er obligatorisk"); 
		
	}
	
}
