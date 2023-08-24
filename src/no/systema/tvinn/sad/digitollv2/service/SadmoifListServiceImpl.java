/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmohfMapper;
import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmoifMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;


/**
 * @author oscardelatorre
 * @date Sep 2020
 */
public class SadmoifListServiceImpl implements SadmoifListService {

	public SadmoifContainer getListContainer(String utfPayload) {
		SadmoifContainer container = null;
		try{
			SadmoifMapper mapper = new SadmoifMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
