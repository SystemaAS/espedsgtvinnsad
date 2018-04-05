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
public class JsonMaintSadImportKodts1Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportKodts1Record> list;
	public void setList(Collection<JsonMaintSadImportKodts1Record> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodts1Record> getList(){ return list; }
	
}
