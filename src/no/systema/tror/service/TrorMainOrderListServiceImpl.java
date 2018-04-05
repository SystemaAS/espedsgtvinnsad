/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.JsonTrorOrderListMapper;
import no.systema.tror.model.JsonTrorOrderListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 * 
 */
public class TrorMainOrderListServiceImpl implements TrorMainOrderListService {

	/**
	 * 
	 */
	public JsonTrorOrderListContainer getMainListContainer(String utfPayload) {
		JsonTrorOrderListContainer container = null;
		try{
			JsonTrorOrderListMapper mapper = new JsonTrorOrderListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}


}
