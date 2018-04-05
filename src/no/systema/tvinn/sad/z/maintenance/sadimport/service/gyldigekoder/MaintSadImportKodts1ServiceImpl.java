/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts1Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts1Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadImportKodts1ServiceImpl implements MaintSadImportKodts1Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts1Container getList(String utfPayload) {
		JsonMaintSadImportKodts1Container container = null;
		try{
			MaintSadImportKodts1Mapper mapper = new MaintSadImportKodts1Mapper();
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
	public JsonMaintSadImportKodts1Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts1Container container = null;
		try{
			MaintSadImportKodts1Mapper mapper = new MaintSadImportKodts1Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
