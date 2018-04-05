/**
 * 
 */
package no.systema.tvinn.sad.admin.service;

import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminTransportListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Maj 26, 2014
 * 
 */
public interface SadAdminTransportService {
	public JsonSadAdminTransportListContainer getSadAdminTransportListContainer(String utfPayload);
	
}
