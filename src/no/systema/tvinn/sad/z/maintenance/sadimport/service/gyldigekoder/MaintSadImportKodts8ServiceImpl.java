/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts8Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 * 
 */
public class MaintSadImportKodts8ServiceImpl implements MaintSadImportKodts8Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts8Container getList(String utfPayload) {
		JsonMaintSadImportKodts8Container container = null;
		try{
			MaintSadImportKodts8Mapper mapper = new MaintSadImportKodts8Mapper();
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
	public JsonMaintSadImportKodts8Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts8Container container = null;
		try{
			MaintSadImportKodts8Mapper mapper = new MaintSadImportKodts8Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
