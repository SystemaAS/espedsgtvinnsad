/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmodoclogMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmodoclgContainer;

/**
 * @author oscardelatorre
 * @date Feb 2025
 */
public class SadmodoclgListServiceImpl implements SadmodoclgListService {

	public SadmodoclgContainer getListContainer(String utfPayload) {
		SadmodoclgContainer container = null;
		try{
			SadmodoclogMapper mapper = new SadmodoclogMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
