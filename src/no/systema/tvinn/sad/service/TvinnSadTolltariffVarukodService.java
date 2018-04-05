/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public interface TvinnSadTolltariffVarukodService {
	public JsonTvinnSadTolltariffVarukodContainer getContainer(String utfPayload) throws Exception;
}
