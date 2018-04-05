package no.systema.tvinn.sad.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 *
 */
public class MaintSadFellesSyft02rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadFellesSyft02rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintKodtvaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintKodtvaRecord record = (JsonMaintKodtvaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvakod", "", "Valutakode. (KVAKOD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvakrs", "", "Kurs (KVAKRS) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvaomr", "", "Omr.fakt (KVAOMR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvadt", "", "F.o.m. dato (KVADT) er obligatorisk"); 
		
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
		
		JsonMaintKodtvaRecord record = (JsonMaintKodtvaRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvakod", "", "Valutakode. (KVAKOD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kvadt", "", "F.o.m. dato (KVADT) er obligatorisk"); 
		

	}
}
