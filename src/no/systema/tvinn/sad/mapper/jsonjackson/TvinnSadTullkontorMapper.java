/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadTullkontorMapper {
	private static final Logger logger = Logger.getLogger(TvinnSadTullkontorMapper.class.getName());
	
	public JsonTvinnSadTullkontorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTvinnSadTullkontorContainer listContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonTvinnSadTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonTvinnSadTullkontorRecord record : fields){
			//logger.info("tullkontor-txt: " + record.getTktxtn());
			//logger.info("tullkontor-code: " + record.getTkkode());
		}
			
		return listContainer;
	}
}
