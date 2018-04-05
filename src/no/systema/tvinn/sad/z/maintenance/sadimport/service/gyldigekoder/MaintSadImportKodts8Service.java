/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Container;

/**
 * 
 * @author oscardelatorre
 * @date May 13, 2016
 * 
 *
 */
public interface MaintSadImportKodts8Service {
	public JsonMaintSadImportKodts8Container getList(String utfPayload);
	public JsonMaintSadImportKodts8Container doUpdate(String utfPayload);
	
}
