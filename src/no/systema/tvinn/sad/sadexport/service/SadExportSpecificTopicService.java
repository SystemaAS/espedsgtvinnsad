/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;


import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicSendParametersContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurContainer;



/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public interface SadExportSpecificTopicService {
	public JsonSadExportSpecificTopicContainer getSadExportSpecificTopicContainer(String utfPayload);
	public JsonSadExportTopicCopiedContainer getSadExportTopicCopiedContainer(String utfPayload);
	public JsonSadExportSpecificTopicArchiveContainer getSadExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSadExportSpecificTopicLoggingContainer getSadExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSadExportSpecificTopicLoggingLargeTextContainer getSadExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSadExportTopicCopiedFromTransportUppdragContainer getSadExportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	public JsonSadExportSpecificTopicAvdDataContainer getSadExportSpecificTopicAvdDataContainer (String utfPayload);
	
	public JsonSadExportTopicFinansOpplysningerContainer getSadExportTopicFinansOpplysningerContainer (String utfPayload);
	public JsonSadExportTopicFinansOpplysningerContainer getSadExportTopicFinansOpplysningerContainerOneInvoice (String utfPayload);
	public JsonSadExportSpecificTopicFaktTotalContainer getSadExportSpecificTopicFaktTotalContainer (String utfPayload);
	
	//External invoices
	public JsonSadExportTopicFinansOpplysningerExternalContainer getSadExportTopicFinansOpplysningerContainerContainerExternal (String utfPayload);
	public JsonSadExportTopicFinansOpplysningerExternalContainer getSadExportTopicFinansOpplysningerContainerOneInvoiceExternal (String utfPayload);
	public JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer getSadExportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate (String utfPayload);
	
	public JsonSadExportSpecificTopicSendParametersContainer getSadExportSpecificTopicSendParametersContainer (String utfPayload);
	
	//EUR
	public JsonSadExportTopicEurContainer getSadExportSpecificTopicEur (String utfPayload);
	
	
	
	
}
