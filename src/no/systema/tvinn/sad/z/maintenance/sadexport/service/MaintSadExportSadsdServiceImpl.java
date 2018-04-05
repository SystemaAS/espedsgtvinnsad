/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSadsdMapper;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 12, 2016
 * 
 * 
 */
public class MaintSadExportSadsdServiceImpl implements MaintSadExportSadsdService {
	/**
	 * 
	 */
	//TODO move container to sad package
	public JsonMaintSadImportSadsdContainer getList(String utfPayload) {
		JsonMaintSadImportSadsdContainer container = null;
		try{
			MaintSadImportSadsdMapper mapper = new MaintSadImportSadsdMapper();
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
	//TODO move container to sad packages
	public JsonMaintSadImportSadsdContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSadsdContainer container = null;
		try{
			MaintSadImportSadsdMapper mapper = new MaintSadImportSadsdMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
