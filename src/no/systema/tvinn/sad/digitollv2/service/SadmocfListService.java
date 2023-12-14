/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfContainer;

/**
 * @author oscardelatorre
 * @date Oct 2023
 *
 */
public interface SadmocfListService {
	public SadmocfContainer getListContainer(String utfPayload);
	
}
