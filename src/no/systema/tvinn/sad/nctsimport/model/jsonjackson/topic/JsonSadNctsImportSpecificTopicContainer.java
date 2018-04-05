/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 * 
 * 
 */
public class JsonSadNctsImportSpecificTopicContainer {
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
	
	private Collection<JsonSadNctsImportSpecificTopicRecord> oneorder;
	public void setOneorder(Collection<JsonSadNctsImportSpecificTopicRecord> value){ this.oneorder = value; }
	public Collection<JsonSadNctsImportSpecificTopicRecord> getOneorder(){ return oneorder; }
	
	
}
