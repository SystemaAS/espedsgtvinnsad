package no.systema.main.model.jsonjackson;

import java.util.Collection;

public class JsonFirmLoginContainer {
	private String user = null; 
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null; 
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonFirmLoginRecord>list;
	public void setList(Collection<JsonFirmLoginRecord> value){ this.list = value; }
	public Collection<JsonFirmLoginRecord> getList(){ return list; }
	
}
