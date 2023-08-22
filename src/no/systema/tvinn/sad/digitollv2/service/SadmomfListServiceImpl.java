/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmomfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;


/**
 * @author oscardelatorre
 * @date Sep 2020
 */
public class SadmomfListServiceImpl implements SadmomfListService {

	public SadmomfContainer getListContainer(String utfPayload) {
		SadmomfContainer container = null;
		try{
			SadmomfMapper mapper = new SadmomfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
