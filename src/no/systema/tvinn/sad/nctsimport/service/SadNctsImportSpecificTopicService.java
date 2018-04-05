/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public interface SadNctsImportSpecificTopicService {
	public JsonSadNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload);
	public JsonSadNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSadNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSadNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	
}
