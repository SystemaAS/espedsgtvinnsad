/**
 * 
 */
package no.systema.main.service.general;

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.mapper.jsonjackson.general.CurrencyRateMapper;

/**
 * 
 * @author oscardelatorre
 *
 */
public class CurrencyRateServiceImpl implements CurrencyRateService{
	public JsonCurrencyRateContainer getCurrencyRateContainer(String utfPayload) {
		JsonCurrencyRateContainer jsonCurrencyRateContainer = null;
		try{
			CurrencyRateMapper currencyRateMapper = new CurrencyRateMapper();
			jsonCurrencyRateContainer = currencyRateMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonCurrencyRateContainer;
		
	}
	
}
