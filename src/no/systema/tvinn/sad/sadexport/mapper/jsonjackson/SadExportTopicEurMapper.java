/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurRecord;

/**
 * 
 * @author oscardelatorre
 * @date Feb, 2019
 * 
 * 
 */
public class SadExportTopicEurMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(SadExportTopicEurMapper.class.getName());
	
	public JsonSadExportTopicEurContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicEurContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicEurContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSadExportTopicEurRecord record : topicListContainer.getGeteur1()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return topicListContainer;
	}
	
}


