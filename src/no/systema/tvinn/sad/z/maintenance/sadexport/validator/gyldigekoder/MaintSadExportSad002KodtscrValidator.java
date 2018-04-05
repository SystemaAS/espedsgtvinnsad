package no.systema.tvinn.sad.z.maintenance.sadexport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtscRecord;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 *
 */
public class MaintSadExportSad002KodtscrValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadExportSad002KodtscrValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadExportKodtscRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadExportKodtscRecord record = (JsonMaintSadExportKodtscRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksckd", "", "Dok.type (KSCKD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kscft", "", "Fritekst (KSCFT) er obligatorisk"); 
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
		
		JsonMaintSadExportKodtscRecord record = (JsonMaintSadExportKodtscRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksckd", "", "Dok.type (KSCKD) er obligatorisk"); 
		
	}
	
}
