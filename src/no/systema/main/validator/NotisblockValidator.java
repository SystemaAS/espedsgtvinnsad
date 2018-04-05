package no.systema.main.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 20, 2015
 * 
 *
 */
public class NotisblockValidator implements Validator {
	private static final Logger logger = Logger.getLogger(NotisblockValidator.class.getName());
	
	/**
	* This Validator validates just User instances 
	* 
	**/
	public boolean supports(Class clazz) {
		return JsonNotisblockRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 

		JsonNotisblockRecord record = (JsonNotisblockRecord) obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frtdt", "notisblock.header.item.null.date_frtdt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frtkod", "notisblock.header.item.null.date_frtkod"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frttxt", "notisblock.header.item.null.date_frttxt"); 
		
	}
	
	
}
