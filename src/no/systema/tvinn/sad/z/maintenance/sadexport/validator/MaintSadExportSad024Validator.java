package no.systema.tvinn.sad.z.maintenance.sadexport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 *
 */
public class MaintSadExportSad024Validator implements Validator {
	private DateValidator dateValidator = new DateValidator();

	public boolean supports(Class clazz) {
		return JsonMaintSadImportSadhHeadfRecord.class.isAssignableFrom(clazz); 
	}
	
	public void validate(Object obj, Errors errors) {
		JsonMaintSadExportSaehRecord record = (JsonMaintSadExportSaehRecord) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setdn", "", "Tolldekl.nr (SETDN) er obligatorisk");

		// Logical (RULES) controls if we passed the NOT NULL errors
		if (!errors.hasFieldErrors()) {
			if (record != null) {
				if ((record.getSetll() != null && !"".equals(record.getSetll()))
						|| (record.getSetle() != null && !"".equals(record.getSetle()))
						|| (record.getSedtg() != null && !"".equals(record.getSedtg()))) {

					if (record.getSedtg() != null && !"".equals(record.getSedtg())) {
						if (!dateValidator.validateDateIso203_YYYYMMDD(record.getSedtg())) {
							errors.rejectValue("sedtg", "systema.tvinn.sad.export.error.rule.invalidAckDate");
						}
					}
					
				// Nothing = OK
				} else {
					errors.rejectValue("setdn", "", "Du må fylle Løpenr, Eksp.nr. eller Dato");
				}

			}
		}
	}
	
}
