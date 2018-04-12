/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.service;

import no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable.MaintNctsExportTrughMapper;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;
import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainCundfMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 * 
 */
public class MaintNctsExportTrughServiceImpl implements MaintNctsExportTrughService {

	@Override
	public JsonMaintNctsTrughContainer getList(String utfPayload) {
		JsonMaintNctsTrughContainer container = null;
		try{
			MaintNctsExportTrughMapper mapper = new MaintNctsExportTrughMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	@Override
	public JsonMaintNctsTrughContainer doUpdate(String utfPayload) {
		JsonMaintNctsTrughContainer container = null;
		try{
			MaintNctsExportTrughMapper mapper = new MaintNctsExportTrughMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	@Override
	public JsonMaintMainCundfContainer getCustomer(String utfPayload) {
		JsonMaintMainCundfContainer container =  null;
		
		try {
			MaintMainCundfMapper mapper = new MaintMainCundfMapper();
			container = mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return container;
	}

}
