/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.mapper.jsonjackson;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsExportTopicCopiedMapper {
	private static final Logger logger = LoggerFactory.getLogger(SadNctsExportTopicCopiedMapper.class.getName());
	
	public JsonSadNctsExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
		
	}
}
