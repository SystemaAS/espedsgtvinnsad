/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadNctsImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSadNctsImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSadNctsImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
