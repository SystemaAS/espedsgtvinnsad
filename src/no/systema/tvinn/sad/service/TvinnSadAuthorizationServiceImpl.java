/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.mapper.jsonjackson.authorization.TvinnSadAuthorizationMapper;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */

public class TvinnSadAuthorizationServiceImpl implements TvinnSadAuthorizationService {
	private TvinnSadAuthorizationMapper mapper = new TvinnSadAuthorizationMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadAuthorizationContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
}
