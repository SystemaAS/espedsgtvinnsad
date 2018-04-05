/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 *
 */
public interface MaintSadKodtsoService {
	public JsonMaintSadKodtsoContainer getList(String utfPayload);
	public JsonMaintSadKodtsoContainer doUpdate(String utfPayload);
	
}
