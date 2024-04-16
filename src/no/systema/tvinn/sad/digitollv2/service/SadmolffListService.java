/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolffContainer;

/**
 * @author oscardelatorre
 * @date Apr 2024
 *
 */
public interface SadmolffListService {
	public SadmolffContainer getListContainer(String utfPayload);
	
}
