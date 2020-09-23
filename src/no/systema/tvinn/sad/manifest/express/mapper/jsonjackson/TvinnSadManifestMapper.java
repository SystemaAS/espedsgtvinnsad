/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestPostalCodeContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRpgContainer;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep, 2018
 * 
 * 
 */
public class TvinnSadManifestMapper {
	private static final Logger logger = Logger.getLogger(TvinnSadManifestMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadManifestContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadManifestContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadManifestContainer.class); 
			
		}
			
		return container;
	}
	
	public JsonTvinnSadManifestRpgContainer getContainerRgp132Raw(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadManifestRpgContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadManifestRpgContainer.class); 
			
		}
			
		return container;
	}
	
	public JsonTvinnSadManifestCargoLinesContainer getCargolinesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadManifestCargoLinesContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadManifestCargoLinesContainer.class); 
			
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadManifestPostalCodeContainer getPostalCodeContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTvinnSadManifestPostalCodeContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTvinnSadManifestPostalCodeContainer.class); 
			
		}
			
		return container;
	}
	
	

}
