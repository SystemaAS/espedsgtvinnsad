/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 *
 */
public interface MaintSadFellesTariService {
	public JsonMaintSadFellesTariContainer getList(String utfPayload);
	public JsonMaintSadFellesTariContainer doUpdate(String utfPayload);
	
}
