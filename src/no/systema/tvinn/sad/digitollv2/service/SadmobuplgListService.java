/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;


import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmobuplgContainer;

/**
 * @author oscardelatorre
 * @date Sep 2024
 *
 */
public interface SadmobuplgListService {
	public SadmobuplgContainer getListContainer(String utfPayload);
	
}
