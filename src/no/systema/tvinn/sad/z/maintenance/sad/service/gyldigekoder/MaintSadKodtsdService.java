/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadKodtsdService {
	public JsonMaintSadKodtsdContainer getList(String utfPayload);
	public JsonMaintSadKodtsdContainer doUpdate(String utfPayload);
	
}
