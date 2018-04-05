/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadSignatureNameContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadSignatureNameRecord> getsignname = null;
	public void setGetsignname(Collection<JsonTvinnSadSignatureNameRecord> value){ this.getsignname = value;}
	public Collection<JsonTvinnSadSignatureNameRecord> getGetsignname(){ return this.getsignname; }
}
