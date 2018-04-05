/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 29, 2016
 *
 */
public class JsonMaintSadFellesKodtlbContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadFellesKodtlbRecord> list;
	public void setList(Collection<JsonMaintSadFellesKodtlbRecord> value){ this.list = value; }
	public Collection<JsonMaintSadFellesKodtlbRecord> getList(){ return list; }
	
}
