/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;


import org.apache.log4j.Logger;

//application library
import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;
//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Feb 24, 2017
 * 
 * 
 */
public class FileUploadToArchiveMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(FileUploadToArchiveMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFileUploadToArchiveValidationContainer getFileUploadValidationContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonFileUploadToArchiveValidationContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonFileUploadToArchiveValidationContainer.class); 
	
		return container;
	}
	
}
