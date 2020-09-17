package no.systema.tvinn.sad.manifest.express.validator;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import no.systema.main.util.StringManager;
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
						}
					}
				}
				if(strMgr.isNotNull(record.getEfsjadt())  && !"999999".equals(record.getEfsjadt())){
					if(record.getEfsjadt().length()>6){
						errors.rejectValue("efsjadt", "systema.tvinn.sad.manifest.express.header.error.rule.invalidDriverBirthday");
					}else{
						if(!dateValidator.validateDate(record.getEfsjadt(), DateValidator.DATE_MASK_NO)){
							errors.rejectValue("efsjadt", "systema.tvinn.sad.manifest.express.header.error.rule.invalidDriverBirthday"); 	
						}
					}
				}
				
			}
		}
	}
	
	
}
