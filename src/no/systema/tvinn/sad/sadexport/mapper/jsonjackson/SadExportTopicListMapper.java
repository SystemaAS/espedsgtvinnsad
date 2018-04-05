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
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 * 
 */
public class SadExportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SadExportTopicListMapper.class.getName());
	
	public JsonSadExportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadExportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
