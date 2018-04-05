package no.systema.tvinn.sad.nctsimport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
//import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
//import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicServiceImpl;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingRecord;


/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 */
public class SadNctsImportUnloadingValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsImportUnloadingValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	//private SkatNctsImportSpecificTopicService nctsImportSpecificTopicService = new SkatNctsImportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNctsImportSpecificTopicUnloadingRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNctsImportSpecificTopicUnloadingRecord record = (JsonSadNctsImportSpecificTopicUnloadingRecord)obj;
		logger.info("Inside module...");
		
		//Check for Mandatory fields first
		/*	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "systema.skat.ncts.import.header.error.null.tiavd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tisg", "systema.skat.ncts.import.header.error.null.tisg"); 
		*/
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Konform
				if( "0".equals(record.getNikonf())){
					/* TODO Covi: activate after talks with YBC,CB and BOG 
					if(!this.isValidKonformMandatoryFields(record)){
						errors.rejectValue("nikonf", "systema.skat.ncts.import.header.error.null.unloading.conform.nikonf");
					}
					*/
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidKonformMandatoryFields(JsonSadNctsImportSpecificTopicUnloadingRecord record){
		boolean retval = false;
		if( (record.getNictb()!=null && !"".equals(record.getNictb()) ) || (record.getNictb2()!=null && !"".equals(record.getNictb2()) ) ){
			if(record.getNictsk()!=null &&!"".equals(record.getNictsk())){
				retval = true;
			}
			
		}
		
		return retval;
	}
	
}
