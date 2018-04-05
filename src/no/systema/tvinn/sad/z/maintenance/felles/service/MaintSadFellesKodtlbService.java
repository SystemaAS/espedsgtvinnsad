/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 *
 */
public interface MaintSadFellesKodtlbService {
	public JsonMaintSadFellesKodtlbContainer getList(String utfPayload);
	public JsonMaintSadFellesKodtlbContainer doUpdate(String utfPayload);
	
}
