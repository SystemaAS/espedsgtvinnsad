/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 31, 2016
 * 
 */
public class MaintSadImportKodtlikMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtlikMapper.class.getName());
	
	public JsonMaintSadImportKodtlikContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtlikContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodtlikContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodtlikRecord> list = container.getList();
		for(JsonMaintSadImportKodtlikRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
