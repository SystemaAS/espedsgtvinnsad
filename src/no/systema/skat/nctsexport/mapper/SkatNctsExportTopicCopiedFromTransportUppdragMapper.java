/**
 * 
 */
package no.systema.skat.nctsexport.mapper;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 28, 2014
 * 
 */
public class SkatNctsExportTopicCopiedFromTransportUppdragMapper {
	private static final Logger logger = LoggerFactory.getLogger(SkatNctsExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs�ndare: " + record.getAvsNavn());
		}*/
		
		
		return topicListContainer;
	}
}
