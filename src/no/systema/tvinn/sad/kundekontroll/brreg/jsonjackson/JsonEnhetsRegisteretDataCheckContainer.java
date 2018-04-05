/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Sep 26, 2016
 *
 */
public class JsonEnhetsRegisteretDataCheckContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonEnhetsRegisteretDataCheckRecord> list;
	public void setList(Collection<JsonEnhetsRegisteretDataCheckRecord> value){ this.list = value; }
	public Collection<JsonEnhetsRegisteretDataCheckRecord> getList(){ return list; }
	
}
