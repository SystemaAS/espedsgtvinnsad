/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.mapper.jsonjackson;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadAvdSignContainer;


import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug, 2023
 * 
 * 
 */
public class SadAvdSignMapper {
	private static final Logger logger = LoggerFactory.getLogger(SadAvdSignMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public SadAvdSignContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		SadAvdSignContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), SadAvdSignContainer.class); 
			
		}
			
		return container;
	}

}
