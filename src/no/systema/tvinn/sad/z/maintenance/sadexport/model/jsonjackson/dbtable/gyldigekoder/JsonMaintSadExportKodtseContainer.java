/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 24, 2016
 *
 */
public class JsonMaintSadExportKodtseContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadExportKodtseRecord> list;
	public void setList(Collection<JsonMaintSadExportKodtseRecord> value){ this.list = value; }
	public Collection<JsonMaintSadExportKodtseRecord> getList(){ return list; }
	
}
