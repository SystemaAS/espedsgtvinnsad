/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson.avdsignature;

//jackson library
import org.apache.logging.log4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;


import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadAvdelningMapper {
	private static final Logger logger = LogManager.getLogger(TvinnSadAvdelningMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadAvdelningContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadAvdelningContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadAvdelningContainer.class); 
			//logger.info(mapper.writeValueAsString(container));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadAvdelningRecord> fields = container.getAvdelningar();
			for(JsonTvinnSadAvdelningRecord record : fields){
				//logger.info("Avd: " + record.getAvd());
				//logger.info("Namn: " + record.getNamn());
				//logger.info("Tst: " + record.getTst());
			}
		}
			
		return container;
	}
}
