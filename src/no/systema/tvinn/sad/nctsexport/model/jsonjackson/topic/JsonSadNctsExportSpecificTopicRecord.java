/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * AS400 File: TRUH
 *
 *
 */
public class JsonSadNctsExportSpecificTopicRecord extends JsonAbstractGrandFatherRecord {
	private static final Logger logger = Logger.getLogger(JsonSadNctsExportSpecificTopicRecord.class.getName());
	
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
	
	//test indicator
	private String dkxh_0035 = null;
	public void setDkxh_0035(String value) {  this.dkxh_0035 = value; }
	public String getDkxh_0035() {return this.dkxh_0035;}
		
	//thavd Avdeling 
	private String thavd = null;
	public void setThavd(String value) {  this.thavd = value; }
	public String getThavd() {return this.thavd;}
	//thtdn Oppdragsnr
	private String thtdn = null;
	public void setThtdn(String value) {  this.thtdn = value; }
	public String getThtdn() {return this.thtdn;}
	//status
	private String thst = null;
	public void setThst(String value) {  this.thst = value; }
	public String getThst() {return this.thst;}
	//signatur
	private String thsg = null;
	public void setThsg(String value) {  this.thsg = value; }
	public String getThsg() {return this.thsg;}
	//Date YMD
	private String thdt = null;
	public void setThdt(String value) {  this.thdt = value; }
	public String getThdt() {return this.thdt;}
	
	//lrnNr
	private String lrnNr = null;
	public void setLrnNr(String value) {  this.lrnNr = value; }
	public String getLrnNr() {return this.lrnNr;}
	
	//MRN-nr.
	private String thtrnr = null;
	public void setThtrnr(String value) {  this.thtrnr = value; }
	public String getThtrnr() { return this.thtrnr;}
		
	
	//projektnr
	private String thpro = null;
	public void setThpro(String value) {  this.thpro = value; }
	public String getThpro() {return this.thpro;}
	
	//Förenklad procedur
	private String thenkl = null;
	public void setThenkl(String value) {  this.thenkl = value; }
	public String getThenkl() {return this.thenkl;}
	
	private String thdk = null;
	public void setThdk(String value) {  this.thdk = value; }
	public String getThdk() {return this.thdk;}
	
	private String thkna = null;
	public void setThkna(String value) {  this.thkna = value; }
	public String getThkna() {return this.thkna;}
	
	private String thnaa = null;
	public void setThnaa(String value) {  this.thnaa = value; }
	public String getThnaa() {return this.thnaa;}
	
	private String thada1 = null;
	public void setThada1(String value) {  this.thada1 = value; }
	public String getThada1() {return this.thada1;}
	
	private String thpna = null;
	public void setThpna(String value) {  this.thpna = value; }
	public String getThpna() {return this.thpna;}
	
	
	
	private String thpsa = null;
	public void setThpsa(String value) {  this.thpsa = value; }
	public String getThpsa() {return this.thpsa;}
	
	private String thlka = null;
	public void setThlka(String value) {  this.thlka = value; }
	public String getThlka() {return this.thlka;}
	
	private String thska = null;
	public void setThska(String value) {  this.thska = value; }
	public String getThska() {return this.thska;}
	
	
	private String thtina = null;
	public void setThtina(String value) {  this.thtina = value; }
	public String getThtina() {return this.thtina;}
	
	private String thkns = null;
	public void setThkns(String value) {  this.thkns = value; }
	public String getThkns() {return this.thkns;}
	
	private String thnas = null;
	public void setThnas(String value) {  this.thnas = value; }
	public String getThnas() {return this.thnas;}
	
	private String thads1 = null;
	public void setThads1(String value) {  this.thads1 = value; }
	public String getThads1() {return this.thads1;}
	
	private String thpns = null;
	public void setThpns(String value) {  this.thpns = value; }
	public String getThpns() {return this.thpns;}
	
	private String thpss = null;
	public void setThpss(String value) {  this.thpss = value; }
	public String getThpss() {return this.thpss;}
	
	private String thlks = null;
	public void setThlks(String value) {  this.thlks = value; }
	public String getThlks() {return this.thlks;}
	
	private String thsks = null;
	public void setThsks(String value) {  this.thsks = value; }
	public String getThsks() {return this.thsks;}
	
	private String thtins = null;
	public void setThtins(String value) {  this.thtins = value; }
	public String getThtins() {return this.thtins;}
	
	private String thknk = null;
	public void setThknk(String value) {  this.thknk = value; }
	public String getThknk() {return this.thknk;}
	
	
	private String thnak = null;
	public void setThnak(String value) {  this.thnak = value; }
	public String getThnak() { return this.thnak;}
	
	private String thadk1 = null;
	public void setThadk1(String value) {  this.thadk1 = value; }
	public String getThadk1() { return this.thadk1;}
	
	private String thpnk = null;
	public void setThpnk(String value) {  this.thpnk = value; }
	public String getThpnk() { return this.thpnk;}
	
	private String thpsk = null;
	public void setThpsk(String value) {  this.thpsk = value; }
	public String getThpsk() { return this.thpsk;}
	
	private String thlkk = null;
	public void setThlkk(String value) {  this.thlkk = value; }
	public String getThlkk() { return this.thlkk;}
	
	private String thskk = null;
	public void setThskk(String value) {  this.thskk = value; }
	public String getThskk() { return this.thskk;}
	
	private String thtink = null;
	public void setThtink(String value) {  this.thtink = value; }
	public String getThtink() { return this.thtink;}
	
	private String thblk = null;
	public void setThblk(String value) {  this.thblk = value; }
	public String getThblk() { return this.thblk;}
	
	private String thlsd = null;
	public void setThlsd(String value) {  this.thlsd = value; }
	public String getThlsd() { return this.thlsd;}
	
	private String thlasd = null;
	public void setThlasd(String value) {  this.thlasd = value; }
	public String getThlasd() { return this.thlasd;}
	
	private String thlas2 = null;
	public void setThlas2(String value) {  this.thlas2 = value; }
	public String getThlas2() { return this.thlas2;}
	
	private String thalk = null;
	public void setThalk(String value) {  this.thalk = value; }
	public String getThalk() { return this.thalk;}
	
	private String thtrm = null;
	public void setThtrm(String value) {  this.thtrm = value; }
	public String getThtrm() { return this.thtrm;}
	
	private String thtrmi = null;
	public void setThtrmi(String value) {  this.thtrmi = value; }
	public String getThtrmi() { return this.thtrmi;}
	
	private String thtaid = null;
	public void setThtaid(String value) {  this.thtaid = value; }
	public String getThtaid() { return this.thtaid;}
	
	private String thtask = null;
	public void setThtask(String value) {  this.thtask = value; }
	public String getThtask() { return this.thtask;}
	
	private String thtalk = null;
	public void setThtalk(String value) {  this.thtalk = value; }
	public String getThtalk() { return this.thtalk;}
	
	private String thtgid = null;
	public void setThtgid(String value) {  this.thtgid = value; }
	public String getThtgid() { return this.thtgid;}
	
	private String thtgsk = null;
	public void setThtgsk(String value) {  this.thtgsk = value; }
	public String getThtgsk() { return this.thtgsk;}
	
	private String thtglk = null;
	public void setThtglk(String value) {  this.thtglk = value; }
	public String getThtglk() { return this.thtglk;}
	
	private String thskfd = null;
	public void setThskfd(String value) {  this.thskfd = value; }
	public String getThskfd() { return this.thskfd;}
	
	private String thcats = null;
	public void setThcats(String value) {  this.thcats = value; }
	public String getThcats() { return this.thcats;}
	
	private String thtsd1 = null;
	public void setThtsd1(String value) {  this.thtsd1 = value; }
	public String getThtsd1() { return this.thtsd1;}
	
	private String thtsd2 = null;
	public void setThtsd2(String value) {  this.thtsd2 = value; }
	public String getThtsd2() { return this.thtsd2;}
	
	private String thtsd3 = null;
	public void setThtsd3(String value) {  this.thtsd3 = value; }
	public String getThtsd3() { return this.thtsd3;}
	
	private String thtsd4 = null;
	public void setThtsd4(String value) {  this.thtsd4 = value; }
	public String getThtsd4() { return this.thtsd4;}
	
	private String thtsd5 = null;
	public void setThtsd5(String value) {  this.thtsd5 = value; }
	public String getThtsd5() { return this.thtsd5;}
	
	private String thtsd6 = null;
	public void setThtsd6(String value) {  this.thtsd6 = value; }
	public String getThtsd6() { return this.thtsd6;}
	
	private String thtsd7 = null;
	public void setThtsd7(String value) {  this.thtsd7 = value; }
	public String getThtsd7() { return this.thtsd7;}
	
	private String thtsd8 = null;
	public void setThtsd8(String value) {  this.thtsd8 = value; }
	public String getThtsd8() { return this.thtsd8;}
	
	private String thtsb = null;
	public void setThtsb(String value) {  this.thtsb = value; }
	public String getThtsb() { return this.thtsb;}
	
	private String thddt = null;
	public void setThddt(String value) {  this.thddt = value; }
	public String getThddt() { return this.thddt;}
	
	private String thdfkd = null;
	public void setThdfkd(String value) {  this.thdfkd = value; }
	public String getThdfkd() { return this.thdfkd;}
	
	private String thdfsk = null;
	public void setThdfsk(String value) {  this.thdfsk = value; }
	public String getThdfsk() { return this.thdfsk;}
	
	private String thdkr = null;
	public void setThdkr(String value) {  this.thdkr = value; }
	public String getThdkr() { return this.thdkr;}
	
	private String thdant = null;
	public void setThdant(String value) {  this.thdant = value; }
	public String getThdant() { return this.thdant;}
	
	private String thddtk = null;
	public void setThddtk(String value) {  this.thddtk = value; }
	public String getThddtk() { return this.thddtk;}
	
	private String thdats = null;
	public void setThdats(String value) {  this.thdats = value; }
	public String getThdats() { return this.thdats;}
	
	private String thdkav = null;
	public void setThdkav(String value) {  this.thdkav = value; }
	public String getThdkav() { return this.thdkav;}
	
	private String thdksk = null;
	public void setThdksk(String value) {  this.thdksk = value; }
	public String getThdksk() { return this.thdksk;}
	
	private String thgkd = null;
	public void setThgkd(String value) {  this.thgkd = value; }
	public String getThgkd() { return this.thgkd;}
	
	private String thgft1 = null;
	public void setThgft1(String value) {  this.thgft1 = value; }
	public String getThgft1() { return this.thgft1;}
	
	private String thgft2 = null;
	public void setThgft2(String value) {  this.thgft2 = value; }
	public String getThgft2() { return this.thgft2;}
	
	private String thgadk = null;
	public void setThgadk(String value) {  this.thgadk = value; }
	public String getThgadk() { return this.thgadk;}
	
	private String thgbl = null;
	public void setThgbl(String value) {  this.thgbl = value; }
	public String getThgbl() { 
		String retval = this.thgbl;
		//insert a decimal comma since the field is wrongly formatted from RPG
		/*String value = new String(this.thgbl);
		if(value!=null && value.length()>3){
			if(!value.contains(",")){
				int x = value.length();
				String delInt = value.substring(0,x-2);
				String delDec = value.substring(x-2);
				
				String formattedValue = delInt + "," + delDec;
				retval = formattedValue;
			}
		}*/
		return retval;
	}
	
	private String thgvk = null;
	public void setThgvk(String value) {  this.thgvk = value; }
	public String getThgvk() { return this.thgvk;}
	
	private String thgbgi = null;
	public void setThgbgi(String value) {  this.thgbgi = value; }
	public String getThgbgi() { return this.thgbgi;}
	
	private String thgbgu = null;
	public void setThgbgu(String value) {  this.thgbgu = value; }
	public String getThgbgu() { return this.thgbgu;}
	
	private String thkdc = null;
	public void setThkdc(String value) {  this.thkdc = value; }
	public String getThkdc() { return this.thkdc;}
	
	private String thtssd = null;
	public void setThtssd(String value) {  this.thtssd = value; }
	public String getThtssd() { return this.thtssd;}
	
	private String thtsn1 = null;
	public void setThtsn1(String value) {  this.thtsn1 = value; }
	public String getThtsn1() { return this.thtsn1;}
	
	private String thtsn2 = null;
	public void setThtsn2(String value) {  this.thtsn2 = value; }
	public String getThtsn2() { return this.thtsn2;}
	
	private String thtspn = null;
	public void setThtspn(String value) {  this.thtspn = value; }
	public String getThtspn() { return this.thtspn;}
	
	private String thtsps = null;
	public void setThtsps(String value) {  this.thtsps = value; }
	public String getThtsps() { return this.thtsps;}
	
	private String thtslk = null;
	public void setThtslk(String value) {  this.thtslk = value; }
	public String getThtslk() { return this.thtslk;}
	
	private String thtssk = null;
	public void setThtssk(String value) {  this.thtssk = value; }
	public String getThtssk() { return this.thtssk;}
	
	private String thlstl = null;
	public void setThlstl(String value) {  this.thlstl = value; }
	public String getThlstl() { return this.thlstl;}
	
	private String thvpos = null;
	public void setThvpos(String value) {  this.thvpos = value; }
	public String getThvpos() { return this.thvpos;}
	
	private String thntk = null;
	public void setThntk(String value) {  this.thntk = value; }
	public String getThntk() { return this.thntk;}
	
	private String thvkb = null;
	public void setThvkb(String value) {  this.thvkb = value; }
	public String getThvkb() { return this.thvkb;}
	
	private String thdst = null;
	public void setThdst(String value) {  this.thdst = value; }
	public String getThdst() { return this.thdst;}
	
	private String thdsk = null;
	public void setThdsk(String value) {  this.thdsk = value; }
	public String getThdsk() { return this.thdsk;}
	
	private String thtarf = null;
	public void setThtarf(String value) {  this.thtarf = value; }
	public String getThtarf() { return this.thtarf;}
	
	private String thws = null;
	public void setThws(String value) {  this.thws = value; }
	public String getThws() { return this.thws;}
	
	
	private String thtrdt = null;
	public void setThtrdt(String value) {  this.thtrdt = value; }
	public String getThtrdt() { return this.thtrdt;}
	
	private String thomd = null;
	public void setThomd(String value) {  this.thomd = value; }
	public String getThomd() { return this.thomd;}
	
	private String thbind = null;
	public void setThbind(String value) {  this.thbind = value; }
	public String getThbind() { return this.thbind;}
	
	private String thtet = null;
	public void setThtet(String value) {  this.thtet = value; }
	public String getThtet() { return this.thtet;}
	
	//Std value for NCTS export. Needed when Signering (send to) is used
	//This value is valid ONLY for NCTS export
	private String thmf = "015";
	public void setThmf(String value) {  this.thmf = value; }
	public String getThmf() { return this.thmf;}
	
	private String thmp = null;
	public void setThmp(String value) {  this.thmp = value; }
	public String getThmp() { return this.thmp;}
	
	private String thpkl = null;
	public void setThpkl(String value) {  this.thpkl = value; }
	public String getThpkl() { return this.thpkl;}
	
	private String thkdv = null;
	public void setThkdv(String value) {  this.thkdv = value; }
	public String getThkdv() { return this.thkdv;}
	
	private String th0035 = null;
	public void setTh0035(String value) {  this.th0035 = value; }
	public String getTh0035() { return this.th0035;}
	
	//LRN-nr
	String thtuid = null;
	public void setThtuid(String value) {  this.thtuid = value; }
	public String getThtuid() { return this.thtuid;}
	
	private String thsgdk = null;
	public void setThsgdk(String value) {  this.thsgdk = value; }
	public String getThsgdk() { return this.thsgdk;}
	
	private String thsgme = null;
	public void setThsgme(String value) {  this.thsgme = value; }
	public String getThsgme() { return this.thsgme;}
	
	private String thsgut = null;
	public void setThsgut(String value) {  this.thsgut = value; }
	public String getThsgut() { return this.thsgut;}
	
	private String thsgid = null;
	public void setThsgid(String value) {  this.thsgid = value; }
	public String getThsgid() { return this.thsgid;}
	
	private String thsgdt = null;
	public void setThsgdt(String value) {  this.thsgdt = value; }
	public String getThsgdt() { return this.thsgdt;}
	
	private String thbyte = null;
	public void setThbyte(String value) {  this.thbyte = value; }
	public String getThbyte() { return this.thbyte;}
	
	private String tggbl = null;
	public void setTggbl(String value) {  this.tggbl = value; }
	public String getTggbl() { return this.tggbl;}
	
	private String tggblb = null;
	public void setTggblb(String value) {  this.tggblb = value; }
	public String getTggblb() { return this.tggblb;}
	
	private String tgprm = null;
	public void setTgprm(String value) {  this.tgprm = value; }
	public String getTgprm() { return this.tgprm;}
	
	//Criteria in order to present a warranty alarm
	private boolean warrantyAlarm = false;
	public boolean getWarrantyAlarm() { 
		try{
			Double totalLimit= null;
			Double totalUsed = null;
			Double percentage = null;
			if(this.tggbl != null && this.tggblb !=null && this.tgprm !=null){
				if(!"".equals(this.tggbl)&& !"".equals(this.tggblb) && !"".equals(this.tgprm)){
					try{
						totalLimit = Double.valueOf(this.tggbl);
						totalUsed = Double.valueOf(this.tggblb);
						percentage = Double.valueOf(this.tgprm);
						//Check for math validity
						if(percentage>0){
							Double validCeiling = totalLimit * (percentage/100);
							if(validCeiling<totalUsed){
								this.warrantyAlarm = true;
							}
						}
					}catch(Exception e){
						//nothing;	
					}
				}
			}
		}catch(Exception e){
			logger.info("CATCH ON Warranty....!!!!!!!!!!!!!");
		}
		
		return this.warrantyAlarm;
	}
	
	//---------
	//Sikkerhet
	//---------
	private String thsik = null;
	public void setThsik(String value) {  this.thsik = value; }
	public String getThsik() { return this.thsik;}
	
	private String thdta = null;
	public void setThdta(String value) {  this.thdta = value; }
	public String getThdta() { return this.thdta;}
	
	private String thtma = null;
	public void setThtma(String value) {  this.thtma = value; }
	public String getThtma() { return this.thtma;}
	
	private String thspom = null;
	public void setThspom(String value) {  this.thspom = value; }
	public String getThspom() { return this.thspom;}
	
	private String thtkbm = null;
	public void setThtkbm(String value) {  this.thtkbm = value; }
	public String getThtkbm() { return this.thtkbm;}
	
	private String thkref = null;
	public void setThkref(String value) {  this.thkref = value; }
	public String getThkref() { return this.thkref;}

	private String thtref = null;
	public void setThtref(String value) {  this.thtref = value; }
	public String getThtref() { return this.thtref;}

	private String thlosd = null;
	public void setThlosd(String value) {  this.thlosd = value; }
	public String getThlosd() { return this.thlosd;}
	
	private String thlosdsk = null;
	public void setThlosdsk(String value) {  this.thlosdsk = value; }
	public String getThlosdsk() { return this.thlosdsk;}
	
	private String thlkr1 = null;
	public void setThlkr1(String value) {  this.thlkr1 = value; }
	public String getThlkr1() { return this.thlkr1;}
	private String thlkr2 = null;
	public void setThlkr2(String value) {  this.thlkr2 = value; }
	public String getThlkr2() { return this.thlkr2;}
	private String thlkr3 = null;
	public void setThlkr3(String value) {  this.thlkr3 = value; }
	public String getThlkr3() { return this.thlkr3;}
	private String thlkr4 = null;
	public void setThlkr4(String value) {  this.thlkr4 = value; }
	public String getThlkr4() { return this.thlkr4;}
	private String thlkr5 = null;
	public void setThlkr5(String value) {  this.thlkr5 = value; }
	public String getThlkr5() { return this.thlkr5;}
	private String thlkr6 = null;
	public void setThlkr6(String value) {  this.thlkr6 = value; }
	public String getThlkr6() { return this.thlkr6;}
	private String thlkr7 = null;
	public void setThlkr7(String value) {  this.thlkr7 = value; }
	public String getThlkr7() { return this.thlkr7;}
	private String thlkr8 = null;
	public void setThlkr8(String value) {  this.thlkr8 = value; }
	public String getThlkr8() { return this.thlkr8;}
	
	
	//-------------
	// Selger
	//-------------
	private String thknss = null;
	public void setThknss(String value) {  this.thknss = value; }
	public String getThknss() { return this.thknss;}
	
	private String thnass = null;
	public void setThnass(String value) {  this.thnass = value; }
	public String getThnass() { return this.thnass;}
	
	private String thadss1 = null;
	public void setThadss1(String value) {  this.thadss1 = value; }
	public String getThadss1() { return this.thadss1;}
	
	private String thpnss = null;
	public void setThpnss(String value) {  this.thpnss = value; }
	public String getThpnss() { return this.thpnss;}
	
	private String thpsss = null;
	public void setThpsss(String value) {  this.thpsss = value; }
	public String getThpsss() { return this.thpsss;}
	
	private String thlkss = null;
	public void setThlkss(String value) {  this.thlkss = value; }
	public String getThlkss() { return this.thlkss;}
	
	private String thskss = null;
	public void setThskss(String value) {  this.thskss = value; }
	public String getThskss() { return this.thskss;}
	
	private String thtinss = null;
	public void setThtinss(String value) {  this.thtinss = value; }
	public String getThtinss() { return this.thtinss;}
	
	//-------------
	// Kjøper
	//-------------
	private String thknks = null;
	public void setThknks(String value) {  this.thknks = value; }
	public String getThknks() { return this.thknks;}
	
	private String thnaks = null;
	public void setThnaks(String value) {  this.thnaks = value; }
	public String getThnaks() { return this.thnaks;}
	
	private String thadks1 = null;
	public void setThadks1(String value) {  this.thadks1 = value; }
	public String getThadks1() { return this.thadks1;}
	
	private String thpnks = null;
	public void setThpnks(String value) {  this.thpnks = value; }
	public String getThpnks() { return this.thpnks;}
	
	private String thpsks = null;
	public void setThpsks(String value) {  this.thpsks = value; }
	public String getThpsks() { return this.thpsks;}
	
	private String thlkks = null;
	public void setThlkks(String value) {  this.thlkks = value; }
	public String getThlkks() { return this.thlkks;}
	
	private String thskks = null;
	public void setThskks(String value) {  this.thskks = value; }
	public String getThskks() { return this.thskks;}
	
	private String thtinks = null;
	public void setThtinks(String value) {  this.thtinks = value; }
	public String getThtinks() { return this.thtinks;}
	
	//-------------
	// Kjøper
	//-------------
	private String thknt = null;
	public void setThknt(String value) {  this.thknt = value; }
	public String getThknt() { return this.thknt;}
	
	private String thnat = null;
	public void setThnat(String value) {  this.thnat = value; }
	public String getThnat() { return this.thnat;}
	
	private String thadt1 = null;
	public void setThadt1(String value) {  this.thadt1 = value; }
	public String getThadt1() { return this.thadt1;}
	
	private String thpnt = null;
	public void setThpnt(String value) {  this.thpnt = value; }
	public String getThpnt() { return this.thpnt;}
	
	private String thpst = null;
	public void setThpst(String value) {  this.thpst = value; }
	public String getThpst() { return this.thpst;}
	
	private String thlkt = null;
	public void setThlkt(String value) {  this.thlkt = value; }
	public String getThlkt() { return this.thlkt;}
	
	private String thskt = null;
	public void setThskt(String value) {  this.thskt = value; }
	public String getThskt() { return this.thskt;}
	
	private String thtint = null;
	public void setThtint(String value) {  this.thtint = value; }
	public String getThtint() { return this.thtint;}
		
	
	/**
	 * Used for java reflection in other classes
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
