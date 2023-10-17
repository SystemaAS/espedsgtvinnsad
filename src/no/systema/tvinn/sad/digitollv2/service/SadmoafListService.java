/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;

/**
 * @author oscardelatorre
 * @date Oct 2023
 *
 */
public interface SadmoafListService {
	public SadmoafContainer getListContainer(String utfPayload);
	
}
