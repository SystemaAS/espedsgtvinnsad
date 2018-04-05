/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadCodeMapper;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;



/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Jan 19, 2016
 * 
 */
public class SadImportGeneralCodesChildWindowServiceImpl implements SadImportGeneralCodesChildWindowService {
	private TvinnSadCodeMapper codeMapper = new TvinnSadCodeMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	/*
	public JsonTvinnSadNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getNctsContainer(utfPayload);
	}
	*/
	
}
