/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicUnloadingMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingContainer;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsImportSpecificTopicUnloadingServiceImpl implements SadNctsImportSpecificTopicUnloadingService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSadNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicUnloadingContainer container = null;
		try{
			SadNctsImportSpecificTopicUnloadingMapper mapper = new SadNctsImportSpecificTopicUnloadingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
