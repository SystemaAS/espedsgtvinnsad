package no.systema.tvinn.sad.admin.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.admin.filter.SearchFilterSadAdminAvggrunnlag;

/**
 * 
 * @author oscardelatorre
 * @date Sep 21, 2014
 * 
 */
public class SadAdminAvggrunnlagListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterSadAdminAvggrunnlag.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterSadAdminAvggrunnlag record = (SearchFilterSadAdminAvggrunnlag)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "", "Fra dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "", "Til dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avggCustomerId", "", "Kundenr er obligatorisk"); 
		
		//Check for Mandatory fields first
		if(record!=null){
			//TODO if applicable
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean valueExists(String value){
		boolean retval = false;
		if(value!=null){
			if(!"".equals(value)){
				retval = true;
			}
		}
		
		return retval;
	}
}
