/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import no.systema.tvinn.sad.nctsimport.mapper.jsonjackson.SadNctsImportTopicListMapper;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2015
 * 
 * 
 */
public class SadNctsImportTopicListServiceImpl implements SadNctsImportTopicListService {

	public JsonSadNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload) {
		JsonSadNctsImportTopicListContainer container = null;
		try{
			SadNctsImportTopicListMapper mapper = new SadNctsImportTopicListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
