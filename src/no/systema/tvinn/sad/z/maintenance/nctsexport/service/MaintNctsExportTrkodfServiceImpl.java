/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.service;

import no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable.MaintNctsExportTransitKodeTypeMapper;
import no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable.MaintNctsExportTrkodfMapper;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 * 
 */
public class MaintNctsExportTrkodfServiceImpl implements MaintNctsExportTrkodfService {

	@Override
	public JsonMaintNctsTrkodfContainer getList(String utfPayload) {
		JsonMaintNctsTrkodfContainer container = null;
		try{
			MaintNctsExportTrkodfMapper mapper = new MaintNctsExportTrkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	@Override
	public JsonMaintNctsTrkodfContainer doUpdate(String utfPayload) {
		JsonMaintNctsTrkodfContainer container = null;
		try{
			MaintNctsExportTrkodfMapper mapper = new MaintNctsExportTrkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	@Override
	public JsonMaintNctsTransitKodeTypeContainer getTransitKoderList(String utfPayload) {
		JsonMaintNctsTransitKodeTypeContainer container = null;
		try{
			MaintNctsExportTransitKodeTypeMapper mapper = new MaintNctsExportTransitKodeTypeMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}

}
