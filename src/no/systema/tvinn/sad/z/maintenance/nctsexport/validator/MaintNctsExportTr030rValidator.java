package no.systema.tvinn.sad.z.maintenance.nctsexport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 3, 2016
 * 
 *
 */
public class MaintNctsExportTr030rValidator implements Validator {

	public boolean supports(Class clazz) {
		return JsonMaintNctsTrughRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintNctsTrughRecord record = (JsonMaintNctsTrughRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtina", "", "Organisasjonnr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgnaa", "", "Navn er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpna", "", "Postnr. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpsa", "", "Postadr. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tglka", "", "Landkode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtsd", "", "Garanti tollsted er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggty", "", "Garantityp er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgakny", "", "Adgangskode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggbl", "", "Garantibeløp er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggvk", "", "Valuta er obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//TODO ?? or remove
			}
		}
	}

	public void validateDelete(Object obj, Errors errors) { 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr er obligatorisk"); 
		
	}
	
	
}
