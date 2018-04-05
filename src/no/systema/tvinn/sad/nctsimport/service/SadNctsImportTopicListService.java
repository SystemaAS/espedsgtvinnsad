/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2015
 * 
 *
 */
public interface SadNctsImportTopicListService {
	public JsonSadNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload);
}
