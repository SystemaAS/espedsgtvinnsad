/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public interface MaintSadImportKodts3Service {
	public JsonMaintSadImportKodts3Container getList(String utfPayload);
	public JsonMaintSadImportKodts3Container doUpdate(String utfPayload);
	
}
