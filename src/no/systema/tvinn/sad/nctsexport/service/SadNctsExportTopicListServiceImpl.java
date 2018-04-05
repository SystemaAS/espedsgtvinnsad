/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service;

import no.systema.tvinn.sad.nctsexport.mapper.jsonjackson.SadNctsExportTopicListMapper;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public class SadNctsExportTopicListServiceImpl implements SadNctsExportTopicListService {

	public JsonSadNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload) {
		JsonSadNctsExportTopicListContainer jsonNctsExportTopicListContainer = null;
		try{
			SadNctsExportTopicListMapper nctsExportTopicListMapper = new SadNctsExportTopicListMapper();
			jsonNctsExportTopicListContainer = nctsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportTopicListContainer;
		
	}

}
