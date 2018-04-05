/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 *
 */
public interface MaintSadImportSadhHeadfService {
	public JsonMaintSadImportSadhHeadfContainer getList(String utfPayload);
	public JsonMaintSadImportSadhHeadfContainer doUpdate(String utfPayload);
	
}
