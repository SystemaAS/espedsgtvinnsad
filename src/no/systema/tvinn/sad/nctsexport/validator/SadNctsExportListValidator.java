package no.systema.tvinn.sad.nctsexport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.nctsexport.filter.SearchFilterSadNctsExportTopicList;

/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 *
 */
public class SadNctsExportListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterSadNctsExportTopicList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterSadNctsExportTopicList record = (SearchFilterSadNctsExportTopicList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			if( !this.valueExists(record.getAvd()) && !this.valueExists(record.getSign()) ){
				if( this.valueExists(record.getOpd()) || this.valueExists(record.getLrnNr()) || 
					this.valueExists(record.getDatum()) ||  this.valueExists(record.getBruttoVikt()) ||	
					this.valueExists(record.getMotNavn()) ||  this.valueExists(record.getStatus()) ){
					//do nothing. Validation test passed!
				}else{
					//at least avd or sign must exist IF everything else is empty... 
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avd", "systema.tvinn.sad.ncts.export.list.error.null.avdOrSign"); 
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
