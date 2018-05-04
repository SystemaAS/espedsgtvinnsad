/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadGodsnrMapper;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrContainer;
import no.systema.tvinn.sad.model.jsonjackson.JsonTvinnSadGodsnrListContainer;


/**
 * This service could be moved to the service.html.dropdown package but we do not know if the Taric varukod
 * will be render as list or maybe as a tree. Could be a table. We do separate this service outside the html.dropdown package...
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class TvinnSadGodsnrServiceImpl implements TvinnSadGodsnrService {
	private TvinnSadGodsnrMapper godsnrMapper = new TvinnSadGodsnrMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 * 
	 */
	public JsonTvinnSadGodsnrContainer getContainer(String utfPayload) throws Exception{
		return this.godsnrMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 */
	public JsonTvinnSadGodsnrListContainer getContainerGodsnrList(String utfPayload) throws Exception{
		return this.godsnrMapper.getContainerGodsnrList(utfPayload);
	}
	
}
