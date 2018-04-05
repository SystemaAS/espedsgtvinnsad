/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 23, 2016
 *
 */
public class JsonMaintSadImportKodts5Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadImportKodts5Record> list;
	public void setList(Collection<JsonMaintSadImportKodts5Record> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodts5Record> getList(){ return list; }
	
}
