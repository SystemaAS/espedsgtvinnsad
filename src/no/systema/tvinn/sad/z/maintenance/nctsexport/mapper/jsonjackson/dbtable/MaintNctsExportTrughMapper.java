/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;

/**
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 */
public class MaintNctsExportTrughMapper {
	
	public JsonMaintNctsTrughContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintNctsTrughContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintNctsTrughContainer.class); 
		return container;
	}
}
