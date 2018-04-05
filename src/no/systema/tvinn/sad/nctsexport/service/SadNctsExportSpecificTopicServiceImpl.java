/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicGuaranteeValidatorMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicArchiveMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicLoggingLargeTextMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicLoggingMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportTopicCopiedMapper;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 * 
 */
public class SadNctsExportSpecificTopicServiceImpl implements SadNctsExportSpecificTopicService{
	public JsonSadNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload) {
		JsonSadNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = null;
		try{
			SadNctsExportSpecificTopicMapper nctsExportSpecificTopicMapper = new SadNctsExportSpecificTopicMapper();
			jsonNctsExportSpecificTopicContainer = nctsExportSpecificTopicMapper.getContainer(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicContainer;
		
	}
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonSadNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSadNctsExportSpecificTopicArchiveContainer container = null;
		try{
			SadNctsExportSpecificTopicArchiveMapper mapper = new SadNctsExportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	/**
	 * Logging
	 * @param utfPayload
	 * @return
	 */
	public JsonSadNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSadNctsExportSpecificTopicLoggingContainer container = null;
		try{
			SadNctsExportSpecificTopicLoggingMapper mapper = new SadNctsExportSpecificTopicLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Show large text
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSadNctsExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SadNctsExportSpecificTopicLoggingLargeTextMapper mapper = new SadNctsExportSpecificTopicLoggingLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	
	public JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload){
		JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer container = null;
		try{
			SadNctsExportSpecificTopicGuaranteeValidatorMapper mapper = new SadNctsExportSpecificTopicGuaranteeValidatorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	/**
	 * Cloned record
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload){
		JsonSadNctsExportTopicCopiedContainer container = null;
		try{
			SadNctsExportTopicCopiedMapper mapper = new SadNctsExportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	
}
