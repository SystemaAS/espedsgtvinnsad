package no.systema.tvinn.sad.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariRecord;

/**
 * 
 * @author oscardelatorre
 * @date Okt 25, 2016
 * 
 *
 */
public class MaintSadFellesSad062rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadFellesSad062rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadFellesSoktariRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadFellesSoktariRecord record = (JsonMaintSadFellesSoktariRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tariff", "", "Tariffnr. (TARIFF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beskr1", "", "BEskrivelse (BESKR) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSadFellesSoktariRecord record = (JsonMaintSadFellesSoktariRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tariff", "", "Tariffnr. (TARIFF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beskr1", "", "BEskrivelse (BESKR) er obligatorisk"); 
		

	}
}
