/**
 * 
 */
package no.systema.main.service.login;

import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.mapper.jsonjackson.SystemaUserMapper;
import no.systema.main.mapper.jsonjackson.SystemaUserChangePasswordMapper;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 16, 2013
 */
public class SystemaWebLoginServiceImpl implements SystemaWebLoginService {

	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSystemaUserContainer getSystemaUserContainer(String utfPayload) {
		JsonSystemaUserContainer jsonSystemaUserContainer = null;
		try{
			SystemaUserMapper mapper = new SystemaUserMapper();
			jsonSystemaUserContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonSystemaUserContainer;
		
	}
	
	/**
	 * Used for change password routines 
	 * @param utfPayload
	 * @return
	 */
	public JsonSystemaUserContainer getSystemaUserContainerForPassword(String utfPayload) {
		JsonSystemaUserContainer jsonSystemaUserContainer = null;
		try{
			SystemaUserChangePasswordMapper mapper = new SystemaUserChangePasswordMapper();
			jsonSystemaUserContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonSystemaUserContainer;
		
	}
}

