/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 25, 2015
 * 
 *
 */
public class JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord  extends JsonAbstractGrandFatherRecord   {
	
	private String wi = null;
	public void setWi(String value) {  this.wi = value; }
	public String getWi() { return this.wi;}
	
	private String wj = null;
	public void setWj(String value) {  this.wj = value; }
	public String getWj() { return this.wj;}
	
	//This attributes will be filled out from the container record and ergo not automatically as: wi & wj
	private String wg = null;
	public void setWg(String value) {  this.wg = value; }
	public String getWg() { return this.wg;}

	private String wh = null;
	public void setWh(String value) {  this.wh = value; }
	public String getWh() { return this.wh;}

	private String wk = null;
	public void setWk(String value) {  this.wk = value; }
	public String getWk() { return this.wk;}

	/**
	 * 
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
