/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadOppdragMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;

/**
 * @author oscardelatorre
 * @date Sep 2023
 */
public class SadOppdragServiceImpl implements SadOppdragService {

	public SadOppdragContainer getListContainer(String utfPayload) {
		SadOppdragContainer container = null;
		try{
			SadOppdragMapper mapper = new SadOppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
