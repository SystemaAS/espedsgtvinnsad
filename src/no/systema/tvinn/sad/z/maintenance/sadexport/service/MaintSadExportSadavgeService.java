/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 16, 2016
 * 
 *
 */
public interface MaintSadExportSadavgeService {
	public JsonMaintSadExportSadavgeContainer getList(String utfPayload);
	public JsonMaintSadExportSadavgeContainer doUpdate(String utfPayload);
}
