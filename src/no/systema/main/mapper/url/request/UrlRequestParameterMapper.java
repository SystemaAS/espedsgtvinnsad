/**
 * 
 */
package no.systema.main.mapper.url.request;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;
import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @param Feb 19, 2013
 */
public class UrlRequestParameterMapper {
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonSystemaUserRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				field.setAccessible(true);//we must do this in order to access private fields
				sb.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					sb.append(value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	
}
