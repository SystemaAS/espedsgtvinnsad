/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfContainer;

/**
 * @author oscardelatorre
 * @date Apr 2024
 *
 */
public interface ZadmomlfListService {
	public ZadmomlfContainer getListContainer(String utfPayload);
	
}
