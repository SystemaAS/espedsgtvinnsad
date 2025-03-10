package no.systema.tvinn.sad.digitollv2.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.jservices.common.util.EmailValidator;
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
	private EmailValidator emailValidator = new EmailValidator();
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
				if(StringUtils.isNotEmpty(record.getEhprt())){
					if(record.getEhprt().startsWith("TRANSIT")){
						if("CUDE".equals(record.getEhtrty())) {
							errors.rejectValue("ehtrty", "systema.tvinn.sad.digitoll.house.error.rule.invalidReferenceType");
						}
						if(StringUtils.isNotEmpty(record.getEhrg()) || (record.getEh0068a()!=null && record.getEh0068a()>0 ) || (record.getEh0068b()!=null && record.getEh0068b()>0 ) ) {
							errors.rejectValue("ehtrty", "systema.tvinn.sad.digitoll.house.error.rule.notrequired.deklDateSekv");
						}
					}else if (record.getEhprt().startsWith("IMMEDIATE")){
						if(!"CUDE".equals(record.getEhtrty())) {
							errors.rejectValue("ehtrty", "systema.tvinn.sad.digitoll.house.error.rule.invalidReferenceType");
						}else {
							//CUDE
							/*
							if( StringUtils.isNotEmpty(record.getEhrg())){
								if(record.getEh0068a()!=null && record.getEh0068b() !=null) {
									if(record.getEh0068a()>0 && record.getEh0068b() > 0) {
									 //OK
									}else {
										errors.rejectValue("ehrg", "systema.tvinn.sad.digitoll.house.error.rule.required.deklDateSekv");	
									}
								}else {
									errors.rejectValue("ehrg", "systema.tvinn.sad.digitoll.house.error.rule.required.deklDateSekv");
								}
							}else {
								//all 3 or none
								if(record.getEh0068a()!=null && record.getEh0068b()!=null && StringUtils.isNotEmpty(record.getEhrg())){
									//OK
								}else {
									errors.rejectValue("ehrg", "systema.tvinn.sad.digitoll.house.error.rule.required.deklDateSekv");
								}
							}*/
							//CUDE
							if(record.getEh0068a()!=null && record.getEh0068b()!=null && StringUtils.isNotEmpty(record.getEhrg())){
								//OK - All three not empty
							}else if(record.getEh0068a()==null && record.getEh0068b()==null && StringUtils.isEmpty(record.getEhrg())){
								//OK - all three empty
							}else {
								errors.rejectValue("ehrg", "systema.tvinn.sad.digitoll.house.error.rule.required.deklDateSekv");
							}
							
						}
					}else if (record.getEhprt().startsWith("WAREHOUSE")){
						if("GONU".equals(record.getEhtrty())) {
							if(StringUtils.isNotEmpty(record.getEhtrnr())) {
								//OK
							}else {
								errors.rejectValue("ehtrnr", "systema.tvinn.sad.digitoll.house.error.rule.invalidGonuRef");
							}
						}
					}
				}
				
				//email sender 
				if(StringUtils.isNotEmpty(record.getOwn_ehems_email()) ) {
					if(!emailValidator.validateEmail(record.getOwn_ehems_email())){
						errors.rejectValue("own_ehems_email", "systema.tvinn.sad.digitoll.house.error.rule.invalid.email.sender");
					}
				}
				//email receiver 
				if(StringUtils.isNotEmpty(record.getOwn_ehemm_email()) ) {
					if(!emailValidator.validateEmail(record.getOwn_ehemm_email())){
						errors.rejectValue("own_ehemm_email", "systema.tvinn.sad.digitoll.house.error.rule.invalid.email.receiver");
					}
				}
				//Sender address (one of these MUST be present)
				if(StringUtils.isEmpty(record.getEhad1s()) && StringUtils.isEmpty(record.getEhpbs()) ) {
					errors.rejectValue("ehad1s", "systema.tvinn.sad.digitoll.house.error.rule.required.sender.adressOrAdress2");
				}
				//Receiver address (one of these MUST be present)
				if(StringUtils.isEmpty(record.getEhad1m()) && StringUtils.isEmpty(record.getEhpbm()) ) {
					errors.rejectValue("ehad1m", "systema.tvinn.sad.digitoll.house.error.rule.required.receiver.adressOrAdress2");
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
