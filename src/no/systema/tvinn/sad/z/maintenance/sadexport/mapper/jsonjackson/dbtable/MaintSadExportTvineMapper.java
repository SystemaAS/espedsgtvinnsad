/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineContainer;

/**
 * @author Fredrik Möller
 * @date Aug 9, 2016
 * 
 */
public class MaintSadExportTvineMapper {
	private static final Logger logger = Logger.getLogger(MaintSadExportTvineMapper.class.getName());
	
	public JsonMaintSadExportTvineContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadExportTvineContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportTvineContainer.class); 
		return container;
	}
}
