/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.MaintSadExportSadavgeMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 16, 2016
 * 
 * 
 */
public class MaintSadExportSadavgeServiceImpl implements MaintSadExportSadavgeService {
	/**
	 * 
	 */
	public JsonMaintSadExportSadavgeContainer getList(String utfPayload) {
		JsonMaintSadExportSadavgeContainer container = null;
		try{
			MaintSadExportSadavgeMapper mapper = new MaintSadExportSadavgeMapper();
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
	public JsonMaintSadExportSadavgeContainer doUpdate(String utfPayload) {
		JsonMaintSadExportSadavgeContainer container = null;
		try{
			MaintSadExportSadavgeMapper mapper = new MaintSadExportSadavgeMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
