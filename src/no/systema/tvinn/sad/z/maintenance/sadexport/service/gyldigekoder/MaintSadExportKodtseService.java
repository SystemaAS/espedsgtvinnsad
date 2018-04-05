/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 12, 2016
 * 
 *
 */
public interface MaintSadExportKodtseService {
	public JsonMaintSadExportKodtseContainer getList(String utfPayload);
	public JsonMaintSadExportKodtseContainer doUpdate(String utfPayload);
}
