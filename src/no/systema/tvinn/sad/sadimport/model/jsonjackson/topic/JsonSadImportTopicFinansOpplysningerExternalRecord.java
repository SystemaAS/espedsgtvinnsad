/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 26, 2015
 *
 */
public class JsonSadImportTopicFinansOpplysningerExternalRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	
	
	private String sfavd = null;
	public void setSfavd(String value) {  this.sfavd = value; }
	public String getSfavd() { return this.sfavd;}
	
	private String sfopdn = null;
	public void setSfopdn(String value) {  this.sfopdn = value; }
	public String getSfopdn() { return this.sfopdn;}
	
	private String sftxt = null;
	public void setSftxt(String value) {  this.sftxt = value; }
	public String getSftxt() { return this.sftxt;}
	
	private String sfdt = null;
	public void setSfdt(String value) {  this.sfdt = value; }
	public String getSfdt() { return this.sfdt;}
	
	private String sfvk28 = null;
	public void setSfvk28(String value) {  this.sfvk28 = value; }
	public String getSfvk28() { return this.sfvk28;}
	
	private String sfkr28 = null;
	public void setSfkr28(String value) {  this.sfkr28 = value; }
	public String getSfkr28() { return this.sfkr28;}
	
	private String sfbl28 = null;
	public void setSfbl28(String value) {  this.sfbl28 = value; }
	public String getSfbl28() { return this.sfbl28;}
	
	private Double sfbl28Dbl = 0.00D;
	public Double getsfbl28Dbl() { 
		if(this.sfbl28!=null){
			try{
				this.sfbl28Dbl = Double.parseDouble(this.sfbl28.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.sfbl28Dbl;
	}
	
	
	private String sfunik = null;
	public void setSfunik(String value) {  this.sfunik = value; }
	public String getSfunik() { return this.sfunik;}
	
	private String sfreff = null;
	public void setSfreff(String value) {  this.sfreff = value; }
	public String getSfreff() { return this.sfreff;}
	
	
	
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
