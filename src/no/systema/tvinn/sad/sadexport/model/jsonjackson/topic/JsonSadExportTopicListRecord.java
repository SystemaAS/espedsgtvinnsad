/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 *
 */
public class JsonSadExportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String tst = null;
	public void setTst(String value) {  this.tst = value; }
	public String getTst() { return this.tst;}
	
	private String sitll = null;
	public void setSitll(String value) {  this.sitll = value; }
	public String getSitll() { return this.sitll;}

	private String setll = null;
	public void setSetll(String value) {  this.setll = value; }
	public String getSetll() { return this.setll;}
	
	private String sitle = null;
	public void setSitle(String value) {  this.sitle = value; }
	public String getSitle() { return this.sitle;}
	
	private String aart = null;
	public void setAart(String value) {  this.aart = value; }
	public String getAart() { return this.aart;}
	
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
	
	private String sivkb = null;
	public void setSivkb(String value) {  this.sivkb = value; }
	public String getSivkb() { return this.sivkb;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}

	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String semi = null;
	public void setSemi(String value) {  this.semi = value; }
	public String getSemi() { return this.semi;}
	
	private String o2_semf = null;
	public void setO2_semf(String value) {  this.o2_semf = value; }
	public String getO2_semf() { return this.o2_semf;}
	
	private String o2_sest = null;
	public void setO2_sest(String value) {  this.o2_sest = value; }
	public String getO2_sest() { return this.o2_sest;}
	
	private String o2_sedt = null;
	public void setO2_sedt(String value) {  this.o2_sedt = value; }
	public String getO2_sedt() { return this.o2_sedt;}
	
	private String o2_setll = null;
	public void setO2_setll(String value) {  this.o2_setll = value; }
	public String getO2_setll() { return this.o2_setll;}
	
	
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
