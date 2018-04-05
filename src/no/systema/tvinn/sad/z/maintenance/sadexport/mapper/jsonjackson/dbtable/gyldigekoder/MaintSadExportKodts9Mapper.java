/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodts9Container;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodts9Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Okt 25, 2016
 * 
 */
public class MaintSadExportKodts9Mapper {
	private static final Logger logger = Logger.getLogger(MaintSadExportKodts9Mapper.class.getName());
	
	public JsonMaintSadExportKodts9Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadExportKodts9Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportKodts9Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadExportKodts9Record> list = container.getList();
		for(JsonMaintSadExportKodts9Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
