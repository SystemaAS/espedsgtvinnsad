/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmohfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;


/**
 * @author oscardelatorre
 * @date Sep 2020
 */
public class SadmohfListServiceImpl implements SadmohfListService {

	public SadmohfContainer getListContainer(String utfPayload) {
		SadmohfContainer container = null;
		try{
			SadmohfMapper mapper = new SadmohfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
