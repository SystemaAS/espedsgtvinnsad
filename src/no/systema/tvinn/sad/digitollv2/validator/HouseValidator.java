package no.systema.tvinn.sad.digitollv2.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.util.*;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public class HouseValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(HouseValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SadmohfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SadmohfRecord record = (SadmohfRecord)obj;
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------
				//dates 
				//------
				/*
				String strEtetad = String.valueOf(record.getEtetad());
				if(strMgr.isNotNull(strEtetad)  && !"999999".equals(strEtetad)){
					if(strEtetad.length()>6){
						logger.warn("A");
						errors.rejectValue("etetad", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDate");
					}else{
						if(!dateValidator.validateDate(strEtetad, DateValidator.DATE_MASK_NO)){
							logger.warn("B");
							errors.rejectValue("etetad", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDate"); 	
						}else{
							//logical check. ETA must be at least 2 hours ahead from now
							DateTimeManager dateTimeMgr = new DateTimeManager();
							boolean isValidDate = dateTimeMgr.isValidCurrentAndForwardDateNO(strEtetad);
							if(!isValidDate){
								logger.warn("C");
								errors.rejectValue("etetad", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDateForward"); 
							}else{
								if(dateTimeMgr.isToday(strEtetad, DateTimeManager.NO_FORMAT)){
									String strEtetat = String.valueOf(record.getEtetat());
									strEtetat = dateTimeMgr.adjustUserTimeToHHmm(strEtetat);
									//check the hour. At least 2 hour ahead
									if(!dateTimeMgr.isValidTime(strEtetat, JsonTvinnSadManifestRecord.MANIFEST_AT_LEAST_HOURS_AHEAD_VALID)){
										logger.warn("D");
										errors.rejectValue("etetad", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTimeForward");
									}
								}
							}
							
						}
					}
				}
				
				//------
				//times
				//------
				//String strEtetat = String.valueOf(record.getEtetatStr());
				if(strMgr.isNotNull(record.getEtetatStr())){
					if(record.getEtetatStr().length() < 4){ //HHmm
						errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTime");
					}else{
						if(!dateValidator.validateTime24Hours(record.getEtetatStr())){
							errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTimeRegEx"); 	
						}
					}
				}
				*/
			}
		}
	}
	
}
