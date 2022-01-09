package no.systema.tvinn.sad.manifest.express.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
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
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadManifestHeaderCargoLinesValidator.class.getName());
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
				//Direktfortolling 
				if("01".equals(record.getClpr())){
					if(StringUtils.isEmpty(record.getCl0068a()) || StringUtils.isEmpty(record.getCl0068b()) || StringUtils.isEmpty(record.getClrg())
							|| StringUtils.isEmpty(record.getCletyp()) || StringUtils.isEmpty(record.getCleid())  ){
						errors.rejectValue("clrg", "systema.tvinn.sad.manifest.express.cargolines.error.rule.directfortolling.mandatory.ids");
					}
				}else if ("02".equals(record.getClpr())){
					if(StringUtils.isEmpty(record.getCltrnr()) || StringUtils.isEmpty(record.getClnas()) || StringUtils.isEmpty(record.getClnak()) ){
						errors.rejectValue("cltrnr", "systema.tvinn.sad.manifest.express.cargolines.error.rule.transit.mandatory.ids");
					}
					//Certified check
					if(record.getCleser().equals("J")){
						errors.rejectValue("cleser", "systema.tvinn.sad.manifest.express.cargolines.error.rule.transit.certified.invalid");
					}
				}
				
				//------
				//dates 
				//------
				if(strMgr.isNotNull(record.getCl0068a())  && !"999999".equals(record.getCl0068a())){
					if(!"0".equals(record.getCl0068a())){
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
	
	
}
