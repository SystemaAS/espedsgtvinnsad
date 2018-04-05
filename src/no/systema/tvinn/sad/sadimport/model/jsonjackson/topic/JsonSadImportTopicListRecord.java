/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;

/**
 * @author oscardelatorre
 * @date May 22, 2014
 *
 */
public class JsonSadImportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String refnr = null;
	public void setRefnr(String value) {  this.refnr = value; }
	public String getRefnr() { return this.refnr;}
	
	//Godsnr = sign
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String sg = null;
	public void setSg(String value) {  this.sg = value; }
	public String getSg() { return this.sg;}
	
	private String datum = null;
	public void setDatum(String value) { this.datum = value; }
	public String getDatum() {
		if(this.datum != null && !"".equals(this.datum)){
			return dateFormatter.convertToDate_NO(this.datum);
		}else{
			return this.datum;
		}
	}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String aart = null;
	public void setAart(String value) {  this.aart = value; }
	public String getAart() { return this.aart;}
	
	private String sitll = null;
	public void setSitll(String value) {  this.sitll = value; }
	public String getSitll() { return this.sitll;}
	
	private String sitle = null;
	public void setSitle(String value) {  this.sitle = value; }
	public String getSitle() { return this.sitle;}
	
	private String sivkb = null;
	public void setSivkb(String value) {  this.sivkb = value; }
	public String getSivkb() { return this.sivkb;}
	
	private String simi = null;
	public void setSimi(String value) {  this.simi = value; }
	public String getSimi() { return this.simi;}
	
	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String o2_simf = null;
	public void setO2_simf(String value) {  this.o2_simf = value; }
	public String getO2_simf() { return this.o2_simf;}
	
	private String o2_sist = null;
	public void setO2_sist(String value) {  this.o2_sist = value; }
	public String getO2_sist() { return this.o2_sist;}
	
	private String o2_sidt = null;
	public void setO2_sidt(String value) {  this.o2_sidt = value; }
	public String getO2_sidt() { return this.o2_sidt;}
	
	private String o2_sitll = null;
	public void setO2_sitll(String value) {  this.o2_sitll = value; }
	public String getO2_sitll() { return this.o2_sitll;}
	
	
	
	private Collection<JsonNotisblockRecord> notisblockList;
	public void setNotisblockList(Collection<JsonNotisblockRecord> value){ this.notisblockList = value; }
	public Collection<JsonNotisblockRecord> getNotisblockList(){ return notisblockList; }
	
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
