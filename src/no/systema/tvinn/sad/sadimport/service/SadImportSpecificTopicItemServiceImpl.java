/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadAutoControlErrorMapper;
import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;

import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportSpecificTopicItemMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTolltariffKundensRegisterVarukodMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference.JsonSadImportSpecificTopicItemPreferenceContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva.JsonSadImportSpecificTopicItemPvaContainer;


/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public class SadImportSpecificTopicItemServiceImpl implements SadImportSpecificTopicItemService{
	private SadImportTolltariffKundensRegisterVarukodMapper kundensRegisterVarukodMapper = new SadImportTolltariffKundensRegisterVarukodMapper();
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadImportSpecificTopicItemContainer getSadImportSpecificTopicItemContainer(String utfPayload) {
		JsonSadImportSpecificTopicItemContainer container = null;
		try{
			SadImportSpecificTopicItemMapper mapper = new SadImportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadImportSpecificTopicItemPreferenceContainer getSadImportSpecificTopicItemPreferenceContainer(String utfPayload) {
		JsonSadImportSpecificTopicItemPreferenceContainer container = null;
		try{
			SadImportSpecificTopicItemMapper mapper = new SadImportSpecificTopicItemMapper();
			container = mapper.getPreferenceContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadImportSpecificTopicItemPvaContainer getSadImportSpecificTopicItemPvaContainer(String utfPayload) {
		JsonSadImportSpecificTopicItemPvaContainer container = null;
		try{
			SadImportSpecificTopicItemMapper mapper = new SadImportSpecificTopicItemMapper();
			container = mapper.getPvaContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	
	/**
	 * Maps the avgifter
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	
	public JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer getSadImportSpecificTopicItemAvgifterBeforeCalculationContainer(String utfPayload) {
		JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer container = null;
		try{
			SadImportSpecificTopicItemMapper mapper = new SadImportSpecificTopicItemMapper();
			container = mapper.getAvgifterBeforeCalculationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer getSadImportSpecificTopicItemAvgifterAfterCalculationContainer(String utfPayload) {
		JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer container = null;
		try{
			SadImportSpecificTopicItemMapper mapper = new SadImportSpecificTopicItemMapper();
			container = mapper.getAvgifterAfterCalculationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadAutoControlErrorContainer getSadImportSpecificTopicItemAutoControlErrorContainer(String utfPayload){
		JsonSadAutoControlErrorContainer container= null;
		try{
			TvinnSadAutoControlErrorMapper mapper = new TvinnSadAutoControlErrorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadImportTolltariffKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception{
		return this.kundensRegisterVarukodMapper.getContainer(utfPayload);
	}
	
	
	
}
