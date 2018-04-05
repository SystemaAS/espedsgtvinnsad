/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Container;

/**
 * 
 * @author oscardelatorre
 * @date May 25, 2016
 * 
 *
 */
public interface MaintSadImportKodts2Service {
	public JsonMaintSadImportKodts2Container getList(String utfPayload);
	public JsonMaintSadImportKodts2Container doUpdate(String utfPayload);
	
}
