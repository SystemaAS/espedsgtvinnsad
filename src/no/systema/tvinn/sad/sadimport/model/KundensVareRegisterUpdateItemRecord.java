package no.systema.tvinn.sad.sadimport.model;

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

	private String levenr = null;
	public void setLevenr(String value) {  this.levenr = value; }
	public String getLevenr() {return this.levenr;}
	
	private String varenr = null;
	public void setVarenr(String value) {  this.varenr = value; }
	public String getVarenr() {return this.varenr;}
	
	private String varebe = null;
	public void setVarebe(String value) {  this.varebe = value; }
	public String getVarebe() {return this.varebe;}
	
	private String w2lk = null;
	public void setW2lk(String value) {  this.w2lk = value; }
	public String getW2lk() {return this.w2lk;}

	private String w2vnti = null;
	public void setW2vnti(String value) {  this.w2vnti = value; }
	public String getW2vnti() {return this.w2vnti;}
	
	private String w2vf = null;
	public void setW2vf(String value) {  this.w2vf = value; }
	public String getW2vf() {return this.w2vf;}
	
	private String w2tn = null;
	public void setW2tn(String value) {  this.w2tn = value; }
	public String getW2tn() {return this.w2tn;}
	
	private String w2pre = null;
	public void setW2pre(String value) {  this.w2pre = value; }
	public String getW2pre() {return this.w2pre;}
	
	private String w2belt = null;
	public void setW2belt(String value) {  this.w2belt = value; }
	public String getW2belt() {return this.w2belt;}
	
	private String w2vktb = null;
	public void setW2vktb(String value) {  this.w2vktb = value; }
	public String getW2vktb() {return this.w2vktb;}
	
	private String w2vktn = null;
	public void setW2vktn(String value) {  this.w2vktn = value; }
	public String getW2vktn() {return this.w2vktn;}
	
	private String w2ntm = null;
	public void setW2ntm(String value) {  this.w2ntm = value; }
	public String getW2ntm() {return this.w2ntm;}
	
	private String w2pva = null;
	public void setW2pva(String value) {  this.w2pva = value; }
	public String getW2pva() {return this.w2pva;}
	
	private String w2as = null;
	public void setW2as(String value) {  this.w2as = value; }
	public String getW2as() {return this.w2as;}
	
	private String w2mfr = null;
	public void setW2mfr(String value) {  this.w2mfr = value; }
	public String getW2mfr() {return this.w2mfr;}
	
	private String w2akd1 = null;
	public void setW2akd1(String value) {  this.w2akd1 = value; }
	public String getW2akd1() {return this.w2akd1;}
	
	private String w2asv1 = null;
	public void setW2asv1(String value) {  this.w2asv1 = value; }
	public String getW2asv1() {return this.w2asv1;}
	
	private String w2asa1 = null;
	public void setW2asa1(String value) {  this.w2asa1 = value; }
	public String getW2asa1() {return this.w2asa1;}
	
	private String w2agr1 = null;
	public void setW2agr1(String value) {  this.w2agr1 = value; }
	public String getW2agr1() {return this.w2agr1;}
	
	private String w2abl1 = null;
	public void setW2abl1(String value) {  this.w2abl1 = value; }
	public String getW2abl1() {return this.w2abl1;}
	
	private String w2bel = null;
	public void setW2bel(String value) {  this.w2bel = value; }
	public String getW2bel() {return this.w2bel;}
	
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

