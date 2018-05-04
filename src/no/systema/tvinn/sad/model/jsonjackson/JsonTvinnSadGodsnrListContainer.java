/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 4, 2018
 */
public class JsonTvinnSadGodsnrListContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ugn = null;
	public void setUgn(String value){ this.ugn = value;}
	public String getUgn(){ return this.ugn; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadGodsnrListRecord> godsliste = null;
	public void setGodsliste(Collection<JsonTvinnSadGodsnrListRecord> value){ this.godsliste = value;}
	public Collection<JsonTvinnSadGodsnrListRecord> getGodsliste(){ return this.godsliste; }
}
