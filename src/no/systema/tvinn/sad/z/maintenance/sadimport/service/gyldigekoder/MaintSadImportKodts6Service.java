/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts6Container;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 *
 */
public interface MaintSadImportKodts6Service {
	public JsonMaintSadImportKodts6Container getList(String utfPayload);
	public JsonMaintSadImportKodts6Container doUpdate(String utfPayload);
	
}
