/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;

/**
 * @author oscardelatorre
 * @date Aug 2023
 *
 */
public interface SadmohfListService {
	public SadmohfContainer getListContainer(String utfPayload);
	
}
