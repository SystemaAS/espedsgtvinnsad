/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.ZadmoattfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfContainer;


/**
 * @author oscardelatorre
 * @date Mar 2025
 */
public class ZadmoattfListServiceImpl implements ZadmoattfListService {

	public ZadmoattfContainer getListContainer(String utfPayload) {
		ZadmoattfContainer container = null;
		try{
			ZadmoattfMapper mapper = new ZadmoattfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
