package no.systema.tvinn.sad.z.maintenance.sadexport.validator;

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
public class MaintSadExportSad004Validator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadExportSad004Validator.class.getName());
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sloppl", "", "L/F (SLOPPL) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sltanr", "", "Tariffnr (SLTANR) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if (record != null) {
				if (record.getR31() != null && !"".equals(record.getR31())) {
					if ("J".equals(record.getR31()) || "N".equals(record.getR31())) {
						// OK
					} else {
						errors.rejectValue("r31", "", "Tasted rubrik kode er feil."); // J or N
					}
				}
				if (record.getMf() != null && !"".equals(record.getMf())) {
					if ("F".equals(record.getMf())) {
						// OK
					} else {
						errors.rejectValue("mf", "", "Tasted momsfritak er feil."); // F=momsfritak
					}
				}
				//Check valid kode/sekv combination
				if (record.getSlkdae() != null && !"".equals(record.getSlkdae())) {
					if (record.getSlkdse() != null && !"".equals(record.getSlkdse())) {
						// OK
					} else {
						errors.rejectValue("slkdse", "", "Tasted kode/avg er feil."); 
					}
				}
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slknr", "", "Kundnr (SLKNR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slalfa", "", "Varenr. (SLALFA) er obligatorisk"); 
		
	}
}
