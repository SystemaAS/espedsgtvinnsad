/**
 * 
 */
package no.systema.main.service;

import org.springframework.core.io.Resource;
import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 *
 */
public interface FirmLoginService {
	public JsonFirmLoginContainer getContainer(String utfPayload);
	
}
