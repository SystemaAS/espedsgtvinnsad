/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Container;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 *
 */
public interface MaintSadImportKodts5Service {
	public JsonMaintSadImportKodts5Container getList(String utfPayload);
	public JsonMaintSadImportKodts5Container doUpdate(String utfPayload);
	
}
