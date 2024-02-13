/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Aug, 2023
 */
public class SadmotfContainer <T> {
	//public static final int LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS = 300;
	public static final int LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS = 2000;
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<SadmotfRecord> list = null;
	public void setList(Collection<SadmotfRecord> value){ this.list = value;}
	public Collection<SadmotfRecord> getList(){ return this.list; }
	
	
}
