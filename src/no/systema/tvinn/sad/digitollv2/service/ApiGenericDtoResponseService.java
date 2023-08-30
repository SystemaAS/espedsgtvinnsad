/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;


/**
 * @author oscardelatorre
 * @date Sep 2023
 *
 */
public interface ApiGenericDtoResponseService {
	public ApiGenericDtoResponse getReponse(String utfPayload);
	
}
