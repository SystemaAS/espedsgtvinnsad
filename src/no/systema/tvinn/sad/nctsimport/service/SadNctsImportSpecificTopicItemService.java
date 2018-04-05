/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemContainer;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public interface SadNctsImportSpecificTopicItemService {
	public JsonSadNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload);
}
