/**
 * 
 */
package no.systema.tvinn.sad.admin.mapper.jsonjackson;

//jackson library
import org.apache.logging.log4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//application library
import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminTransportListContainer;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date May 26, 2014
 * 
 */
public class SadAdminTransportListMapper {
	private static final Logger logger = LogManager.getLogger(SadAdminTransportListMapper.class.getName());
	
	public JsonSadAdminTransportListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadAdminTransportListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadAdminTransportListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
