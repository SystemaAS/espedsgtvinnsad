/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 *
 */
public interface SadExportTopicListService {
	public JsonSadExportTopicListContainer getSadExportTopicListContainer(String utfPayload);
}
