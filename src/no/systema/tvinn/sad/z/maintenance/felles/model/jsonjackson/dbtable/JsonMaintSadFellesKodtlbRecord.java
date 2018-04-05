/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 29, 2016
 *
 */
public class JsonMaintSadFellesKodtlbRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String klbsta = null;
	public void setKlbsta(String value) {  this.klbsta = value; }
	public String getKlbsta() { return this.klbsta;}
	
	private String klbuni = null;
	public void setKlbuni(String value) {  this.klbuni = value; }
	public String getKlbuni() { return this.klbuni;}
	
	private String klbkod = null;
	public void setKlbkod(String value) {  this.klbkod = value; }
	public String getKlbkod() { return this.klbkod;}
	
	private String klbkt = null;
	public void setKlbkt(String value) {  this.klbkt = value; }
	public String getKlbkt() { return this.klbkt;}

	private String klbnav = null;
	public void setKlbnav(String value) {  this.klbnav = value; }
	public String getKlbnav() { return this.klbnav;}
	
	private String klbfok = null;
	public void setKlbfok(String value) {  this.klbfok = value; }
	public String getKlbfok() { return this.klbfok;}
	
	private String klbprm = null;
	public void setKlbprm(String value) {  this.klbprm = value; }
	public String getKlbprm() { return this.klbprm;}
	
	private String klbfrk = null;
	public void setKlbfrk(String value) {  this.klbfrk = value; }
	public String getKlbfrk() { return this.klbfrk;}
	
	private String klbxxx = null;
	public void setKlbxxx(String value) {  this.klbxxx = value; }
	public String getKlbxxx() { return this.klbxxx;}
	
	//aux 
	private String klbxxx_avs = null;
	public void setKlbxxx_avs(String value) { this.klbxxx_avs = value; }
	public String getKlbxxx_avs() {
		if(this.klbxxx!=null && this.klbxxx.length()>=1){
			this.klbxxx_avs = this.klbxxx.substring(0,1);			
		}
		return this.klbxxx_avs;
	}
	
	private String klbxxx_mot = null;
	public void setKlbxxx_mot(String value) {  this.klbxxx_mot = value; }
	public String getKlbxxx_mot() {
		if(this.klbxxx!=null && this.klbxxx.length()>=2){
			this.klbxxx_mot = this.klbxxx.substring(1, 2);
		}
		return this.klbxxx_mot;
	}
	
	private String klbxxx_andrek = null;
	public void setKlbxxx_andrek(String value) {  this.klbxxx_andrek = value; }
	public String getKlbxxx_andrek() {
		if(this.klbxxx!=null && this.klbxxx.length()==3){
			this.klbxxx_andrek = this.klbxxx.substring(2);
		}
		return this.klbxxx_andrek;
	}
	
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
