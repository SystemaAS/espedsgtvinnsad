/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Sep 24, 2014
 *
 */
public class JsonSadImportTopicFinansOpplysningerContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String fak = null;
	public void setFak(String value) {  this.fak = value; }
	public String getFak() { return this.fak;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private String calculatedItemLinesTotalAmount = null;
	public void setCalculatedItemLinesTotalAmount(String value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() { return this.calculatedItemLinesTotalAmount; }
	
	/*
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		//return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3);
		return formatter.getString(this.calculatedItemLinesTotalAmount, 3, false, "NO");
	}*/
	
	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		//return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3);
		return formatter.getString(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3, false, "NO");
	}
	
	//Used when different currencies exist. The main currency must be = NOK
	private String calculatedValidCurrency = null;
	public void setCalculatedValidCurrency(String value) {  this.calculatedValidCurrency = value; }
	public String getCalculatedValidCurrency() { return this.calculatedValidCurrency; }
	
	
	/**
	 * This is usually done in the JSP but in this case (Finans.Opplysn.) we do it right here since there are some
	 * presentation issues that unable the counter on the JSP to work in the correct time line.
	 * Here we just send the size of the item list.
	 * 
	 */
	private String totalNumberOfItemLines = "";
	public String getTotalNumberOfItemLines() {
		String retval = "";
		if(this.invoicList!=null){
			retval = String.valueOf(this.invoicList.size());
		}
		return retval;
	}
	
	
	/**
	 * 
	 */
	private Collection<JsonSadImportTopicFinansOpplysningerRecord> invoicList;
	public void setInvoicList(Collection<JsonSadImportTopicFinansOpplysningerRecord> value){ this.invoicList = value; }
	public Collection<JsonSadImportTopicFinansOpplysningerRecord> getInvoicList(){ return invoicList; }
	
	/**
	 * 
	 */
	private Collection<JsonSadImportTopicFinansOpplysningerRecord> oneInvoice;
	public void setOneInvoice(Collection<JsonSadImportTopicFinansOpplysningerRecord> value){ this.oneInvoice = value; }
	public Collection<JsonSadImportTopicFinansOpplysningerRecord> getOneInvoice(){ return oneInvoice; }
	
}
