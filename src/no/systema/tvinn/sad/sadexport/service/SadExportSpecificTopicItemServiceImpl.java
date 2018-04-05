/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadAutoControlErrorMapper;
import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicItemMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemAvgifterContainer;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportTolltariffKundensRegisterVarukodMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportTolltariffKundensRegisterVarukodContainer;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 *
 */
public class SadExportSpecificTopicItemServiceImpl implements SadExportSpecificTopicItemService{
	private SadExportTolltariffKundensRegisterVarukodMapper kundensRegisterVarukodMapper = new SadExportTolltariffKundensRegisterVarukodMapper();
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadExportSpecificTopicItemContainer getSadExportSpecificTopicItemContainer(String utfPayload) {
		JsonSadExportSpecificTopicItemContainer container = null;
		try{
			SadExportSpecificTopicItemMapper mapper = new SadExportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
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
	
	public JsonSadExportSpecificTopicItemAvgifterContainer getSadExportSpecificTopicItemAvgifterContainer(String utfPayload) {
		JsonSadExportSpecificTopicItemAvgifterContainer container = null;
		try{
			SadExportSpecificTopicItemMapper mapper = new SadExportSpecificTopicItemMapper();
			container = mapper.getAvgifterContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSadAutoControlErrorContainer getSadExportSpecificTopicItemAutoControlErrorContainer(String utfPayload){
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
	public JsonSadExportTolltariffKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception{
		return this.kundensRegisterVarukodMapper.getContainer(utfPayload);
	}

}
