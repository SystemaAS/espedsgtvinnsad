/**
 * 
 */
package no.systema.main.service.general;

import org.springframework.core.io.Resource;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 *
 */
public interface CurrencyRateService {
	public JsonCurrencyRateContainer getCurrencyRateContainer(String utfPayload);
	
}
