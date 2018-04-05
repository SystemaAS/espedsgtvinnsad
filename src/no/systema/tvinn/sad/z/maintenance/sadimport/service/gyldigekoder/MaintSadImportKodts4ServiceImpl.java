/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts4Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadImportKodts4ServiceImpl implements MaintSadImportKodts4Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts4Container getList(String utfPayload) {
		JsonMaintSadImportKodts4Container container = null;
		try{
			MaintSadImportKodts4Mapper mapper = new MaintSadImportKodts4Mapper();
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
	public JsonMaintSadImportKodts4Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts4Container container = null;
		try{
			MaintSadImportKodts4Mapper mapper = new MaintSadImportKodts4Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
