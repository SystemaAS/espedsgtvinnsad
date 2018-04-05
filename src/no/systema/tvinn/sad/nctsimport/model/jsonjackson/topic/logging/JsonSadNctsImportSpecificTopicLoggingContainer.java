/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicLoggingContainer {
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
	
	private Collection<JsonSadNctsImportSpecificTopicLoggingRecord> logg;
	public void setLogg(Collection<JsonSadNctsImportSpecificTopicLoggingRecord> value){ this.logg = value; }
	public Collection<JsonSadNctsImportSpecificTopicLoggingRecord> getLogg(){ return logg; }
	
	
}
