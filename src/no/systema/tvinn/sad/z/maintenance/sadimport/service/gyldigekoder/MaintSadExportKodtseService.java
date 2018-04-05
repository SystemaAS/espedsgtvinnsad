/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadExportKodtseService {
	public JsonMaintSadExportKodtseContainer getList(String utfPayload);
	public JsonMaintSadExportKodtseContainer doUpdate(String utfPayload);
	
}
