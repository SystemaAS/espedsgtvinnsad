/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 31, 2016
 * 
 *
 */
public interface MaintSadSadlService {
	public JsonMaintSadSadlContainer getList(String utfPayload);
	public JsonMaintSadSadlContainer doUpdate(String utfPayload);
	
}
