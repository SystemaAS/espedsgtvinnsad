/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date	 Jun 4, 2014
 * 
 * 
 */
public class SadImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SadImportSpecificTopicItemMapper.class.getName());
	
	public JsonSadImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		/* DEBUG
		if(list!=null){
			logger.info(list.size());
		}*/
		for(JsonSadImportSpecificTopicItemRecord record : list){
			//logger.info("svvf:" + record.getSvvf());
		}
		return topicItemContainer;
	}
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicItemPreferenceContainer getPreferenceContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicItemPreferenceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicItemPreferenceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicItemPreferenceRecord> list = container.getGetpreferanse();
		/* DEBUG
		if(list!=null){
			logger.info(list.size());
		}*/
		for(JsonSadImportSpecificTopicItemPreferenceRecord record : list){
			//logger.info("svvf:" + record.getSvvf());
		}
		return container;
	}
	/**
	 * Preference Mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicItemPvaContainer getPvaContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicItemPvaContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicItemPvaContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicItemPvaRecord> list = container.getGetPVA();
		/* DEBUG
		if(list!=null){
			logger.info(list.size());
		}*/
		for(JsonSadImportSpecificTopicItemPvaRecord record : list){
			//logger.info("svvf:" + record.getSvvf());
		}
		return container;
	}
	
	
	/**
	 * Avgifts mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer getAvgifterBeforeCalculationContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> list = container.getGetavgifter();
		for(JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord record : list){
			//logger.info("TODO");
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer getAvgifterAfterCalculationContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> list = container.getCalcavgifter();
		for(JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord record : list){
			//logger.info("TODO");
		}
		return container;
	}
	
	
}
