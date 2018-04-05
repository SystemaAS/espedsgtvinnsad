/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;


import org.apache.log4j.Logger;

//application library
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Feb 21, 2015
 * 
 * 
 */
public class PostalCodesMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(PostalCodesMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonPostalCodesContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonPostalCodesContainer customerListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonPostalCodesContainer.class); 
		
		//DEBUG
		Collection<JsonPostalCodesRecord> fields = customerListContainer.getPostnrlist();
		for(JsonPostalCodesRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return customerListContainer;
	}
	
}
