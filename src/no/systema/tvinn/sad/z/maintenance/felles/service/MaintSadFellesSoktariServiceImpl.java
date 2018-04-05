/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable.MaintSadFellesSoktariMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 25, 2016
 * 
 * 
 */
public class MaintSadFellesSoktariServiceImpl implements MaintSadFellesSoktariService {
	/**
	 * 
	 */
	public JsonMaintSadFellesSoktariContainer getList(String utfPayload) {
		JsonMaintSadFellesSoktariContainer container = null;
		try{
			MaintSadFellesSoktariMapper mapper = new MaintSadFellesSoktariMapper();
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
	public JsonMaintSadFellesSoktariContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesSoktariContainer container = null;
		try{
			MaintSadFellesSoktariMapper mapper = new MaintSadFellesSoktariMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
