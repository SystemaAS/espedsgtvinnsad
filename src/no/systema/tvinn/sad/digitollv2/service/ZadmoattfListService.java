/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfContainer;

/**
 * @author oscardelatorre
 * @date Mar 2025
 *
 */
public interface ZadmoattfListService {
	public ZadmoattfContainer getListContainer(String utfPayload);
	
}
