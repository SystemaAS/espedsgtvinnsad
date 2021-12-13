/**
 * 
 */
package no.systema.main.service.general.notisblock;

import org.apache.logging.log4j.*;

import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.mapper.jsonjackson.general.notisblock.NotisblockMapper;

/**
 * 
 * @author oscardelatorre
 * Jan 17, 2015
 * 
 */
public class NotisblockServiceImpl implements NotisblockService{
	private static final Logger logger = LogManager.getLogger(NotisblockMapper.class.getName());
	
	public JsonNotisblockContainer getNotisblockListContainer(String utfPayload) {
		JsonNotisblockContainer container = null;
		try{
			NotisblockMapper mapper = new NotisblockMapper();
			container = mapper.getContainer(utfPayload);
			//logger.info("CONTAINER:" + container.getErrMsg());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
