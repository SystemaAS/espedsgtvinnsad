/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 2, 2016
 *
 */
public class JsonMaintSadImportCundfLikvKodeContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportCundfLikvKodeRecord> list;
	public void setList(Collection<JsonMaintSadImportCundfLikvKodeRecord> value){ this.list = value; }
	public Collection<JsonMaintSadImportCundfLikvKodeRecord> getList(){ return list; }
	
}
