/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.customer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Sep 25, 2014
 * 
 */
public class JsonTvinnSadCustomerInfoFreeTextRecord {
	
	private String fxtxt = null;
	public void setFxtxt(String value){ this.fxtxt = value;}
	public String getFxtxt(){ return this.fxtxt; }
	
	private String delsys = null;
	public void setDelsys(String value){ this.delsys = value;}
	public String getDelsys(){ return this.delsys; }
	
	
	/**
	 * User for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
