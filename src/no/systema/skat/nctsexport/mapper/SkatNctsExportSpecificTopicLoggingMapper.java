/**
 * 
 */
package no.systema.skat.nctsexport.mapper;

//
import java.util.Collection;

//jackson library
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportSpecificTopicLoggingMapper {
	private static final Logger logger = LogManager.getLogger(SkatNctsExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSkatNctsExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSkatNctsExportSpecificTopicLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
