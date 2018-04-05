/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public interface SkatNctsExportSpecificTopicService {
	public JsonSkatNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload);
	public JsonSkatNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload);
	public JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer getSkatNctsExportTopicCopiedFromTransportUppdragContainer(String utfPayload);
}
