/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportTolltariffKundensRegisterVarukodContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportTolltariffKundensRegisterVarukodRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 25, 2016
 * 
 * 
 */
public class SadExportTolltariffKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(SadExportTolltariffKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTolltariffKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonSadExportTolltariffKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonSadExportTolltariffKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonSadExportTolltariffKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonSadExportTolltariffKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
