/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.filter;

import java.lang.reflect.Field;
import java.util.*;


import org.apache.log4j.Logger;

/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date   Sep 8, 2014
 */
public class SearchFilterSadNctsImportTopicList {
	private static final Logger logger = Logger.getLogger(SearchFilterSadNctsImportTopicList.class.getName());
	
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
	public String getDatum() { return this.datum;}

	private String datumt = null;
	public void setDatumt(String value) {  this.datumt = value; }
	public String getDatumt() { return this.datumt;}
	
	private String datumFr = null;
	public void setDatumFr(String value) {  this.datumFr = value; }
	public String getDatumFr() { return this.datumFr;}
	
	private String mrnNr = null;
	public void setMrnNr(String value) {  this.mrnNr = value; }
	public String getMrnNr() { return this.mrnNr;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String forenklad = null;
	public void setForenklad(String value) {  this.forenklad = value; }
	public String getForenklad() { return this.forenklad;}

	private String ansNavn = null;
	public void setAnsNavn(String value) {  this.ansNavn = value; }
	public String getAnsNavn() { return this.ansNavn;}

	private String godsNr = null;
	public void setGodsNr(String value) {  this.godsNr = value; }
	public String getGodsNr() { return this.godsNr;}

	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			//logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				//logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
}
