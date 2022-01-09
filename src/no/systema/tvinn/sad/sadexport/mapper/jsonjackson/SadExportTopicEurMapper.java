/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

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
	private static final Logger logger = LoggerFactory.getLogger(SadExportTopicEurMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTopicEurContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicEurContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicEurContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		if(topicListContainer!=null){
			if(topicListContainer.getGeteur1()!=null){
				for (JsonSadExportTopicEurRecord record : topicListContainer.getGeteur1()){
				//DEBUG
				//logger.info(record.getSftxt());
				}
			}else if (topicListContainer.getPrteur1()!=null){
				for (JsonSadExportTopicEurRecord record : topicListContainer.getPrteur1()){
					//DEBUG
					//logger.info("Bane:" + record.getBane());
				}
			}
		}
		return topicListContainer;
	}
	
}


