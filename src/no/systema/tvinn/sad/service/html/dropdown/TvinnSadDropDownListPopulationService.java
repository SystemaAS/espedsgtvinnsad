/**
 * 
 */
package no.systema.tvinn.sad.service.html.dropdown;

import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadCodeMapper;
import no.systema.tvinn.sad.mapper.jsonjackson.avdsignature.TvinnSadAvdelningMapper;
import no.systema.tvinn.sad.mapper.jsonjackson.avdsignature.TvinnSadSignatureMapper;


import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCode2Container;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;



/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 */
public class TvinnSadDropDownListPopulationService {
	private TvinnSadCodeMapper codeMapper = new TvinnSadCodeMapper();
	private TvinnSadAvdelningMapper avdMapper = new TvinnSadAvdelningMapper();
	private TvinnSadSignatureMapper signMapper = new TvinnSadSignatureMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadCode2Container getCodeContainer2(String utfPayload) throws Exception{
		return this.codeMapper.getContainer2(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getNctsContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadAvdelningContainer getAvdelningContainer(String utfPayload) throws Exception{
		return this.avdMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTvinnSadSignatureContainer getSignatureContainer(String utfPayload) throws Exception{
		return this.signMapper.getContainer(utfPayload);
	}
}
