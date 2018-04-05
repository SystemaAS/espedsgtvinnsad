/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 20, 2016
 *
 */
public class JsonMaintSadImportKodts4Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadImportKodts4Record> list;
	public void setList(Collection<JsonMaintSadImportKodts4Record> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodts4Record> getList(){ return list; }
	
}
