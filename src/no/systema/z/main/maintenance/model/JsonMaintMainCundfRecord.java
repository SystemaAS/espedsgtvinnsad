package no.systema.z.main.maintenance.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonMaintMainCundfRecord extends JsonAbstractGrandFatherRecord {

	private String kundnr = null;                                
	public void setKundnr (String value){ this.kundnr = value;   }   
	public String getKundnr (){ return this.kundnr;   }              

	private String firma = null;                                
	public void setFirma (String value){ this.firma = value;   }   
	public String getFirma (){ return this.firma;   }              

	private String knavn = null;                                
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = null;                                
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String adr2 = null;                                
	public void setAdr2 (String value){ this.adr2 = value;   }   
	public String getAdr2 (){ return this.adr2;   }              

	private String adr3 = null;                                
	public void setAdr3 (String value){ this.adr3 = value;   }   
	public String getAdr3 (){ return this.adr3;   }              

	private String postnr = null;                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ return this.postnr;   }              

	private String syrg = null;                                
	public void setSyrg (String value){ this.syrg = value;   }   
	public String getSyrg (){ return this.syrg;   }              

	private String syland = null;                                
	public void setSyland (String value){ this.syland = value;   }   
	public String getSyland (){ return this.syland;   }              

	private String sykont = null;                                
	public void setSykont (String value){ this.sykont = value;   }   
	public String getSykont (){ return this.sykont;   }              

	private String syfr02 = null;                                
	public void setSyfr02 (String value){ this.syfr02 = value;   }   
	public String getSyfr02 (){ return this.syfr02;   }              

	private String sonavn = null;                                
	public void setSonavn (String value){ this.sonavn = value;   }   
	public String getSonavn (){ return this.sonavn;   }              

	private String sypoge = null;                                
	public void setSypoge (String value){ this.sypoge = value;   }   
	public String getSypoge (){ return this.sypoge;   }              

	private String spraak = null;                                
	public void setSpraak (String value){ this.spraak = value;   }   
	public String getSpraak (){ return this.spraak;   }              

	private String eori = null;                                
	public void setEori (String value){ this.eori = value;   }   
	public String getEori (){ return this.eori;   }    
	
	private String pnpbku = null;                                
	public void setPnpbku (String value){ this.pnpbku = value;   }   
	public String getPnpbku(){ return this.pnpbku;   }    

	private String kpers = null;                                
	public void setKpers (String value){ this.kpers = value;   }   
	public String getKpers(){ return this.kpers;   }    	
	
	private String tlf = null;                                
	public void setTlf (String value){ this.tlf = value;   }   
	public String getTlf(){ return this.tlf;   }    	

	private String syepos = null;                                
	public void setSyepos (String value){ this.syepos = value;   }   
	public String getSyepos(){ return this.syepos;   }    	

	private String systat = null;                                
	public void setSystat (String value){ this.systat = value;   }   
	public String getSystat(){ return this.systat;   }    	

	private String valkod = null;                                
	public void setValkod (String value){ this.valkod = value;   }   
	public String getValkod(){ return this.valkod;   }    	

	private String kundgr = null;                                
	public void setKundgr (String value){ this.kundgr = value;   }   
	public String getKundgr(){ return this.kundgr;   }    	

	private String adr21 = null;                                
	public void setAdr21 (String value){ this.adr21 = value;   }   
	public String getAdr21(){ return this.adr21;   }    	
	
	private String fmot = null;                                
	public void setFmot (String value){ this.fmot = value;   }   
	public String getFmot(){ return this.fmot;   }    
	
	private String fmotname = null;                                
	public void setFmotname (String value){ this.fmotname = value;   }   
	public String getFmotname(){ return this.fmotname;   }   	
	

	private String bankg = null;                                
	public void setBankg (String value){ this.bankg = value;   }   
	public String getBankg(){ return this.bankg;   }    	

	private String postg = null;                                
	public void setPostg (String value){ this.postg = value;   }   
	public String getPostg(){ return this.postg;   }    	
	
	private String betbet = null;                                
	public void setBetbet (String value){ this.betbet = value;   }   
	public String getBetbet(){ return this.betbet;   }    	
	
	private String betmat = null;                                
	public void setBetmat (String value){ this.betmat = value;   }   
	public String getBetmat(){ return this.betmat;   }    	
	
	private String sfakt = null;                                
	public void setSfakt (String value){ this.sfakt = value;   }   
	public String getSfakt(){ return this.sfakt;   }    
	
	private String kgrens = null;                                
	public void setKgrens (String value){ this.kgrens = value;   }   
	public String getKgrens(){ return this.kgrens;   }    
	
	private String sysalu = null;                                
	public void setSysalu (String value){ this.sysalu = value;   }   
	public String getSysalu(){ return this.sysalu;   }   
	
	private String syfr03 = null;                                
	public void setSyfr03 (String value){ this.syfr03 = value;   }   
	public String getSyfr03(){ return this.syfr03;   }   
	
	private String xxinm3 = null;                                
	public void setXxinm3 (String value){ this.xxinm3 = value;   }   
	public String getXxinm3(){ return this.xxinm3;   }   
	
	private String xxinlm = null;                                
	public void setXxinlm (String value){ this.xxinlm = value;   }   
	public String getXxinlm(){ return this.xxinlm;   }   
	
	private String rnraku = null;                                
	public void setRnraku (String value){ this.rnraku = value;   }   
	public String getRnraku(){ return this.rnraku;   }   
	
	private String symvjn = null;                                
	public void setSymvjn (String value){ this.symvjn = value;   }   
	public String getSymvjn(){ return this.symvjn;   }   
	
	private String symvsp = null;                                
	public void setSymvsp (String value){ this.symvsp = value;   }   
	public String getSymvsp(){ return this.symvsp;   }   	
	
	private String syutlp = null;                                
	public void setSyutlp (String value){ this.syutlp = value;   }   
	public String getSyutlp(){ return this.syutlp;   }   	

	private String syminu = null;                                
	public void setSyminu (String value){ this.syminu = value;   }   
	public String getSyminu(){ return this.syminu;   }   	
	
	private String syopdt = null;                                
	public void setSyopdt (String value){ this.syopdt = value;   }   
	public String getSyopdt(){ return this.syopdt;   }   	
	
	private String sylikv = null;                                
	public void setSylikv (String value){ this.sylikv = value;   }   
	public String getSylikv(){ return this.sylikv;   }   	
	
	private String golk = null;                                
	public void setGolk (String value){ this.golk = value;   }   
	public String getGolk(){ return this.golk;   }   		
	
	private String aktkod = null;                                
	public void setAktkod (String value){ this.aktkod = value;   }   
	public String getAktkod(){ return this.aktkod;   }   

	private String dkund = null;                                
	public void setDkund(String value){ this.dkund = value;   }   
	public String getDkund(){ return this.dkund;   }   

	private String vatkku = null;                                
	public void setVatkku(String value){ this.vatkku = value;   }   
	public String getVatkku(){ return this.vatkku;   }   

	private String syselg = null;                                
	public void setSyselg(String value){ this.syselg = value;   }   
	public String getSyselg(){ return this.syselg;   }   	
	
	private String aknrku = null;                                
	public void setAknrku(String value){ this.aknrku = value;   }   
	public String getAknrku(){ return this.aknrku;   }   	
	
	private String syfr04 = null;                                
	public void setSyfr04(String value){ this.syfr04 = value;   }   
	public String getSyfr04(){ return this.syfr04;   }   	
	
	private String syregn = null;                                
	public void setSyregn(String value){ this.syregn = value;   }   
	public String getSyregn(){ return this.syregn;   }   		

	private String syfr05 = null;                                
	public void setSyfr05(String value){ this.syfr05 = value;   }   
	public String getSyfr05(){ return this.syfr05;   }   	

	private String syfr06 = null;                                
	public void setSyfr06(String value){ this.syfr06 = value;   }   
	public String getSyfr06(){ return this.syfr06;   }   	
	
	private String xxbre = null;                                
	public void setXxbre(String value){ this.xxbre = value;   }   
	public String getXxbre(){ return this.xxbre;   }   		
	
	private String syiat1 = null;                                
	public void setSyiat1(String value){ this.syiat1 = value;   }   
	public String getSyiat1(){ return this.syiat1;   }  
	
	private String xxlen = null;                                
	public void setXxlen(String value){ this.xxlen = value;   }   
	public String getXxlen(){ return this.xxlen;   }  
	
	private String syiat2 = null;                                
	public void setSyiat2(String value){ this.syiat2 = value;   }   
	public String getSyiat2(){ return this.syiat2;   }  

	private String mllm = null;                                
	public void setMllm(String value){ this.mllm = value;   }   
	public String getMllm(){ return this.mllm;   }  
	
	private String m3m3 = null;                                
	public void setM3m3(String value){ this.m3m3 = value;   }   
	public String getM3m3(){ return this.m3m3;   }  	
	
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
