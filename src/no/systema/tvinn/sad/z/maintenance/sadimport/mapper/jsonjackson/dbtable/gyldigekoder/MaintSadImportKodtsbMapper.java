/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */
public class MaintSadImportKodtsbMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtsbMapper.class.getName());
	
	public JsonMaintSadImportKodtsbContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtsbContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSadImportKodtsbContainer.class); 
		
		return container;
	}
}
