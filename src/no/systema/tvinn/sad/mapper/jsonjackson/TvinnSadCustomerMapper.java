/**
 * 
 */
package no.systema.tvinn.sad.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextRecord;



//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadCustomerMapper {
	private static final Logger logger = Logger.getLogger(TvinnSadCustomerMapper.class.getName());
	
	public JsonTvinnSadCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTvinnSadCustomerContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTvinnSadCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTvinnSadCustomerRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return customerListContainer;
	}
	/**
	 * Extra customer information (free text)
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadCustomerInfoFreeTextContainer getContainerInfoFreeText(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTvinnSadCustomerInfoFreeTextContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadCustomerInfoFreeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTvinnSadCustomerInfoFreeTextRecord> fields = customerListContainer.getDspCustFreetxt();
		for(JsonTvinnSadCustomerInfoFreeTextRecord record : fields){
			//logger.info("fxtxt: " + record.getFxtxt());
			//logger.info("delsys: " + record.getDelsys());
		}
		return customerListContainer;
	}
}
