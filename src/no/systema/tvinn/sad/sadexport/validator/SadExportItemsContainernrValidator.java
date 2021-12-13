package no.systema.tvinn.sad.sadexport.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.StringManager;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrRecord;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2019
 */
public class SadExportItemsContainernrValidator implements Validator {
	private static final Logger logger = LogManager.getLogger(SadExportItemsContainernrValidator.class.getName());
	private String nameRegex = "[A-Z]{4}[0-9]{7}";
	private StringManager strMgr = new StringManager();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadExportSpecificTopicItemContainernrRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadExportSpecificTopicItemContainernrRecord record = (JsonSadExportSpecificTopicItemContainernrRecord)obj;
		
		
			if(record!=null){
				
				//Containernr
				if(strMgr.isNotNull(record.getSvcnr())){
					Pattern namePattern = Pattern.compile(nameRegex);
			        Matcher nameMatcher = namePattern.matcher(record.getSvcnr());
			        if (nameMatcher.find()) {
			            //OK
			        } else {
			        	errors.rejectValue("svcnr", "systema.tvinn.sad.general.error.rule.item.svcnr.containernr.invalid");
			        }
				}
			}
	}
}
