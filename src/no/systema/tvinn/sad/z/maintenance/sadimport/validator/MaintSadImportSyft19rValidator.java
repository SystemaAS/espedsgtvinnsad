package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2016
 * 
 *
 */
public class MaintSadImportSyft19rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSyft19rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodtlikRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodtlikRecord record = (JsonMaintSadImportKodtlikRecord)obj;
		//logger.info(record.getKlikod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klikod", "", "Likvid.kode (KLIKOD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klinav", "", "Beskrivelse (KLINAV) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klisto", "", "Stopp? (KLISTO) er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintSadImportKodtlikRecord record = (JsonMaintSadImportKodtlikRecord)obj;
		logger.info(record.getKlikod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klikod", "", "Likvid.kode (KLIKOD) er obligatorisk"); 
	}
}
