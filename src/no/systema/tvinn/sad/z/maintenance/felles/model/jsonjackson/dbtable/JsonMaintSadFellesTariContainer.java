/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Okt 25, 2016
 *
 */
public class JsonMaintSadFellesTariContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadFellesTariRecord> list;
	public void setList(Collection<JsonMaintSadFellesTariRecord> value){ this.list = value; }
	public Collection<JsonMaintSadFellesTariRecord> getList(){ return list; }
	
}
