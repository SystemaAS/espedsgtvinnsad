/**
 * 
 */
package no.systema.tvinn.sad.service;

import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerInfoFreeTextContainer;


/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 *
 */
public interface TvinnSadCustomerService {
	public JsonTvinnSadCustomerContainer getTvinnSadCustomerContainer(String utfPayload);
	public JsonTvinnSadCustomerInfoFreeTextContainer getTvinnSadCustomerInfoFreeTextContainer(String utfPayload);
	
}
