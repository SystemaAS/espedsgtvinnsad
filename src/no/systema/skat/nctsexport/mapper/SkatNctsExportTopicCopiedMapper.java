/**
 * 
 */
package no.systema.skat.nctsexport.mapper;

//jackson library
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportTopicCopiedMapper {
	private static final Logger logger = LogManager.getLogger(SkatNctsExportTopicCopiedMapper.class.getName());
	
	public JsonSkatNctsExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
		
	}
}
