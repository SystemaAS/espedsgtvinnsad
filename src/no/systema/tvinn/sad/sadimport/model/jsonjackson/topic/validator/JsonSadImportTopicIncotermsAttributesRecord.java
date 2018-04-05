/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;

/**
 * @author oscardelatorre
 * @date Jan 23, 2015
 *
 */
public class JsonSadImportTopicIncotermsAttributesRecord extends JsonAbstractGrandFatherRecord {
	
	private String klbkod = null;
	public void setKlbkod(String value) {  this.klbkod = value; }
	public String getKlbkod() { return this.klbkod;}
	
	private String klbkt = null;
	public void setKlbkt(String value) {  this.klbkt = value; }
	public String getKlbkt() { return this.klbkt;}
	
	private String klbnav = null;
	public void setKlbnav(String value) {  this.klbnav = value; }
	public String getKlbnav() { return this.klbnav;}
	
	private String klbfok = null;
	public void setKlbfok(String value) {  this.klbfok = value; }
	public String getKlbfok() { return this.klbfok;}
	
	private String klbprm = null;
	public void setKlbprm(String value) {  this.klbprm = value; }
	public String getKlbprm() { return this.klbprm;}
	
	private String klbfrk = null;
	public void setKlbfrk(String value) {  this.klbfrk = value; }
	public String getKlbfrk() { return this.klbfrk;}
	
	private String klbhad = null;
	public void setKlbhad(String value) {  this.klbhad = value; }
	public String getKlbhad() { return this.klbhad;}
	
	
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
