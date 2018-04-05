/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.service;

import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;
import no.systema.tvinn.sad.kundekontroll.brreg.mapper.BrregEnhetsRegisteretMapper;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 26, 2016
 * 
 * 
 */
public class BrregEnhetsRegisteretServiceImpl implements BrregEnhetsRegisteretService {
	/**
	 * 
	 */
	public JsonEnhetsRegisteretDataCheckContainer getList(String utfPayload) {
		JsonEnhetsRegisteretDataCheckContainer container = null;
		try{
			BrregEnhetsRegisteretMapper mapper = new BrregEnhetsRegisteretMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
