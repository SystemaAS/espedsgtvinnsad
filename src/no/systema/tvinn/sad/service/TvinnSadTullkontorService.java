/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 *
 */
public interface TvinnSadTullkontorService {
	public JsonTvinnSadTullkontorContainer getTvinnSadTullkontorContainer(String utfPayload);
}
