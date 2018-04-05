package no.systema.tvinn.sad.z.maintenance.sadexport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax.MaintSadExportAjaxHandlerController;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 22, 2016
 * 
 *
 */
public class MaintSadExportSad015Validator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static final Logger logger = Logger.getLogger(MaintSadExportSad015Validator.class.getName());

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadExportSadavgeRecord.class.isAssignableFrom(clazz);
	}

	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) {
		JsonMaintSadExportSadavgeRecord record = (JsonMaintSadExportSadavgeRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agtanr", "", "Tariffnr. (AGTANR) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agskv", "", "Sekv. (AGSKV) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agdtf", "", "F.o.m dato (AGDTF) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agdtt", "", "T.o.m dato (AGDTT) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agsats", "", "Sats (AGSATS) er obligatorisk");

		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if(!dateValidator.validateDateIso203_YYYYMMDD(record.getAgdtf())){
					errors.rejectValue("agdtf", "systema.tvinn.sad.export.error.rule.invalidFromDate"); 
				}
				if(!dateValidator.validateDateIso203_YYYYMMDD(record.getAgdtt())){
					errors.rejectValue("agdtt", "systema.tvinn.sad.export.error.rule.invalidToDate");
				}
				
				if (record.getAgkd() != null && !"".equals(record.getAgkd())) {
					if ("B".equals(record.getAgkd())) {
						// OK
					} else {
						errors.rejectValue("agkd", "", "Tasted EU kode er feil.");  //B or empty
					}
				}
				if (record.getAgpp() != null && !"".equals(record.getAgpp())) {
					if ("%".equals(record.getAgpp()) || "P".equals(record.getAgpp())) {
						// OK
					} else {
						errors.rejectValue("agpp", "", "Tasted verdi er feil."); // % or P
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agtanr", "", "Tariffnr. (AGTANR) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agakd", "", "Tariffnr. (AGAKD) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agskv", "", "Avg. (AGSKV) er obligatorisk");

	}

	
}
