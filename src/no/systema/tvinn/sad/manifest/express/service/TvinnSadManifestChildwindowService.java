/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestPostalCodeContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep, 2020
 * 
 *
 */
public interface TvinnSadManifestChildwindowService {
	
	public JsonTvinnSadManifestPostalCodeContainer getPostalCodeListContainer(String utfPayload);
	
}
