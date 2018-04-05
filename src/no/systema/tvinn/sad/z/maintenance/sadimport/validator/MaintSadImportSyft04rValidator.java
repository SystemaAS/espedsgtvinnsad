package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 *
 */
public class MaintSadImportSyft04rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSyft04rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodttsRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodttsRecord record = (JsonMaintSadImportKodttsRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ktspnr", "", "Tolldistrikt (KTSPNR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ktsnav", "", "Tollsted (KTSNAV) er obligatorisk"); 

	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintSadImportKodttsRecord record = (JsonMaintSadImportKodttsRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ktspnr", "", "Tolldistrikt (KTSPNR) er obligatorisk"); 
	}
}
