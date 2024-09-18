/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmobuplogContainer;

/**
 * @author oscardelatorre
 * @date Sep 2024
 *
 */
public interface SadmobuplogListService {
	public SadmobuplogContainer getListContainer(String utfPayload);
	
}
