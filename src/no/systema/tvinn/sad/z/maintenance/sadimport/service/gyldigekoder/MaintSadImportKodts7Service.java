/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts7Container;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadImportKodts7Service {
	public JsonMaintSadImportKodts7Container getList(String utfPayload);
	public JsonMaintSadImportKodts7Container doUpdate(String utfPayload);
	
}
