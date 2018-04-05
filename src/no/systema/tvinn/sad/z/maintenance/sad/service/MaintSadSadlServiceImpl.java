/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service;

import no.systema.tvinn.sad.z.maintenance.sad.mapper.jsonjackson.dbtable.MaintSadSadlMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 * 
 */
public class MaintSadSadlServiceImpl implements MaintSadSadlService {
	/**
	 * 
	 */
	public JsonMaintSadSadlContainer getList(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
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
	public JsonMaintSadSadlContainer doUpdate(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
