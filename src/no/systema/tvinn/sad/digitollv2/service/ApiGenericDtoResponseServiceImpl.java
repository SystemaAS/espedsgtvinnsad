/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.ApiGenericDtoResponseMapper;
import no.systema.tvinn.sad.digitollv2.mapper.jsonjackson.GeneralUpdateMapper;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;

/**
 * @author oscardelatorre
 * @date Aug 2023
 */
public class ApiGenericDtoResponseServiceImpl implements ApiGenericDtoResponseService {

	public ApiGenericDtoResponse getReponse(String utfPayload) {
		ApiGenericDtoResponse response = null;
		try{
			ApiGenericDtoResponseMapper mapper = new ApiGenericDtoResponseMapper();
			response = mapper.getResponse(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return response;
		
	}
	
	
	

}
