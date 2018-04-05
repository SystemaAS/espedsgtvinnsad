/**
 * 
 */
package no.systema.main.service.general.notisblock;

import org.springframework.core.io.Resource;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 * Jan 17, 2015
 */
public interface NotisblockService {
	public JsonNotisblockContainer getNotisblockListContainer(String utfPayload);
	
}
