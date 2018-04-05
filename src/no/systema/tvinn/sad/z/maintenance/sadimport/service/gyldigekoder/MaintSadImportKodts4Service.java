/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public interface MaintSadImportKodts4Service {
	public JsonMaintSadImportKodts4Container getList(String utfPayload);
	public JsonMaintSadImportKodts4Container doUpdate(String utfPayload);
	
}
