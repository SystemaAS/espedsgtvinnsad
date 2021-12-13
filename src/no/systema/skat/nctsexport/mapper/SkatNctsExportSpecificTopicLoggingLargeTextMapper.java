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
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = LogManager.getLogger(SkatNctsExportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
