/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.ZadmohfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmohfContainer;


/**
 * @author oscardelatorre
 * @date Maj 2024
 */
public class ZadmohfListServiceImpl implements ZadmohfListService {

	public ZadmohfContainer getListContainer(String utfPayload) {
		ZadmohfContainer container = null;
		try{
			ZadmohfMapper mapper = new ZadmohfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
