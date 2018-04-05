/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 */
public class MaintSadImportKodts5Mapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodts5Mapper.class.getName());
	
	public JsonMaintSadImportKodts5Container getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts5Container container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodts5Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodts5Record> list = container.getList();
		for(JsonMaintSadImportKodts5Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
