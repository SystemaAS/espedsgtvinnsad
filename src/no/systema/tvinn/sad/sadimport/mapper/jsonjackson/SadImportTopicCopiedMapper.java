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
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 11, 2014
 * 
 */
public class SadImportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(SadImportTopicCopiedMapper.class.getName());
	
	public JsonSadImportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadImportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadImportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
