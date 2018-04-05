/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSadsdMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 * 
 */
public class MaintSadImportSadsdServiceImpl implements MaintSadImportSadsdService {
	/**
	 * 
	 */
	public JsonMaintSadImportSadsdContainer getList(String utfPayload) {
		JsonMaintSadImportSadsdContainer container = null;
		try{
			MaintSadImportSadsdMapper mapper = new MaintSadImportSadsdMapper();
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
	public JsonMaintSadImportSadsdContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSadsdContainer container = null;
		try{
			MaintSadImportSadsdMapper mapper = new MaintSadImportSadsdMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
