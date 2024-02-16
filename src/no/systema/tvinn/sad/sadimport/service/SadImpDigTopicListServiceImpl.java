/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImpDigTopicListMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImpDigTopicListServiceImpl implements SadImpDigTopicListService {

	public SadImpDigContainer getSadImpDigContainer(String utfPayload) {
		SadImpDigContainer listContainer = null;
		try{
			SadImpDigTopicListMapper mapper = new SadImpDigTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
