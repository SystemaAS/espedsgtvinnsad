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
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 * 
 */
public class SadImportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(SadImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSadImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSadImportSpecificTopicLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
