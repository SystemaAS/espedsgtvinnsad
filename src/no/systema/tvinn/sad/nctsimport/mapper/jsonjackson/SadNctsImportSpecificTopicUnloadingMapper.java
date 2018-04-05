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
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.JsonSadNctsImportSpecificTopicUnloadingRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsImportSpecificTopicUnloadingMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicUnloadingMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicUnloadingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicUnloadingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicUnloadingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonSadNctsImportSpecificTopicUnloadingRecord> fields = container.getOneorder();
		for(JsonSadNctsImportSpecificTopicUnloadingRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
