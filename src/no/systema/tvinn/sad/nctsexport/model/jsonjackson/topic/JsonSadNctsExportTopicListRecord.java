/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsExportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() {
		if(this.datum != null && !"".equals(this.datum)){
			return dateFormatter.convertToDate_NO(this.datum);
		}else{
			return this.datum;
		}
	}
	
	private String lrnNr = null;
	public void setLrnNr(String value) {  this.lrnNr = value; }
	public String getLrnNr() { return this.lrnNr;}
	
	private String mrnNr = null;
	public void setMrnNr(String value) {  this.mrnNr = value; }
	public String getMrnNr() { return this.mrnNr;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String kolli = null;
	public void setKolli(String value) {  this.kolli = value; }
	public String getKolli() { return this.kolli;}
	
	private String bruttoVikt = null;
	public void setBruttoVikt(String value) {  this.bruttoVikt = value; }
	public String getBruttoVikt() { return this.bruttoVikt;}

	private String forenklad = null;
	public void setForenklad(String value) {  this.forenklad = value; }
	public String getForenklad() { return this.forenklad;}

	private String elList = null;
	public void setElList(String value) {  this.elList = value; }
	public String getElList() { return this.elList;}

	private String eksternref = null;
	public void setEksternref(String value) {  this.eksternref = value; }
	public String getEksternref() { return this.eksternref;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
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
