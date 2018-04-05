/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicArchiveMapper;
import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicLoggingLargeTextMapper;
import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicLoggingMapper;
import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicContainer;

/**
 * @author oscardelatorre
 * @date Mar 6, 2015
 */
public class SadNctsImportSpecificTopicServiceImpl implements SadNctsImportSpecificTopicService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSadNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicContainer container = null;
		try{
			SadNctsImportSpecificTopicMapper mapper = new SadNctsImportSpecificTopicMapper();
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
	public JsonSadNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicLoggingContainer container = null;
		try{
			SadNctsImportSpecificTopicLoggingMapper mapper = new SadNctsImportSpecificTopicLoggingMapper();
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
	public JsonSadNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSadNctsImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SadNctsImportSpecificTopicLoggingLargeTextMapper mapper = new SadNctsImportSpecificTopicLoggingLargeTextMapper();
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
	public JsonSadNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicArchiveContainer container = null;
		try{
			SadNctsImportSpecificTopicArchiveMapper mapper = new SadNctsImportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}	

	
}
