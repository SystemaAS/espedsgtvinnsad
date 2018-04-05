/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 *
 */
public interface MaintSadImportSadsdService {
	public JsonMaintSadImportSadsdContainer getList(String utfPayload);
	public JsonMaintSadImportSadsdContainer doUpdate(String utfPayload);
	
}
