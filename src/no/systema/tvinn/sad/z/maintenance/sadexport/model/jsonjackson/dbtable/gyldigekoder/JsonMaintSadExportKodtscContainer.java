/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Okt 26, 2016
 *
 */
public class JsonMaintSadExportKodtscContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadExportKodtscRecord> list;
	public void setList(Collection<JsonMaintSadExportKodtscRecord> value){ this.list = value; }
	public Collection<JsonMaintSadExportKodtscRecord> getList(){ return list; }
	
}
