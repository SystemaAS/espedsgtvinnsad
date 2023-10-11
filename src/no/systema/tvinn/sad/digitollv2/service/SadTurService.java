/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;


/**
 * @author oscardelatorre
 * @date Sep 2023
 *
 */
public interface SadTurService {
	public SadTurContainer getListContainer(String utfPayload);
	
}
