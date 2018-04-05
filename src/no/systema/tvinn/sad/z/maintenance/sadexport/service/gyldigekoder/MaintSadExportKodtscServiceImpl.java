/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtscContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadExportKodtscMapper;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 * 
 */
public class MaintSadExportKodtscServiceImpl implements MaintSadExportKodtscService {
	/**
	 * 
	 */
	public JsonMaintSadExportKodtscContainer getList(String utfPayload) {
		JsonMaintSadExportKodtscContainer container = null;
		try{
			MaintSadExportKodtscMapper mapper = new MaintSadExportKodtscMapper();
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
	public JsonMaintSadExportKodtscContainer doUpdate(String utfPayload) {
		JsonMaintSadExportKodtscContainer container = null;
		try{
			MaintSadExportKodtscMapper mapper = new MaintSadExportKodtscMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
