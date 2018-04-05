/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicSendParametersContainer;



//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImportSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(SadImportSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSadImportSpecificTopicRecord> fields = container.getOneorder();
		for(JsonSadImportSpecificTopicRecord record : fields){
			//logger.info("DKIH_t07a: " + record.getDkih_t07a());
			//logger.info("DKIH_t07d: " + record.getDkih_t07d());
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSadImportSpecificTopicAvdDataContainer getAvdDataContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicAvdDataContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicAvdDataContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonSadImportSpecificTopicRecord> fields = container.getGetdepinf();
		for(JsonSadImportSpecificTopicRecord record : fields){
			//TODO
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicFaktTotalContainer getFaktTotalContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicFaktTotalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicFaktTotalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportSpecificTopicSendParametersContainer getSendParametersContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadImportSpecificTopicSendParametersContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadImportSpecificTopicSendParametersContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
}
