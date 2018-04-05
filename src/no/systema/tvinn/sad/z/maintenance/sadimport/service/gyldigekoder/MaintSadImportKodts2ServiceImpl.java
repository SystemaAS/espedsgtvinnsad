/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts2Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadImportKodts2ServiceImpl implements MaintSadImportKodts2Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts2Container getList(String utfPayload) {
		JsonMaintSadImportKodts2Container container = null;
		try{
			MaintSadImportKodts2Mapper mapper = new MaintSadImportKodts2Mapper();
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
	public JsonMaintSadImportKodts2Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts2Container container = null;
		try{
			MaintSadImportKodts2Mapper mapper = new MaintSadImportKodts2Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
