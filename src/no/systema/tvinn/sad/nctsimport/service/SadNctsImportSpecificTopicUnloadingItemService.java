/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemContainer;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public interface SadNctsImportSpecificTopicUnloadingItemService {
	public JsonSadNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload);
	
}
