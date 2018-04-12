/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 *
 */
public interface MaintMainKodtaService {
	public JsonMaintMainKodtaContainer getList(String utfPayload);
	public JsonMaintMainKodtaContainer doUpdate(String utfPayload);
	
}
