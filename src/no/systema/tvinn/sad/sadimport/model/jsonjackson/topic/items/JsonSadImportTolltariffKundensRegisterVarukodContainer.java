/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.util.Collection;

/**
 * Kundens varuregister (to allow for the search of tolltariff nr via the customer's item data)
 * 
 * @author oscardelatorre
 * @date Jan 25, 2016
 */
public class JsonSadImportTolltariffKundensRegisterVarukodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String levenr = null;
	public void setLevenr(String value){ this.levenr = value;}
	public String getLevenr(){ return this.levenr; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSadImportTolltariffKundensRegisterVarukodRecord> kundvarlist = null;
	public void setKundvarlist(Collection<JsonSadImportTolltariffKundensRegisterVarukodRecord> value){ this.kundvarlist = value;}
	public Collection<JsonSadImportTolltariffKundensRegisterVarukodRecord> getKundvarlist(){ return this.kundvarlist; }
}
