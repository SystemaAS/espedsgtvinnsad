/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 *
 */
public interface MaintSadExportSaehService {
	public JsonMaintSadExportSaehContainer getList(String utfPayload);
	public JsonMaintSadExportSaehContainer doUpdate(String utfPayload);
	
}
