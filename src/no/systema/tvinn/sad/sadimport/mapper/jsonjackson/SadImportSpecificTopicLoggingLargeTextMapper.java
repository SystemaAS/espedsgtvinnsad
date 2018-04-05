/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 * 
 */
public class SadImportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SadImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSadImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSadImportSpecificTopicLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
