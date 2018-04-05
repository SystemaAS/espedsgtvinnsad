/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicItemMapper;
import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.validation.JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.JsonSadNctsExportSpecificTopicItemSecurityContainer;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class SadNctsExportSpecificTopicItemServiceImpl implements SadNctsExportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload) {
		JsonSadNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer = null;
		try{
			SadNctsExportSpecificTopicItemMapper nctsExportSpecificTopicItemMapper = new SadNctsExportSpecificTopicItemMapper();
			jsonNctsExportSpecificTopicItemContainer = nctsExportSpecificTopicItemMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicItemContainer;
		
	}

	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload){
		JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = null;
		try{
			SadNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper mapper = new SadNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonSadNctsExportSpecificTopicItemSecurityContainer getNctsExportSpecificTopicItemSecurityContainer(String utfPayload){
		JsonSadNctsExportSpecificTopicItemSecurityContainer container = null;
		try{
			SadNctsExportSpecificTopicItemMapper mapper = new SadNctsExportSpecificTopicItemMapper();
			container = mapper.getSecurityContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
