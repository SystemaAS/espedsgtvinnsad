/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.JsonTrorOrderListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 *
 */
public interface TrorMainOrderListService {
	public JsonTrorOrderListContainer getMainListContainer(String utfPayload);

}
