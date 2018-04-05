/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesSoktariRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Okt 25, 2016
 * 
 */
public class MaintSadFellesSoktariMapper {
	private static final Logger logger = Logger.getLogger(MaintSadFellesSoktariMapper.class.getName());
	
	public JsonMaintSadFellesSoktariContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadFellesSoktariContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadFellesSoktariContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadFellesSoktariRecord> list = container.getList();
		for(JsonMaintSadFellesSoktariRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
