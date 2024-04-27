/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfContainer;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr, 2024
 * 
 * 
 */
public class ZadmomlfMapper {
	private static final Logger logger = LoggerFactory.getLogger(ZadmomlfMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public ZadmomlfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
		ZadmomlfContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), ZadmomlfContainer.class); 
			
		}
			
		return container;
	}
	
	

}
