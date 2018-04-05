/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Aug 16, 2016
 *
 */
public class JsonMaintSadExportKodts6Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadExportKodts6Record> list;
	public void setList(Collection<JsonMaintSadExportKodts6Record> value){ this.list = value; }
	public Collection<JsonMaintSadExportKodts6Record> getList(){ return list; }
	
}
