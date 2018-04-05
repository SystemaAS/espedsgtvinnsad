/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 26, 2016
 *
 */
public class JsonMaintSadFellesSoktariContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadFellesSoktariRecord> list;
	public void setList(Collection<JsonMaintSadFellesSoktariRecord> value){ this.list = value; }
	public Collection<JsonMaintSadFellesSoktariRecord> getList(){ return list; }
	
}
