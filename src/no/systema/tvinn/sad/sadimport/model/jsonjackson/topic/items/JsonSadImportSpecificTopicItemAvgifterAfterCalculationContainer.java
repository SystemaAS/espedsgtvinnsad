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
 * @date Mar 25, 2015
 * 
 * 
 */
public class JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String ubvnv = null;
	public void setUbvnv(String value) {  this.ubvnv = value; }
	public String getUbvnv() { return this.ubvnv;}

	private String sibel3 = null;
	public void setSibel3(String value) {  this.sibel3 = value; }
	public String getSibel3() { return this.sibel3;}

	private String sibel4 = null;
	public void setSibel4(String value) {  this.sibel4 = value; }
	public String getSibel4() { return this.sibel4;}

	private String sibelr = null;
	public void setSibelr(String value) {  this.sibelr = value; }
	public String getSibelr() { return this.sibelr;}

	private String sibels = null;
	public void setSibels(String value) {  this.sibels = value; }
	public String getSibels() { return this.sibels;}

	private String wg = null;
	public void setWg(String value) {  this.wg = value; }
	public String getWg() { return this.wg;}

	private String wh = null;
	public void setWh(String value) {  this.wh = value; }
	public String getWh() { return this.wh;}

	private String wk = null;
	public void setWk(String value) {  this.wk = value; }
	public String getWk() { return this.wk;}

	private String svbelt = null;
	public void setSvbelt(String value) {  this.svbelt = value; }
	public String getSvbelt() { return this.svbelt;}

	private String svvktb = null;
	public void setSvvktb(String value) {  this.svvktb = value; }
	public String getSvvktb() { return this.svvktb;}
	
	private String svvktn = null;
	public void setSvvktn(String value) {  this.svvktn = value; }
	public String getSvvktn() { return this.svvktn;}

	private String svntm = null;
	public void setSvntm(String value) {  this.svntm = value; }
	public String getSvntm() { return this.svntm;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> calcavgifter;
	public void setCalcavgifter(Collection<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> value){ this.calcavgifter = value; }
	public Collection<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> getCalcavgifter(){ return calcavgifter; }
	
}
