/**
 * 
 */
package no.systema.tvinn.sad.manifest.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tvinn.sad.manifest.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.model.jsonjackson.JsonTvinnSadManifestRecord;



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
	

}
