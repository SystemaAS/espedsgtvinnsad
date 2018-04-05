/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items;

import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class JsonSadExportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String varenr = null;
	public void setVarenr(String value) {  this.varenr = value; }
	public String getVarenr() { return this.varenr;}
	
	private String w2fyl = null;
	public void setW2fyl(String value) {  this.w2fyl = value; }
	public String getW2fyl() { return this.w2fyl;}
	
	private String w2topl = null;
	public void setW2topl(String value) {  this.w2topl = value; }
	public String getW2topl() { return this.w2topl;}
	
	private String w2cref = null;
	public void setW2cref(String value) {  this.w2cref = value; }
	public String getW2cref() { return this.w2cref;}
	
	
	
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

	
	private Collection<JsonSadExportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSadExportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSadExportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
	
	
}
