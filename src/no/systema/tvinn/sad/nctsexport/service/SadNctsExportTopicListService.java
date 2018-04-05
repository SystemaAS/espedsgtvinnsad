/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public interface SadNctsExportTopicListService {
	public JsonSadNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload);
}
