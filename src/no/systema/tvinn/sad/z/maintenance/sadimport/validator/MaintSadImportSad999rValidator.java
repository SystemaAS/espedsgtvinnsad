package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 13, 2016
 * 
 *
 */
public class MaintSadImportSad999rValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static final Logger logger = Logger.getLogger(MaintSadImportSad999rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportSadsdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportSadsdRecord record = (JsonMaintSadImportSadsdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sdtnrf", "", "Tariffnr. (SDTNRF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sdkdae", "", "Avg. (SDKDAE) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sddtf", "", "F.o.m dato (SDDTF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sddtt", "", "T.o.m dato (SDDTT) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sdblse", "", "Sats (SDBLSE) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if(!dateValidator.validateDateIso203_YYYYMMDD(record.getSddtf())){
					logger.error("record.getSddtf()="+record.getSddtf());
					errors.rejectValue("sddtf", "systema.tvinn.sad.export.error.rule.invalidFromDate"); 
				}
				if(!dateValidator.validateDateIso203_YYYYMMDD(record.getSddtt())){
					logger.error("record.getSddtt()="+record.getSddtt());
					errors.rejectValue("sddtt", "systema.tvinn.sad.export.error.rule.invalidToDate");
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
		
		JsonMaintSadImportSadsdRecord record = (JsonMaintSadImportSadsdRecord)obj;
		logger.info(record.getSdtnrf());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sdtnrf", "", "Tariffnr. (SDTNRF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sddtf", "", "F.o.m dato (SDDTF) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sddtt", "", "T.o.m dato (SDDTT) er obligatorisk"); 
		

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
