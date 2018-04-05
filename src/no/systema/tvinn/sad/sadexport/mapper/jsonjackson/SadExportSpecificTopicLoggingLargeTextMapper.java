/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class SadExportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SadExportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSadExportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSadExportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
