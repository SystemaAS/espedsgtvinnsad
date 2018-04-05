/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.mapper;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;

/**
 * @author Fredrik Möller
 * @date Sep 26, 2016
 * 
 */
public class BrregEnhetsRegisteretMapper {
	
	public JsonEnhetsRegisteretDataCheckContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		return mapper.readValue(utfPayload.getBytes(), JsonEnhetsRegisteretDataCheckContainer.class); 
		
	}
}
