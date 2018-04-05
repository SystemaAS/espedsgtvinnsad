/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadNctsExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSadNctsExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSadNctsExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
