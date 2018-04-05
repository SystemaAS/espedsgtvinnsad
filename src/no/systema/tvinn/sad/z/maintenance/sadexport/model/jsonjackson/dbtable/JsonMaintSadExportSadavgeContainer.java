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
public class JsonMaintSadExportSadavgeContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadExportSadavgeRecord> list;
	public void setList(Collection<JsonMaintSadExportSadavgeRecord> value){ this.list = value; }
	public Collection<JsonMaintSadExportSadavgeRecord> getList(){ return list; }
	
}
