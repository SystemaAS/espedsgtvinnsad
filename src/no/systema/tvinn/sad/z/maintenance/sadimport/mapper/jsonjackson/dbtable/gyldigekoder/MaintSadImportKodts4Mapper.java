/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.slf4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 */
public class MaintSadImportKodts4Mapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadImportKodts4Mapper.class.getName());
	
	public JsonMaintSadImportKodts4Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts4Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts4Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts4Record> list = container.getList();
		for(JsonMaintSadImportKodts4Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
