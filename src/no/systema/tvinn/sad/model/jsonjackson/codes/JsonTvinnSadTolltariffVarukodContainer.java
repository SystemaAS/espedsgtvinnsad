/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.codes;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadTolltariffVarukodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String kod 	 = null;
	public void setKod(String value){ this.kod = value;}
	public String getKod(){ return this.kod; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadTolltariffVarukodRecord> tariclist = null;
	public void setTariclist(Collection<JsonTvinnSadTolltariffVarukodRecord> value){ this.tariclist = value;}
	public Collection<JsonTvinnSadTolltariffVarukodRecord> getTariclist(){ return this.tariclist; }
}
