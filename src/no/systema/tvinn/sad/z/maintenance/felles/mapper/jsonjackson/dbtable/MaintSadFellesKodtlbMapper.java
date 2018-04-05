/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtlbRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 */
public class MaintSadFellesKodtlbMapper {
	private static final Logger logger = Logger.getLogger(MaintSadFellesKodtlbMapper.class.getName());
	
	public JsonMaintSadFellesKodtlbContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadFellesKodtlbContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadFellesKodtlbContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadFellesKodtlbRecord> list = container.getList();
		for(JsonMaintSadFellesKodtlbRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
