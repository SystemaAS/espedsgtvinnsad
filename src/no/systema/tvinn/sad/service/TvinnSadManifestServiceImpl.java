/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.manifest.express.mapper.jsonjackson.TvinnSadManifestMapper;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2018
 */
public class TvinnSadManifestServiceImpl implements TvinnSadManifestService {
	private TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 * 
	 */
	public JsonTvinnSadManifestContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
	
	
}
