/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodts9Container;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 *
 */
public interface MaintSadExportKodts9Service {
	public JsonMaintSadExportKodts9Container getList(String utfPayload);
	public JsonMaintSadExportKodts9Container doUpdate(String utfPayload);
	
}
