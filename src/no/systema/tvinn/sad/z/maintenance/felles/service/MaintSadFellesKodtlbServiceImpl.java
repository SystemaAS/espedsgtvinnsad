/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable.MaintSadFellesKodtlbMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 * 
 */
public class MaintSadFellesKodtlbServiceImpl implements MaintSadFellesKodtlbService {
	/**
	 * 
	 */
	public JsonMaintSadFellesKodtlbContainer getList(String utfPayload) {
		JsonMaintSadFellesKodtlbContainer container = null;
		try{
			MaintSadFellesKodtlbMapper mapper = new MaintSadFellesKodtlbMapper();
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
	public JsonMaintSadFellesKodtlbContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesKodtlbContainer container = null;
		try{
			MaintSadFellesKodtlbMapper mapper = new MaintSadFellesKodtlbMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
