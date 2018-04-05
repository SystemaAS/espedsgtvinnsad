/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.topic.items;

/**
 * @author oscardelatorre
 * @date Mar 24, 2015
 * 
 * This object is used as a place holder for Avgift-variables
 * Mainly because all avgift-variables are not dynamic. This will function as a bridge when used in a loop...
 * 
 */
public class SadImportSpecificTopicItemAvgiftDynamicObject {
	
	private String kode = null;
	public void setKode(String value) {  this.kode = value; }
	public String getKode() {return this.kode;}
	
	private String sekvens = null;
	public void setSekvens(String value) {  this.sekvens = value; }
	public String getSekvens() {
		if(this.sekvens!=null && !"".equals(this.sekvens)){
			//Usually when we have done a special adaptation via jQuery on an event in a field
			if(this.sekvens.contains("@")){
				String[] tmp = this.sekvens.split("@");
				this.sekvens = tmp[0];
			}
		}
		return this.sekvens;
	}
	
	private String sats = null;
	public void setSats(String value) {  this.sats = value; }
	public String getSats() {return this.sats;}
	
	private String grunnlag = null;
	public void setGrunnlag(String value) {  this.grunnlag = value; }
	public String getGrunnlag() {return this.grunnlag;}
	
	private String belopp = null;
	public void setBelopp(String value) {  this.belopp = value; }
	public String getBelopp() {return this.belopp;}
	
}
