/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmocfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmolffContainer;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Dec, 2023
 * 
 * 
 */
public class SadmolffMapper {
	private static final Logger logger = LoggerFactory.getLogger(SadmolffMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public SadmolffContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
		SadmolffContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), SadmolffContainer.class); 
			
		}
			
		return container;
	}
	
	

}
