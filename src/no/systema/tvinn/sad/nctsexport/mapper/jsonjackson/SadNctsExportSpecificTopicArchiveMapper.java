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
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class SadNctsExportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SadNctsExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSadNctsExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNctsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSadNctsExportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
