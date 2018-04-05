/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicUnloadingItemMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemContainer;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 */
public class SadNctsImportSpecificTopicUnloadingItemServiceImpl implements SadNctsImportSpecificTopicUnloadingItemService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSadNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicUnloadingItemContainer container = null;
		try{
			SadNctsImportSpecificTopicUnloadingItemMapper mapper = new SadNctsImportSpecificTopicUnloadingItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
