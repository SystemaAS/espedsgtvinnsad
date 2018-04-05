/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtscContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 *
 */
public interface MaintSadExportKodtscService {
	public JsonMaintSadExportKodtscContainer getList(String utfPayload);
	public JsonMaintSadExportKodtscContainer doUpdate(String utfPayload);
	
}
