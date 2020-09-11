/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

import no.systema.tvinn.sad.manifest.express.mapper.jsonjackson.TvinnSadManifestMapper;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestPostalCodeContainer;


/**
 * 
 * @author oscardelatorre
 * @date Aug 31, 2017
 * 
 * 
 */
public class TvinnSadManifestChildwindowServiceImpl implements TvinnSadManifestChildwindowService {

	
	/**
	 * 
	 */
	public JsonTvinnSadManifestPostalCodeContainer getPostalCodeListContainer(String utfPayload){
		JsonTvinnSadManifestPostalCodeContainer container = null;
		try{
			TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
			container = mapper.getPostalCodeContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}
	
}
