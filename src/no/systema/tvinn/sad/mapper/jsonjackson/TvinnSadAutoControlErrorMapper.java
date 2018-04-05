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
import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 28, 2015
 * 
 */
public class TvinnSadAutoControlErrorMapper {
	private static final Logger logger = Logger.getLogger(TvinnSadAutoControlErrorMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadAutoControlErrorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonSadAutoControlErrorContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonSadAutoControlErrorContainer.class); 
		}	
		return container;
	}
	
	
}
