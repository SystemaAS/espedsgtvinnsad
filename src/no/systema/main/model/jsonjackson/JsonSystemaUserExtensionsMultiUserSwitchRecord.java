package no.systema.main.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * Representation of the user applications
 * This records contains the valid applications for a Systema user extension - multiuser.
 *  
 * @author oscardelatorre
 * @date May 3:rd, 2017
 */

public class JsonSystemaUserExtensionsMultiUserSwitchRecord extends JsonAbstractGrandFatherRecord {
	
	private String multiID = null; 
	public void setMultiID(String value) {  this.multiID = value; }
	public String getMultiID() { return this.multiID;}

	private String multiTxt = null;
	public void setMultiTxt(String value) {  this.multiTxt = value; }
	public String getMultiTxt() { return this.multiTxt;}
	
	
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
