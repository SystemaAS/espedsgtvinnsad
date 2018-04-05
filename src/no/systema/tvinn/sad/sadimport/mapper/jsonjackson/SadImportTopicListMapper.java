/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImportTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(SadImportTopicListMapper.class.getName());
	
	public JsonSadImportTopicListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadImportTopicListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSadImportTopicListRecord record : topicListContainer.getOrderList()){
			//DEBUG
			//logger.info(record.getAvsNavn());
		}
		return topicListContainer;
	}
}
