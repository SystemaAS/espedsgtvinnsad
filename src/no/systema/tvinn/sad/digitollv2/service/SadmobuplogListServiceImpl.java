/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmobuplogMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmobuplogContainer;

/**
 * @author oscardelatorre
 * @date Sep 2024
 */
public class SadmobuplogListServiceImpl implements SadmobuplogListService {

	public SadmobuplogContainer getListContainer(String utfPayload) {
		SadmobuplogContainer container = null;
		try{
			SadmobuplogMapper mapper = new SadmobuplogMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
