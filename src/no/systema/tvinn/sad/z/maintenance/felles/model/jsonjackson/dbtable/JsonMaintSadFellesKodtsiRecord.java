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
 * @date Apr 9, 2016
 *
 */
public class JsonMaintSadFellesKodtsiRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
    	
	private String ksista = null;
	public void setKsista(String value) {  this.ksista = value; }
	public String getKsista() { return this.ksista;}
	
	private String ksiuni = null;
	public void setKsiuni(String value) {  this.ksiuni = value; }
	public String getKsiuni() { return this.ksiuni;}
	
	private String ksisig = null;
	public void setKsisig(String value) {  this.ksisig = value; }
	public String getKsisig() { return this.ksisig;}
	
	private String ksinav = null;
	public void setKsinav(String value) {  this.ksinav = value; }
	public String getKsinav() { return this.ksinav;}

	private String ksixxx = null;
	public void setKsixxx(String value) {  this.ksixxx = value; }
	public String getKsixxx() { return this.ksixxx;}
	
	private String ksovl = null;
	public void setKsovl(String value) {  this.ksovl = value; }
	public String getKsovl() { return this.ksovl;}
	
	private String ksuser = null;
	public void setKsuser(String value) {  this.ksuser = value; }
	public String getKsuser() { return this.ksuser;}
	
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
