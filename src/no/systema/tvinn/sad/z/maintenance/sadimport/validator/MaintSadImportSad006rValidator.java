package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 *
 */
public class MaintSadImportSad006rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad006rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportSadhHeadfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportSadhHeadfRecord record = (JsonMaintSadImportSadhHeadfRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sitdn", "", "Tolldekl.nr (SITDN) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
  				if(  (record.getSitll()!=null && !"".equals(record.getSitll())) ||
  					 (record.getSitle()!=null && !"".equals(record.getSitle())) ||
  					 (record.getSidtg()!=null && !"".equals(record.getSidtg()))		 ){
  					 //Nothing = OK
  				}else{
  					errors.rejectValue("sitdn", "", "Du må fylle Løpenr, Eksp.nr. eller Dato");
  				}
  				
				
			}
		}
	}
	
}
