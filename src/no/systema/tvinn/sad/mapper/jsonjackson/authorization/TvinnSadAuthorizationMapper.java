/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson.authorization;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationContainer;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadAuthorizationMapper {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadAuthorizationMapper.class.getName());
	
	public JsonTvinnSadAuthorizationContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadAuthorizationContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadAuthorizationContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadAuthorizationRecord> fields = container.getSadtillatelse();
			for(JsonTvinnSadAuthorizationRecord record : fields){
				logger.info("ok: " + record.getOk());
				logger.info("sign: " + record.getSign());
			}
		}
		return container;
	}
	
}
