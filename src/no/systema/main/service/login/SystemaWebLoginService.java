/**
 * 
 */
package no.systema.main.service.login;

import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;

/**
 * @author oscardelatorre
 * @date Feb 16, 2013
 *
 */
public interface SystemaWebLoginService {
	public JsonSystemaUserContainer getSystemaUserContainer(String utfPayload);
	public JsonSystemaUserContainer getSystemaUserContainerForPassword(String utfPayload);
	
}
