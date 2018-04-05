/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtsbMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodtsbServiceImpl implements MaintSadImportKodtsbService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtsbContainer getList(String utfPayload) {
		JsonMaintSadImportKodtsbContainer container = null;
		try{
			MaintSadImportKodtsbMapper mapper = new MaintSadImportKodtsbMapper();
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
	public JsonMaintSadImportKodtsbContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtsbContainer container = null;
		try{
			MaintSadImportKodtsbMapper mapper = new MaintSadImportKodtsbMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
