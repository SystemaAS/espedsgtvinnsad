/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.filter;

import java.lang.reflect.Field;
import java.util.*;
import lombok.Data;
import org.apache.log4j.Logger;

/**
 * 
 * @author oscardelatorre
 * @date   Sep 24, 2018
 */
@Data
public class SearchFilterManifestList {
	private static final Logger logger = Logger.getLogger(SearchFilterManifestList.class.getName());
	
	private String avd = null;
	private String opd = null;
	private String turnr = null;
	private String sign = null;
	private String datum = null;
	private String datumt = null;
	
	
	
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
