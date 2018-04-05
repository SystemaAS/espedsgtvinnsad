/**
 * 
 */
package no.systema.skat.nctsexport.model;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public class JsonSkatNctsExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatNctsExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSkatNctsExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSkatNctsExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
