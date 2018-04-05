/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 7, 2016
 *
 */
public class JsonMaintKodtvaContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintKodtvaRecord> list;
	public void setList(Collection<JsonMaintKodtvaRecord> value){ this.list = value; }
	public Collection<JsonMaintKodtvaRecord> getList(){ return list; }
	
}
