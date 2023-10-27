/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadAvdSignMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadAvdSignContainer;

/**
 * @author oscardelatorre
 * @date Oct 2023
 */
public class SadAvdSignServiceImpl implements SadAvdSignService {

	public SadAvdSignContainer getListContainer(String utfPayload) {
		SadAvdSignContainer container = null;
		try{
			SadAvdSignMapper mapper = new SadAvdSignMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
