/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jan 23, 2015
 *
 */
public class JsonSadImportTopicIncotermsAttributesContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() { return this.kode;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadImportTopicIncotermsAttributesRecord> levvillkar;
	public void setLevvillkar(Collection<JsonSadImportTopicIncotermsAttributesRecord> value){ this.levvillkar = value; }
	public Collection<JsonSadImportTopicIncotermsAttributesRecord> getLevvillkar(){ return levvillkar; }
	
}
