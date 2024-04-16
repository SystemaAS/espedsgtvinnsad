/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmolffMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolffContainer;

/**
 * @author oscardelatorre
 * @date Dec 2023
 */
public class SadmolffListServiceImpl implements SadmolffListService {

	public SadmolffContainer getListContainer(String utfPayload) {
		SadmolffContainer container = null;
		try{
			SadmolffMapper mapper = new SadmolffMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
