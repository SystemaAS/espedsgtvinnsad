/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts3Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadImportKodts3ServiceImpl implements MaintSadImportKodts3Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts3Container getList(String utfPayload) {
		JsonMaintSadImportKodts3Container container = null;
		try{
			MaintSadImportKodts3Mapper mapper = new MaintSadImportKodts3Mapper();
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
	public JsonMaintSadImportKodts3Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts3Container container = null;
		try{
			MaintSadImportKodts3Mapper mapper = new MaintSadImportKodts3Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
