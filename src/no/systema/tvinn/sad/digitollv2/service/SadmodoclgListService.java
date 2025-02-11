/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmodoclgContainer;

/**
 * @author oscardelatorre
 * @date Feb 2025
 *
 */
public interface SadmodoclgListService {
	public SadmodoclgContainer getListContainer(String utfPayload);
	
}
