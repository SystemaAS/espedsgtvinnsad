/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.validation.JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public interface SadNctsExportSpecificTopicItemService {
	public JsonSadNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicItemSecurityContainer getNctsExportSpecificTopicItemSecurityContainer(String utfPayload);
	public JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload);
}
