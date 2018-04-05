/**
 * 
 */
package no.systema.tvinn.sad.admin.service;

import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminAvggrunnlagListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 21, 2014
 * 
 */
public interface SadAdminAvggrunnlagService {
	public JsonSadAdminAvggrunnlagListContainer getSadAdminAvggrunnlagListContainer(String utfPayload);
	
}
