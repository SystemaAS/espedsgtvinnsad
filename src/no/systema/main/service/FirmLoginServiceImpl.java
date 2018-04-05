/**
 * 
 */
package no.systema.main.service;

import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
import no.systema.main.mapper.jsonjackson.FirmLoginMapper;

/**
 * 
 * @author oscardelatorre
 * Jan 27, 2017
 * 
 */
public class FirmLoginServiceImpl implements FirmLoginService{
	public JsonFirmLoginContainer getContainer(String utfPayload) {
		JsonFirmLoginContainer container = null;
		try{
			FirmLoginMapper mapper = new FirmLoginMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
