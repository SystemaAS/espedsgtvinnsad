/**
 * 
 */
package no.systema.tvinn.sad.admin.service;

import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminTransportListContainer;
import no.systema.tvinn.sad.admin.mapper.jsonjackson.SadAdminTransportListMapper;

/**
 * @author oscardelatorre
 * @date May 26, 2014
 * 
 */
public class SadAdminTransportServiceImpl implements SadAdminTransportService {
	
	/**
	 * Transportuppdrag
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSadAdminTransportListContainer getSadAdminTransportListContainer(String utfPayload){
		JsonSadAdminTransportListContainer container = null;
		try{
			SadAdminTransportListMapper mapper = new SadAdminTransportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
}
