/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodttsMapper;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 * 
 */
public class MaintSadImportKodttsServiceImpl implements MaintSadImportKodttsService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodttsContainer getList(String utfPayload) {
		JsonMaintSadImportKodttsContainer container = null;
		try{
			MaintSadImportKodttsMapper mapper = new MaintSadImportKodttsMapper();
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
	public JsonMaintSadImportKodttsContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodttsContainer container = null;
		try{
			MaintSadImportKodttsMapper mapper = new MaintSadImportKodttsMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
