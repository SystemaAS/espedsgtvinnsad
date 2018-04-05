/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Aug 9, 2016
 *
 */
public class JsonMaintSadExportTvineContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadExportTvineRecord> list;
	public void setList(Collection<JsonMaintSadExportTvineRecord> value){ this.list = value; }
	public Collection<JsonMaintSadExportTvineRecord> getList(){ return list; }
	
}
