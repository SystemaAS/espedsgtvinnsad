/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable.MaintSadFellesKodtsiMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 20, 2016
 * 
 * 
 */
public class MaintSadFellesKodtsiServiceImpl implements MaintSadFellesKodtsiService {
	/**
	 * 
	 */
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
		try{
			MaintSadFellesKodtsiMapper mapper = new MaintSadFellesKodtsiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
		try{
			MaintSadFellesKodtsiMapper mapper = new MaintSadFellesKodtsiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
