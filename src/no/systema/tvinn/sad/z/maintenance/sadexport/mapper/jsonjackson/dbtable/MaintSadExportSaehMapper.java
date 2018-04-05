/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSaehContainer;

/**
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 */
public class MaintSadExportSaehMapper {
	
	public JsonMaintSadExportSaehContainer getContainer(String utfPayload) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// At this point we now have an UTF-8 payload
		return mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportSaehContainer.class);

	}
}
