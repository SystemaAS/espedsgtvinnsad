package no.systema.tvinn.sad.sadexport.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2016
 * 
 */
public class KundensVareRegisterUpdateItemRecord {

	private String slknr = null;
	public void setSlknr(String value) {  this.slknr = value; }
	public String getSlknr() {return this.slknr;}
	
	private String slalfa = null;
	public void setSlalfa(String value) {  this.slalfa = value; }
	public String getSlalfa() {return this.slalfa;}
	
	private String sltxt = null;
	public void setSltxt(String value) {  this.sltxt = value; }
	public String getSltxt() {return this.sltxt;}
	
	private String sloppl = null;
	public void setSloppl(String value) {  this.sloppl = value; }
	public String getSloppl() {return this.sloppl;}

	private String slvekt = null;
	public void setSlvekt(String value) {  this.slvekt = value; }
	public String getSlvekt() {return this.slvekt;}
	
	private String sltanr = null;
	public void setSltanr(String value) {  this.sltanr = value; }
	public String getSltanr() {return this.sltanr;}
	
	private String sltar = null;
	public void setSltar(String value) {  this.sltar = value; }
	public String getSltar() {return this.sltar;}
	
	private String slpva = null;
	public void setSlpva(String value) {  this.slpva = value; }
	public String getSlpva() {return this.slpva;}
	
	private String slsats = null;
	public void setSlsats(String value) {  this.slsats = value; }
	public String getSlsats() {return this.slsats;}
	
	private String sltn = null;
	public void setSltn(String value) {  this.sltn = value; }
	public String getSltn() {return this.sltn;}
	
	
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

