/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadTurMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;

/**
 * @author oscardelatorre
 * @date Sep 2023
 */
public class SadTurServiceImpl implements SadTurService {

	public SadTurContainer getListContainer(String utfPayload) {
		SadTurContainer container = null;
		try{
			SadTurMapper mapper = new SadTurMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
