/**
 * 
 */
package no.systema.main.model.jsonjackson.general.postalcodes;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Mar 11, 2015
 * 
 */
public class JsonPostalCodesRecord extends JsonAbstractGrandFatherRecord {
	
	//Fra eller Til (own bean parameter in order to know if it is from (fra) or to (til)
	private String direction = null;
	public void setDirection(String value){ this.direction = value;}
	public String getDirection(){ return this.direction; }
	
	private String st2kod = null;
	public void setSt2kod(String value){ this.st2kod = value;}
	public String getSt2kod(){ return this.st2kod; }
	
	private String st2nvn = null;
	public void setSt2nvn(String value){ this.st2nvn = value;}
	public String getSt2nvn(){ return this.st2nvn; }
	
	private String st2lk = null;
	public void setSt2lk(String value){ this.st2lk = value;}
	public String getSt2lk(){ return this.st2lk; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String oprkod = null;
	public void setOprkod(String value){ this.oprkod = value;}
	public String getOprkod(){ return this.oprkod; }
	
	private String oprnvn = null;
	public void setOprnvn(String value){ this.oprnvn = value;}
	public String getOprnvn(){ return this.oprnvn; }
	
	private String viapnr = null;
	public void setViapnr(String value){ this.viapnr = value;}
	public String getViapnr(){ return this.viapnr; }
	
	private String wskunpa = null;
	public void setWskunpa(String value){ this.wskunpa = value;}
	public String getWskunpa(){ return this.wskunpa; }
	
	private String caller = null;
	public void setCaller(String value){ this.caller = value;}
	public String getCaller(){ return this.caller; }
	
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
