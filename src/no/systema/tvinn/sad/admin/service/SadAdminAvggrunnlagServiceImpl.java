/**
 * 
 */
package no.systema.tvinn.sad.admin.service;

import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminAvggrunnlagListContainer;
import no.systema.tvinn.sad.admin.mapper.jsonjackson.SadAdminAvggrunnlagListMapper;

/**
 * @author oscardelatorre
 * @date Sep 21, 2014
 * 
 */
public class SadAdminAvggrunnlagServiceImpl implements SadAdminAvggrunnlagService {
	
	/**
	 * Avgiftsgrunnlag
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSadAdminAvggrunnlagListContainer getSadAdminAvggrunnlagListContainer(String utfPayload){
		JsonSadAdminAvggrunnlagListContainer container = null;
		try{
			SadAdminAvggrunnlagListMapper mapper = new SadAdminAvggrunnlagListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
}
