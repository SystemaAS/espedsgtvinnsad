/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sad.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadKodtsaMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadKodtsaServiceImpl implements MaintSadKodtsaService {
	/**
	 * 
	 */
	public JsonMaintSadKodtsaContainer getList(String utfPayload) {
		JsonMaintSadKodtsaContainer container = null;
		try{
			MaintSadKodtsaMapper mapper = new MaintSadKodtsaMapper();
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
	public JsonMaintSadKodtsaContainer doUpdate(String utfPayload) {
		JsonMaintSadKodtsaContainer container = null;
		try{
			MaintSadKodtsaMapper mapper = new MaintSadKodtsaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
