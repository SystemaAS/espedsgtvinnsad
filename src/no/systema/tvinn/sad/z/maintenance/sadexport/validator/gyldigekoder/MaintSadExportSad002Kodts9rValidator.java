package no.systema.tvinn.sad.z.maintenance.sadexport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodts9Record;

/**
 * 
 * @author oscardelatorre
 * @date Oct 25, 2016
 * 
 *
 */
public class MaintSadExportSad002Kodts9rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadExportSad002Kodts9rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadExportKodts9Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadExportKodts9Record record = (JsonMaintSadExportKodts9Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks9typ", "", "Kode (KS9TYP) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks9ftx", "", "Fritekst (KS9FTX) er obligatorisk"); 
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getKs9typ()) ){
					errors.rejectValue("ks9typ", "", "Kode value invalid. The value can not be greater than 99");
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
		
		JsonMaintSadExportKodts9Record record = (JsonMaintSadExportKodts9Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks9typ", "", "Kode (KS9TYP) er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Integer UPPER_LIMIT = 99;
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
