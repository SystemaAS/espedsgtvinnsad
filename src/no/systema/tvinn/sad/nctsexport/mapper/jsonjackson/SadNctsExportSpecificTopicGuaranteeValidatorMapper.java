/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.mapper.jsonjackson;



import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public class SadNctsExportSpecificTopicGuaranteeValidatorMapper {
	private static final Logger logger = Logger.getLogger(SadNctsExportSpecificTopicGuaranteeValidatorMapper.class.getName());
		
	public JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
}


