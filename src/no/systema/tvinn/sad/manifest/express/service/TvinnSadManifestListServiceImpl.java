/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

import no.systema.tvinn.sad.manifest.express.mapper.jsonjackson.TvinnSadManifestMapper;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;

/**
 * @author oscardelatorre
 * @date Sep 2020
 */
public class TvinnSadManifestListServiceImpl implements TvinnSadManifestListService {

	public JsonTvinnSadManifestContainer getListContainer(String utfPayload) {
		JsonTvinnSadManifestContainer container = null;
		try{
			TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTvinnSadManifestCargoLinesContainer getListCargolinesContainer(String utfPayload){
		JsonTvinnSadManifestCargoLinesContainer container = null;
		try{
			TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
			container = mapper.getCargolinesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}

}
