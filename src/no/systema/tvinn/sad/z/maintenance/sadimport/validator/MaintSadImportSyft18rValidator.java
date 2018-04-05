package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 *
 */
public class MaintSadImportSyft18rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSyft18rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportCundfLikvKodeRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportCundfLikvKodeRecord record = (JsonMaintSadImportCundfLikvKodeRecord)obj;
		//logger.info(record.getKlikod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kundnr", "", "Kundnr (KUNDNR) er obligatorisk"); 
		
	}
	
}
