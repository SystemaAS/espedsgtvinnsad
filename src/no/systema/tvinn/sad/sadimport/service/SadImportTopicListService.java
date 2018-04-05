/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 *
 */
public interface SadImportTopicListService {
	public JsonSadImportTopicListContainer getSadImportTopicListContainer(String utfPayload);
}
