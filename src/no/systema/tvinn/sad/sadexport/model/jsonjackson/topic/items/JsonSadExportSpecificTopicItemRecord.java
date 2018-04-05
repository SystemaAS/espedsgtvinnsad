/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * AS400 File: SAEV ; Format: SAEVR
 * 
 *
 */
public class JsonSadExportSpecificTopicItemRecord  extends JsonAbstractGrandFatherRecord   {
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	
	//Aux. attribute to pass some header values into a Validator for Items.
	/*private String header_svih_cont = null;
	public void setHeader_svih_cont(String value) {  this.header_svih_cont = value; }
	public String getHeader_svih_cont() {return this.header_svih_cont;}
	*/
	
	//Aux. attribute to validate tolltariff
	private boolean validTolltariff = true;
	public void setValidTolltariff(boolean value) {  this.validTolltariff = value; }
	public boolean isValidTolltariff() {return this.validTolltariff;}
	
	//Aux. attribute to validate tolltariff
	private boolean mandatoryFiskAvgift = false;
	public void setMandatoryFiskAvgift(boolean value) {  this.mandatoryFiskAvgift = value; }
	public boolean isMandatoryFiskAvgift() {return this.mandatoryFiskAvgift;}
	
	
	//Aux. attribute to validate mangdenhet
	private String extraMangdEnhet = null;
	public void setExtraMangdEnhet(String value) {  this.extraMangdEnhet = value; }
	public String getExtraMangdEnhet() {return this.extraMangdEnhet;}
	
	
	private String svavd = null;
	public void setSvavd(String value) {  this.svavd = value; }
	public String getSvavd() {return this.svavd;}
	
	private String svtdn = null;
	public void setSvtdn(String value) {  this.svtdn = value; }
	public String getSvtdn() {return this.svtdn;}
	
	private String svli = null;
	public void setSvli(String value) {  this.svli = value; }
	public String getSvli() {return this.svli;}
	
	private Integer svliInt = 0;
	public Integer getSvliInt() {
		if(svli!=null){
			svliInt = Integer.valueOf(this.svli);
		}
		return this.svliInt;
	}
	
	private String svbelt = null;
	public void setSvbelt(String value) {  this.svbelt = value; }
	public String getSvbelt() {return this.svbelt;}
	
	private String svfyl = null;
	public void setSvfyl(String value) {  this.svfyl = value; }
	public String getSvfyl() {return this.svfyl;}
	
	private String svvnt = null;
	public void setSvvnt(String value) {  this.svvnt = value; }
	public String getSvvnt() {return this.svvnt;}
	
	private Boolean svvntValid = true;
	public void setSvvntValid(Boolean value) {  this.svvntValid = value; }
	public Boolean isSvvntValid() {return this.svvntValid;}
	
	private String svavt = null;
	public void setSvavt(String value) {  this.svavt = value; }
	public String getSvavt() {return this.svavt;}
	
	private String svavts = null;
	public void setSvavts(String value) {  this.svavts = value; }
	public String getSvavts() {return this.svavts;}
	
	private String svavtp = null;
	public void setSvavtp(String value) {  this.svavtp = value; }
	public String getSvavtp() {return this.svavtp;}
	
	private String svvktb = null;
	public void setSvvktb(String value) {  this.svvktb = value; }
	public String getSvvktb() {return this.svvktb;}
	
	private String svvktn = null;
	public void setSvvktn(String value) {  this.svvktn = value; }
	public String getSvvktn() {return this.svvktn;}
	
	private String svntm = null;
	public void setSvntm(String value) {  this.svntm = value; }
	public String getSvntm() { return this.svntm;}
	
	private String svcref = null;
	public void setSvcref(String value) {  this.svcref = value; }
	public String getSvcref() { return this.svcref;}
	
	private String svtoa = null;
	public void setSvtoa(String value) {  this.svtoa = value; }
	public String getSvtoa() { return this.svtoa;}
	
	private String svtob = null;
	public void setSvtob(String value) {  this.svtob = value; }
	public String getSvtob() { return this.svtob;}
	
	private String svft = null;
	public void setSvft(String value) {  this.svft = value; }
	public String getSvft() { return this.svft;}
	
	private String svnt = null;
	public void setSvnt(String value) {  this.svnt = value; }
	public String getSvnt() { return this.svnt;}
	
	private String sveh = null;
	public void setSveh(String value) {  this.sveh = value; }
	public String getSveh() { return this.sveh;}
	
	private String svvt = null;
	public void setSvvt(String value) {  this.svvt = value; }
	public String getSvvt() { return this.svvt;}
	
	
	private String svln = null;
	public void setSvln(String value) {  this.svln = value; }
	public String getSvln() { return this.svln; }
	
	
	private String svrefl = null;
	public void setSvrefl(String value) {  this.svrefl = value; }
	public String getSvrefl() { return this.svrefl; }
	
	private String svexr01 = null;
	public void setSvexr01(String value) {  this.svexr01 = value; }
	public String getSvexr01() { return this.svexr01; }
	
	private String svexr02 = null;
	public void setSvexr02(String value) {  this.svexr02 = value; }
	public String getSvexr02() { return this.svexr02; }
	
	private String svexr03 = null;
	public void setSvexr03(String value) {  this.svexr03 = value; }
	public String getSvexr03() { return this.svexr03; }
	
	private String svexr04 = null;
	public void setSvexr04(String value) {  this.svexr04 = value; }
	public String getSvexr04() { return this.svexr04; }
	
	private String svexr05 = null;
	public void setSvexr05(String value) {  this.svexr05 = value; }
	public String getSvexr05() { return this.svexr05; }
	
	private String svexr06 = null;
	public void setSvexr06(String value) {  this.svexr06 = value; }
	public String getSvexr06() { return this.svexr06; }
	
	private String svexr07 = null;
	public void setSvexr07(String value) {  this.svexr07 = value; }
	public String getSvexr07() { return this.svexr07; }
	
	private String svlk = null;
	public void setSvlk(String value) {  this.svlk = value; }
	public String getSvlk() { return this.svlk; }
	
	private String svnyl = null;
	public void setSvnyl(String value) {  this.svnyl = value; }
	public String getSvnyl() { return this.svnyl; }
	
	
	//These w(x)(n) group is not a database field but an agreed work variable array from/to the 
	//service program...
	//wa1-wa7 array of SVFT (database field)
	private String wa1 = null;
	public void setWa1(String value) {  this.wa1 = value; }
	public String getWa1() { return this.wa1; }
	private String wa2 = null;
	public void setWa2(String value) {  this.wa2 = value; }
	public String getWa2() { return this.wa2; }
	private String wa3 = null;
	public void setWa3(String value) {  this.wa3 = value; }
	public String getWa3() { return this.wa3; }
	private String wa4 = null;
	public void setWa4(String value) {  this.wa4 = value; }
	public String getWa4() { return this.wa4; }
	private String wa5 = null;
	public void setWa5(String value) {  this.wa5 = value; }
	public String getWa5() { return this.wa5; }
	private String wa6 = null;
	public void setWa6(String value) {  this.wa6 = value; }
	public String getWa6() { return this.wa6; }
	private String wa7 = null;
	public void setWa7(String value) {  this.wa7 = value; }
	public String getWa7() { return this.wa7; }
	//wb1-wb7 array of SVNT (database field)
	private String wb1 = null;
	public void setWb1(String value) {  this.wb1 = value; }
	public String getWb1() { return this.wb1; }
	private String wb2 = null;
	public void setWb2(String value) {  this.wb2 = value; }
	public String getWb2() { return this.wb2; }
	private String wb3 = null;
	public void setWb3(String value) {  this.wb3 = value; }
	public String getWb3() { return this.wb3; }
	private String wb4 = null;
	public void setWb4(String value) {  this.wb4 = value; }
	public String getWb4() { return this.wb4; }
	private String wb5 = null;
	public void setWb5(String value) {  this.wb5 = value; }
	public String getWb5() { return this.wb5; }
	private String wb6 = null;
	public void setWb6(String value) {  this.wb6 = value; }
	public String getWb6() { return this.wb6; }
	private String wb7 = null;
	public void setWb7(String value) {  this.wb7 = value; }
	public String getWb7() { return this.wb7; }
	
	//wc1-wc7 array of SVEH (database field)
	private String wc1 = null;
	public void setWc1(String value) {  this.wc1 = value; }
	public String getWc1() { return this.wc1; }
	private String wc2 = null;
	public void setWc2(String value) {  this.wc2 = value; }
	public String getWc2() { return this.wc2; }
	private String wc3 = null;
	public void setWc3(String value) {  this.wc3 = value; }
	public String getWc3() { return this.wc3; }
	private String wc4 = null;
	public void setWc4(String value) {  this.wc4 = value; }
	public String getWc4() { return this.wc4; }
	private String wc5 = null;
	public void setWc5(String value) {  this.wc5 = value; }
	public String getWc5() { return this.wc5; }
	private String wc6 = null;
	public void setWc6(String value) {  this.wc6 = value; }
	public String getWc6() { return this.wc6; }
	private String wc7 = null;
	public void setWc7(String value) {  this.wc7 = value; }
	public String getWc7() { return this.wc7; }
	
	//wd1-wd5 array of SVVT (database field)
	private String wd1 = null;
	public void setWd1(String value) {  this.wd1 = value; }
	public String getWd1() { return this.wd1; }
	private String wd2 = null;
	public void setWd2(String value) {  this.wd2 = value; }
	public String getWd2() { return this.wd2; }
	private String wd3 = null;
	public void setWd3(String value) {  this.wd3 = value; }
	public String getWd3() { return this.wd3; }
	private String wd4 = null;
	public void setWd4(String value) {  this.wd4 = value; }
	public String getWd4() { return this.wd4; }
	private String wd5 = null;
	public void setWd5(String value) {  this.wd5 = value; }
	public String getWd5() { return this.wd5; }
	
	//we1-we10 array of SVCREF (database field)
	private String we1 = null;
	public void setWe1(String value) {  this.we1 = value; }
	public String getWe1() { return this.we1; }
	private String we2 = null;
	public void setWe2(String value) {  this.we2 = value; }
	public String getWe2() { return this.we2; }
	private String we3 = null;
	public void setWe3(String value) {  this.we3 = value; }
	public String getWe3() { return this.we3; }
	private String we4 = null;
	public void setWe4(String value) {  this.we4 = value; }
	public String getWe4() { return this.we4; }
	private String we5 = null;
	public void setWe5(String value) {  this.we5 = value; }
	public String getWe5() { return this.we5; }
	private String we6 = null;
	public void setWe6(String value) {  this.we6 = value; }
	public String getWe6() { return this.we6; }
	private String we7 = null;
	public void setWe7(String value) {  this.we7 = value; }
	public String getWe7() { return this.we7; }
	private String we8 = null;
	public void setWe8(String value) {  this.we8 = value; }
	public String getWe8() { return this.we8; }
	private String we9 = null;
	public void setWe9(String value) {  this.we9 = value; }
	public String getWe9() { return this.we9; }
	private String we10 = null;
	public void setWe10(String value) {  this.we10 = value; }
	public String getWe10() { return this.we10; }
	
	//wf1-wf10 array of SVTOA (database field)
	private String wf1 = null;
	public void setWf1(String value) {  this.wf1 = value; }
	public String getWf1() { return this.wf1; }
	private String wf2 = null;
	public void setWf2(String value) {  this.wf2 = value; }
	public String getWf2() { return this.wf2; }
	private String wf3 = null;
	public void setWf3(String value) {  this.wf3 = value; }
	public String getWf3() { return this.wf3; }
	private String wf4 = null;
	public void setWf4(String value) {  this.wf4 = value; }
	public String getWf4() { return this.wf4; }
	private String wf5 = null;
	public void setWf5(String value) {  this.wf5 = value; }
	public String getWf5() { return this.wf5; }
	private String wf6 = null;
	public void setWf6(String value) {  this.wf6 = value; }
	public String getWf6() { return this.wf6; }
	private String wf7 = null;
	public void setWf7(String value) {  this.wf7 = value; }
	public String getWf7() { return this.wf7; }
	private String wf8 = null;
	public void setWf8(String value) {  this.wf8 = value; }
	public String getWf8() { return this.wf8; }
	private String wf9 = null;
	public void setWf9(String value) {  this.wf9 = value; }
	public String getWf9() { return this.wf9; }
	private String wf10 = null;
	public void setWf10(String value) {  this.wf10 = value; }
	public String getWf10() { return this.wf10; }
	
	private String sverr = null;
	public void setSverr(String value) {  this.sverr = value; }
	public String getSverr() { return this.sverr;}
	
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
