/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.MaintSadExportTvineMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 9, 2016
 * 
 * 
 */
public class MaintSadExportTvineServiceImpl implements MaintSadExportTvineService {
	/**
	 * 
	 */
	public JsonMaintSadExportTvineContainer getList(String utfPayload) {
		JsonMaintSadExportTvineContainer container = null;
		try{
			MaintSadExportTvineMapper mapper = new MaintSadExportTvineMapper();
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
	public JsonMaintSadExportTvineContainer doUpdate(String utfPayload) {
		JsonMaintSadExportTvineContainer container = null;
		try{
			MaintSadExportTvineMapper mapper = new MaintSadExportTvineMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
