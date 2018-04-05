/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 9, 2016
 * 
 *
 */
public interface MaintSadExportTvineService {
	public JsonMaintSadExportTvineContainer getList(String utfPayload);
	public JsonMaintSadExportTvineContainer doUpdate(String utfPayload);
}
