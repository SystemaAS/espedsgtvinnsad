/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsExportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(SadNctsExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSadNctsExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNctsExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSadNctsExportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
