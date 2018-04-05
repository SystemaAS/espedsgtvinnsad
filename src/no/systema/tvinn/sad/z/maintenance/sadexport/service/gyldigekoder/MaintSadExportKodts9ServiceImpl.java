/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodts9Container;
import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadExportKodts9Mapper;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 * 
 */
public class MaintSadExportKodts9ServiceImpl implements MaintSadExportKodts9Service {
	/**
	 * 
	 */
	public JsonMaintSadExportKodts9Container getList(String utfPayload) {
		JsonMaintSadExportKodts9Container container = null;
		try{
			MaintSadExportKodts9Mapper mapper = new MaintSadExportKodts9Mapper();
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
	public JsonMaintSadExportKodts9Container doUpdate(String utfPayload) {
		JsonMaintSadExportKodts9Container container = null;
		try{
			MaintSadExportKodts9Mapper mapper = new MaintSadExportKodts9Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
