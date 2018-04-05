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
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 * 
 */
public class SadNctsImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicItemMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSadNctsImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonSadNctsImportSpecificTopicItemRecord record : list){
			//logger.info("Item description (event desc): " + record.getTvinf1());
			//logger.info("Place name: " + record.getTvst());
			//logger.info("Transport Id: " + record.getTvtaid());
		}
		return topicItemContainer;
	}
}
