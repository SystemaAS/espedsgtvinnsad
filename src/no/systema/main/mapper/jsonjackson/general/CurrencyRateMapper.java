/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * 
 */
public class CurrencyRateMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(CurrencyRateMapper.class.getName());
	
	public JsonCurrencyRateContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonCurrencyRateContainer systemaUserContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonCurrencyRateContainer.class); 
		//logger.info("Mapping currency rate object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + systemaUserContainer.getUser());
		
		//DEBUG
		Collection<JsonCurrencyRateRecord> fields = systemaUserContainer.getValutakurs();
		for(JsonCurrencyRateRecord record : fields){
			//logger.info("Currency factor: " + record.getSvvs_omr());
			//logger.info("Currency rate: " + record.getSvvk_krs());
		}
			
		return systemaUserContainer;
	}
}
