/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jan 14, 2018
 * 
 */
public class JsonSadImportSpecificTopicSendParametersRecord extends JsonAbstractGrandFatherRecord{
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	

	//Send function extra parameters
	private String m1N07 = null;
	public void setM1N07(String value) {  this.m1N07 = value; }
	public String getM1N07() {return this.m1N07;}
	
	private String m3039e = null;
	public void setM3039e(String value) {  this.m3039e = value; }
	public String getM3039e() {return this.m3039e;}
	
	private String m2005b = null;
	public void setM2005b(String value) {  this.m2005b = value; }
	public String getM2005b() {return this.m2005b;}
	
	private String m5004d = null;
	public void setM5004d(String value) {  this.m5004d = value; }
	public String getM5004d() {return this.m5004d;}
	
	private String mven = null;
	public void setMven(String value) {  this.mven = value; }
	public String getMven() {return this.mven;}
	
	private String m0035 = null;
	public void setM0035(String value) {  this.m0035 = value; }
	public String getM0035() {return this.m0035;}
	
	private String m9n01 = null;
	public void setM9n01(String value) {  this.m9n01 = value; }
	public String getM9n01() {return this.m9n01;}
	
	
		
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
