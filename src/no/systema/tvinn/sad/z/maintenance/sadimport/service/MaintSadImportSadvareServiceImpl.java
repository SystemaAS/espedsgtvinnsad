/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSadvareMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 * 
 */
public class MaintSadImportSadvareServiceImpl implements MaintSadImportSadvareService {
	/**
	 * 
	 */
	public JsonMaintSadImportSadvareContainer getList(String utfPayload) {
		JsonMaintSadImportSadvareContainer container = null;
		try{
			MaintSadImportSadvareMapper mapper = new MaintSadImportSadvareMapper();
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
	public JsonMaintSadImportSadvareContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSadvareContainer container = null;
		try{
			MaintSadImportSadvareMapper mapper = new MaintSadImportSadvareMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
