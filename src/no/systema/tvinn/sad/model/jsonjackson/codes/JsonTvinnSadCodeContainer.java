/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.codes;

import java.util.Collection;


/**
 * General Code Container for Tvinn general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Jun 13, 2014
 *
 */
public class JsonTvinnSadCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String typ = null;
	public void setTyp(String value){ this.typ = value;}
	public String getTyp(){ return this.typ; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadCodeRecord> kodlista = null;
	public void setKodlista(Collection<JsonTvinnSadCodeRecord> value){ this.kodlista = value;}
	public Collection<JsonTvinnSadCodeRecord> getKodlista(){ return this.kodlista; }
	
}
