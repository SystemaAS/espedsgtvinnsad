/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSadhHeadfMapper;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 * 
 */
public class MaintSadImportSadhHeadfServiceImpl implements MaintSadImportSadhHeadfService {
	/**
	 * 
	 */
	public JsonMaintSadImportSadhHeadfContainer getList(String utfPayload) {
		JsonMaintSadImportSadhHeadfContainer container = null;
		try{
			MaintSadImportSadhHeadfMapper mapper = new MaintSadImportSadhHeadfMapper();
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
	public JsonMaintSadImportSadhHeadfContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSadhHeadfContainer container = null;
		try{
			MaintSadImportSadhHeadfMapper mapper = new MaintSadImportSadhHeadfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
