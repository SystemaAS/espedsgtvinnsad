/**
 * 
 */
package no.systema.main.service.general;

import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;
/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 * @date feb 24, 2017
 * 
 */
public interface UploadFileToArchiveService {
	public JsonFileUploadToArchiveValidationContainer getFileUploadValidationContainer(String utfPayload);
	
}
