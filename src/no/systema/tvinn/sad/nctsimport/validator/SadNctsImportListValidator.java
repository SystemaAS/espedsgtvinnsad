package no.systema.tvinn.sad.nctsimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.nctsimport.filter.SearchFilterSadNctsImportTopicList;

/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 *
 */
public class SadNctsImportListValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsImportListValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterSadNctsImportTopicList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterSadNctsImportTopicList record = (SearchFilterSadNctsImportTopicList)obj;
		logger.info("Inside validator class...");
		//Check for Mandatory fields first
		if(record!=null){
			
			if( !this.valueExists(record.getAvd()) && !this.valueExists(record.getSign()) ){
				if( this.valueExists(record.getOpd()) ||  this.valueExists(record.getStatus()) || 
					this.valueExists(record.getForenklad()) ||  this.valueExists(record.getForenklad()) ||	
					this.valueExists(record.getGodsNr()) ||  this.valueExists(record.getDatumFr()) ||
					this.valueExists(record.getMrnNr()) ||  this.valueExists(record.getDatum())	 ){
					//do nothing. Validation test passed!
				}else{
					//at least avd or sign must exist IF everything else is empty... 
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avd", "systema.tvinn.sad.ncts.import.list.error.null.avdOrSign"); 
				}
			}
			
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean valueExists(String value){
		boolean retval = false;
		if(value!=null){
			if(!"".equals(value)){
				retval = true;
			}
		}
		
		return retval;
	}
}
