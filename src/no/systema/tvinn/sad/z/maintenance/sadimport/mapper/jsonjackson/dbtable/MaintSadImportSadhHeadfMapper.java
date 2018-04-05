/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadhHeadfRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 */
public class MaintSadImportSadhHeadfMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportSadhHeadfMapper.class.getName());
	
	public JsonMaintSadImportSadhHeadfContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadhHeadfContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportSadhHeadfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadhHeadfRecord> list = container.getList();
		for(JsonMaintSadImportSadhHeadfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
