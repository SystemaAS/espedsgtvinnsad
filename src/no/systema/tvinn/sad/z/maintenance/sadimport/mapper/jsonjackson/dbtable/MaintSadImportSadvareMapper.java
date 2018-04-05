/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 */
public class MaintSadImportSadvareMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportSadvareMapper.class.getName());
	
	public JsonMaintSadImportSadvareContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadvareContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportSadvareContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadvareRecord> list = container.getList();
		for(JsonMaintSadImportSadvareRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
