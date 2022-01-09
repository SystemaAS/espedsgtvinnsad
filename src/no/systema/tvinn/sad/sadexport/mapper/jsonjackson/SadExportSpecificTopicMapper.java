/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

import java.util.*;

//jackson library
import org.slf4j.*;

//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicSendParametersContainer;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 * 
 */
public class SadExportSpecificTopicMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LoggerFactory.getLogger(SadExportSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSadExportSpecificTopicRecord> fields = container.getOneorder();
		for(JsonSadExportSpecificTopicRecord record : fields){
			//debug here when applicable
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSadExportSpecificTopicAvdDataContainer getAvdDataContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicAvdDataContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicAvdDataContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadExportSpecificTopicRecord> fields = container.getGetdepinf();
		for(JsonSadExportSpecificTopicRecord record : fields){
			//debug here when applicable
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportSpecificTopicFaktTotalContainer getFaktTotalContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicFaktTotalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicFaktTotalContainer.class); 
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
	public JsonSadExportSpecificTopicSendParametersContainer getSendParametersContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonSadExportSpecificTopicSendParametersContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportSpecificTopicSendParametersContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
}
