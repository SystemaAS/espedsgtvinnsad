package no.systema.tvinn.sad.digitollv2.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.util.*;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public class TransportValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(TransportValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SadmotfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SadmotfRecord record = (SadmotfRecord)obj;
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------
				//dates 
				//------
				String strEtetad = record.getEtetadStr();
				logger.warn(strEtetad);
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
				
				String strEtshed = record.getEtshedStr();
				if(strMgr.isNotNull(strEtshed)  && !"999999".equals(strEtshed)){
					if(strEtshed.length()>6){
						logger.warn("AA");
						errors.rejectValue("etshed", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaDate");
					}else{
						if(!dateValidator.validateDate(strEtshed, DateValidator.DATE_MASK_NO)){
							logger.warn("BB");
							errors.rejectValue("etshed", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaDate"); 	
						}else{
							//logical check. ETA must be at least 2 hours ahead from now
							DateTimeManager dateTimeMgr = new DateTimeManager();
							boolean isValidDate = dateTimeMgr.isValidCurrentAndForwardDateNO(strEtshed);
							if(!isValidDate){
								logger.warn("CC");
								errors.rejectValue("etshed", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaDateForward"); 
							}else{
								if(dateTimeMgr.isToday(strEtshed, DateTimeManager.NO_FORMAT)){
									String strEtshet = String.valueOf(record.getEtshet());
									strEtshet = dateTimeMgr.adjustUserTimeToHHmm(strEtshet);
									//check the hour. At least 2 hour ahead
									if(!dateTimeMgr.isValidTime(strEtshet, JsonTvinnSadManifestRecord.MANIFEST_AT_LEAST_HOURS_AHEAD_VALID)){
										logger.warn("DD");
										errors.rejectValue("etshed", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaTimeForward");
									}
								}
							}
							
						}
					}
				}
				
				
				
				//------
				//times
				//------
				//ETA
				if(strMgr.isNotNull(record.getEtetatStr())){
					if(record.getEtetatStr().length() < 4){ //HHmm
						errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTime");
					}else{
						if(!dateValidator.validateTime24Hours(record.getEtetatStr())){
							errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTimeRegEx"); 	
						}
					}
				}
				//STA
				if(strMgr.isNotNull(record.getEtshetStr())){
					if(record.getEtshetStr().length() < 4){ //HHmm
						errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaTime");
					}else{
						if(!dateValidator.validateTime24Hours(record.getEtshetStr())){
							errors.rejectValue("etetat", "systema.tvinn.sad.manifest.express.header.error.rule.invalidStaTimeRegEx"); 	
						}
					}
				}
				
		
				
				
				//IATA number (air)
				if(StringUtils.isNotEmpty(record.getEtcref())) {
					if(record.getEtshed()==null || record.getEtshet()==null  ) {
						errors.rejectValue("etshed", "systema.tvinn.sad.digitoll.transport.error.rule.required.ScheduleDates");
					}else {
						if(record.getEtshed()==0 || record.getEtshet()==0  ) {
							errors.rejectValue("etshed", "systema.tvinn.sad.digitoll.transport.error.rule.required.ScheduleDates");
						}
					}
					
				}
				
				if(StringUtils.isNotEmpty(record.getEttsd())) {
					if(record.getEttsd().length()>8){
						errors.rejectValue("ettsd", "systema.tvinn.sad.digitoll.transport.error.rule.invalid.length.tollsted");
					}
				}
				
			}
		}
	}
	
}
