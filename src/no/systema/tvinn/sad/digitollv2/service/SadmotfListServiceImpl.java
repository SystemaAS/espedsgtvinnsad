/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.EoriValidationMapper;
import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.SadmotfMapper;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.EoriValidationContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;

/**
 * @author oscardelatorre
 * @date Aug 2023
 */
public class SadmotfListServiceImpl implements SadmotfListService {

	public SadmotfContainer getListContainer(String utfPayload) {
		SadmotfContainer container = null;
		try{
			SadmotfMapper mapper = new SadmotfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	public EoriValidationContainer getListContainerEORIValidation(String utfPayload) {
		EoriValidationContainer container = null;
		try{
			EoriValidationMapper mapper = new EoriValidationMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
	

}
