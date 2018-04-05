/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;

/**
 * @author oscardelatorre
 * @date Jan 19, 2016
 * 
 */
public interface SadExportGeneralCodesChildWindowService {
	public JsonTvinnSadCodeContainer getCodeContainer(String utfPayload) throws Exception;
}
