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
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCode2Container;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCode2Record;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadCodeMapper {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadCodeMapper.class.getName());
	
	public JsonTvinnSadCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTvinnSadCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTvinnSadCodeRecord record : fields){
				/*logger.info("Code: " + record.getZkod());
				logger.info("Value: " + record.getZtxt());
				*/
			}
		}	
		return codeContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadCode2Container getContainer2(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTvinnSadCode2Container codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadCode2Container.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadCode2Record> fields = codeContainer.getArkivkodelist();
			for(JsonTvinnSadCode2Record record : fields){
				/*logger.info("Code: " + record.getZkod());
				logger.info("Value: " + record.getZtxt());
				*/
			}
		}	
		return codeContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonTvinnSadNctsCodeContainer getNctsContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTvinnSadNctsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadNctsCodeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadNctsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTvinnSadNctsCodeRecord record : fields){
				//logger.info("Code: " + record.getTkkode());
			}
		}	
		return codeContainer;
	}
	
}
