/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsImportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNctsImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSadNctsImportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
