/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 *
 */
public interface MaintSadImportCundfLikvKodeService {
	public JsonMaintSadImportCundfLikvKodeContainer getList(String utfPayload);
	public JsonMaintSadImportCundfLikvKodeContainer doUpdate(String utfPayload);
	
}
