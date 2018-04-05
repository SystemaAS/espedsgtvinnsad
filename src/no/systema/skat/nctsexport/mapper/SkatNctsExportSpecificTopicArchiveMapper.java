/**
 * 
 */
package no.systema.skat.nctsexport.mapper;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSkatNctsExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSkatNctsExportSpecificTopicArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
