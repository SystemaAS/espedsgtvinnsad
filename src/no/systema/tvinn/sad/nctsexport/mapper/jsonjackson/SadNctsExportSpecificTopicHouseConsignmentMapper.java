/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.mapper.jsonjackson;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment.JsonSadNcts5ExportHouseConsignmentContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment.JsonSadNcts5ExportHouseConsignmentRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 26, 2023
 * 
 */
public class SadNctsExportSpecificTopicHouseConsignmentMapper {
	private static final Logger logger = LoggerFactory.getLogger(SadNctsExportSpecificTopicHouseConsignmentMapper.class.getName());
	
	public JsonSadNcts5ExportHouseConsignmentContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNcts5ExportHouseConsignmentContainer container = mapper.readValue(utfPayload.getBytes(), JsonSadNcts5ExportHouseConsignmentContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSadNcts5ExportHouseConsignmentRecord> list = container.getOneorder();
		for(JsonSadNcts5ExportHouseConsignmentRecord record : list){
			/*logger.info("Item description: " + record.getTvvt());
			logger.info("Sender name: " + record.getTvnas());
			logger.info("Receiver name: " + record.getTvnak());
			logger.info("Tvdref: " + record.getTvdref());
			*/
		}
		return container;
	}
	
}
