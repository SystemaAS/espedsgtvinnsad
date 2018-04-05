/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 2, 2016
 *
 */
public class JsonMaintSadImportCundfLikvKodeRecord extends JsonAbstractGrandFatherRecord {
	
	private String firma = null;
	public void setFirma(String value) {  this.firma = value; }
	public String getFirma() { return this.firma;}
	
	
	private String kundnr = null;
	public void setKundnr(String value) {  this.kundnr = value; }
	public String getKundnr() { return this.kundnr;}
	
	private String knavn = null;
	public void setKnavn(String value) {  this.knavn = value; }
	public String getKnavn() { return this.knavn;}
	
	private String adr1 = null;
	public void setAdr1(String value) {  this.adr1 = value; }
	public String getAdr1() { return this.adr1;}
	
	private String sylikv = null;
	public void setSylikv(String value) {  this.sylikv = value; }
	public String getSylikv() { return this.sylikv;}

	

	
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
