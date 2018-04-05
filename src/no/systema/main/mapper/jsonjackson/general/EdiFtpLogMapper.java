/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogContainer;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogRecord;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLog2Container;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLog2Record;
//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class EdiFtpLogMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(EdiFtpLogMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEdiFtpLogContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonEdiFtpLogContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonEdiFtpLogContainer.class); 
		
		//DEBUG
		Collection<JsonEdiFtpLogRecord> fields = container.getList();
		for(JsonEdiFtpLogRecord record : fields){
			//logger.info("DEBUG: " + record.TODO);
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEdiFtpLog2Container getLog2Container(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonEdiFtpLog2Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonEdiFtpLog2Container.class); 
		
		//DEBUG
		Collection<JsonEdiFtpLog2Record> fields = container.getList();
		for(JsonEdiFtpLog2Record record : fields){
			//logger.info("DEBUG: " + record.TODO);
		}
			
		return container;
	}
}
