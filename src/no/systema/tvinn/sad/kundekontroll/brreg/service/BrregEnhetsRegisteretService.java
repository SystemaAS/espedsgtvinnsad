/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.service;

import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 26, 2016
 * 
 *
 */
public interface BrregEnhetsRegisteretService {
	public JsonEnhetsRegisteretDataCheckContainer getList(String utfPayload);
}
