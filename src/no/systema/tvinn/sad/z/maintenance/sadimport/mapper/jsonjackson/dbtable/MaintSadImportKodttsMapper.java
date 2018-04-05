/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 */
public class MaintSadImportKodttsMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodttsMapper.class.getName());
	
	public JsonMaintSadImportKodttsContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodttsContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodttsContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodttsRecord> list = container.getList();
		for(JsonMaintSadImportKodttsRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
