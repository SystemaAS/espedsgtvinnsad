/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 13, 2016
 * 
 */
public class MaintSadImportKodts8Mapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSadImportKodts8Mapper.class.getName());
	
	public JsonMaintSadImportKodts8Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts8Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts8Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts8Record> list = container.getList();
		for(JsonMaintSadImportKodts8Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
