package no.systema.tvinn.sad.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;

/**
 * 
 * @author oscardelatorre
 * @date Sep 24, 2014
 */
public class SadImportHeaderFinansOpplysningerValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadImportHeaderFinansOpplysningerValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadImportTopicFinansOpplysningerRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonSadImportTopicFinansOpplysningerRecord record = (JsonSadImportTopicFinansOpplysningerRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sftxt", "systema.tvinn.sad.import.header.error.null.finans.infoiceNr.sftxt");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfdt", "systema.tvinn.sad.import.header.error.null.finans.dato.sfdt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfbl28", "systema.tvinn.sad.import.header.error.null.finans.belop.sfbl28"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfvk28", "systema.tvinn.sad.import.header.error.null.finans.valuta.sfvk28"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfkr28", "systema.tvinn.sad.import.header.error.null.finans.valutaKurs.sfkr28"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//----------------
				//Date validation
				//----------------
				if(record.getSfdt()!=null && !"".equals(record.getSfdt())){
					if(!isValidDate(record)){
						errors.rejectValue("sfdt", "systema.tvinn.sad.import.header.error.rule.finans.dato.sfdt.invalidDate");
					}
				}
			}
		}
		
	}
	/**
	 * The date should be in the correct format and not bigger than current date.
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidDate(JsonSadImportTopicFinansOpplysningerRecord record){
		boolean retval = true;
		DateTimeManager dateMgr = new DateTimeManager();
		if(record.getSfdt().length()!=6){
			retval = false;
		}else{
			String isoDate = dateMgr.getDateFormatted_ISO(record.getSfdt(), "ddMMyy");
			logger.info("ISO-date:"+ isoDate);
			retval = dateMgr.isValidCurrentAndBackwardDate(isoDate, "yyyMMdd");
			
		}
		
		return retval;
	}
	
}
