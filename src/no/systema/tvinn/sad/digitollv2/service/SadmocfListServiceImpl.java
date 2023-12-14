/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmocfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfContainer;

/**
 * @author oscardelatorre
 * @date Dec 2023
 */
public class SadmocfListServiceImpl implements SadmocfListService {

	public SadmocfContainer getListContainer(String utfPayload) {
		SadmocfContainer container = null;
		try{
			SadmocfMapper mapper = new SadmocfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
