/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts1Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public interface MaintSadImportKodts1Service {
	public JsonMaintSadImportKodts1Container getList(String utfPayload);
	public JsonMaintSadImportKodts1Container doUpdate(String utfPayload);
	
}
