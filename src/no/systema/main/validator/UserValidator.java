package no.systema.main.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.SystemaWebUser;

/**
 * 
 * @author oscardelatorre
 *
 */
public class UserValidator implements Validator {

	
	/**
	* This Validator validates just User instances 
	* 
	**/
	public boolean supports(Class clazz) {
		return SystemaWebUser.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "login.user.error.name"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.user.error.password"); 
		
		SystemaWebUser user = (SystemaWebUser) obj;
		
	}
	
	
}
