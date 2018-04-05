/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 *
 */
public interface MaintSadFellesSoktariService {
	public JsonMaintSadFellesSoktariContainer getList(String utfPayload);
	public JsonMaintSadFellesSoktariContainer doUpdate(String utfPayload);
	
}
