/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadTolltariffVarukodMapper {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadTolltariffVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadTolltariffVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonTvinnSadTolltariffVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadTolltariffVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadTolltariffVarukodRecord> fields = taricCodeContainer.getTariclist();
			for(JsonTvinnSadTolltariffVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
