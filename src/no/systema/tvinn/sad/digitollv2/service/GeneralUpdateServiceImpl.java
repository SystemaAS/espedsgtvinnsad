/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.GeneralUpdateMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;

/**
 * @author oscardelatorre
 * @date Aug 2023
 */
public class GeneralUpdateServiceImpl implements GeneralUpdateService {

	public GeneralUpdateContainer getListContainer(String utfPayload) {
		GeneralUpdateContainer container = null;
		try{
			GeneralUpdateMapper mapper = new GeneralUpdateMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	

}
