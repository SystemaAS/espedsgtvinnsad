/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportSpecificTopicItemMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items.JsonSadNctsImportSpecificTopicItemContainer;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class SadNctsImportSpecificTopicItemServiceImpl implements SadNctsImportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload) {
		JsonSadNctsImportSpecificTopicItemContainer container = null;
		try{
			SadNctsImportSpecificTopicItemMapper mapper = new SadNctsImportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
