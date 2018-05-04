/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrContainer;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrRecord;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrListContainer;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrListRecord;



import java.util.*;

/**
 * @author oscardelatorre
 * @date Maj 04, 2018
 * 
 * 
 */
public class TvinnSadGodsnrMapper {
	private static final Logger logger = Logger.getLogger(TvinnSadGodsnrMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadGodsnrContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadGodsnrContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadGodsnrContainer.class); 
			//logger.info(mapper.writeValueAsString(container));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTvinnSadGodsnrRecord> fields = container.getCrtgodsnr();
			for(JsonTvinnSadGodsnrRecord record : fields){
				//logger.info("Avd: " + record.getAvd());
				//logger.info("Namn: " + record.getNamn());
				//logger.info("Tst: " + record.getTst());
			}
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadGodsnrListContainer getContainerGodsnrList(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadGodsnrListContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadGodsnrListContainer.class); 
			
			//DEBUG
			Collection<JsonTvinnSadGodsnrListRecord> fields = container.getGodsliste();
			for(JsonTvinnSadGodsnrListRecord record : fields){
				//logger.info("TODO: " + record.getTODO());
			}
		}
			
		return container;
	}
}
