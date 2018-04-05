/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadKodtsaService {
	public JsonMaintSadKodtsaContainer getList(String utfPayload);
	public JsonMaintSadKodtsaContainer doUpdate(String utfPayload);
	
}
