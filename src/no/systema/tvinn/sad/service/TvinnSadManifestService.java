/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.manifest.model.jsonjackson.JsonTvinnSadManifestContainer;


/**
 * @author oscardelatorre
 * @date Sep , 2018
 * 
 *
 */
public interface TvinnSadManifestService {
	public JsonTvinnSadManifestContainer getContainer(String utfPayload) throws Exception;
}
