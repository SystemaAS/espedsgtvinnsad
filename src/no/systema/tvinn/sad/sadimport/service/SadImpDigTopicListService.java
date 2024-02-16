/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 *
 */
public interface SadImpDigTopicListService {
	public SadImpDigContainer getSadImpDigContainer(String utfPayload);
}
