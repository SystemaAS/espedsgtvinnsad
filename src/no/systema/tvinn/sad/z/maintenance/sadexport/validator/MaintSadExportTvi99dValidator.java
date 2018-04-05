package no.systema.tvinn.sad.z.maintenance.sadexport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 9, 2016
 * 
 *
 */
public class MaintSadExportTvi99dValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadFellesKodtsiRecord.class.isAssignableFrom(clazz);
	}

	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "e9705", "", "Kode (E9705) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "e4440", "", "Tollvesenets text (E4440) er obligatorisk");

	}

	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "e9705", "", "Tollvesenets text (E9705) er obligatorisk");
	}
}
