package no.systema.tvinn.sad.nctsexport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicService;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicServiceImpl;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment.JsonSadNcts5ExportHouseConsignmentRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;

import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 *
 */
public class SadNcts5ExportHouseConsignmentValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(SadNcts5ExportHouseConsignmentValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SadNctsExportSpecificTopicService nctsExportSpecificTopicService = new SadNctsExportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNcts5ExportHouseConsignmentRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNcts5ExportHouseConsignmentRecord record = (JsonSadNcts5ExportHouseConsignmentRecord)obj;
		logger.info("Inside SadNcts5ExportHeaderValidator");
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tcavd", "systema.tvinn.sad.ncts5.export.house.consignment.error.null.tcavd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tcvktb", "systema.tvinn.sad.ncts5.export.house.consignment.error.null.tcvktb"); 
				
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//-----------------
				//Date validations
				//-----------------
				/*
				if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
					if(!this.isValidDate(record.getThtrdt())){
						errors.rejectValue("thtrdt", "systema.tvinn.sad.ncts.export.header.error.rule.thtrdt.invalid.date.format");
						logger.info("ERROR thtrdt");
					}
				}
				*/
				
				
				
				
			}
		}
		
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		//ISO->>SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy"); //NO
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
