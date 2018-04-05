/**
 * 
 */
package no.systema.z.main.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.z.main.maintenance.model.JsonMaintMainCundfContainer;

/**
 * @author oscardelatorre
 * @date Apr 03, 2018
 * 
 */
public class MaintMainCundfMapper extends ObjectMapperAbstractGrandFather{
	public JsonMaintMainCundfContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainCundfContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintMainCundfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		return container;
	}
}
