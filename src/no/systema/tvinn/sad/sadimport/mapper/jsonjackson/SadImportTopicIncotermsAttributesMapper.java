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
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 23, 2015
 * 
 * 
 */
public class SadImportTopicIncotermsAttributesMapper {
	private static final Logger logger = Logger.getLogger(SadImportTopicIncotermsAttributesMapper.class.getName());
	
	public JsonSadImportTopicIncotermsAttributesContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadImportTopicIncotermsAttributesContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportTopicIncotermsAttributesContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSadImportTopicIncotermsAttributesRecord record : container.getLevvillkar()){
			//DEBUG
			//logger.info(record.getAvsNavn());
		}
		return container;
	}
}
