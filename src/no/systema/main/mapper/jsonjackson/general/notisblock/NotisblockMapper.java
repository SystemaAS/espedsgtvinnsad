/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general.notisblock;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * 
 */
public class NotisblockMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NotisblockMapper.class.getName());
	
	public JsonNotisblockContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNotisblockContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNotisblockContainer.class); 
		//DEBUG
		/*
		logger.info("Mapping mapper container values (i.e. errMsg) JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getErrMsg());
		logger.info("container:" + container);
		if(container!=null){
			if(container.getFreetextlist()!=null){	
				Collection<JsonNotisblockRecord> fields = container.getFreetextlist();
				for(JsonNotisblockRecord record : fields){
					logger.info("Txt field[getFreetextlist]: " + record.getFrttxt());
				}
			}
			if(container.getFreetxtGet()!=null){	
				Collection<JsonNotisblockRecord> fields = container.getFreetxtGet();
				for(JsonNotisblockRecord record : fields){
					logger.info("Txt field[getFreetxtGet]: " + record.getFrttxt());
				}
			}
		}*/
			
		return container;
	}
}
