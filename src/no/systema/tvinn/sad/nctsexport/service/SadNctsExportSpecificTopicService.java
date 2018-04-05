/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public interface SadNctsExportSpecificTopicService {
	public JsonSadNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload);
	public JsonSadNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload);
}
