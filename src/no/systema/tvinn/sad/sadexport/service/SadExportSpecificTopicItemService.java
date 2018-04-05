/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;

import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemAvgifterContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportTolltariffKundensRegisterVarukodContainer;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 *
 */
public interface SadExportSpecificTopicItemService {
	
	public JsonSadExportSpecificTopicItemContainer getSadExportSpecificTopicItemContainer(String utfPayload);
	public JsonSadExportSpecificTopicItemAvgifterContainer getSadExportSpecificTopicItemAvgifterContainer(String utfPayload);
	public JsonSadAutoControlErrorContainer getSadExportSpecificTopicItemAutoControlErrorContainer(String utfPayload);
	
	public JsonSadExportTolltariffKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception;
	
}
