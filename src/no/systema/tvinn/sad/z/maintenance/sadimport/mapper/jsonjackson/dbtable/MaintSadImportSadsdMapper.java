/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public class MaintSadImportSadsdMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSadImportSadsdMapper.class.getName());
	
	public JsonMaintSadImportSadsdContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadsdContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportSadsdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadsdRecord> list = container.getList();
		for(JsonMaintSadImportSadsdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
