/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 12, 2016
 * 
 *
 */
public interface MaintSadExportSadsdService {
	//TODO move container to sad package
	public JsonMaintSadImportSadsdContainer getList(String utfPayload);
	public JsonMaintSadImportSadsdContainer doUpdate(String utfPayload);
}
