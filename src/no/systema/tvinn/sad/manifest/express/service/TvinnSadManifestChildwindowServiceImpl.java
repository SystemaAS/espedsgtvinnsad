/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

import no.systema.tvinn.sad.manifest.express.mapper.jsonjackson.TvinnSadManifestMapper;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestExportIdLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestFileUploadValidationContainer;
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
	
	/**
	 * 
	 */
	public JsonTvinnSadManifestFileUploadValidationContainer getFileUploadValidationContainer(String utfPayload){
		JsonTvinnSadManifestFileUploadValidationContainer container = null;
		try{
			TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
			container = mapper.getFileUploadValidationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	public JsonTvinnSadManifestExportIdLinesContainer getExportIdListContainer(String utfPayload) {
		JsonTvinnSadManifestExportIdLinesContainer container = null;
		try{
			TvinnSadManifestMapper mapper = new TvinnSadManifestMapper();
			container = mapper.getExportIdListContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

	
}
