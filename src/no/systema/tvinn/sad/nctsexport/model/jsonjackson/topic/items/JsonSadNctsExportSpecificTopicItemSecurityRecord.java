/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Mar 13, 2016
 *
 */
public class JsonSadNctsExportSpecificTopicItemSecurityRecord extends JsonAbstractGrandFatherRecord  {
	//---------
	//Sikkerhet
	//---------
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() { return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() { return this.tvtdn;}
	
	private String tvli = null;
	public void setTvli(String value) {  this.tvli = value; }
	public String getTvli() { return this.tvli;}
	
	private String tvtkbm = null;
	public void setTvtkbm(String value) {  this.tvtkbm = value; }
	public String getTvtkbm() { return this.tvtkbm;}
	
	private String tvkref = null;
	public void setTvkref(String value) {  this.tvkref = value; }
	public String getTvkref() { return this.tvkref;}
	
	private String tvfgkd = null;
	public void setTvfgkd(String value) {  this.tvfgkd = value; }
	public String getTvfgkd() { return this.tvfgkd;}
	
	private String tvknss = null;
	public void setTvknss(String value) {  this.tvknss = value; }
	public String getTvknss() { return this.tvknss;}
	
	private String tvnass = null;
	public void setTvnass(String value) {  this.tvnass = value; }
	public String getTvnass() { return this.tvnass;}
	
	private String tvadss1 = null;
	public void setTvadss1(String value) {  this.tvadss1 = value; }
	public String getTvadss1() { return this.tvadss1;}
	
	private String tvpnss = null;
	public void setTvpnss(String value) {  this.tvpnss = value; }
	public String getTvpnss() { return this.tvpnss;}
	
	private String tvpsss = null;
	public void setTvpsss(String value) {  this.tvpsss = value; }
	public String getTvpsss() { return this.tvpsss;}
	
	private String tvlkss = null;
	public void setTvlkss(String value) {  this.tvlkss = value; }
	public String getTvlkss() { return this.tvlkss;}
	
	private String tvskss = null;
	public void setTvskss(String value) {  this.tvskss = value; }
	public String getTvskss() { return this.tvskss;}
	
	private String tvtinss = null;
	public void setTvtinss(String value) {  this.tvtinss = value; }
	public String getTvtinss() { return this.tvtinss;}
	
	private String tvknks = null;
	public void setTvknks(String value) {  this.tvknks = value; }
	public String getTvknks() { return this.tvknks;}
	
	private String tvnaks = null;
	public void setTvnaks(String value) {  this.tvnaks = value; }
	public String getTvnaks() { return this.tvnaks;}
	
	private String tvadks1 = null;
	public void setTvadks1(String value) {  this.tvadks1 = value; }
	public String getTvadks1() { return this.tvadks1;}
	
	private String tvpnks = null;
	public void setTvpnks(String value) {  this.tvpnks = value; }
	public String getTvpnks() { return this.tvpnks;}
	
	private String tvpsks = null;
	public void setTvpsks(String value) {  this.tvpsks = value; }
	public String getTvpsks() { return this.tvpsks;}
	
	private String tvlkks = null;
	public void setTvlkks(String value) {  this.tvlkks = value; }
	public String getTvlkks() { return this.tvlkks;}
	
	private String tvskks = null;
	public void setTvskks(String value) {  this.tvskks = value; }
	public String getTvskks() { return this.tvskks;}
	
	private String tvtinks = null;
	public void setTvtinks(String value) {  this.tvtinks = value; }
	public String getTvtinks() { return this.tvtinks;}
	
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
