/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jan 14, 2014
 * AS400 File: SADH ; Format: SADHR
 *
 */
public class JsonSadImportSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String o2_sist = null;
	public void setO2_sist(String value) {  this.o2_sist = value; }
	public String getO2_sist() { return this.o2_sist;}
	
	private String o2_sidt = null;
	public void setO2_sidt(String value) {  this.o2_sidt = value; }
	public String getO2_sidt() { return this.o2_sidt;}
	
	private String o2_simf = null;
	public void setO2_simf(String value) {  this.o2_simf = value; }
	public String getO2_simf() { return this.o2_simf;}
	
	
	//These variables have been replaced by true variables implemented in AS400 (antk, antvp, sumbv)
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
		this.sumTotalAmountItemLinesStr = String.valueOf(sumTotalAmountItemLines);
		return this.sumTotalAmountItemLinesStr;
	}
	
	private Double sumTotalBruttoViktItemLines = 0.00D;
	public void setSumTotalBruttoViktItemLines(Double value) {  this.sumTotalBruttoViktItemLines = value; }
	public Double getSumTotalBruttoViktItemLines() {return this.sumTotalBruttoViktItemLines;}
	
	private String sumTotalBruttoViktItemLinesStr = null;
	public String getSumTotalBruttoViktItemLinesStr() {
		this.sumTotalBruttoViktItemLinesStr = String.valueOf(sumTotalBruttoViktItemLines);
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
	
	//New Sum-variables (antk, antvp, sumbv) coming now from the back-end
	private String antk = null;
	public void setAntk(String value) {  this.antk = value; }
	public String getAntk() { return this.antk;}
	private Integer antkInt = 0;
	public Integer getAntkInt() {
		if(this.antk!=null){
			try{
				this.antkInt = Integer.parseInt(this.antk);
			}catch(Exception e){
				//nothing
			}
		}
		return this.antkInt;
	}
	
	private String antvp = null;
	public void setAntvp(String value) {  this.antvp = value; }
	public String getAntvp() { return this.antvp;}
	private Integer antvpInt = 0;
	public Integer getAntvpInt() {
		if(this.antvp!=null){
			try{
				this.antvpInt = Integer.parseInt(this.antvp);
			}catch(Exception e){
				//nothing
			}
		}
		return this.antvpInt;
	}
	
	
	private String sumbv = null;
	public void setSumbv(String value) {  this.sumbv = value; }
	public String getSumbv() { return this.sumbv;}
	private Double sumbvDbl = 0.00D;
	public Double getSumbvDbl() {
		if(this.sumbv!=null){
			try{
				this.sumbvDbl = Double.parseDouble(this.sumbv.replace(",","."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.sumbvDbl;
	}
	//Send function extra parameters
	private JsonSadImportSpecificTopicSendParametersRecord sendParametersRecord = null;
	public void setSendParametersRecord(JsonSadImportSpecificTopicSendParametersRecord value) {  this.sendParametersRecord = value; }
	public JsonSadImportSpecificTopicSendParametersRecord getSendParametersRecord() {return this.sendParametersRecord;}

	
	//in order to validate before a "send topic"
	private boolean validUpdate = false;
	public void setValidUpdate(boolean value) {  this.validUpdate = value; }
	public boolean isValidUpdate() {return this.validUpdate;}
	
	private String sist = null;
	public void setSist(String value) {  this.sist = value; }
	public String getSist() {return this.sist;}
	
	private String siavd = null;
	public void setSiavd(String value) {  this.siavd = value; }
	public String getSiavd() {return this.siavd;}
	
	private String sitdn = null;
	public void setSitdn(String value) {  this.sitdn = value; }
	public String getSitdn() {return this.sitdn;}
	
	private String siur = null;
	public void setSiur(String value) {  this.siur = value; }
	public String getSiur() {return this.siur;}
	
	private String sidty = null;
	public void setSidty(String value) {  this.sidty = value; }
	public String getSidty() {return this.sidty;}
	
	private String sidp = null;
	public void setSidp(String value) {  this.sidp = value; }
	public String getSidp() {return this.sidp;}
	
	private String sikns = null;
	public void setSikns(String value) {  this.sikns = value; }
	public String getSikns() {return this.sikns;}

	private String sinas = null;
	public void setSinas(String value) {  this.sinas = value; }
	public String getSinas() {return this.sinas;}
	
	private String siads1 = null;
	public void setSiads1(String value) {  this.siads1 = value; }
	public String getSiads1() {return this.siads1;}
	
	private String siads2 = null;
	public void setSiads2(String value) {  this.siads2 = value; }
	public String getSiads2() {return this.siads2;}
	
	private String siads3 = null;
	public void setSiads3(String value) {  this.siads3 = value; }
	public String getSiads3() {return this.siads3;}
	
	private String sintk = null;
	public void setSintk(String value) {  this.sintk = value; }
	public String getSintk() {return this.sintk;}
	
	private String siski = null;
	public void setSiski(String value) {  this.siski = value; }
	public String getSiski() {return this.siski;}
	
	private String sikddk = null;
	public void setSikddk(String value) {  this.sikddk = value; }
	public String getSikddk() {return this.sikddk;}
	
	private String sivkb = null;
	public void setSivkb(String value) {  this.sivkb = value; }
	public String getSivkb() {return this.sivkb;}
	private Double sivkbDbl = 0.00D;
	public Double getSivkbDbl() {
		if(this.sivkb!=null){
			try{
				this.sivkbDbl = Double.parseDouble(this.sivkb.replace(",","."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.sivkbDbl;
	}
	
	private String sikdc = null;
	public void setSikdc(String value) {  this.sikdc = value; }
	public String getSikdc() {return this.sikdc;}
	
	private String sisg = null;
	public void setSisg(String value) {  this.sisg = value; }
	public String getSisg() {return this.sisg;}
	
	private String siknk = null;
	public void setSiknk(String value) {  this.siknk = value; }
	public String getSiknk() {return this.siknk;}
	
	private String sirg = null;
	public void setSirg(String value) {  this.sirg = value; }
	public String getSirg() {return this.sirg;}
	
	private String simva = null;
	public void setSimva(String value) {  this.simva = value; }
	public String getSimva() { return this.simva;}

	private String sinak = null;
	public void setSinak(String value) {  this.sinak = value; }
	public String getSinak() {return this.sinak;}
	
	private String siadk1 = null;
	public void setSiadk1(String value) {  this.siadk1 = value; }
	public String getSiadk1() {return this.siadk1;}
	
	private String siadk2 = null;
	public void setSiadk2(String value) {  this.siadk2 = value; }
	public String getSiadk2() {return this.siadk2;}

	private String siadk3 = null;
	public void setSiadk3(String value) {  this.siadk3 = value; }
	public String getSiadk3() {return this.siadk3;}

	private String sival1 = null;
	public void setSival1(String value) {  this.sival1 = value; }
	public String getSival1() {return this.sival1;}

	private String sibel1 = null;
	public void setSibel1(String value) {  this.sibel1 = value; }
	public String getSibel1() {return this.sibel1;}
	
	private String sival2 = null;
	public void setSival2(String value) {  this.sival2 = value; }
	public String getSival2() {return this.sival2;}
	
	private String sibel2 = null;
	public void setSibel2(String value) {  this.sibel2 = value; }
	public String getSibel2() {return this.sibel2;}
	
	private String siftg2 = null;
	public void setSiftg2(String value) {  this.siftg2 = value; }
	public String getSiftg2() {return this.siftg2;}
	
	private String silka = null;
	public void setSilka(String value) {  this.silka = value; }
	public String getSilka() {return this.silka;}
	
	private String sitlf = null;
	public void setSitlf(String value) {  this.sitlf = value; }
	public String getSitlf() {return this.sitlf;}
	
	private String sinad = null;
	public void setSinad(String value) {  this.sinad = value; }
	public String getSinad() {return this.sinad;}
	
	private String silv = null;
	public void setSilv(String value) {  this.silv = value; }
	public String getSilv() {return this.silv;}
	
	private String silvt = null;
	public void setSilvt(String value) {  this.silvt = value; }
	public String getSilvt() {return this.silvt;}
	
	private String sitrid = null;
	public void setSitrid(String value) {  this.sitrid = value; }
	public String getSitrid() {return this.sitrid;}
	
	private String silkt = null;
	public void setSilkt(String value) {  this.silkt = value; }
	public String getSilkt() {return this.silkt;}
	
	private String sival3 = null;
	public void setSival3(String value) {  this.sival3 = value; }
	public String getSival3() {return this.sival3;}
	
	private String sibel3 = null;
	public void setSibel3(String value) {  this.sibel3 = value; }
	public String getSibel3() {return this.sibel3;}
	
	private Double sibel3Dbl = 0.00D;
	public Double getSibel3Dbl() {
		Double retval = sibel3Dbl;
		try{
			if(this.sibel3!=null){
				retval = Double.parseDouble(this.sibel3.replace(",", "."));
			}
		}catch(Exception e){
			//Let it go
		}
		return retval;
	}
	
	private String sivku = null;
	public void setSivku(String value) {  this.sivku = value; }
	public String getSivku() {return this.sivku;}
	
	private String sitst = null;
	public void setSitst(String value) {  this.sitst = value; }
	public String getSitst() {return this.sitst;}
	
	private String sitrt = null;
	public void setSitrt(String value) {  this.sitrt = value; }
	public String getSitrt() {return this.sitrt;}
	
	private String sitrm = null;
	public void setSitrm(String value) {  this.sitrm = value; }
	public String getSitrm() {return this.sitrm;}
	
	private String sifif = null;
	public void setSifif(String value) {  this.sifif = value; }
	public String getSifif() {return this.sifif;}
	
	private String sifid = null;
	public void setSifid(String value) {  this.sifid = value; }
	public String getSifid() {return this.sifid;}
	
	private String sibelt = null;
	public void setSibelt(String value) {  this.sibelt = value; }
	public String getSibelt() {return this.sibelt;}
	
	private String si07 = null;
	public void setSi07(String value) {  this.si07 = value; }
	public String getSi07() {return this.si07;}
	
	private String sikta = null;
	public void setSikta(String value) {  this.sikta = value; }
	public String getSikta() {return this.sikta;}

	private String siktb = null;
	public void setSiktb(String value) {  this.siktb = value; }
	public String getSiktb() {return this.siktb;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() {return this.sign;}

	private String sift1 = null;
	public void setSift1(String value) {  this.sift1 = value; }
	public String getSift1() {return this.sift1;}

	private String sift2 = null;
	public void setSift2(String value) {  this.sift2 = value; }
	public String getSift2() {return this.sift2;}

	private String sift3 = null;
	public void setSift3(String value) {  this.sift3 = value; }
	public String getSift3() {return this.sift3;}
	
	private String sift4 = null;
	public void setSift4(String value) {  this.sift4 = value; }
	public String getSift4() {return this.sift4;}
	
	private String sidst = null;
	public void setSidst(String value) {  this.sidst = value; }
	public String getSidst() {return this.sidst;}
	
	private String sibel4 = null;
	public void setSibel4(String value) {  this.sibel4 = value; }
	public String getSibel4() {return this.sibel4;}
	
	private String sidtg = null;
	public void setSidtg(String value) {  this.sidtg = value; }
	public String getSidtg() {return this.sidtg;}

	private String sitll = null;
	public void setSitll(String value) {  this.sitll = value; }
	public String getSitll() {return this.sitll;}

	private String sitle = null;
	public void setSitle(String value) {  this.sitle = value; }
	public String getSitle() {return this.sitle;}

	private String sils = null;
	public void setSils(String value) {  this.sils = value; }
	public String getSils() {return this.sils;}
	
	private String sikdls = null;
	public void setSikdls(String value) {  this.sikdls = value; }
	public String getSikdls() {return this.sikdls;}
	
	private String sikdh = null;
	public void setSikdh(String value) {  this.sikdh = value; }
	public String getSikdh() {return this.sikdh;}
		
	private String sikdtr = null;
	public void setSikdtr(String value) {  this.sikdtr = value; }
	public String getSikdtr() { return this.sikdtr;}
	
	private String sias = null;
	public void setSias(String value) {  this.sias = value; }
	public String getSias() { return this.sias;}
	
	private String sidt = null;
	public void setSidt(String value) {  this.sidt = value; }
	public String getSidt() { return this.sidt;}
	
	private String sibel5 = null;
	public void setSibel5(String value) {  this.sibel5 = value; }
	public String getSibel5() { return this.sibel5;}
	
	private String sibel6 = null;
	public void setSibel6(String value) {  this.sibel6 = value; }
	public String getSibel6() { return this.sibel6;}
	
	private String sibel7 = null;
	public void setSibel7(String value) {  this.sibel7 = value; }
	public String getSibel7() { return this.sibel7;}
	
	private String sibel8 = null;
	public void setSibel8(String value) {  this.sibel8 = value; }
	public String getSibel8() { return this.sibel8;}
	
	private String sibel9 = null;
	public void setSibel9(String value) {  this.sibel9 = value; }
	public String getSibel9() { return this.sibel9;}
	
	private String sibela = null;
	public void setSibela(String value) {  this.sibela = value; }
	public String getSibela() { return this.sibela;}
	
	private String silv2 = null;
	public void setSilv2(String value) {  this.silv2 = value; }
	public String getSilv2() {return this.silv2;}
	
	private String sipos = null;
	public void setSipos(String value) {  this.sipos = value; }
	public String getSipos() {return this.sipos;}
	
	private String sitarf = null;
	public void setSitarf(String value) {  this.sitarf = value; }
	public String getSitarf() { return this.sitarf;}
	
	private String sivalb = null;
	public void setSivalb(String value) {  this.sivalb = value; }
	public String getSivalb() { return this.sivalb;}

	private String sibelb = null;
	public void setSibelb(String value) {  this.sibelb = value; }
	public String getSibelb() { return this.sibelb;}
	
	private String simid = null;
	public void setSimid(String value) {  this.simid = value; }
	public String getSimid() { return this.simid;}
	
	private String simidk = null;
	public void setSimidk(String value) {  this.simidk = value; }
	public String getSimidk() { return this.simidk;}
	
	private String simf = null;
	public void setSimf(String value) {  this.simf = value; }
	public String getSimf() { return this.simf;}
	
	private String simp = null;
	public void setSimp(String value) {  this.simp = value; }
	public String getSimp() { return this.simp;}
	
	private String sidto = null;
	public void setSidto(String value) {  this.sidto = value; }
	public String getSidto() { return this.sidto;}
	
	private String simi = null;
	public void setSimi(String value) {  this.simi = value; }
	public String getSimi() { return this.simi;}
	
	private String sibeld = null;
	public void setSibeld(String value) {  this.sibeld = value; }
	public String getSibeld() { return this.sibeld;}
	
	private String sipkl = null;
	public void setSipkl(String value) {  this.sipkl = value; }
	public String getSipkl() { return this.sipkl;}
	
	private String sikdv = null;
	public void setSikdv(String value) {  this.sikdv = value; }
	public String getSikdv() { return this.sikdv;}
	
	private String si0035 = null;
	public void setSi0035(String value) {  this.si0035 = value; }
	public String getSi0035() { return this.si0035;}
	
	private String si27 = null;
	public void setSi27(String value) {  this.si27 = value; }
	public String getSi27() { return this.si27;}

	private String siopd = null;
	public void setSiopd(String value) {  this.siopd = value; }
	public String getSiopd() { return this.siopd;}

	private String sifrbn = null;
	public void setSifrbn(String value) {  this.sifrbn = value; }
	public String getSifrbn() { return this.sifrbn;}

	private String siws = null;
	public void setSiws(String value) {  this.siws = value; }
	public String getSiws() { return this.siws;}

	private String siktc = null;
	public void setSiktc(String value) {  this.siktc = value; }
	public String getSiktc() { return this.siktc;}

	private String sivalr = null;
	public void setSivalr(String value) {  this.sivalr = value; }
	public String getSivalr() { return this.sivalr;}
	
	private String sibelr = null;
	public void setSibelr(String value) {  this.sibelr = value; }
	public String getSibelr() { return this.sibelr;}
	
	private String sibels = null;
	public void setSibels(String value) {  this.sibels = value; }
	public String getSibels() { return this.sibels;}
	
	private String sirab = null;
	public void setSirab(String value) {  this.sirab = value; }
	public String getSirab() { return this.sirab;}
	
	private String insivf = null;
	public void setInsivf(String value) {  this.insivf = value; }
	public String getInsivf() {return this.insivf;}

	private String insibvnv = null;
	public void setInsibvnv(String value) {  this.insibvnv = value; }
	public String getInsibvnv() {return this.insibvnv;}

	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	//OMBEREGNING variables
	private String om_sitype = null; 
	public void setOm_sitype(String value) {  this.om_sitype = value; }
	public String getOm_sitype() { return this.om_sitype;}
	
	private String om_sift01 = null; 
	public void setOm_sift01(String value) {  this.om_sift01 = value; }
	public String getOm_sift01() { return this.om_sift01;}
	
	private String om_sift02 = null; 
	public void setOm_sift02(String value) {  this.om_sift02 = value; }
	public String getOm_sift02() { return this.om_sift02;}
	
	private String om_sift03 = null; 
	public void setOm_sift03(String value) {  this.om_sift03 = value; }
	public String getOm_sift03() { return this.om_sift03;}
	
	private String om_sift04 = null; 
	public void setOm_sift04(String value) {  this.om_sift04 = value; }
	public String getOm_sift04() { return this.om_sift04;}
	
	private String om_sift05 = null; 
	public void setOm_sift05(String value) {  this.om_sift05 = value; }
	public String getOm_sift05() { return this.om_sift05;}
	
	private String om_sift11 = null; 
	public void setOm_sift11(String value) {  this.om_sift11 = value; }
	public String getOm_sift11() { return this.om_sift11;}
	
	private String om_sift12 = null; 
	public void setOm_sift12(String value) {  this.om_sift12 = value; }
	public String getOm_sift12() { return this.om_sift12;}
	
	private String om_sift13 = null; 
	public void setOm_sift13(String value) {  this.om_sift13 = value; }
	public String getOm_sift13() { return this.om_sift13;}
	
	private String om_sift14 = null; 
	public void setOm_sift14(String value) {  this.om_sift14 = value; }
	public String getOm_sift14() { return this.om_sift14;}
	
	private String om_sift15 = null; 
	public void setOm_sift15(String value) {  this.om_sift15 = value; }
	public String getOm_sift15() { return this.om_sift15;}
	
	private String om_sibltt = null; 
	public void setOm_sibltt(String value) {  this.om_sibltt = value; }
	public String getOm_sibltt() { return this.om_sibltt;}
	
	private String om_sity01 = null; 
	public void setOm_sity01(String value) {  this.om_sity01 = value; }
	public String getOm_sity01() { return this.om_sity01;}
	
	private String om_sity02 = null; 
	public void setOm_sity02(String value) {  this.om_sity02 = value; }
	public String getOm_sity02() { return this.om_sity02;}
	
	private String om_sity03 = null; 
	public void setOm_sity03(String value) {  this.om_sity03 = value; }
	public String getOm_sity03() { return this.om_sity03;}
	
	private String om_sity04 = null; 
	public void setOm_sity04(String value) {  this.om_sity04 = value; }
	public String getOm_sity04() { return this.om_sity04;}
	
	private String om_sity05 = null; 
	public void setOm_sity05(String value) {  this.om_sity05 = value; }
	public String getOm_sity05() { return this.om_sity05;}
	
	private String om_sibl01 = null; 
	public void setOm_sibl01(String value) {  this.om_sibl01 = value; }
	public String getOm_sibl01() { return this.om_sibl01;}
	
	private String om_sibl02 = null; 
	public void setOm_sibl02(String value) {  this.om_sibl02 = value; }
	public String getOm_sibl02() { return this.om_sibl02;}
	
	private String om_sibl03 = null; 
	public void setOm_sibl03(String value) {  this.om_sibl03 = value; }
	public String getOm_sibl03() { return this.om_sibl03;}
	
	private String om_sibl04 = null; 
	public void setOm_sibl04(String value) {  this.om_sibl04 = value; }
	public String getOm_sibl04() { return this.om_sibl04;}
	
	private String om_sibl05 = null; 
	public void setOm_sibl05(String value) {  this.om_sibl05 = value; }
	public String getOm_sibl05() { return this.om_sibl05;}
	
	
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
