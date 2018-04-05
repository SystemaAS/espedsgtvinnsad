/**
 * 
 */
package no.systema.main.model.jsonjackson.general.postalcodes;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Feb 21, 2015
 */
public class JsonPostalCodesContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String soklk = null;
	public void setSoklk(String value){ this.soklk = value;}
	public String getSoklk(){ return this.soklk; }
	
	private String soknvn = null;
	public void setSoknvn(String value){ this.soknvn = value;}
	public String getSoknvn(){ return this.soknvn; }
	
	private String sokkod = null;
	public void setSokkod(String value){ this.sokkod = value;}
	public String getSokkod(){ return this.sokkod; }
	
	
	private String varlk = null;
	public void setVarlk(String value){ this.varlk = value;}
	public String getVarlk(){ return this.varlk; }
	
	private String varkod = null;
	public void setVarkod(String value){ this.varkod = value;}
	public String getVarkod(){ return this.varkod; }
	
	private String wst2kod = null;
	public void setWst2kod(String value){ this.wst2kod = value;}
	public String getWst2kod(){ return this.wst2kod; }
	
	private String wskunpa = null;
	public void setWskunpa(String value){ this.wskunpa = value;}
	public String getWskunpa(){ return this.wskunpa; }
	
	private String error = null;
	public void setError(String value){ this.error = value;}
	public String getError(){ return this.error; }
	                 
	private Collection<JsonPostalCodesRecord> postnrlist = null;
	public void setPostnrlist(Collection<JsonPostalCodesRecord> value){ this.postnrlist = value;}
	public Collection<JsonPostalCodesRecord> getPostnrlist(){ return this.postnrlist; }
	
}
