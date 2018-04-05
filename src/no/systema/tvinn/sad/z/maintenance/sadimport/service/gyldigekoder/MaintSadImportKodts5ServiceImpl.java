/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts5Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 * 
 */
public class MaintSadImportKodts5ServiceImpl implements MaintSadImportKodts5Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts5Container getList(String utfPayload) {
		JsonMaintSadImportKodts5Container container = null;
		try{
			MaintSadImportKodts5Mapper mapper = new MaintSadImportKodts5Mapper();
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
	public JsonMaintSadImportKodts5Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts5Container container = null;
		try{
			MaintSadImportKodts5Mapper mapper = new MaintSadImportKodts5Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
