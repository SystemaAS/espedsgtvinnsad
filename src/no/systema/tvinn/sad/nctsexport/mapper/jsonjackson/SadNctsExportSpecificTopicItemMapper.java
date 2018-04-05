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
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsExportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SadNctsExportSpecificTopicItemMapper.class.getName());
	
	public JsonSadNctsExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSadNctsExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonSadNctsExportSpecificTopicItemRecord record : list){
			/*logger.info("Item description: " + record.getTvvt());
			logger.info("Sender name: " + record.getTvnas());
			logger.info("Receiver name: " + record.getTvnak());
			logger.info("Tvdref: " + record.getTvdref());
			*/
		}
		return topicItemContainer;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadNctsExportSpecificTopicItemSecurityContainer getSecurityContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicItemSecurityContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicItemSecurityContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNctsExportSpecificTopicItemSecurityRecord> list = container.getSecurityline();
		for(JsonSadNctsExportSpecificTopicItemSecurityRecord record : list){
			//DEBUG logger.info("Tvtinks (security): " + record.getTvtinks());
		}
		return container;
	}
}
