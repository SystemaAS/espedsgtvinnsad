/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 20, 2016
 *
 */
public class JsonMaintSadImportKodttsContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportKodttsRecord> list;
	public void setList(Collection<JsonMaintSadImportKodttsRecord> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodttsRecord> getList(){ return list; }
	
}
