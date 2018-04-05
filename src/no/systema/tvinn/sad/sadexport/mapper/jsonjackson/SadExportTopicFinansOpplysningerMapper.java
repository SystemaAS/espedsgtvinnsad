/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 25, 2014
 * 
 * 
 */
public class SadExportTopicFinansOpplysningerMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(SadExportTopicFinansOpplysningerMapper.class.getName());
	
	public JsonSadExportTopicFinansOpplysningerContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicFinansOpplysningerContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicFinansOpplysningerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSadExportTopicFinansOpplysningerRecord record : topicListContainer.getInvoicList()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return topicListContainer;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTopicFinansOpplysningerContainer getContainerOneInvoice(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicFinansOpplysningerContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicFinansOpplysningerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSadExportTopicFinansOpplysningerRecord record : container.getOneInvoice()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTopicFinansOpplysningerExternalContainer getContainerInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicFinansOpplysningerExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicFinansOpplysningerExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSadExportTopicFinansOpplysningerExternalRecord record : container.getListexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTopicFinansOpplysningerExternalContainer getContainerOneInvoiceInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicFinansOpplysningerExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicFinansOpplysningerExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSadExportTopicFinansOpplysningerExternalRecord record : container.getGetexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer getContainerOneInvoiceInvoiceExternalForUpdate(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		
		return container;
	}
}


