package no.systema.main.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * Representation of the user applications
 * This records contains the valid applications for a Systema user extension (archive).
 * The number of valid applications corresponds to the number of menu choices in the dashboard
 *  
 * @author oscardelatorre
 *
 */

public class JsonSystemaUserExtensionsArchiveRecord extends JsonAbstractGrandFatherRecord {
	
	private String arkKod = null; 
	public void setArkKod(String value) {  this.arkKod = value; }
	public String getArkKod() { return this.arkKod;}

	private String arkTxt = null;
	public void setArkTxt(String value) {  this.arkTxt = value; }
	public String getArkTxt() { return this.arkTxt;}
	
	
	/**
	 * Required for java reflection in other classes
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
