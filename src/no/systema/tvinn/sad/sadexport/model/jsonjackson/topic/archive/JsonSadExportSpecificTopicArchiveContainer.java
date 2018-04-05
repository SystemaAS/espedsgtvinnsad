/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 *
 */
public class JsonSadExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSadExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSadExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
