/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.service;

import no.systema.tvinn.sad.z.maintenance.main.mapper.jsonjackson.dbtable.MaintKodtvaMapper;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 * 
 */
public class MaintKodtvaServiceImpl implements MaintKodtvaService {
	/**
	 * 
	 */
	public JsonMaintKodtvaContainer getList(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
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
	public JsonMaintKodtvaContainer doUpdate(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
