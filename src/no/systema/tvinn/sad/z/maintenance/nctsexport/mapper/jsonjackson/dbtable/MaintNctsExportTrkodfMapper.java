/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;

/**
 * @author Fredrik Möller
 * @date Okt 17, 2016
 * 
 */
public class MaintNctsExportTrkodfMapper {
	
	public JsonMaintNctsTrkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintNctsTrkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintNctsTrkodfContainer.class); 
		return container;
	}
}
