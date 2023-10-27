/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import org.springframework.stereotype.Service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadAvdSignContainer;

/**
 * @author oscardelatorre
 * @date Oct 2023
 *
 */
public interface SadAvdSignService {
	public SadAvdSignContainer getListContainer(String utfPayload);
	
}
