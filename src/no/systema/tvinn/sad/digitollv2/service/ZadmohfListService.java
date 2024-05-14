/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmohfContainer;

/**
 * @author oscardelatorre
 * @date Maj 2024
 *
 */
public interface ZadmohfListService {
	public ZadmohfContainer getListContainer(String utfPayload);
	
}
