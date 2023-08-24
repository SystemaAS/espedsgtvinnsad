/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;

/**
 * @author oscardelatorre
 * @date Aug 2023
 *
 */
public interface SadmoifListService {
	public SadmoifContainer getListContainer(String utfPayload);
	
}
