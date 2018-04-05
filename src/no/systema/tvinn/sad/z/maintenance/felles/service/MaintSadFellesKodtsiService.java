/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 20, 2016
 * 
 *
 */
public interface MaintSadFellesKodtsiService {
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload);
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload);
	
}
