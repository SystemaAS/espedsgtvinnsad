/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadTolltariffVarukodMapper;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadTolltariffVarukodContainer;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTolltariffKundensRegisterVarukodMapper;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportTolltariffKundensRegisterVarukodContainer;

/**
 * This service could be moved to the service.html.dropdown package but we do not know if the Taric varukod
 * will be render as list or maybe as a tree. Could be a table. We do separate this service outside the html.dropdown package...
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class TvinnSadTolltariffVarukodServiceImpl implements TvinnSadTolltariffVarukodService {
	private TvinnSadTolltariffVarukodMapper tolltariffMapper = new TvinnSadTolltariffVarukodMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 * 
	 */
	public JsonTvinnSadTolltariffVarukodContainer getContainer(String utfPayload) throws Exception{
		return this.tolltariffMapper.getContainer(utfPayload);
	}
	
	
}
