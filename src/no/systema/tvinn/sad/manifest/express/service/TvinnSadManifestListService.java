/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;

/**
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public interface TvinnSadManifestListService {
	public JsonTvinnSadManifestContainer getListContainer(String utfPayload);
}
