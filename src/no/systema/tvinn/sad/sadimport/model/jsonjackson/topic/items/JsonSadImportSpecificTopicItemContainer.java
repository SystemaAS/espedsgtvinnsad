/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * AS400 File: SADV
 */
public class JsonSadImportSpecificTopicItemContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3);
	}

	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3);
	}
	
	private String lastSelectedItemLineNumber = null;
	public void setLastSelectedItemLineNumber(String value) {  this.lastSelectedItemLineNumber = value; }
	public String getLastSelectedItemLineNumber() { return this.lastSelectedItemLineNumber;}
	
	
	//In order to search a specific item line per item-line nr. basis
	private String startItemLineNr = null;
	public void setStartItemLineNr(String value) {  this.startItemLineNr = value; }
	public String getStartItemLineNr() {return this.startItemLineNr;}
	
	//In order to search a specific item line per tariffNr basis
	private String tariffNr = null;
	public void setTariffNr(String value) {  this.tariffNr = value; }
	public String getTariffNr() {return this.tariffNr;}

	private Collection<JsonSadImportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSadImportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSadImportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
	
	
}
