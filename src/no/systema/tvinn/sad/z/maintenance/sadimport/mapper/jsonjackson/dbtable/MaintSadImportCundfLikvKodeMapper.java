/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 */
public class MaintSadImportCundfLikvKodeMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportCundfLikvKodeMapper.class.getName());
	
	public JsonMaintSadImportCundfLikvKodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportCundfLikvKodeContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportCundfLikvKodeContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportCundfLikvKodeRecord> list = container.getList();
		for(JsonMaintSadImportCundfLikvKodeRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
