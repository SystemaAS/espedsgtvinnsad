/**
 * 
 */
package no.systema.main.service.general;

import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;
import no.systema.main.mapper.jsonjackson.general.FileUploadToArchiveMapper;


/**
 * 
 * @author oscardelatorre
 * @date Feb 24, 2017
 * 
 */

public class UploadFileToArchiveServiceImpl implements UploadFileToArchiveService{
	/**
	 * 
	 */
	public JsonFileUploadToArchiveValidationContainer getFileUploadValidationContainer (String utfPayload) {
		JsonFileUploadToArchiveValidationContainer container = null;
		try{
			FileUploadToArchiveMapper mapper = new FileUploadToArchiveMapper();
			container = mapper.getFileUploadValidationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
