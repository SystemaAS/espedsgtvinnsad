/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicLoggingLargeTextRecord {
	
	
	private String f0078a = null;
	public void setF0078a(String value) {  this.f0078a = value; }
	public String getF0078a() {return this.f0078a;}
	
	private String f0078b = null;
	public void setF0078b(String value) {  this.f0078b = value; }
	public String getF0078b() {return this.f0078b;}
	
	private String f0078c = null;
	public void setF0078c(String value) {  this.f0078c = value; }
	public String getF0078c() {return this.f0078c;}
	
	private String f0078d = null;
	public void setF0078d(String value) {  this.f0078d = value; }
	public String getF0078d() {return this.f0078d;}
	
	private String f0078e = null;
	public void setF0078e(String value) {  this.f0078e = value; }
	public String getF0078e() {return this.f0078e;}
	
	
	/**
	 * Used for java reflection in other classes
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
