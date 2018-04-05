/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts2Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 25, 2016
 * 
 */
public class MaintSadImportKodts2Mapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodts2Mapper.class.getName());
	
	public JsonMaintSadImportKodts2Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts2Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts2Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts2Record> list = container.getList();
		for(JsonMaintSadImportKodts2Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
