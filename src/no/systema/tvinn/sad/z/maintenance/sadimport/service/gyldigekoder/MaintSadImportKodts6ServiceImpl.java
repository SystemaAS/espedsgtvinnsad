/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts6Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts6Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 * 
 */
public class MaintSadImportKodts6ServiceImpl implements MaintSadImportKodts6Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts6Container getList(String utfPayload) {
		JsonMaintSadImportKodts6Container container = null;
		try{
			MaintSadImportKodts6Mapper mapper = new MaintSadImportKodts6Mapper();
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
	public JsonMaintSadImportKodts6Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts6Container container = null;
		try{
			MaintSadImportKodts6Mapper mapper = new MaintSadImportKodts6Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
