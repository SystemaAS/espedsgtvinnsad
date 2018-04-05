/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportCundfLikvKodeMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 * 
 */
public class MaintSadImportCundfLikvKodeServiceImpl implements MaintSadImportCundfLikvKodeService {
	/**
	 * 
	 */
	public JsonMaintSadImportCundfLikvKodeContainer getList(String utfPayload) {
		JsonMaintSadImportCundfLikvKodeContainer container = null;
		try{
			MaintSadImportCundfLikvKodeMapper mapper = new MaintSadImportCundfLikvKodeMapper();
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
	public JsonMaintSadImportCundfLikvKodeContainer doUpdate(String utfPayload) {
		JsonMaintSadImportCundfLikvKodeContainer container = null;
		try{
			MaintSadImportCundfLikvKodeMapper mapper = new MaintSadImportCundfLikvKodeMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
