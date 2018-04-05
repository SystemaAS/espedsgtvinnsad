/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 */
public class MaintKodtvaMapper {
	private static final Logger logger = Logger.getLogger(MaintKodtvaMapper.class.getName());
	
	public JsonMaintKodtvaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintKodtvaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintKodtvaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintKodtvaRecord> list = container.getList();
		for(JsonMaintKodtvaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
