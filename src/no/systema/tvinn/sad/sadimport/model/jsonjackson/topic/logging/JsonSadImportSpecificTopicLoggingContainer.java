/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 *
 */
public class JsonSadImportSpecificTopicLoggingContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String typ = null;
	public void setTyp(String value) {  this.typ = value; }
	public String getTyp() { return this.typ;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicLoggingRecord> logg;
	public void setLogg(Collection<JsonSadImportSpecificTopicLoggingRecord> value){ this.logg = value; }
	public Collection<JsonSadImportSpecificTopicLoggingRecord> getLogg(){ return logg; }
	
	
}
