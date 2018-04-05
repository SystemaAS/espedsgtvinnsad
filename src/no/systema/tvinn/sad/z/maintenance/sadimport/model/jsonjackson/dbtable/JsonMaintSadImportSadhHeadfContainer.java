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
public class JsonMaintSadImportSadhHeadfContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportSadhHeadfRecord> list;
	public void setList(Collection<JsonMaintSadImportSadhHeadfRecord> value){ this.list = value; }
	public Collection<JsonMaintSadImportSadhHeadfRecord> getList(){ return list; }
	
}
