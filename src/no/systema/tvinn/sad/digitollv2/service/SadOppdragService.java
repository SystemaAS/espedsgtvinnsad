/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;


/**
 * @author oscardelatorre
 * @date Sep 2023
 *
 */
public interface SadOppdragService {
	public SadOppdragContainer getListContainer(String utfPayload);
	
}
