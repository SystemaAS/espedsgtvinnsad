/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading;

import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public class JsonSadNctsImportSpecificTopicUnloadingContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	//for fetch
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	//for update
	private String tiavd = null;
	public void setTiavd(String value) {  this.tiavd = value; }
	public String getTiavd() { return this.tiavd;}
	
	//for fetch
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	//for update
	private String titdn = null;
	public void setTitdn(String value) {  this.titdn = value; }
	public String getTitdn() { return this.titdn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonSadNctsImportSpecificTopicUnloadingRecord> oneorder;
	public void setOneorder(Collection<JsonSadNctsImportSpecificTopicUnloadingRecord> value){ this.oneorder = value; }
	public Collection<JsonSadNctsImportSpecificTopicUnloadingRecord> getOneorder(){ return oneorder; }
	
}
