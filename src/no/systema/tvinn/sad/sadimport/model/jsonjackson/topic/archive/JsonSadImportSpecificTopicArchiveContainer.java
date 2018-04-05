/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 *
 */
public class JsonSadImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSadImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSadImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
