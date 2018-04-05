/**
 * 
 */
package no.systema.main.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
import no.systema.main.model.jsonjackson.JsonFirmLoginRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * 
 */
public class FirmLoginMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(FirmLoginMapper.class.getName());
	
	public JsonFirmLoginContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonFirmLoginContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonFirmLoginContainer.class); 
		//logger.info("Mapping currency rate object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + systemaUserContainer.getUser());
		
		//DEBUG
		Collection<JsonFirmLoginRecord> fields = container.getList();
		for(JsonFirmLoginRecord record : fields){
			//logger.info("Currency factor: " + record.getSvvs_omr());
			//logger.info("Currency rate: " + record.getSvvk_krs());
		}
			
		return container;
	}
}
