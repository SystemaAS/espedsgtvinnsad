/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.topic;

/**
 * @author oscardelatorre
 * @date Jun 25, 2015
 * 
 * This object is used as a place holder instead of a Map.
 * Mainly because there are different return types. The object is only used within a controller domain.
 * 
 */
public class SadExportSpecificTopicFinansOpplysningarAjaxObject {
	
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
