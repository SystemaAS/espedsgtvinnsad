/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationContainer;



/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 */
public interface TvinnSadAuthorizationService {
	public JsonTvinnSadAuthorizationContainer getContainer(String utfPayload) throws Exception;
	
}
