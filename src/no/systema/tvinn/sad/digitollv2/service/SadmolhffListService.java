/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolhffContainer;

/**
 * @author oscardelatorre
 * @date Maj 2024
 *
 */
public interface SadmolhffListService {
	public SadmolhffContainer getListContainer(String utfPayload);
	
}
