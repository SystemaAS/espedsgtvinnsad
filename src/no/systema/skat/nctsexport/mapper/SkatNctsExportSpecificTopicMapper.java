/**
 * 
 */
package no.systema.skat.nctsexport.mapper;

//
import java.util.Collection;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportSpecificTopicMapper {
	private static final Logger logger = LoggerFactory.getLogger(SkatNctsExportSpecificTopicMapper.class.getName());
	
	public JsonSkatNctsExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonSkatNctsExportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		if(fields!=null){
		//for(JsonSkatNctsExportSpecificTopicRecord record : fields){
			/*logger.info("SVEH_SYAV: " + record.getSveh_syav());
			logger.info("SVEH_SYOP: " + record.getSveh_syop());
			logger.info("SVEH_AVTL: " + record.getSveh_avtl());
			logger.info("SVEH_MOPA: " + record.getSveh_mopa());
			logger.info("SVEH_MOLK: " + record.getSveh_molk());
			logger.info("SVEH_FABL: " + record.getSveh_fabl());
			logger.info("SVEH_FATX: " + record.getSveh_fatx());
			logger.info("SVEH_VAKD: " + record.getSveh_vakd());
			*/
		//}
		}	
		return topicListContainer;
	}
}
