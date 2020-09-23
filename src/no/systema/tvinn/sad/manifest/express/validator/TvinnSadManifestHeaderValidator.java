package no.systema.tvinn.sad.manifest.express.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
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
							//logical check. ETA must be at least 1 hour ahead from now
							DateTimeManager dateTimeMgr = new DateTimeManager();
							boolean isValidDate = dateTimeMgr.isValidCurrentAndForwardDateNO(record.getEfeta());
							if(!isValidDate){
								errors.rejectValue("efeta", "systema.tvinn.sad.manifest.express.header.error.rule.invalidEtaDateForward"); 
							}else{
								if(this.dateIsToday(record.getEfeta(), DateTimeManager.NO_FORMAT)){
									record.setEfetm(dateTimeMgr.adjustUserTimeToHHmm(record.getEfetm()));
									//check the hour. At least one hour ahead
									if(!this.isValidTime(record.getEfetm())){
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
	
	/**
	 * 
	 * @param userValue
	 * @param mask
	 * @return
	 */
	private boolean dateIsToday(String userValue, String mask){
		boolean retval = false;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
			String x = dateFormat.format(Calendar.getInstance().getTime());
			Date now = dateFormat.parse(x);
			Date userTime = dateFormat.parse(userValue);
			if(now.compareTo(userTime)==0){
				retval = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	    return retval;
	}
	
	/**
	 * 
	 * @param timeUserValue
	 * @return
	 */
	private boolean isValidTime(String timeUserValue){
		boolean retval = false;
		int _LIMIT_HOURS = 1;
		try{
			Calendar now = Calendar.getInstance();
		    // Incrementing hours by _LIMIT_HOURS
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.add(Calendar.HOUR_OF_DAY, +_LIMIT_HOURS);
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
			Date userTime = dateFormat.parse(timeUserValue);
			String minimumHour = String.valueOf(calendar2.get(Calendar.HOUR_OF_DAY));
			String minimumMinute = String.valueOf(calendar2.get(Calendar.MINUTE));
			Date minimumTime = dateFormat.parse(minimumHour + minimumMinute);
	
			if (userTime.after(minimumTime)){
			    logger.warn("Ahead!");
			    retval = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retval;
		
	}
	
}
