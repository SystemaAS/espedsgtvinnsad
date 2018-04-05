/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.topic;

/**
 * @author oscardelatorre
 * @date Dec 10, 2014
 * 
 * This object is used as a place holder instead of a Map.
 * Mainly because there are different return types. The object is only used within a controller domain.
 * 
 */
public class SadImportSpecificTopicFinansOpplysningarAjaxObject {
	
	private String calculatedValidCurrency;
	public void setCalculatedValidCurrency(String value) {  this.calculatedValidCurrency = value; }
	public String getCalculatedValidCurrency() {return this.calculatedValidCurrency;}
	
	private String calculatedItemLinesTotalAmount;
	public void setCalculatedItemLinesTotalAmount(String value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {return this.calculatedItemLinesTotalAmount;}
	
	private String factor;
	public void setFactor(String value) {  this.factor = value; }
	public String getFactor() {return this.factor;}
	
}
