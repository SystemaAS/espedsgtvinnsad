/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class SadExportTopicCopiedFromTransportUppdragMapper {
	private static final Logger logger = LoggerFactory.getLogger(SadExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonSadExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicCopiedFromTransportUppdragContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadExportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
