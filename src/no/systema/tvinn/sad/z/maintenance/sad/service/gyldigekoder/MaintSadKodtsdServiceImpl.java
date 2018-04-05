/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadKodtsdMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadKodtsdServiceImpl implements MaintSadKodtsdService {
	/**
	 * 
	 */
	public JsonMaintSadKodtsdContainer getList(String utfPayload) {
		JsonMaintSadKodtsdContainer container = null;
		try{
			MaintSadKodtsdMapper mapper = new MaintSadKodtsdMapper();
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
	public JsonMaintSadKodtsdContainer doUpdate(String utfPayload) {
		JsonMaintSadKodtsdContainer container = null;
		try{
			MaintSadKodtsdMapper mapper = new MaintSadKodtsdMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
