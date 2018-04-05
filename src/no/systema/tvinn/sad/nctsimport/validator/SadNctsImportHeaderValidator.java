package no.systema.tvinn.sad.nctsimport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicService;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicServiceImpl;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicRecord;

/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 */
public class SadNctsImportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsImportHeaderValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SadNctsImportSpecificTopicService nctsImportSpecificTopicService = new SadNctsImportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNctsImportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNctsImportSpecificTopicRecord record = (JsonSadNctsImportSpecificTopicRecord)obj;
		System.out.print("Inside SadNctsImportHeaderValidator");
		
		//Check for Mandatory fields first
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "systema.tvinn.sad.ncts.import.header.error.null.tiavd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tisg", "systema.tvinn.sad.ncts.import.header.error.null.tisg"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tienkl", "systema.tvinn.sad.ncts.import.header.error.null.tienkl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tign", "systema.tvinn.sad.ncts.import.header.error.null.tign"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titrnr", "systema.tvinn.sad.ncts.import.header.error.null.titrnr"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tignsk", "systema.tvinn.sad.ncts.import.header.error.null.tignsk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tialk", "systema.tvinn.sad.ncts.import.header.error.null.tialk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titsb", "systema.tvinn.sad.ncts.import.header.error.null.titsb"); 
		//ansvarig
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tina", "systema.tvinn.sad.ncts.import.header.error.null.tina"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titin", "systema.tvinn.sad.ncts.import.header.error.null.titin"); 
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//MRN = 18 char
				if( record.getTitrnr().length() != 18 ){
					errors.rejectValue("titrnr", "systema.tvinn.sad.ncts.import.header.error.rule.mrnnr.mandatory.size");
				}
				//Forenklad
				if( "J".equals(record.getTienkl()) ){
					if(record.getTiacts()!=null && !"".equals(record.getTiacts())){
						errors.rejectValue("tiacts", "systema.tvinn.sad.ncts.import.header.error.rule.tienkl.rejectKontrollPlatsOrAvtaladLagerPlats");
						logger.info("INVALID tiacts");
					}else if (this.avtalLagerPlatsOneOfTrioExists(record)){
						errors.rejectValue("tialsk", "systema.tvinn.sad.ncts.import.header.error.rule.tienkl.rejectKontrollPlatsOrAvtaladLagerPlats");
						logger.info("INVALID tialskTrio");	
					}
				//Normal				
				}else if ( "N".equals(record.getTienkl()) ){
					if(record.getTiglsk()!=null && !"".equals(record.getTiglsk())){
						errors.rejectValue("tiglsk", "systema.tvinn.sad.ncts.import.header.error.rule.tienklNormal.rejectGodkandLagerPlats");
						logger.info("INVALID tiglsk");
					}
					if(this.avtalLagerPlatsOneOfTrioExists(record)){
						if(!this.avtalLagerPlatsDuetExists(record)){
							errors.rejectValue("tialsk", "systema.tvinn.sad.ncts.import.header.error.rule.tienklNormal.incompleteAvtaladLagerPlats");
							logger.info("INVALID tialsk");
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean avtalLagerPlatsOneOfTrioExists(JsonSadNctsImportSpecificTopicRecord record){
		boolean retval = false;
		if(record.getTialsk()!=null || record.getTialss()!=null || record.getTials()!=null){
			if(!"".equals(record.getTialsk()) || !"".equals(record.getTialss()) || !"".equals(record.getTials()) ){
				retval = true;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean avtalLagerPlatsDuetExists(JsonSadNctsImportSpecificTopicRecord record){
		boolean retval = false;
		if(record.getTialsk()!=null || record.getTialss()!=null){
			if(!"".equals(record.getTialsk()) && !"".equals(record.getTialss()) ){
				retval = true;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	
	
	  
}
