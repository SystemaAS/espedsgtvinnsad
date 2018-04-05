/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

/**
 * @author oscardelatorre
 * @date Mar 6, 2015
 *
 */
public class JsonSadNctsImportSpecificTopicItemRecord extends JsonAbstractGrandFatherRecord  {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
		
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() {return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() {return this.tvtdn;}
	
	private String tvli = null;
	public void setTvli(String value) {  this.tvli = value; }
	public String getTvli() {return this.tvli;}
	
	private String tvst = null;
	public void setTvst(String value) {  this.tvst = value; }
	public String getTvst() {return this.tvst;}
	
	private String tvstsk = null;
	public void setTvstsk(String value) {  this.tvstsk = value; }
	public String getTvstsk() {return this.tvstsk;}
	
	private String tvstlk = null;
	public void setTvstlk(String value) {  this.tvstlk = value; }
	public String getTvstlk() {return this.tvstlk;}
	
	
	private String tvctl = null;
	public void setTvctl(String value) {  this.tvctl = value; }
	public String getTvctl() {return this.tvctl;}
	
	private String tvflg = null;
	public void setTvflg(String value) {  this.tvflg = value; }
	public String getTvflg() {return this.tvflg;}
	
	//This field does not exist in the db so we must substring the original string in chunks of 70-chars
	private String tvinf = null;
	public void setTvinf(String value) {  this.tvinf = value; }
	public String getTvinf() {return this.tvinf;}
	
	private String tvinf1 = null;
	public void setTvinf1(String value) {  this.tvinf1 = value; }
	public String getTvinf1() {return this.tvinf1;}
	
	private String tvinf2 = null;
	public void setTvinf2(String value) {  this.tvinf2 = value; }
	public String getTvinf2() {return this.tvinf2;}
	
	private String tvinf3 = null;
	public void setTvinf3(String value) {  this.tvinf3 = value; }
	public String getTvinf3() {return this.tvinf3;}
	
	private String tvinf4 = null;
	public void setTvinf4(String value) {  this.tvinf4 = value; }
	public String getTvinf4() {return this.tvinf4;}
	
	private String tvinsk = null;
	public void setTvinsk(String value) {  this.tvinsk = value; }
	public String getTvinsk() {return this.tvinsk;}
	
	private String tvgdt = null;
	public void setTvgdt(String value) { this.tvgdt=value; }
	public String getTvgdt() { 
		if(this.tvgdt != null && !"".equals(this.tvgdt)){
			return dateFormatter.convertToDate_NO(this.tvgdt);
		}else{
			return this.tvgdt;
		}
	}
	
	private String tvgm = null;
	public void setTvgm(String value) {  this.tvgm = value; }
	public String getTvgm() { return this.tvgm;}
	
	private String tvgmsk = null;
	public void setTvgmsk(String value) {  this.tvgmsk = value; }
	public String getTvgmsk() { return this.tvgmsk;}
	
	private String tvgmst = null;
	public void setTvgmst(String value) {  this.tvgmst = value; }
	public String getTvgmst() { return this.tvgmst;}
	
	private String tvgmss = null;
	public void setTvgmss(String value) {  this.tvgmss = value; }
	public String getTvgmss() { return this.tvgmss;}
	
	private String tvgmlk = null;
	public void setTvgmlk(String value) {  this.tvgmlk = value; }
	public String getTvgmlk() { return this.tvgmlk;}
	
	private String tvdant = null;
	public void setTvdant(String value) {  this.tvdant = value; }
	public String getTvdant() { return this.tvdant;}
	
	private String tvdfkd = null;
	public void setTvdfkd(String value) {  this.tvdfkd = value; }
	public String getTvdfkd() { return this.tvdfkd;}
	
	private String tvdfsk = null;
	public void setTvdfsk(String value) {  this.tvdfsk = value; }
	public String getTvdfsk() { return this.tvdfsk;}
	
	private String tvtaid = null;
	public void setTvtaid(String value) {  this.tvtaid = value; }
	public String getTvtaid() { return this.tvtaid;}
	
	private String tvtask = null;
	public void setTvtask(String value) {  this.tvtask = value; }
	public String getTvtask() { return this.tvtask;}

	private String tvtalk = null;
	public void setTvtalk(String value) {  this.tvtalk = value; }
	public String gettvtalk() { return this.tvtalk;}

	private String tvgodt = null;
	public void setTvgodt(String value) { this.tvgodt=value; }
	public String getTvgodt() {
		if(this.tvgodt != null && !"".equals(this.tvgodt)){
			return dateFormatter.convertToDate_NO(this.tvgodt);
		}else{
			return this.tvgodt;
		}
	}

	private String tvom = null;
	public void setTvom(String value) {  this.tvom = value; }
	public String getTvom() { return this.tvom;}

	private String tvomsk = null;
	public void setTvomsk(String value) {  this.tvomsk = value; }
	public String getTvomsk() { return this.tvomsk;}

	private String tvomst = null;
	public void setTvomst(String value) {  this.tvomst = value; }
	public String getTvomst() { return this.tvomst;}

	private String tvomss = null;
	public void setTvomss(String value) {  this.tvomss = value; }
	public String getTvomss() { return this.tvomss;}
	
	private String tvomlk = null;
	public void setTvomlk(String value) {  this.tvomlk = value; }
	public String getTvomlk() { return this.tvomlk;}
	
	private String tvcnr = null;
	public void setTvcnr(String value) {  this.tvcnr = value; }
	public String getTvcnr() { return this.tvcnr;}
	
	private String tvcnr2 = null;
	public void setTvcnr2(String value) {  this.tvcnr2 = value; }
	public String getTvcnr2() { return this.tvcnr2;}
	
	private String tvcnr3 = null;
	public void setTvcnr3(String value) {  this.tvcnr3 = value; }
	public String getTvcnr3() { return this.tvcnr3;}
	
	private String tvcnr4 = null;
	public void setTvcnr4(String value) {  this.tvcnr4 = value; }
	public String getTvcnr4() { return this.tvcnr4;}
	
	private String tvcnr5 = null;
	public void setTvcnr5(String value) {  this.tvcnr5 = value; }
	public String getTvcnr5() { return this.tvcnr5;}
	

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
