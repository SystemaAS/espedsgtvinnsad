/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadImportKodtsbService {
	public JsonMaintSadImportKodtsbContainer getList(String utfPayload);
	public JsonMaintSadImportKodtsbContainer doUpdate(String utfPayload);
	
}
