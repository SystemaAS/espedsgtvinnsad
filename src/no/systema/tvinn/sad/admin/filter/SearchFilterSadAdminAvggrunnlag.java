/**
 * 
 */
package no.systema.tvinn.sad.admin.filter;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import org.apache.log4j.Logger;

/**
 * 
 * @author oscardelatorre
 * @date 	Sep 15, 2016
 */
public class SearchFilterSadAdminAvggrunnlag {
	private static final Logger logger = Logger.getLogger(SearchFilterSadAdminAvggrunnlag.class.getName());
	protected TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String fromDate = null;
	public void setFromDate(String value) {  this.fromDate = value; }
	public String getFromDate() { return this.fromDate;}
	
	private String fromDateNO = null; 
	public void setFromDateNO (String value){ this.fromDateNO = value;   }   
	public String getFromDateNO() {
		if (fromDateNO != null) { // from UI
			return fromDateNO;
		} else { 				// from DB
			return this.dateFormatter.convertToDate_NO(this.fromDate);
		}
	}
	
	private String toDate = null;
	public void setToDate(String value) {  this.toDate = value; }
	public String getToDate() { return this.toDate;}

	private String toDateNO = null; 
	public void setToDateNO (String value){ this.toDateNO = value;   }   
	public String getToDateNO() {
		if (toDateNO != null) { // from UI
			return toDateNO;
		} else { 				// from DB
			return this.dateFormatter.convertToDate_NO(this.toDate);
		}
	}
	
	private String avggCustomerId = null;
	public void setAvggCustomerId(String value) {  this.avggCustomerId = value; }
	public String getAvggCustomerId() { return this.avggCustomerId;}
	
	private String avggCustomerName = null;
	public void setAvggCustomerName(String value) {  this.avggCustomerName = value; }
	public String getAvggCustomerName() { return this.avggCustomerName;}
	
	
	
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
