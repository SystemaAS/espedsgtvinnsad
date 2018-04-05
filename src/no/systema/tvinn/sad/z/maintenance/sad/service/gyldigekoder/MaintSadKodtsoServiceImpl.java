/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadKodtsoMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadKodtsoServiceImpl implements MaintSadKodtsoService {
	/**
	 * 
	 */
	public JsonMaintSadKodtsoContainer getList(String utfPayload) {
		JsonMaintSadKodtsoContainer container = null;
		try{
			MaintSadKodtsoMapper mapper = new MaintSadKodtsoMapper();
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
	public JsonMaintSadKodtsoContainer doUpdate(String utfPayload) {
		JsonMaintSadKodtsoContainer container = null;
		try{
			MaintSadKodtsoMapper mapper = new MaintSadKodtsoMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
