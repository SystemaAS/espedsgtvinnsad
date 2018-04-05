/**
 * 
 */
package no.systema.tror.mapper;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.JsonTrorOrderListContainer;
import no.systema.tror.model.JsonTrorOrderListRecord;



/**
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 */
public class JsonTrorOrderListMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderListContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderListRecord record : container.getDtoList()){
			//DEBUG
		}
		
		return container;
	}
	
}
