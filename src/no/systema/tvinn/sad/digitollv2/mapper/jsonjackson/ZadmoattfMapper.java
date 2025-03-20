/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmomlfContainer;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 2025
 * 
 * 
 */
public class ZadmoattfMapper {
	private static final Logger logger = LoggerFactory.getLogger(ZadmoattfMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public ZadmoattfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
		ZadmoattfContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), ZadmoattfContainer.class); 
			
		}
			
		return container;
	}
	
	

}
