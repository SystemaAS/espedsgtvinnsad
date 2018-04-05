/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsExportSpecificTopicItemRecord extends JsonAbstractGrandFatherRecord  {
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
		
	//used as an auxiliary in a validate sequence (gross weight) on item line nr 1
	private String numberOfItemLinesInTopicStr = null;
	public void setNumberOfItemLinesInTopicStr(String value) {  this.numberOfItemLinesInTopicStr = value; }
	public String getNumberOfItemLinesInTopicStr() {return this.numberOfItemLinesInTopicStr;}
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private boolean validOppdragRef = true;
	public void setValidOppdragRef(boolean value) {  this.validOppdragRef = value; }
	public boolean isValidOppdragRef() {return this.validOppdragRef;}
	
	
	//Aux. attr. in order to sum all packages
	private Integer sum_of_tvnt = 0;
	public Integer getSum_of_tvnt(){
		int sum = 0;
		try{
			if(this.tvnt!=null && !"".equals(this.tvnt)){
				sum += Integer.parseInt(this.tvnt);
			}
			if(this.tvnt2!=null && !"".equals(this.tvnt2)){
				sum += Integer.parseInt(this.tvnt2);
			}
			if(this.tvnt3!=null && !"".equals(this.tvnt3)){
				sum += Integer.parseInt(this.tvnt3);
			}
			if(this.tvnt4!=null && !"".equals(this.tvnt4)){
				sum += Integer.parseInt(this.tvnt4);
			}
			if(this.tvnt5!=null && !"".equals(this.tvnt5)){
				sum += Integer.parseInt(this.tvnt5);
			}
			
			this.sum_of_tvnt = sum;
			
		}catch(Exception e){
			//Nothing
		}
		
		return sum_of_tvnt;
	}
	
	//Aux. attr. in order to sum all packages
	private Integer sum_of_tvnteh = 0;
	public Integer getSum_of_tvnteh(){
		int sum = 0;
		try{
			//Include tvnteh-series
			if(this.tvnteh!=null && !"".equals(this.tvnteh)){
				sum += Integer.parseInt(this.tvnteh);
			}
			if(this.tvnteh2!=null && !"".equals(this.tvnteh2)){
				sum += Integer.parseInt(this.tvnteh2);
			}
			if(this.tvnteh3!=null && !"".equals(this.tvnteh3)){
				sum += Integer.parseInt(this.tvnteh3);
			}
			if(this.tvnteh4!=null && !"".equals(this.tvnteh4)){
				sum += Integer.parseInt(this.tvnteh4);
			}
			if(this.tvnteh5!=null && !"".equals(this.tvnteh5)){
				sum += Integer.parseInt(this.tvnteh5);
			}
			
			this.sum_of_tvnteh = sum;
			
		}catch(Exception e){
			//Nothing
		}
		
		return sum_of_tvnteh;
	}

	private String tv01 = null;
	public void setTv01(String value) {  this.tv01 = value; }
	public String getTv01() {return this.tv01;}
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() {return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() {return this.tvtdn;}
	
	private String tvli = null;
	public void setTvli(String value) {  this.tvli = value; }
	public String getTvli() {return this.tvli;}
	
	private String tvvnt = null;
	public void setTvvnt(String value) {  this.tvvnt = value; }
	public String getTvvnt() {return this.tvvnt;}
	
	private String tvdk = null;
	public void setTvdk(String value) {  this.tvdk = value; }
	public String getTvdk() {return this.tvdk;}
	
	private String tvvt = null;
	public void setTvvt(String value) {  this.tvvt = value; }
	public String getTvvt() {return this.tvvt;}
	private String tvvt2 = null;
	public void setTvvt2(String value) {  this.tvvt2 = value; }
	public String getTvvt2() {return this.tvvt2;}
	private String tvvt3 = null;
	public void setTvvt3(String value) {  this.tvvt3 = value; }
	public String getTvvt3() {return this.tvvt3;}
	private String tvvt4 = null;
	public void setTvvt4(String value) {  this.tvvt4 = value; }
	public String getTvvt4() {return this.tvvt4;}
	private String tvvt5 = null;
	public void setTvvt5(String value) {  this.tvvt5 = value; }
	public String getTvvt5() {return this.tvvt5;}
	
	
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
	
	//-------------------------------
	//Rubrik 40 (Tidigare Handlingar)
	//-------------------------------
	private String tvtdt = null;
	public void setTvtdt(String value) {  this.tvtdt = value; }
	public String getTvtdt() {return this.tvtdt;}
	private String tvtdr = null;
	public void setTvtdr(String value) {  this.tvtdr = value; }
	public String getTvtdr() { return this.tvtdr;}
	private String tvtdsk = null;
	public void setTvtdsk(String value) {  this.tvtdsk = value; }
	public String getTvtdsk() {return this.tvtdsk;}
	private String tvtdo = null;
	public void setTvtdo(String value) {  this.tvtdo = value; }
	public String getTvtdo() {return this.tvtdo;}
	private String tvtdos = null;
	public void setTvtdos(String value) {  this.tvtdos = value; }
	public String getTvtdos() {return this.tvtdos;}
	//multi-lines have not been implemented yet
	private String tvtdt2 = null;
	public void setTvtdt2(String value) {  this.tvtdt2 = value; }
	public String getTvtdt2() {return this.tvtdt2;}
	private String tvtdr2 = null;
	public void setTvtdr2(String value) {  this.tvtdr2 = value; }
	public String getTvtdr2() { return this.tvtdr2;}
	private String tvtdsk2 = null;
	public void setTvtdsk2(String value) {  this.tvtdsk2 = value; }
	public String getTvtdsk2() {return this.tvtdsk2;}
	private String tvtdo2 = null;
	public void setTvtdo2(String value) {  this.tvtdo2 = value; }
	public String getTvtdo2() {return this.tvtdo2;}
	private String tvtdos2 = null;
	public void setTvtdos2(String value) {  this.tvtdos2 = value; }
	public String getTvtdos2() {return this.tvtdos2;}
	//-------------
	//END Rubrik 40
	//-------------
	
	
	//-------------------------------------------
	//Rubrik 44 (Särskilda- Tilläggsupplysningar)
	//-------------------------------------------
	private String tvdty = null;
	public void setTvdty(String value) {  this.tvdty = value; }
	public String getTvdty() {return this.tvdty;}
	private String tvdty2 = null;
	public void setTvdty2(String value) {  this.tvdty2 = value; }
	public String getTvdty2() {return this.tvdty2;}
	private String tvdty3 = null;
	public void setTvdty3(String value) {  this.tvdty3 = value; }
	public String getTvdty3() {return this.tvdty3;}
	private String tvdty4 = null;
	public void setTvdty4(String value) {  this.tvdty4 = value; }
	public String getTvdty4() {return this.tvdty4;}
	
	private String tvdref = null;
	public void setTvdref(String value) {  this.tvdref = value; }
	public String getTvdref() {return this.tvdref;}
	private String tvdref2 = null;
	public void setTvdref2(String value) {  this.tvdref2 = value; }
	public String getTvdref2() {return this.tvdref2;}
	private String tvdref3 = null;
	public void setTvdref3(String value) {  this.tvdref3 = value; }
	public String getTvdref3() {return this.tvdref3;}
	private String tvdref4 = null;
	public void setTvdref4(String value) {  this.tvdref4 = value; }
	public String getTvdref4() {return this.tvdref4;}
	
	
	private String tvdsk = null;
	public void setTvdsk(String value) {  this.tvdsk = value; }
	public String getTvdsk() { return this.tvdsk;}
	private String tvdsk2 = null;
	public void setTvdsk2(String value) {  this.tvdsk2 = value; }
	public String getTvdsk2() { return this.tvdsk2;}
	private String tvdsk3 = null;
	public void setTvdsk3(String value) {  this.tvdsk3 = value; }
	public String getTvdsk3() { return this.tvdsk3;}
	private String tvdsk4 = null;
	public void setTvdsk4(String value) {  this.tvdsk4 = value; }
	public String getTvdsk4() { return this.tvdsk4;}
	
	private String tvdo = null;
	public void setTvdo(String value) {  this.tvdo = value; }
	public String getTvdo() { return this.tvdo;}
	private String tvdo2 = null;
	public void setTvdo2(String value) {  this.tvdo2 = value; }
	public String getTvdo2() { return this.tvdo2;}
	private String tvdo3 = null;
	public void setTvdo3(String value) {  this.tvdo3 = value; }
	public String getTvdo3() { return this.tvdo3;}
	private String tvdo4 = null;
	public void setTvdo4(String value) {  this.tvdo4 = value; }
	public String getTvdo4() { return this.tvdo4;}
	
	
	private String tvdosk = null;
	public void setTvdosk(String value) {  this.tvdosk = value; }
	public String getTvdosk() { return this.tvdosk;}
	private String tvdosk2 = null;
	public void setTvdosk2(String value) {  this.tvdosk2 = value; }
	public String getTvdosk2() { return this.tvdosk2;}
	private String tvdosk3 = null;
	public void setTvdosk3(String value) {  this.tvdosk3 = value; }
	public String getTvdosk3() { return this.tvdosk3;}
	private String tvdosk4 = null;
	public void setTvdosk4(String value) {  this.tvdosk4 = value; }
	public String getTvdosk4() { return this.tvdosk4;}
	
	private String tvmtxt = null;
	public void setTvmtxt(String value) {  this.tvmtxt = value; }
	public String getTvmtxt() { return this.tvmtxt;}
	private String tvmtxt2 = null;
	public void setTvmtxt2(String value) {  this.tvmtxt2 = value; }
	public String getTvmtxt2() { return this.tvmtxt2;}
	private String tvmtxt3 = null;
	public void setTvmtxt3(String value) {  this.tvmtxt3 = value; }
	public String getTvmtxt3() { return this.tvmtxt3;}
	private String tvmtxt4 = null;
	public void setTvmtxt4(String value) {  this.tvmtxt4 = value; }
	public String getTvmtxt4() { return this.tvmtxt4;}
	
	
	private String tvmsk = null;
	public void setTvmsk(String value) {  this.tvmsk = value; }
	public String getTvmsk() { return this.tvmsk;}
	private String tvmsk2 = null;
	public void setTvmsk2(String value) {  this.tvmsk2 = value; }
	public String getTvmsk2() { return this.tvmsk2;}
	private String tvmsk3 = null;
	public void setTvmsk3(String value) {  this.tvmsk3 = value; }
	public String getTvmsk3() { return this.tvmsk3;}
	private String tvmsk4 = null;
	public void setTvmsk4(String value) {  this.tvmsk4 = value; }
	public String getTvmsk4() { return this.tvmsk4;}
	
	
	private String tvtlo = null;
	public void setTvtlo(String value) {  this.tvtlo = value; }
	public String getTvtlo() { return this.tvtlo;}
	private String tvtlo2 = null;
	public void setTvtlo2(String value) {  this.tvtlo2 = value; }
	public String getTvtlo2() { return this.tvtlo2;}
	private String tvtlo3 = null;
	public void setTvtlo3(String value) {  this.tvtlo3 = value; }
	public String getTvtlo3() { return this.tvtlo3;}
	private String tvtlo4 = null;
	public void setTvtlo4(String value) {  this.tvtlo4 = value; }
	public String getTvtlo4() { return this.tvtlo4;}
	
	
	private String tvexkd = null;
	public void setTvexkd(String value) {  this.tvexkd = value; }
	public String getTvexkd() { return this.tvexkd;}
	private String tvexkd2 = null;
	public void setTvexkd2(String value) {  this.tvexkd2 = value; }
	public String getTvexkd2() { return this.tvexkd2;}
	private String tvexkd3 = null;
	public void setTvexkd3(String value) {  this.tvexkd3 = value; }
	public String getTvexkd3() { return this.tvexkd3;}
	private String tvexkd4 = null;
	public void setTvexkd4(String value) {  this.tvexkd4 = value; }
	public String getTvexkd4() { return this.tvexkd4;}
	
	private String tvexlk = null;
	public void setTvexlk(String value) {  this.tvexlk = value; }
	public String getTvexlk() { return this.tvexlk;}
	private String tvexlk2 = null;
	public void setTvexlk2(String value) {  this.tvexlk2 = value; }
	public String getTvexlk2() { return this.tvexlk2;}
	private String tvexlk3 = null;
	public void setTvexlk3(String value) {  this.tvexlk3 = value; }
	public String getTvexlk3() { return this.tvexlk3;}
	private String tvexlk4 = null;
	public void setTvexlk4(String value) {  this.tvexlk4 = value; }
	public String getTvexlk4() { return this.tvexlk4;}
	//-------------
	//END Rubrik 44
	//-------------
	
	private String tvavd2 = null;
	public void setTvavd2(String value) {  this.tvavd2 = value; }
	public String getTvavd2() { return this.tvavd2;}
	
	private String tvtdn2 = null;
	public void setTvtdn2(String value) {  this.tvtdn2 = value; }
	public String getTvtdn2() { return this.tvtdn2;}
	
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

	private String tvcnr6 = null;
	public void setTvcnr6(String value) {  this.tvcnr6 = value; }
	public String getTvcnr6() { return this.tvcnr6;}

	private String tvmn = null;
	public void setTvmn(String value) {  this.tvmn = value; }
	public String getTvmn() { return this.tvmn;}
	
	private String tvmn2 = null;
	public void setTvmn2(String value) {  this.tvmn2 = value; }
	public String getTvmn2() { return this.tvmn2;}
	
	private String tvmn3 = null;
	public void setTvmn3(String value) {  this.tvmn3 = value; }
	public String getTvmn3() { return this.tvmn3;}
	
	private String tvmn4 = null;
	public void setTvmn4(String value) {  this.tvmn4 = value; }
	public String getTvmn4() { return this.tvmn4;}
	
	private String tvmn5 = null;
	public void setTvmn5(String value) {  this.tvmn5 = value; }
	public String getTvmn5() { return this.tvmn5;}
	
	//only one variable since the language is always the same in all 5 variants
	private String tvmnsk = null;
	public void setTvmnsk(String value) {  this.tvmnsk = value; }
	public String getTvmnsk() { return this.tvmnsk;}
	
	private String tveh = null;
	public void setTveh(String value) {  this.tveh = value; }
	public String getTveh() { return this.tveh;}
	
	private String tveh2 = null;
	public void setTveh2(String value) {  this.tveh2 = value; }
	public String getTveh2() { return this.tveh2;}
	
	private String tveh3 = null;
	public void setTveh3(String value) {  this.tveh3 = value; }
	public String getTveh3() { return this.tveh3;}
	
	private String tveh4 = null;
	public void setTveh4(String value) {  this.tveh4 = value; }
	public String getTveh4() { return this.tveh4;}
	
	private String tveh5 = null;
	public void setTveh5(String value) {  this.tveh5 = value; }
	public String getTveh5() { return this.tveh5;}
	
	
	private String tvnt = null;
	public void setTvnt(String value) { this.tvnt = value; }  
	public String getTvnt() { return this.tvnt; }
		
	private String tvnt2 = null;
	public void setTvnt2(String value) {  this.tvnt2 = value; }
	public String getTvnt2() { return this.tvnt2; }
	
	private String tvnt3 = null;
	public void setTvnt3(String value) {  this.tvnt3 = value; }
	public String getTvnt3() { return this.tvnt3; }
	
	private String tvnt4 = null;
	public void setTvnt4(String value) {  this.tvnt4 = value; }
	public String getTvnt4() { return this.tvnt4; }
	
	private String tvnt5 = null;
	public void setTvnt5(String value) {  this.tvnt5 = value; }
	public String getTvnt5() { return this.tvnt5; }
	
	private String tvnteh = null;
	public void setTvnteh(String value) {  this.tvnteh = value; }
	public String getTvnteh() { return this.tvnteh; }
	
	private String tvnteh2 = null;
	public void setTvnteh2(String value) {  this.tvnteh2 = value; }
	public String getTvnteh2() { return this.tvnteh2; }
	
	private String tvnteh3 = null;
	public void setTvnteh3(String value) {  this.tvnteh3 = value; }
	public String getTvnteh3() { return this.tvnteh3; }
	
	private String tvnteh4 = null;
	public void setTvnteh4(String value) {  this.tvnteh4 = value; }
	public String getTvnteh4() { return this.tvnteh4; }
	
	private String tvnteh5 = null;
	public void setTvnteh5(String value) {  this.tvnteh5 = value; }
	public String getTvnteh5() { return this.tvnteh5; }

	
	private String tvfv = null;
	public void setTvfv(String value) {  this.tvfv = value; }
	public String getTvfv() { return this.tvfv; }
	
	private String tvfvnt = null;
	public void setTvfvnt(String value) {  this.tvfvnt = value; }
	public String getTvfvnt() { return this.tvfvnt; }
	
	private String tvln = null;
	public void setTvln(String value) {  this.tvln = value; }
	public String getTvln() { return this.tvln; }
	
	private String tvrefl = null;
	public void setTvrefl(String value) {  this.tvrefl = value; }
	public String getTvrefl() { return this.tvrefl; }
	
	
	//Parties SENDER
	private String tvnas = null;
	public void setTvnas(String value) {  this.tvnas = value; }
	public String getTvnas() { return this.tvnas; }
	
	private String tvkns = null;
	public void setTvkns(String value) {  this.tvkns = value; }
	public String getTvkns() { return this.tvkns; }
	
	private String tvtins = null;
	public void setTvtins(String value) {  this.tvtins = value; }
	public String getTvtins() { return this.tvtins; }
	
	private String tvads1 = null;
	public void setTvads1(String value) {  this.tvads1 = value; }
	public String getTvads1() { return this.tvads1; }
	
	private String tvpns = null;
	public void setTvpns(String value) {  this.tvpns = value; }
	public String getTvpns() { return this.tvpns; }
	
	private String tvpss = null;
	public void setTvpss(String value) {  this.tvpss = value; }
	public String getTvpss() { return this.tvpss; }
	
	private String tvlks = null;
	public void setTvlks(String value) {  this.tvlks = value; }
	public String getTvlks() { return this.tvlks; }
	
	private String tvsks = null;
	public void setTvsks(String value) {  this.tvsks = value; }
	public String getTvsks() { return this.tvsks; }

	//Parties RECEIVER
	private String tvnak = null;
	public void setTvnak(String value) {  this.tvnak = value; }
	public String getTvnak() { return this.tvnak; }
	
	private String tvknk = null;
	public void setTvknk(String value) {  this.tvknk = value; }
	public String getTvknk() { return this.tvknk; }
	
	private String tvtink = null;
	public void setTvtink(String value) {  this.tvtink = value; }
	public String getTvtink() { return this.tvtink; }
	
	private String tvadk1 = null;
	public void setTvadk1(String value) {  this.tvadk1 = value; }
	public String getTvadk1() { return this.tvadk1; }
	
	private String tvpnk = null;
	public void setTvpnk(String value) {  this.tvpnk = value; }
	public String getTvpnk() { return this.tvpnk; }
	
	private String tvpsk = null;
	public void setTvpsk(String value) {  this.tvpsk = value; }
	public String getTvpsk() { return this.tvpsk; }
	
	private String tvlkk = null;
	public void setTvlkk(String value) {  this.tvlkk = value; }
	public String getTvlkk() { return this.tvlkk; }
	
	private String tvskk = null;
	public void setTvskk(String value) {  this.tvskk = value; }
	public String getTvskk() { return this.tvskk; }
	
	
	
	//---------
	//Sikkerhet
	//---------
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
