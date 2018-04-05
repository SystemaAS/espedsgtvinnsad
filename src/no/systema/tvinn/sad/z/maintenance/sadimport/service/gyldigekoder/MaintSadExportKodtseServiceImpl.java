/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadExportKodtseMapper;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2017
 * 
 * 
 */
public class MaintSadExportKodtseServiceImpl implements MaintSadExportKodtseService {
	/**
	 * 
	 */
	public JsonMaintSadExportKodtseContainer getList(String utfPayload) {
		JsonMaintSadExportKodtseContainer container = null;
		try{
			MaintSadExportKodtseMapper mapper = new MaintSadExportKodtseMapper();
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
	public JsonMaintSadExportKodtseContainer doUpdate(String utfPayload) {
		JsonMaintSadExportKodtseContainer container = null;
		try{
			MaintSadExportKodtseMapper mapper = new MaintSadExportKodtseMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
