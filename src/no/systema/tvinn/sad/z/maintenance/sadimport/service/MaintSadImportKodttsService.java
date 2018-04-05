/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 *
 */
public interface MaintSadImportKodttsService {
	public JsonMaintSadImportKodttsContainer getList(String utfPayload);
	public JsonMaintSadImportKodttsContainer doUpdate(String utfPayload);
	
}
