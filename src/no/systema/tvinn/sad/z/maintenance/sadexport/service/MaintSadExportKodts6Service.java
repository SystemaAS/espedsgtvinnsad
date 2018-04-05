/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Container;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 7, 2016
 * 
 *
 */
public interface MaintSadExportKodts6Service {
	public JsonMaintSadExportKodts6Container getList(String utfPayload);
	public JsonMaintSadExportKodts6Container doUpdate(String utfPayload);
}
