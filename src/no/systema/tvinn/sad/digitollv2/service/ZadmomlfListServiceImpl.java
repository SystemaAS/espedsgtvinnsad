/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.ZadmomlfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfContainer;

/**
 * @author oscardelatorre
 * @date Apr 2024
 */
public class ZadmomlfListServiceImpl implements ZadmomlfListService {

	public ZadmomlfContainer getListContainer(String utfPayload) {
		ZadmomlfContainer container = null;
		try{
			ZadmomlfMapper mapper = new ZadmomlfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
