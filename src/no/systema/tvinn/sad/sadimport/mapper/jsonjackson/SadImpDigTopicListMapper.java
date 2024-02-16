/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImpDigTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = LoggerFactory.getLogger(SadImpDigTopicListMapper.class.getName());
	
	public SadImpDigContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		SadImpDigContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), SadImpDigContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (SadImpDigRecord record : container.getList()){
			//DEBUG
			//logger.info(record.getAvsNavn());
		}
		return container;
	}
}
