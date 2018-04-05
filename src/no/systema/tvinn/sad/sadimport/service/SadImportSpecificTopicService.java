/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

//
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicSendParametersContainer;


import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedContainer;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveContainer;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesContainer;


/**
 * 
 * @author oscardelatorre
 * @date Jun 2, 2014
 *
 * 
 */

public interface SadImportSpecificTopicService {
	public JsonSadImportSpecificTopicContainer getSadImportSpecificTopicContainer(String utfPayload);
	public JsonSadImportTopicCopiedContainer getSadImportTopicCopiedContainer(String utfPayload);
	public JsonSadImportTopicCopiedFromTransportUppdragContainer getSadImportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	//
	public JsonSadImportSpecificTopicLoggingContainer getSadImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSadImportSpecificTopicLoggingLargeTextContainer getSadImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSadImportSpecificTopicAvdDataContainer getSadImportSpecificTopicAvdDataContainer (String utfPayload);
	public JsonSadImportSpecificTopicArchiveContainer getSadImportSpecificTopicArchiveContainer(String utfPayload);
	//
	public JsonSadImportTopicFinansOpplysningerContainer getSadImportTopicFinansOpplysningerContainer (String utfPayload);
	public JsonSadImportTopicFinansOpplysningerContainer getSadImportTopicFinansOpplysningerContainerOneInvoice (String utfPayload);
	public void updateFinansInformationIfApplicable(JsonSadImportSpecificTopicContainer jsonSadImportSpecificTopicContainer);
	public JsonSadImportSpecificTopicFaktTotalContainer getSadImportSpecificTopicFaktTotalContainer (String utfPayload);
	//External invoices
	public JsonSadImportTopicFinansOpplysningerExternalContainer getSadImportTopicFinansOpplysningerContainerContainerExternal (String utfPayload);
	public JsonSadImportTopicFinansOpplysningerExternalContainer getSadImportTopicFinansOpplysningerContainerOneInvoiceExternal (String utfPayload);
	public JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer getSadImportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate (String utfPayload);

	public JsonSadImportTopicIncotermsAttributesContainer getSadImportTopicIncotermsAttributesContainer (String utfPayload);
	
	public JsonSadImportSpecificTopicSendParametersContainer getSadImportSpecificTopicSendParametersContainer (String utfPayload);
	
}
