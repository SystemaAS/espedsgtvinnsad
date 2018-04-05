/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.mapper.MaintMainKodtaMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 * 
 */
public class MaintMainKodtaServiceImpl implements MaintMainKodtaService {
	/**
	 * 
	 */
	public JsonMaintMainKodtaContainer getList(String utfPayload) {
		JsonMaintMainKodtaContainer container = null;
		try{
			MaintMainKodtaMapper mapper = new MaintMainKodtaMapper();
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
	public JsonMaintMainKodtaContainer doUpdate(String utfPayload) {
		JsonMaintMainKodtaContainer container = null;
		try{
			MaintMainKodtaMapper mapper = new MaintMainKodtaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
