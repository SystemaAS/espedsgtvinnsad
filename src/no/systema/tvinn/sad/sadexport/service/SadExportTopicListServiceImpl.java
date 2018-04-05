/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;

import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportTopicListMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 * 
 */
public class SadExportTopicListServiceImpl implements SadExportTopicListService {

	public JsonSadExportTopicListContainer getSadExportTopicListContainer(String utfPayload) {
		JsonSadExportTopicListContainer listContainer = null;
		try{
			SadExportTopicListMapper mapper = new SadExportTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
