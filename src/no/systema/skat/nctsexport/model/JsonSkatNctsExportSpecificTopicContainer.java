/**
 * 
 */
package no.systema.skat.nctsexport.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public class JsonSkatNctsExportSpecificTopicContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatNctsExportSpecificTopicRecord> oneorder;
	public void setOneorder(Collection<JsonSkatNctsExportSpecificTopicRecord> value){ this.oneorder = value; }
	public Collection<JsonSkatNctsExportSpecificTopicRecord> getOneorder(){ return oneorder; }
	
	private Collection<JsonSkatNctsExportSpecificTopicRecord> securityhead;
	public void setSecurityhead(Collection<JsonSkatNctsExportSpecificTopicRecord> value){ this.securityhead = value; }
	public Collection<JsonSkatNctsExportSpecificTopicRecord> getSecurityhead(){ return securityhead; }
	
	//required for JSON auto-fill in
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
