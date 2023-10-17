/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmoafMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;


/**
 * @author oscardelatorre
 * @date Oct 2023
 */
public class SadmoafListServiceImpl implements SadmoafListService {

	public SadmoafContainer getListContainer(String utfPayload) {
		SadmoafContainer container = null;
		try{
			SadmoafMapper mapper = new SadmoafMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
