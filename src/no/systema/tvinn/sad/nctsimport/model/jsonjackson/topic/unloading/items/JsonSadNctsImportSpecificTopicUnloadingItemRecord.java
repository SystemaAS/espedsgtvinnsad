/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public class JsonSadNctsImportSpecificTopicUnloadingItemRecord extends JsonAbstractGrandFatherRecord  {
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
	
	//used as an auxiliary in a validate sequence (gross weight) on item line nr 1
	private String numberOfItemLinesInTopicStr = null;
	public void setNumberOfItemLinesInTopicStr(String value) {  this.numberOfItemLinesInTopicStr = value; }
	public String getNumberOfItemLinesInTopicStr() {return this.numberOfItemLinesInTopicStr;}
		
	//Aux. attribute to pass some header values into a Validator for Items.
	private String header_nikonf = null;//Konform
	public void setHeader_nikonf(String value) {  this.header_nikonf = value; }
	public String getHeader_nikonf() {return this.header_nikonf;}
	
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() { return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() { return this.tvtdn;}
	
	private String tvli = null;
	public void setTvli(String value) {  this.tvli = value; }
	public String getTvli() {return this.tvli;}
	
	private String tvvnt = null;
	public void setTvvnt(String value) {  this.tvvnt = value; }
	public String getTvvnt() {return this.tvvnt;}
	
	private String tvvntl = null;
	public void setTvvntl(String value) {  this.tvvntl = value; }
	public String getTvvntl() {return this.tvvntl;}
	
	private String tvdk = null;
	public void setTvdk(String value) {  this.tvdk = value; }
	public String getTvdk() {return this.tvdk;}
	
	private String tvvt = null;
	public void setTvvt(String value) {  this.tvvt = value; }
	public String getTvvt() {return this.tvvt;}
	
	private String tvvtsk = null;
	public void setTvvtsk(String value) {  this.tvvtsk = value; }
	public String getTvvtsk() {return this.tvvtsk;}
	
	private String tvvktb = null;
	public void setTvvktb(String value) {  this.tvvktb = value; }
	public String getTvvktb() {return this.tvvktb;}
	
	private String tvvktn = null;
	public void setTvvktn(String value) {  this.tvvktn = value; }
	public String getTvvktn() {return this.tvvktn;}
	
	private String tvalk = null;
	public void setTvalk(String value) {  this.tvalk = value; }
	public String getTvalk() {return this.tvalk;}
	
	private String tvblk = null;
	public void setTvblk(String value) {  this.tvblk = value; }
	public String getTvblk() {return this.tvblk;}
	
	private String tvnas = null;
	public void setTvnas(String value) {  this.tvnas = value; }
	public String getTvnas() {return this.tvnas;}
	
	private String tvads1 = null;
	public void setTvads1(String value) {  this.tvads1 = value; }
	public String getTvads1() {return this.tvads1;}
	
	private String tvpns = null;
	public void setTvpns(String value) {  this.tvpns = value; }
	public String getTvpns() {return this.tvpns;}
	
	private String tvpss = null;
	public void setTvpss(String value) {  this.tvpss = value; }
	public String getTvpss() {return this.tvpss;}
	
	private String tvlks = null;
	public void setTvlks(String value) {  this.tvlks = value; }
	public String getTvlks() {return this.tvlks;}
	
	private String tvsks = null;
	public void setTvsks(String value) {  this.tvsks = value; }
	public String getTvsks() {return this.tvsks;}
	
	private String tvtins = null;
	public void setTvtins(String value) {  this.tvtins = value; }
	public String getTvtins() {return this.tvtins;}
	
	private String tvnak = null;
	public void setTvnak(String value) {  this.tvnak = value; }
	public String getTvnak() {return this.tvnak;}
	
	private String tvadk1 = null;
	public void setTvadk1(String value) {  this.tvadk1 = value; }
	public String getTvadk1() {return this.tvadk1;}
	
	private String tvpnk = null;
	public void setTvpnk(String value) {  this.tvpnk = value; }
	public String getTvpnk() {return this.tvpnk;}
	
	private String tvpsk = null;
	public void setTvpsk(String value) {  this.tvpsk = value; }
	public String getTvpsk() {return this.tvpsk;}
	
	private String tvlkk = null;
	public void setTvlkk(String value) {  this.tvlkk = value; }
	public String getTvlkk() {return this.tvlkk;}
	
	private String tvskk = null;
	public void setTvskk(String value) {  this.tvskk = value; }
	public String getTvskk() {return this.tvskk;}
	
	private String tvtink = null;
	public void setTvtink(String value) {  this.tvtink = value; }
	public String getTvtink() {return this.tvtink;}
	
	private String tvdty = null;
	public void setTvdty(String value) {  this.tvdty = value; }
	public String getTvdty() {return this.tvdty;}
	
	private String tvdref = null;
	public void setTvdref(String value) {  this.tvdref = value; }
	public String getTvdref() {return this.tvdref;}

	private String tvdrefl = null;
	public void setTvdrefl(String value) {  this.tvdrefl = value; }
	public String getTvdrefl() {return this.tvdrefl;}
	
	private String tvdsk = null;
	public void setTvdsk(String value) {  this.tvdsk = value; }
	public String getTvdsk() {return this.tvdsk;}
		
	private String tvdo = null;
	public void setTvdo(String value) {  this.tvdo = value; }
	public String getTvdo() {return this.tvdo;}

	private String tvdol = null;
	public void setTvdol(String value) {  this.tvdol = value; }
	public String getTvdol() {return this.tvdol;}

	private String tvdosk = null;
	public void setTvdosk(String value) {  this.tvdosk = value; }
	public String getTvdosk() {return this.tvdosk;}
	
	private String tvtlo = null;
	public void setTvtlo(String value) {  this.tvtlo = value; }
	public String getTvtlo() {return this.tvtlo;}
	
	private String tvexkd = null;
	public void setTvexkd(String value) {  this.tvexkd = value; }
	public String getTvexkd() {return this.tvexkd;}
	
	private String tvexlk = null;
	public void setTvexlk(String value) {  this.tvexlk = value; }
	public String getTvexlk() {return this.tvexlk;}
	
	private String tvavd2 = null;
	public void setTvavd2(String value) {  this.tvavd2 = value; }
	public String getTvavd2() {return this.tvavd2;}
	
	private String tvtdn2 = null;
	public void setTvtdn2(String value) {  this.tvtdn2 = value; }
	public String getTvtdn2() {return this.tvtdn2;}
	
	private String tvcnr = null;
	public void setTvcnr(String value) {  this.tvcnr = value; }
	public String getTvcnr() {return this.tvcnr;}
	
	private String tvmn = null;
	public void setTvmn(String value) {  this.tvmn = value; }
	public String getTvmn() {return this.tvmn;}
	
	private String tvmnl = null;
	public void setTvmnl(String value) {  this.tvmnl = value; }
	public String getTvmnl() {return this.tvmnl;}
	
	private String tvmnsk = null;
	public void setTvmnsk(String value) {  this.tvmnsk = value; }
	public String getTvmnsk() {return this.tvmnsk;}
	
	private String tveh = null;
	public void setTveh(String value) {  this.tveh = value; }
	public String getTveh() {return this.tveh;}
	
	
	private String tvnt = null;
	public void setTvnt(String value) {  this.tvnt = value; }
	public String getTvnt() { return this.tvnt;}
	
	private String tvnteh = null;
	public void setTvnteh(String value) {  this.tvnteh = value; }
	public String getTvnteh() { return this.tvnteh;}
	
	private String tvfv = null;
	public void setTvfv(String value) {  this.tvfv = value; }
	public String getTvfv() { return this.tvfv;}
	
	private String tvfvnt = null;
	public void setTvfvnt(String value) {  this.tvfvnt = value; }
	public String getTvfvnt() { return this.tvfvnt;}
	
	
	private String nvvnt = null;
	public void setNvvnt(String value) {  this.nvvnt = value; }
	public String getNvvnt() {return this.nvvnt;}
	
	private String nvvt = null;
	public void setNvvt(String value) {  this.nvvt = value; }
	public String getNvvt() {return this.nvvt;}
	
	private String nvvtsk = null;
	public void setNvvtsk(String value) {  this.nvvtsk = value; }
	public String getNvvtsk() {return this.nvvtsk;}
	
	private String nvvktb = null;
	public void setNvvktb(String value) {  this.nvvktb = value; }
	public String getNvvktb() {return this.nvvktb;}
	
	private String nvvktn = null;
	public void setNvvktn(String value) {  this.nvvktn = value; }
	public String getNvvktn() {return this.nvvktn;}
	
	private String nvdty = null;
	public void setNvdty(String value) {  this.nvdty = value; }
	public String getNvdty() {return this.nvdty;}
	
	private String nvdref = null;
	public void setNvdref(String value) {  this.nvdref = value; }
	public String getNvdref() {return this.nvdref;}
	
	private String nvdsk = null;
	public void setNvdsk(String value) {  this.nvdsk = value; }
	public String getNvdsk() {return this.nvdsk;}
	
	private String nvdo = null;
	public void setNvdo(String value) {  this.nvdo = value; }
	public String getNvdo() {return this.nvdo;}
	
	private String nvdosk = null;
	public void setNvdosk(String value) {  this.nvdosk = value; }
	public String getNvdosk() {return this.nvdosk;}
	
	private String nvcnr = null;
	public void setNvcnr(String value) {  this.nvcnr = value; }
	public String getNvcnr() {return this.nvcnr;}
	
	private String nvmn = null;
	public void setNvmn(String value) {  this.nvmn = value; }
	public String getNvmn() {return this.nvmn;}
	
	private String nvmnsk = null;
	public void setNvmnsk(String value) {  this.nvmnsk = value; }
	public String getNvmnsk() {return this.nvmnsk;}
	
	private String nveh = null;
	public void setNveh(String value) {  this.nveh = value; }
	public String getNveh() {return this.nveh;}
	
	
	private String nvnt = null;
	public void setNvnt(String value) {  this.nvnt = value; }
	public String getNvnt() { return this.nvnt;}
	
	private String nvnteh = null;
	public void setNvnteh(String value) {  this.nvnteh = value; }
	public String getNvnteh() { return this.nvnteh;}
	
	private String nvfv = null;
	public void setNvfv(String value) {  this.nvfv = value; }
	public String getNvfv() { return this.nvfv;}
	
	private String nvfvnt = null;
	public void setNvfvnt(String value) {  this.nvfvnt = value; }
	public String getNvfvnt() { return this.nvfvnt;}
	
	private String nvct = null;
	public void setNvct(String value) {  this.nvct = value; }
	public String getNvct() { return this.nvct;}
	
	private String nvctb = null;
	public void setNvctb(String value) {  this.nvctb = value; }
	public String getNvctb() { return this.nvctb;}
	
	private String nvctsk = null;
	public void setNvctsk(String value) {  this.nvctsk = value; }
	public String getNvctsk() { return this.nvctsk;}
	
	private String nvctp = null;
	public void setNvctp(String value) {  this.nvctp = value; }
	public String getNvctp() { return this.nvctp;}
	
	

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
