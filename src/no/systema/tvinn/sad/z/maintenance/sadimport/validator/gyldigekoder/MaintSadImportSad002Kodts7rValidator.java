package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts7Record;

/**
 * 
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts7rValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadImportSad002Kodts7rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts7Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts7Record record = (JsonMaintSadImportKodts7Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks7vf", "", "Kode (KS7VF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks7ftx", "", "Fritekst (KS7FTX) er obligatorisk"); 
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getKs7vf()) ){
					errors.rejectValue("ks7vf", "", "Kode value invalid. The value can not be greater than 9");
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
		
		JsonMaintSadImportKodts7Record record = (JsonMaintSadImportKodts7Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks7vf", "", "Kode (KS7VF) er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Integer UPPER_LIMIT = 9;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			try{
				Double tmpDbl = Double.parseDouble(tmp);
				if(tmpDbl>UPPER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
}
