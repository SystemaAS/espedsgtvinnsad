/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodContainer;

import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 4, 2014
 * 
 *
 */
public interface SadImportSpecificTopicItemService {
	
	public JsonSadImportSpecificTopicItemContainer getSadImportSpecificTopicItemContainer(String utfPayload);
	public JsonSadImportSpecificTopicItemPreferenceContainer getSadImportSpecificTopicItemPreferenceContainer(String utfPayload);
	public JsonSadImportSpecificTopicItemPvaContainer getSadImportSpecificTopicItemPvaContainer(String utfPayload);
	public JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer getSadImportSpecificTopicItemAvgifterBeforeCalculationContainer(String utfPayload);
	public JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer getSadImportSpecificTopicItemAvgifterAfterCalculationContainer(String utfPayload);
	public JsonSadAutoControlErrorContainer getSadImportSpecificTopicItemAutoControlErrorContainer(String utfPayload);
	public JsonSadImportTolltariffKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception;
}
