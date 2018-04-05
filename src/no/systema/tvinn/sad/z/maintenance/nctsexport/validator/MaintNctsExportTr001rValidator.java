package no.systema.tvinn.sad.z.maintenance.nctsexport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.tvinn.sad.z.maintenance.nctsexport.controller.SearchFilterTransitKoder;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 * 
 *
 */
public class MaintNctsExportTr001rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintNctsExportTr001rValidator.class.getName());

	public boolean supports(Class clazz) {
		return JsonMaintNctsTrughRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * Validate on mandatory fields, in GUI.
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "systema.tvinn.sad.ncts.export.koderegister.error.null.tkunik"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "systema.tvinn.sad.ncts.export.koderegister.error.null.tkkode"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tktxtn", "systema.tvinn.sad.ncts.export.koderegister.error.null.tktxtn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tktxte", "systema.tvinn.sad.ncts.export.koderegister.error.null.tktxte"); 
		
	}

	public void validateDelete(Object obj, Errors errors) { 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "systema.tvinn.sad.ncts.export.koderegister.error.null.tkunik"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "systema.tvinn.sad.ncts.export.koderegister.error.null.tkkode"); 
	}
	
	/**
	 * Ensure not empty in searchTkkode and/or searchTktxtn when searchTkunik is 106 (Tollsted referansenr)
	 * 
	 */
	public void restrictOnMandatorySearchFields(Object obj, Errors errors) {
		SearchFilterTransitKoder searchRecord = (SearchFilterTransitKoder) obj;
		if ( "106".equals(searchRecord.getSearchTkunik()) && ("".equals(searchRecord.getSearchTkkode()) && "".equals(searchRecord.getSearchTktxtn())) ) {
			errors.rejectValue("searchTkunik", "systema.tvinn.sad.ncts.export.koderegister.search.mandatoryFields"); 
		}
	}
}
