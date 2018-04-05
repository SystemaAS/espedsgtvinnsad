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
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsImportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNctsImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSadNctsImportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
