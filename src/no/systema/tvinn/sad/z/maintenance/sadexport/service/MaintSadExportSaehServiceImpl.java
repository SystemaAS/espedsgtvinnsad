/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.MaintSadExportSaehMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 * 
 */
public class MaintSadExportSaehServiceImpl implements MaintSadExportSaehService {
	/**
	 * 
	 */
	public JsonMaintSadExportSaehContainer getList(String utfPayload) {
		JsonMaintSadExportSaehContainer container = null;
		try{
			MaintSadExportSaehMapper mapper = new MaintSadExportSaehMapper();
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
	public JsonMaintSadExportSaehContainer doUpdate(String utfPayload) {
		JsonMaintSadExportSaehContainer container = null;
		try{
			MaintSadExportSaehMapper mapper = new MaintSadExportSaehMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
