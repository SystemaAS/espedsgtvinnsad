/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrContainer;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrListContainer;

/**
 * @author oscardelatorre
 * @date Maj 04, 2018
 * 
 *
 */
public interface TvinnSadGodsnrService {
	public JsonTvinnSadGodsnrContainer getContainer(String utfPayload) throws Exception;
	public JsonTvinnSadGodsnrListContainer getContainerGodsnrList(String utfPayload) throws Exception;
}
