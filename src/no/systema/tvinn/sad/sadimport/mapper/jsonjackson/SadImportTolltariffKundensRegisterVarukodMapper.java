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
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 25, 2016
 * 
 * 
 */
public class SadImportTolltariffKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(SadImportTolltariffKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadImportTolltariffKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonSadImportTolltariffKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonSadImportTolltariffKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonSadImportTolltariffKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonSadImportTolltariffKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
