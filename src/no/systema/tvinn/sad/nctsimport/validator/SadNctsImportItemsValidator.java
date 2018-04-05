package no.systema.tvinn.sad.nctsimport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicItemService;
import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicItemServiceImpl;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 */
public class SadNctsImportItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsImportItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SadNctsImportSpecificTopicItemService sadNctsImportSpecificTopicItemService = new SadNctsImportSpecificTopicItemServiceImpl();
		
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNctsImportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNctsImportSpecificTopicItemRecord record = (JsonSadNctsImportSpecificTopicItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvst", "systema.tvinn.sad.ncts.import.header.error.null.item.plats.tvst"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvstlk", "systema.tvinn.sad.ncts.import.header.error.null.item.platscountry.tvstlk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvinf", "systema.tvinn.sad.ncts.import.header.error.null.item.eventtext.tvinf1");
						
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------------------------------------------------------------
				//Känsliga varor must always check for mandatory input or none
				//-------------------------------------------------------------
				/*if(record.getTvvnt()!=null && !"".equals(record.getTvvnt()) ){
					if(this.isVarukodWithSensitiveGoodsFlag(record)){
						if(record.getTvfvnt()!=null && !"".equals(record.getTvfvnt())){
							//nothing since it has been filled in
						}else{
							errors.rejectValue("tvfvnt", "systema.ncts.export.header.error.rule.item.tvfvnt.mustExist");
						}
					}else{
						//back to default values
						record.setTvfv(null);
						record.setTvfvnt(null);
					}
				}*/
			}
			
		}
			
	}
	
	
	
}
