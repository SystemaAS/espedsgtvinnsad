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
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items.JsonSadNctsImportSpecificTopicUnloadingItemRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsImportSpecificTopicUnloadingItemMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicUnloadingItemMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicUnloadingItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicUnloadingItemContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicUnloadingItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonSadNctsImportSpecificTopicUnloadingItemRecord> fields = container.getOrderList();
		for(JsonSadNctsImportSpecificTopicUnloadingItemRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
