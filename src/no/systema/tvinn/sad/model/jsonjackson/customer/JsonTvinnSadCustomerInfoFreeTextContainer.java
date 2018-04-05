/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.customer;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Sep 25, 2014
 */
public class JsonTvinnSadCustomerInfoFreeTextContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String kundn = null;
	public void setKundn(String value){ this.kundn = value;}
	public String getKundn(){ return this.kundn; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadCustomerInfoFreeTextRecord> dspCustFreetxt = null;
	public void setDspCustFreetxt(Collection<JsonTvinnSadCustomerInfoFreeTextRecord> value){ this.dspCustFreetxt = value;}
	public Collection<JsonTvinnSadCustomerInfoFreeTextRecord> getDspCustFreetxt(){ return this.dspCustFreetxt; }
	
}
