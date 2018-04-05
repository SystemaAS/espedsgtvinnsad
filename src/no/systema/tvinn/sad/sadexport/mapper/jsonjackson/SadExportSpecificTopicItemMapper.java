/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemAvgifterContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemAvgifterRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date	 Maj 2, 2014
 * 
 * 
 */
public class SadExportSpecificTopicItemMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(SadExportSpecificTopicItemMapper.class.getName());
	
	public JsonSadExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicItemContainer topicItemContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSadExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		logger.info("Record list(size): " + list.size());
		
		for(JsonSadExportSpecificTopicItemRecord record : list){
			//logger.info("svbelt: " + record.getSvbelt());
		}
		return topicItemContainer;
	}
	
	/**
	 * Avgifts mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSadExportSpecificTopicItemAvgifterContainer getAvgifterContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicItemAvgifterContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicItemAvgifterContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadExportSpecificTopicItemAvgifterRecord> list = container.getStatvaluecalc();
		for(JsonSadExportSpecificTopicItemAvgifterRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	
	
}
