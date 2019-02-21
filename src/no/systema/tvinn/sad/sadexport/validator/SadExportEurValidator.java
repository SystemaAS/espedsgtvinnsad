package no.systema.tvinn.sad.sadexport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurRecord;

/**
 * 
 * @author oscardelatorre
 * @date Feb, 2019
 */
public class SadExportEurValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadExportEurValidator.class.getName());
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadExportTopicEurRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		
		JsonSadExportTopicEurRecord record = (JsonSadExportTopicEurRecord)obj;
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//----------------
				//Date validation
				//----------------
				if(record.getEur12b()!=null && !"".equals(record.getEur12b())){
					if(!isValidDate(record)){
						errors.rejectValue("eur12b", "systema.tvinn.sad.export.header.error.rule.finans.dato.sfdt.invalidDate");
					}
				}
			}
		}

		
	}
	/**
	 * The date should be in the correct format and not bigger than current date.
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidDate(JsonSadExportTopicEurRecord record){
		boolean retval = true;
		
		if(record.getEur12b().length()!=6){
			retval = false;
			
		}else{
			if(!dateValidator.validateDate(record.getEur12b(), DateValidator.DATE_MASK_NO)){
				retval = false;
			}
			
		}
		
		return retval;
	}
	
}
