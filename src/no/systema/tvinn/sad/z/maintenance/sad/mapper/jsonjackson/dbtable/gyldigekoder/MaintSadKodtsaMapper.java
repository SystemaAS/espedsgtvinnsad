/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */
public class MaintSadKodtsaMapper {
	private static final Logger logger = Logger.getLogger(MaintSadKodtsaMapper.class.getName());
	
	public JsonMaintSadKodtsaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadKodtsaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadKodtsaContainer.class); 
		
		return container;
	}
}
