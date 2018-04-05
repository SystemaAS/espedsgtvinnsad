package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Record;

/**
 * 
 * @author oscardelatorre
 * @date May 13, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts8rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002Kodts8rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts8Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts8Record record = (JsonMaintSadImportKodts8Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks8avg", "", "Avg.kode (KS8AVG) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks8ftx", "", "Fritekst (KS8FTX) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getKs8sat()) ){
					errors.rejectValue("ks8sat", "", "Sats value invalid. The value can not be greater than 99999.99");
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
		
		JsonMaintSadImportKodts8Record record = (JsonMaintSadImportKodts8Record)obj;
		//logger.info(record.getKs8avg());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks8avg", "", "Avg.kode (KS8AVG) er obligatorisk"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks8skv", "", "Sekv. (KS8SKV) er obligatorisk"); 
		

	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Double UPPER_LIMIT = 99999.99;
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
