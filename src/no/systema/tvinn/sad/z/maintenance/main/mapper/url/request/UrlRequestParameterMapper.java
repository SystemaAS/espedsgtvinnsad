/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;


/**
 * @author oscardelatorre
 * @param Jun 7, 2016
 */
public class UrlRequestParameterMapper {
	private static final Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(JsonAbstractGrandFatherRecord object){
		StringBuffer sb = new StringBuffer();
		
		try{
			for(Field field: object.getFields()){
				try{
					if(field!=null){
						field.setAccessible(true);//we must do this in order to access private fields
						String value = (String)field.get(object);
						//DEBUG
						//logger.info("FIELD:" + field.getName());
						//logger.info("VALUE: " + value);
						if(value==null){
							sb.append("");
						}else{
							//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
							//& will be converted into "%26", %="%25", etc. 
							//Refer to URLEncode special characters for further info)
							value = URLEncoder.encode(value, "UTF-8");
							
							sb.append(TvinnSadMaintenanceConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value.trim());
						}
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(TvinnSadMaintenanceConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(TvinnSadMaintenanceConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
						
					}else{
						logger.info(" Class: " + object.getClass().getName());
						logger.info(" [INFO]data type not yet supported...");
					}
					//add more instances if you need...					
										
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	
}
