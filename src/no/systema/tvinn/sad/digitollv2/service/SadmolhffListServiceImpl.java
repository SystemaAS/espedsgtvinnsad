/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmolhffMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolhffContainer;

/**
 * @author oscardelatorre
 * @date Dec 2023
 */
public class SadmolhffListServiceImpl implements SadmolhffListService {

	public SadmolhffContainer getListContainer(String utfPayload) {
		SadmolhffContainer container = null;
		try{
			SadmolhffMapper mapper = new SadmolhffMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
