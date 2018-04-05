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
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 * 
 */
public class SadImportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SadImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSadImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSadImportSpecificTopicArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
		}
		return container;
	}
}
