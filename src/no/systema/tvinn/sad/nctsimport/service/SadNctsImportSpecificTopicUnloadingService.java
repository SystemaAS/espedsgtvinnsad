/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingContainer;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public interface SadNctsImportSpecificTopicUnloadingService {
	public JsonSadNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload);
	
}
