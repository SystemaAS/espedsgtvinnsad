/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable.MaintSadFellesTariMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 * 
 */
public class MaintSadFellesTariServiceImpl implements MaintSadFellesTariService {
	/**
	 * 
	 */
	public JsonMaintSadFellesTariContainer getList(String utfPayload) {
		JsonMaintSadFellesTariContainer container = null;
		try{
			MaintSadFellesTariMapper mapper = new MaintSadFellesTariMapper();
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
	public JsonMaintSadFellesTariContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesTariContainer container = null;
		try{
			MaintSadFellesTariMapper mapper = new MaintSadFellesTariMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
