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
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsExportSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(SadNctsExportSpecificTopicMapper.class.getName());
	
	public JsonSadNctsExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		/*
		Collection<JsonSadNctsExportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonSadNctsExportSpecificTopicRecord record : fields){
			//logger.info("Thtdn: " + record.getThtdn());
		}
		
		Collection<JsonSadNctsExportSpecificTopicRecord> fieldsSikkerhet = topicListContainer.getSecurityhead();
		for(JsonSadNctsExportSpecificTopicRecord record : fieldsSikkerhet){
			//logger.info("Thtdn: " + record.getThtdn());
		}*/
			
		return topicListContainer;
	}
}
