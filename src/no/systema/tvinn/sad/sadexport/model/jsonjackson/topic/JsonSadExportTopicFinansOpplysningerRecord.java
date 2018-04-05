/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 25, 2014
 *
 */
public class JsonSadExportTopicFinansOpplysningerRecord extends JsonAbstractGrandFatherRecord {
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
	
	private String sfbl28 = null;
	public void setSfbl28(String value) {  this.sfbl28 = value; }
	public String getSfbl28() { return this.sfbl28;}
	
	private String sfdt = null;
	public void setSfdt(String value) { this.sfdt = value; }
	public String getSfdt() { return this.sfdt; }
	
	private String sfvk28 = "NOK";
	public void setSfvk28(String value) {  this.sfvk28 = value; }
	public String getSfvk28() { return this.sfvk28;}

	private String sfkr28 = null;
	public void setSfkr28(String value) {  this.sfkr28 = value; }
	public String getSfkr28() { return this.sfkr28;}
	
	private Double sfkr28Dbl = 0.00D;
	public Double getSfkr28Dbl() { 
		if(this.sfkr28!=null){
			try{
				this.sfkr28Dbl = Double.parseDouble(this.sfkr28.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.sfkr28Dbl;
	}
	
	
	private String sfom28 = null;
	public void setSfom28(String value) {  this.sfom28 = value; }
	public String getSfom28() { return this.sfom28;}
	
	private Integer sfom28Int = 1;
	public Integer getSfom28Int() { 
		if(this.sfom28!= null){
			try{
				sfom28Int = Integer.parseInt(this.sfom28);
			}catch(Exception e){
				//nothing
			}
		}
		return this.sfom28Int;
	}
	
	
	private String sfxxx = null;
	public void setSfxxx(String value) {  this.sfxxx = value; }
	public String getSfxxx() { return this.sfxxx;}
	
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
