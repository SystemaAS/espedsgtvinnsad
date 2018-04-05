/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * AS400 File: SAEH ; Format: SAEHR
 * 
 *
 */
public class JsonSadExportSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	
	private String o2_sest = null;
	public void setO2_sest(String value) {  this.o2_sest = value; }
	public String getO2_sest() { return this.o2_sest;}
	
	private String o2_sedt = null;
	public void setO2_sedt(String value) {  this.o2_sedt = value; }
	public String getO2_sedt() { return this.o2_sedt;}
	
	private String o2_semf = null;
	public void setO2_semf(String value) {  this.o2_semf = value; }
	public String getO2_semf() { return this.o2_semf;}
	
	
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	private String sumOfAntalKolliInItemLinesStr = null;
	public String getSumOfAntalKolliInItemLinesStr() {
		this.sumOfAntalKolliInItemLinesStr = String.valueOf(this.sumOfAntalKolliInItemLines);
		return this.sumOfAntalKolliInItemLinesStr;
	}
	
	private Integer sumOfAntalItemLines = 0;
	public void setSumOfAntalItemLines(Integer value) {  this.sumOfAntalItemLines = value; }
	public Integer getSumOfAntalItemLines() {return this.sumOfAntalItemLines;}
	
	private String sumOfAntalItemLinesStr = null;
	public String getSumOfAntalItemLinesStr() {
		this.sumOfAntalItemLinesStr = String.valueOf(this.sumOfAntalItemLines);
		return this.sumOfAntalItemLinesStr;
	}
	
	private Double sumTotalAmountItemLines = 0.00D;
	public void setSumTotalAmountItemLines(Double value) {  this.sumTotalAmountItemLines = value; }
	public Double getSumTotalAmountItemLines() {return this.sumTotalAmountItemLines;}
	
	private String sumTotalAmountItemLinesStr = null;
	public String getSumTotalAmountItemLinesStr() {
		this.sumTotalAmountItemLinesStr = String.valueOf(this.sumTotalAmountItemLines);
		return this.sumTotalAmountItemLinesStr;
	}
	
	private Double sumTotalBruttoViktItemLines = 0.00D;
	public void setSumTotalBruttoViktItemLines(Double value) {  this.sumTotalBruttoViktItemLines = value; }
	public Double getSumTotalBruttoViktItemLines() {return this.sumTotalBruttoViktItemLines;}
	
	private String sumTotalBruttoViktItemLinesStr = null;
	public String getSumTotalBruttoViktItemLinesStr() {
		this.sumTotalBruttoViktItemLinesStr = String.valueOf(this.sumTotalBruttoViktItemLines);
		return this.sumTotalBruttoViktItemLinesStr;
	}
	
	private boolean finansOpplysningarExist = false;
	public void setFinansOpplysningarExist(boolean value) { this.finansOpplysningarExist = value; }
	public boolean getFinansOpplysningarExist (){ return this.finansOpplysningarExist; }

	//Used when different currencies exist. The main currency must be = SEK
	private String finansOpplysningarTotValidCurrency = null;
	public void setFinansOpplysningarTotValidCurrency(String value) {  this.finansOpplysningarTotValidCurrency = value; }
	public String getFinansOpplysningarTotValidCurrency() { return this.finansOpplysningarTotValidCurrency; }
	
	private String finansOpplysningarTotSum = null;
	public void setFinansOpplysningarTotSum(String value) {  this.finansOpplysningarTotSum = value; }
	public String getFinansOpplysningarTotSum() { return this.finansOpplysningarTotSum; }
	
	private String finansOpplysningarTotKurs = null;
	public void setFinansOpplysningarTotKurs(String value) {  this.finansOpplysningarTotKurs = value; }
	public String getFinansOpplysningarTotKurs() { return this.finansOpplysningarTotKurs; }
	
	
	//in order to validate before a "send topic"
	private boolean validUpdate = false;
	public void setValidUpdate(boolean value) {  this.validUpdate = value; }
	public boolean isValidUpdate() {return this.validUpdate;}
	
	
	private String seavd = null;
	public void setSeavd(String value) {  this.seavd = value; }
	public String getSeavd() {return this.seavd;}
	
	private String sest = null;
	public void setSest(String value) {  this.sest = value; }
	public String getSest() {return this.sest;}
	
	private String setdn = null;
	public void setSetdn(String value) {  this.setdn = value; }
	public String getSetdn() {return this.setdn;}
	
	private String seur = null;
	public void setSeur(String value) {  this.seur = value; }
	public String getSeur() {return this.seur;}
	
	private String sedty = null;
	public void setSedty(String value) {  this.sedty = value; }
	public String getSedty() {return this.sedty;}
	
	private String sedp = null;
	public void setSedp(String value) {  this.sedp = value; }
	public String getSedp() {return this.sedp;}

	private String sekns = null;
	public void setSekns(String value) {  this.sekns = value; }
	public String getSekns() {return this.sekns;}
	
	private String senas = null;
	public void setSenas(String value) {  this.senas = value; }
	public String getSenas() {return this.senas;}
	
	private String seads1 = null;
	public void setSeads1(String value) {  this.seads1 = value; }
	public String getSeads1() {return this.seads1;}
	
	private String seads2 = null;
	public void setSeads2(String value) {  this.seads2 = value; }
	public String getSeads2() {return this.seads2;}
	
	private String seads3 = null;
	public void setSeads3(String value) {  this.seads3 = value; }
	public String getSeads3() {return this.seads3;}
	
	private String sentk = null;
	public void setSentk(String value) {  this.sentk = value; }
	public String getSentk() {return this.sentk;}
	
	private String sevkb = null;
	public void setSevkb(String value) {  this.sevkb = value; }
	public String getSevkb() {return this.sevkb;}
	private Double sevkbDbl = 0.00D;
	public Double getSevkbDbl() {
		if(this.sevkb!=null){
			try{
				this.sevkbDbl = Double.parseDouble(this.sevkb.replace(",","."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.sevkbDbl;
	}
	
	private String sekdc = null;
	public void setSekdc(String value) {  this.sekdc = value; }
	public String getSekdc() {return this.sekdc;}
	
	private String sesg = null;
	public void setSesg(String value) {  this.sesg = value; }
	public String getSesg() {return this.sesg;}
	
	
	private String seknk = null;
	public void setSeknk(String value) {  this.seknk = value; }
	public String getSeknk() {return this.seknk;}
	
	private String serg = null;
	public void setSerg(String value) {  this.serg = value; }
	public String getSerg() {return this.serg;}
	
	private String senak = null;
	public void setSenak(String value) {  this.senak = value; }
	public String getSenak() {return this.senak;}
	
	private String seadk1 = null;
	public void setSeadk1(String value) {  this.seadk1 = value; }
	public String getSeadk1() {return this.seadk1;}
	
	private String seadk2 = null;
	public void setSeadk2(String value) {  this.seadk2 = value; }
	public String getSeadk2() {return this.seadk2;}
	
	
	private String seadk3 = null;
	public void setSeadk3(String value) {  this.seadk3 = value; }
	public String getSeadk3() {return this.seadk3;}
	
	
	private String selka = null;
	public void setSelka(String value) {  this.selka = value; }
	public String getSelka() {return this.selka;}
	
	private String setlf = null;
	public void setSetlf(String value) {  this.setlf = value; }
	public String getSetlf() {return this.setlf;}
	
	private String senad = null;
	public void setSenad(String value) {  this.senad = value; }
	public String getSenad() {return this.senad;}

	private String selv = null;
	public void setSelv(String value) {  this.selv = value; }
	public String getSelv() {return this.selv;}

	private String selvt = null;
	public void setSelvt(String value) {  this.selvt = value; }
	public String getSelvt() {return this.selvt;}

	private String setrid = null;
	public void setSetrid(String value) {  this.setrid = value; }
	public String getSetrid() {return this.setrid;}
	
	private String selkt = null;
	public void setSelkt(String value) {  this.selkt = value; }
	public String getSelkt() {return this.selkt;}

	private String seval1 = null;
	public void setSeval1(String value) {  this.seval1 = value; }
	public String getSeval1() {return this.seval1;}
	
	private String sebel1 = null;
	public void setSebel1(String value) {  this.sebel1 = value; }
	public String getSebel1() {return this.sebel1;}

	
	private Double sebel1Dbl = 0.00D;
	public Double getSebel1Dbl() {
		Double retval = sebel1Dbl;
		try{
			if(this.sebel1!=null){
				retval = Double.parseDouble(this.sebel1.replace(",", "."));
			}
		}catch(Exception e){
			//Let it go
		}
		return retval;
	}
	
	private String sevku = null;
	public void setSevku(String value) {  this.sevku = value; }
	public String getSevku() {return this.sevku;}
	
	private String sekdh = null;
	public void setSekdh(String value) {  this.sekdh = value; }
	public String getSekdh() {return this.sekdh;}
	
	private String setst = null;
	public void setSetst(String value) {  this.setst = value; }
	public String getSetst() {return this.setst;}
	
	private String sebel2 = null;
	public void setSebel2(String value) {  this.sebel2 = value; }
	public String getSebel2() {return this.sebel2;}
	
	private String seftg2 = null;
	public void setSeftg2(String value) {  this.seftg2 = value; }
	public String getSeftg2() {return this.seftg2;}
	
	private String setrm = null;
	public void setSetrm(String value) {  this.setrm = value; }
	public String getSetrm() {return this.setrm;}
	
	private String sefif = null;
	public void setSefif(String value) {  this.sefif = value; }
	public String getSefif() {return this.sefif;}
	
	private String sefid = null;
	public void setSefid(String value) {  this.sefid = value; }
	public String getSefid() {return this.sefid;}
	
	private String sekta = null;
	public void setSekta(String value) {  this.sekta = value; }
	public String getSekta() {return this.sekta;}
	
	private String sektb = null;
	public void setSektb(String value) {  this.sektb = value; }
	public String getSektb() {return this.sektb;}
	
	private String segn = null;
	public void setSegn(String value) {  this.segn = value; }
	public String getSegn() {return this.segn;}
	
	private String seft1 = null;
	public void setSeft1(String value) {  this.seft1 = value; }
	public String getSeft1() {return this.seft1;}
	
	private String seft2 = null;
	public void setSeft2(String value) {  this.seft2 = value; }
	public String getSeft2() {return this.seft2;}
	
	private String seft3 = null;
	public void setSeft3(String value) {  this.seft3 = value; }
	public String getSeft3() {return this.seft3;}
	
	private String sedst = null;
	public void setSedst(String value) {  this.sedst = value; }
	public String getSedst() {return this.sedst;}
	
	private String sedtg = null;
	public void setSedtg(String value) {  this.sedtg = value; }
	public String getSedtg() {return this.sedtg;}
	
	private String setll = null;
	public void setSetll(String value) {  this.setll = value; }
	public String getSetll() {return this.setll;}
	
	private String setle = null;
	public void setSetle(String value) {  this.setle = value; }
	public String getSetle() {return this.setle;}
	
	private String seski = null;
	public void setSeski(String value) {  this.seski = value; }
	public String getSeski() {return this.seski;}
	
	private String sels = null;
	public void setSels(String value) {  this.sels = value; }
	public String getSels() {return this.sels;}
	
	private String sekdls = null;
	public void setSekdls(String value) {  this.sekdls = value; }
	public String getSekdls() {return this.sekdls;}
	
	private String sedt = null;
	public void setSedt(String value) {  this.sedt = value; }
	public String getSedt() {return this.sedt;}
	
	private String selv2 = null;
	public void setSelv2(String value) {  this.selv2 = value; }
	public String getSelv2() {return this.selv2;}

	private String sekddk = null;
	public void setSekddk(String value) {  this.sekddk = value; }
	public String getSekddk() {return this.sekddk;}

	private String segkd = null;
	public void setSegkd(String value) {  this.segkd = value; }
	public String getSegkd() {return this.segkd;}
	
	private String segft1 = null;
	public void setSegft1(String value) {  this.segft1 = value; }
	public String getSegft1() {return this.segft1;}
	
	private String segft2 = null;
	public void setSegft2(String value) {  this.segft2 = value; }
	public String getSegft2() {return this.segft2;}
	
	private String sepos = null;
	public void setSepos(String value) {  this.sepos = value; }
	public String getSepos() {return this.sepos;}
	
	private String seftb = null;
	public void setSeftb(String value) {  this.seftb = value; }
	public String getSeftb() {return this.seftb;}

	private String selkb = null;
	public void setSelkb(String value) {  this.selkb = value; }
	public String getSelkb() {return this.selkb;}
	
	private String seftm = null;
	public void setSeftm(String value) {  this.seftm = value; }
	public String getSeftm() { return this.seftm;}
	
	private String selkm = null;
	public void setSelkm(String value) {  this.selkm = value; }
	public String getSelkm() { return this.selkm;}
	
	private String sekdft = null;
	public void setSekdft(String value) {  this.sekdft = value; }
	public String getSekdft() { return this.sekdft;}
	
	private String setarf = null;
	public void setSetarf(String value) {  this.setarf = value; }
	public String getSetarf() { return this.setarf;}
	
	private String selkat = null;
	public void setSelkat(String value) {  this.selkat = value; }
	public String getSelkat() { return this.selkat;}
	
	
	private String seval2 = null;
	public void setSeval2(String value) {  this.seval2 = value; }
	public String getSeval2() { return this.seval2;}

	private String sebel3 = null;
	public void setSebel3(String value) {  this.sebel3 = value; }
	public String getSebel3() { return this.sebel3;}
	
	private String sews = null;
	public void setSews(String value) {  this.sews = value; }
	public String getSews() { return this.sews;}
	
	private String semf = null;
	public void setSemf(String value) {  this.semf = value; }
	public String getSemf() { return this.semf;}
	
	private String semp = null;
	public void setSemp(String value) {  this.semp = value; }
	public String getSemp() { return this.semp;}
	
	private String sedto = null;
	public void setSedto(String value) {  this.sedto = value; }
	public String getSedto() { return this.sedto;}

	private String sebeld = null;
	public void setSebeld(String value) {  this.sebeld = value; }
	public String getSebeld() { return this.sebeld;}
	
	private String sepkl = null;
	public void setSepkl(String value) {  this.sepkl = value; }
	public String getSepkl() { return this.sepkl;}
	
	private String sekdv = null;
	public void setSekdv(String value) {  this.sekdv = value; }
	public String getSekdv() { return this.sekdv;}
	
	private String se0035 = null;
	public void setSe0035(String value) {  this.se0035 = value; }
	public String getSe0035() { return this.se0035;}
	
	private String se3039 = null;
	public void setSe3039(String value) {  this.se3039 = value; }
	public String getSe3039() { return this.se3039;}
	
	private String semi = null;
	public void setSemi(String value) {  this.semi = value; }
	public String getSemi() { return this.semi;}
	
	private String sektc = null;
	public void setSektc(String value) {  this.sektc = value; }
	public String getSektc() { return this.sektc;}
	
	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String sefvk = null; //brutto/netto factor
	public void setSefvk(String value) {  this.sefvk = value; }
	public String getSefvk() { return this.sefvk;}
	
	private String sesam = null; //slå samman J/N
	public void setSesam(String value) {  this.sesam = value; }
	public String getSesam() { return this.sesam;}
	
	//OMBEREGNING variables
	private String om_setype = null; 
	public void setOm_setype(String value) {  this.om_setype = value; }
	public String getOm_setype() { return this.om_setype;}
	
	private String om_seft01 = null; 
	public void setOm_seft01(String value) {  this.om_seft01 = value; }
	public String getOm_seft01() { return this.om_seft01;}
	
	private String om_seft02 = null; 
	public void setOm_seft02(String value) {  this.om_seft02 = value; }
	public String getOm_seft02() { return this.om_seft02;}
	
	private String om_seft03 = null; 
	public void setOm_seft03(String value) {  this.om_seft03 = value; }
	public String getOm_seft03() { return this.om_seft03;}
	
	private String om_seft04 = null; 
	public void setOm_seft04(String value) {  this.om_seft04 = value; }
	public String getOm_seft04() { return this.om_seft04;}
	
	private String om_seft05 = null; 
	public void setOm_seft05(String value) {  this.om_seft05 = value; }
	public String getOm_seft05() { return this.om_seft05;}
	
	private String om_seft11 = null; 
	public void setOm_seft11(String value) {  this.om_seft11 = value; }
	public String getOm_seft11() { return this.om_seft11;}
	
	private String om_seft12 = null; 
	public void setOm_seft12(String value) {  this.om_seft12 = value; }
	public String getOm_seft12() { return this.om_seft12;}
	
	private String om_seft13 = null; 
	public void setOm_seft13(String value) {  this.om_seft13 = value; }
	public String getOm_seft13() { return this.om_seft13;}
	
	private String om_seft14 = null; 
	public void setOm_seft14(String value) {  this.om_seft14 = value; }
	public String getOm_seft14() { return this.om_seft14;}
	
	private String om_seft15 = null; 
	public void setOm_seft15(String value) {  this.om_seft15 = value; }
	public String getOm_seft15() { return this.om_seft15;}
	
	private String om_sebltt = null; 
	public void setOm_sebltt(String value) {  this.om_sebltt = value; }
	public String getOm_sebltt() { return this.om_sebltt;}
	
	private String om_sety01 = null; 
	public void setOm_sety01(String value) {  this.om_sety01 = value; }
	public String getOm_sety01() { return this.om_sety01;}
	
	private String om_sety02 = null; 
	public void setOm_sety02(String value) {  this.om_sety02 = value; }
	public String getOm_sety02() { return this.om_sety02;}
	
	private String om_sety03 = null; 
	public void setOm_sety03(String value) {  this.om_sety03 = value; }
	public String getOm_sety03() { return this.om_sety03;}
	
	private String om_sety04 = null; 
	public void setOm_sety04(String value) {  this.om_sety04 = value; }
	public String getOm_sety04() { return this.om_sety04;}
	
	private String om_sety05 = null; 
	public void setOm_sety05(String value) {  this.om_sety05 = value; }
	public String getOm_sety05() { return this.om_sety05;}
	
	private String om_sebl01 = null; 
	public void setOm_sebl01(String value) {  this.om_sebl01 = value; }
	public String getOm_sebl01() { return this.om_sebl01;}
	
	private String om_sebl02 = null; 
	public void setOm_sebl02(String value) {  this.om_sebl02 = value; }
	public String getOm_sebl02() { return this.om_sebl02;}
	
	private String om_sebl03 = null; 
	public void setOm_sebl03(String value) {  this.om_sebl03 = value; }
	public String getOm_sebl03() { return this.om_sebl03;}
	
	private String om_sebl04 = null; 
	public void setOm_sebl04(String value) {  this.om_sebl04 = value; }
	public String getOm_sebl04() { return this.om_sebl04;}
	
	private String om_sebl05 = null; 
	public void setOm_sebl05(String value) {  this.om_sebl05 = value; }
	public String getOm_sebl05() { return this.om_sebl05;}
	
	
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
