/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.tullkontor.JsonTvinnSadTullkontorContainer;
import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadTullkontorMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public class TvinnSadTullkontorServiceImpl implements TvinnSadTullkontorService{
	public JsonTvinnSadTullkontorContainer getTvinnSadTullkontorContainer(String utfPayload) {
		JsonTvinnSadTullkontorContainer container = null;
		try{
			TvinnSadTullkontorMapper mapper = new TvinnSadTullkontorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
