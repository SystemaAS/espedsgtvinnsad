package no.systema.tvinn.sad.sadexport.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 25, 2015
 */
public class SadExportHeaderFinansOpplysningerValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(SadExportHeaderFinansOpplysningerValidator.class.getName());
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadExportTopicFinansOpplysningerRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonSadExportTopicFinansOpplysningerRecord record = (JsonSadExportTopicFinansOpplysningerRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sftxt", "systema.tvinn.sad.export.header.error.null.finans.infoiceNr.sftxt");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfdt", "systema.tvinn.sad.export.header.error.null.finans.dato.sfdt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfbl28", "systema.tvinn.sad.export.header.error.null.finans.belop.sfbl28"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfvk28", "systema.tvinn.sad.export.header.error.null.finans.valuta.sfvk28"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sfkr28", "systema.tvinn.sad.export.header.error.null.finans.valutaKurs.sfkr28"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//----------------
				//Date validation
				//----------------
				if(record.getSfdt()!=null && !"".equals(record.getSfdt())){
					if(!isValidDate(record)){
						errors.rejectValue("sfdt", "systema.tvinn.sad.export.header.error.rule.finans.dato.sfdt.invalidDate");
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
	private boolean isValidDate(JsonSadExportTopicFinansOpplysningerRecord record){
		boolean retval = true;
		DateTimeManager dateMgr = new DateTimeManager();
		
		if(record.getSfdt().length()!=6){
			retval = false;
			
		}else{
			if(!dateValidator.validateDate(record.getSfdt(), DateValidator.DATE_MASK_NO)){
				retval = false;
				//retval = dateMgr.isValidCurrentAndBackwardDate(isoDate, "yyyMMdd");
			}
			
		}
		
		return retval;
	}
	
}
