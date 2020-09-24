package no.systema.tvinn.sad.manifest.express.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import no.systema.main.util.*;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public class TvinnSadManifestHeaderCargoLinesValidator implements Validator {
	private static final Logger logger = Logger.getLogger(TvinnSadManifestHeaderCargoLinesValidator.class.getName());
	private StringManager strMgr = new StringManager();
	private DateValidator dateValidator = new DateValidator();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTvinnSadManifestCargoLinesRecord.class.isAssignableFrom(clazz); 
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
		JsonTvinnSadManifestCargoLinesRecord record = (JsonTvinnSadManifestCargoLinesRecord)obj;
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------
				//dates 
				//------
				if(strMgr.isNotNull(record.getCl0068a())  && !"999999".equals(record.getCl0068a())){
					if(record.getCl0068a().length()>6){
						errors.rejectValue("cl0068a", "systema.tvinn.sad.manifest.express.cargolines.error.rule.invalidDeklDate");
					}else{
						if(!dateValidator.validateDate(record.getCl0068a(), DateValidator.DATE_MASK_NO)){
							errors.rejectValue("cl0068a", "systema.tvinn.sad.manifest.express.cargolines.error.rule.invalidDeklDate"); 	
						}
					}
				}
				
			}
		}
	}
	
	
}
