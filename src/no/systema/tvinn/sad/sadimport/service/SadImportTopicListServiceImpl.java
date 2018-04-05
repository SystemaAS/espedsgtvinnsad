/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTopicListMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImportTopicListServiceImpl implements SadImportTopicListService {

	public JsonSadImportTopicListContainer getSadImportTopicListContainer(String utfPayload) {
		JsonSadImportTopicListContainer listContainer = null;
		try{
			SadImportTopicListMapper mapper = new SadImportTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
