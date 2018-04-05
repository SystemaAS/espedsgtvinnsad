/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextContainer;
import no.systema.tvinn.sad.mapper.jsonjackson.TvinnSadCustomerMapper;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public class TvinnSadCustomerServiceImpl implements TvinnSadCustomerService{
	public JsonTvinnSadCustomerContainer getTvinnSadCustomerContainer(String utfPayload) {
		JsonTvinnSadCustomerContainer container = null;
		try{
			TvinnSadCustomerMapper mapper = new TvinnSadCustomerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTvinnSadCustomerInfoFreeTextContainer getTvinnSadCustomerInfoFreeTextContainer(String utfPayload){
		JsonTvinnSadCustomerInfoFreeTextContainer container = null;
		try{
			TvinnSadCustomerMapper mapper = new TvinnSadCustomerMapper();
			container = mapper.getContainerInfoFreeText(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
}
