/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.filter;

import java.lang.reflect.Field;
import java.util.*;
import lombok.Data;
import org.slf4j.*;

/**
 * 
 * @author oscardelatorre
 * @date   Sep  2023
 */
@Data
public class SearchFilterDigitollTransportList {
	private static final Logger logger = LoggerFactory.getLogger(SearchFilterDigitollTransportList.class.getName());
	
	private String avd = null;
	private String opd = null;
	private String turnr = null;
	private String sign = null;
	private String etaDatum = null;
	private String etaDatumt = null;
	private String datum = null;
	private String datumt = null;
	private String status = null;
	
	
	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			//logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				//logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
}
