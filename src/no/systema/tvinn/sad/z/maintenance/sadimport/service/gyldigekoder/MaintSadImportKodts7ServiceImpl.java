/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts7Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts7Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodts7ServiceImpl implements MaintSadImportKodts7Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts7Container getList(String utfPayload) {
		JsonMaintSadImportKodts7Container container = null;
		try{
			MaintSadImportKodts7Mapper mapper = new MaintSadImportKodts7Mapper();
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
	public JsonMaintSadImportKodts7Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts7Container container = null;
		try{
			MaintSadImportKodts7Mapper mapper = new MaintSadImportKodts7Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
