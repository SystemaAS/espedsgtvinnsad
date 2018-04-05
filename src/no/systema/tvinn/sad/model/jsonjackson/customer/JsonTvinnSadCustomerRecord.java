/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.customer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 */
public class JsonTvinnSadCustomerRecord {
	
	private String kundnr = null;
	public void setKundnr(String value){ this.kundnr = value;}
	public String getKundnr(){ return this.kundnr; }
	
	private String knavn = null;
	public void setKnavn(String value){ this.knavn = value;}
	public String getKnavn(){ return this.knavn; }
	
	private String adr1 = null;
	public void setAdr1(String value){ this.adr1 = value;}
	public String getAdr1(){ return this.adr1; }
	
	private String adr2 = null;
	public void setAdr2(String value){ this.adr2 = value;}
	public String getAdr2(){ return this.adr2; }
	
	private String adr3 = null;
	public void setAdr3(String value){ this.adr3 = value;}
	public String getAdr3(){ return this.adr3; }
	
	private String postnr = null;
	public void setPostnr(String value){ this.postnr = value;}
	public String getPostnr(){ return this.postnr; }
	
	private String kpers = null;
	public void setKpers(String value){ this.kpers = value;}
	public String getKpers(){ return this.kpers; }
	
	private String tlf = null;
	public void setTlf(String value){ this.tlf = value;}
	public String getTlf(){ return this.tlf; }
	
	private String sonavn = null;
	public void setSonavn(String value){ this.sonavn = value;}
	public String getSonavn(){ return this.sonavn; }
	
	private String sypoge = null;
	public void setSypoge(String value){ this.sypoge = value;}
	public String getSypoge(){ return this.sypoge; }
	
	private String syland = null;
	public void setSyland(String value){ this.syland = value;}
	public String getSyland(){ return this.syland; }
	
	private String syfr02 = null;
	public void setSyfr02(String value){ this.syfr02 = value;}
	public String getSyfr02(){ return this.syfr02; }
	
	private String syrg = null;
	public void setSyrg(String value){ this.syrg = value;}
	public String getSyrg(){ return this.syrg; }
	
	private String symvjn = null;
	public void setSymvjn(String value){ this.symvjn = value;}
	public String getSymvjn(){ return this.symvjn; }
	
	private String vatkku = null;
	public void setVatkku(String value){ this.vatkku = value;}
	public String getVatkku(){ return this.vatkku; }
	
	private String golk = null;
	public void setGolk(String value){ this.golk = value;}
	public String getGolk(){ return this.golk; }
	
	private String eori = null;
	public void setEori(String value){ this.eori = value;}
	public String getEori(){ return this.eori; }
	
	private String wsktc = null;
	public void setWsktc(String value){ this.wsktc = value;}
	public String getWsktc(){ return this.wsktc; }
	
	private String wskta = null;
	public void setWskta(String value){ this.wskta = value;}
	public String getWskta(){ return this.wskta; }
	
	private String wsktb = null;
	public void setWsktb(String value){ this.wsktb = value;}
	public String getWsktb(){ return this.wsktb; }
	
	
	/**
	 * User for java reflection in other classes
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
