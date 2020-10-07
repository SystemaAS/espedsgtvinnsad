package no.systema.tvinn.sad.manifest.express.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.main.util.*;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public class TvinnSadManifestHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(TvinnSadManifestHeaderValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTvinnSadManifestRecord.class.isAssignableFrom(clazz); 
	}
	
	/*
	public TvinnSadManifestHeaderValidator (UrlCgiProxyService urlCgiProxyServiceParam, String applicationUserParam, CurrencyRateService currencyRateServiceParam){
		this.urlCgiProxyService = urlCgiProxyServiceParam;
		this.currencyRateService =  currencyRateServiceParam;
		this.applicationUser = applicationUserParam;
	}*/
	
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTvinnSadManifestRecord record = (JsonTvinnSadManifestRecord)obj;
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "efrgd", "systema.tvinn.sad.manifest.express.header.error.null.orgnr");
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------
				//dates 
				//------
				if(strMgr.isNotNull(record.getEfeta())  && !"999999".equals(record.getEfeta())){
					if(record.getEfeta().length()>6){
						errors.rejectValue("efeta", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDate");
					}else{
						if(!dateValidator.validateDate(record.getEfeta(), DateValidator.DATE_MASK_NO)){
							errors.rejectValue("efeta", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDate"); 	
						}else{
							//logical check. ETA must be at least 2 hours ahead from now
							DateTimeManager dateTimeMgr = new DateTimeManager();
							boolean isValidDate = dateTimeMgr.isValidForwardDateNO(record.getEfeta());
							if(!isValidDate){
								errors.rejectValue("efeta", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDateForward"); 
							}else{
								if(dateTimeMgr.isToday(record.getEfeta(), DateTimeManager.NO_FORMAT)){
									record.setEfetm(dateTimeMgr.adjustUserTimeToHHmm(record.getEfetm()));
									//check the hour. At least 2 hour ahead
									if(!dateTimeMgr.isValidTime(record.getEfetm(), JsonTvinnSadManifestRecord.MANIFEST_AT_LEAST_HOURS_AHEAD_VALID)){
										errors.rejectValue("efetm", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTimeForward");
									}
								}
							}
							
						}
					}
				}
				if(strMgr.isNotNull(record.getEfsjadt())  && !"999999".equals(record.getEfsjadt())){
					if(record.getEfsjadt().length()>6){
						errors.rejectValue("efsjadt", "systema.tvinn.sad.manifest.express.header.error.rule.invalidDriverBirthday");
					}else{
						if(!dateValidator.validateDate(record.getEfsjadt(), DateValidator.DATE_MASK_NO)){
							errors.rejectValue("efsjadt", "systema.tvinn.sad.manifest.express.header.error.rule.invalidDriverBirthday"); 	
						}else{
							//check if the driver is older than 18 years since toll.no has this check
							if(!dateValidator.validDrivingAgeNorway(record.getEfsjadt())){
								errors.rejectValue("efsjadt", "systema.tvinn.sad.manifest.express.header.error.rule.invalidDriversAge");
							}
						}
					}
				}
				//------
				//times
				//------
				if(strMgr.isNotNull(record.getEfetm())){
					if(record.getEfetm().length() < 4){ //HHmm
						errors.rejectValue("efetm", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTime");
					}else{
						if(!dateValidator.validateTime24Hours(record.getEfetm())){
							errors.rejectValue("efetm", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaTime"); 	
						}
					}
				}
			}
		}
	}
	
}
