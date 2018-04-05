/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 *
 */
public interface MaintSadImportSadvareService {
	public JsonMaintSadImportSadvareContainer getList(String utfPayload);
	public JsonMaintSadImportSadvareContainer doUpdate(String utfPayload);
	
}
