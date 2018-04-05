package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Record;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public class MaintSadImportSad002Kodts3rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002Kodts3rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodts3Record.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodts3Record record = (JsonMaintSadImportKodts3Record)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks3trt", "", "Kode (KS3TRT) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks3ftx", "", "Fritekst (KS3FTX) er obligatorisk"); 
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getKs3trt()) ){
					errors.rejectValue("ks3trt", "", "Kode value invalid. The value can not be greater than 9");
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
		
		JsonMaintSadImportKodts3Record record = (JsonMaintSadImportKodts3Record)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ks3trt", "", "Kode (KS3TRT) er obligatorisk"); 
		
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
