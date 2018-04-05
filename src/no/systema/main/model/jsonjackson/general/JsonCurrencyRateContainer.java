/**
 * 
 */
package no.systema.main.model.jsonjackson.general;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Mar 01, 2013
 */
public class JsonCurrencyRateContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String kod = null;
	public void setKod(String value){ this.kod = value;}
	public String getKod(){ return this.kod; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonCurrencyRateRecord> valutakurs = null;
	public void setValutakurs(Collection<JsonCurrencyRateRecord> value){ this.valutakurs = value;}
	public Collection<JsonCurrencyRateRecord> getValutakurs(){ return this.valutakurs; }
	
}
