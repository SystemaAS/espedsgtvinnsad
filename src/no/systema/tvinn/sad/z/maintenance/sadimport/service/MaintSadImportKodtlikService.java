/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 31, 2016
 * 
 *
 */
public interface MaintSadImportKodtlikService {
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload);
	public JsonMaintSadImportKodtlikContainer doUpdate(String utfPayload);
	
}
