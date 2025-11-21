package no.systema.tvinn.sad.digitollv2.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.util.EmailValidator;
import no.systema.main.util.*;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public class MasterValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MasterValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	private EmailValidator emailValidator = new EmailValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SadmomfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SadmomfRecord record = (SadmomfRecord)obj;
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//confirmation email-1 ombud 
				if(StringUtils.isNotEmpty(record.getEmrcem1()) ) {
					if(!emailValidator.validateEmail(record.getEmrcem1())){
						errors.rejectValue("emrcem1", "systema.tvinn.sad.digitoll.master.error.rule.invalid.email1.ombud");
					}
				}

				//confirmation email-2 ombud 
				if(StringUtils.isNotEmpty(record.getEmrcem2()) ) {
					if(!emailValidator.validateEmail(record.getEmrcem2())){
						errors.rejectValue("emrcem2", "systema.tvinn.sad.digitoll.master.error.rule.invalid.email2.ombud");
					}
				}
				
				//confirmation email-3 ombud 
				if(StringUtils.isNotEmpty(record.getEmrcem3()) ) {
					if(!emailValidator.validateEmail(record.getEmrcem3())){
						errors.rejectValue("emrcem3", "systema.tvinn.sad.digitoll.master.error.rule.invalid.email3.ombud");
					}
				}
				
				
				//email sender 
				if(StringUtils.isNotEmpty(record.getOwn_emems_email()) ) {
					if(!emailValidator.validateEmail(record.getOwn_emems_email())){
						logger.info("FEL! sender email " + record.getOwn_emems_email());
						errors.rejectValue("own_emems_email", "systema.tvinn.sad.digitoll.master.error.rule.invalid.email.sender");
					}
				}
				//email receiver 
				if(StringUtils.isNotEmpty(record.getOwn_ememm_email()) ) {
					if(!emailValidator.validateEmail(record.getOwn_ememm_email())){
						logger.info("FEL! receiver email " + record.getOwn_ememm_email());
						errors.rejectValue("own_ememm_email", "systema.tvinn.sad.digitoll.master.error.rule.invalid.email.receiver");
					}
				}

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
