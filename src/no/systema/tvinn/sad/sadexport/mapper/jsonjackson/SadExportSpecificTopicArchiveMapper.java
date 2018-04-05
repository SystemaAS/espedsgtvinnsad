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
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class SadExportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SadExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSadExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSadExportSpecificTopicArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
		}
		return container;
	}
}
