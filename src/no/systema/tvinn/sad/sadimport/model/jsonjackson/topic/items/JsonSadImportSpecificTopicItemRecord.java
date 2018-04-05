/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * AS400 File: SADV ; Format: SADVR
 * 
 *
 */
public class JsonSadImportSpecificTopicItemRecord  extends JsonAbstractGrandFatherRecord   {
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	//Aux. attribute to validate tolltariff
	private boolean validTolltariff = true;
	public void setValidTolltariff(boolean value) {  this.validTolltariff = value; }
	public boolean isValidTolltariff() {return this.validTolltariff;}
	
	private boolean multipleChoiceAvgifter = false;
	public void setMultipleChoiceAvgifter(boolean value) {  this.multipleChoiceAvgifter = value; }
	public boolean isMultipleChoiceAvgifter() {return this.multipleChoiceAvgifter;}
	
	private boolean singleChoiceAvgifter = false;
	public void setSingleChoiceAvgifter(boolean value) {  this.singleChoiceAvgifter = value; }
	public boolean isSingleChoiceAvgifter() {return this.singleChoiceAvgifter;}
	
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
	
	
	
	private String svlk = null;
	public void setSvlk(String value) {  this.svlk = value; }
	public String getSvlk() {return this.svlk;}
	
	private String svvnt = null;
	public void setSvvnt(String value) {  this.svvnt = value; }
	public String getSvvnt() {return this.svvnt;}
	
	private Boolean svvntValid = true;
	public void setSvvntValid(Boolean value) {  this.svvntValid = value; }
	public Boolean isSvvntValid() {return this.svvntValid;}
	
	private String svtn = null;
	public void setSvtn(String value) {  this.svtn = value; }
	public String getSvtn() {return this.svtn;}
	
	private String svpre = null;
	public void setSvpre(String value) {  this.svpre = value; }
	public String getSvpre() {return this.svpre;}
	
	private String svpreae = null;
	public void setSvpreae(String value) {  this.svpreae = value; }
	public String getSvpreae() {return this.svpreae;}
	
	private String svas = null;
	public void setSvas(String value) {  this.svas = value; }
	public String getSvas() {return this.svas;}
	
	private String svpva = null;
	public void setSvpva(String value) {  this.svpva = value; }
	public String getSvpva() {return this.svpva;}
	
	private String svmfr = null;
	public void setSvmfr(String value) {  this.svmfr = value; }
	public String getSvmfr() {return this.svmfr;}
	
	private String svvf = null;
	public void setSvvf(String value) {  this.svvf = value; }
	public String getSvvf() {return this.svvf;}
	
	private String svvktb = null;
	public void setSvvktb(String value) {  this.svvktb = value; }
	public String getSvvktb() {return this.svvktb;}
	
	private String svntm = null;
	public void setSvntm(String value) {  this.svntm = value; }
	public String getSvntm() {return this.svntm;}
	
	
	private String svbelt = null;
	public void setSvbelt(String value) {  this.svbelt = value; }
	public String getSvbelt() {return this.svbelt;}
	
	private String svbels = null;
	public void setSvbels(String value) {  this.svbels = value; }
	public String getSvbels() {return this.svbels;}
	
	private String svvktn = null;
	public void setSvvktn(String value) {  this.svvktn = value; }
	public String getSvvktn() {return this.svvktn;}
	
	private String svft = null;
	public void setSvft(String value) {  this.svft = value; }
	public String getSvft() {return this.svft;}
	
	private String svnt = null;
	public void setSvnt(String value) {  this.svnt = value; }
	public String getSvnt() {return this.svnt;}
	
	private String sveh = null;
	public void setSveh(String value) {  this.sveh = value; }
	public String getSveh() {return this.sveh;}
	
	private String svvt = null;
	public void setSvvt(String value) {  this.svvt = value; }
	public String getSvvt() {return this.svvt;}
	
	private String svcref = null;
	public void setSvcref(String value) {  this.svcref = value; }
	public String getSvcref() {return this.svcref;}
	
	private String svtoa = null;
	public void setSvtoa(String value) {  this.svtoa = value; }
	public String getSvtoa() {return this.svtoa;}
	
	private String svtob = null;
	public void setSvtob(String value) {  this.svtob = value; }
	public String getSvtob() {return this.svtob;}
	
	private String svkdaae = null;
	public void setSvkdaae(String value) {  this.svkdaae = value; }
	public String getSvkdaae() {return this.svkdaae;}
	
	private String svkdsae = null;
	public void setSvkdsae(String value) {  this.svkdsae = value; }
	public String getSvkdsae() {return this.svkdsae;}
	
	private String svblae = null;
	public void setSvblae(String value) {  this.svblae = value; }
	public String getSvblae() {return this.svblae;}
	
	private String svblgae = null;
	public void setSvblgae(String value) {  this.svblgae = value; }
	public String getSvblgae() {return this.svblgae;}
	
	
	private String svblsae = null;
	public void setSvblsae(String value) {  this.svblsae = value; }
	public String getSvblsae() { return this.svblsae;}
	
	private String sveh2ae = null;
	public void setSveh2ae(String value) {  this.sveh2ae = value; }
	public String getSveh2ae() { return this.sveh2ae;}
	
	private String svln = null;
	public void setSvln(String value) {  this.svln = value; }
	public String getSvln() { return this.svln;}
	
	private Integer svlnInt = 0;
	public Integer getSvlnInt() {
		if(svln!=null){
			svlnInt = Integer.valueOf(this.svln);
		}
		return this.svlnInt;
	}
	
	private String svrefl = null;
	public void setSvrefl(String value) {  this.svrefl = value; }
	public String getSvrefl() { return this.svrefl;}
	
	private String svexr01 = null;
	public void setSvexr01(String value) {  this.svexr01 = value; }
	public String getSvexr01() { return this.svexr01;}
	
	private String svexr02 = null;
	public void setSvexr02(String value) {  this.svexr02 = value; }
	public String getSvexr02() { return this.svexr02;}
	
	private String svexr03 = null;
	public void setSvexr03(String value) {  this.svexr03 = value; }
	public String getSvexr03() { return this.svexr03;}
	
	private String svexr04 = null;
	public void setSvexr04(String value) {  this.svexr04 = value; }
	public String getSvexr04() { return this.svexr04;}
	
	private String svexr05 = null;
	public void setSvexr05(String value) {  this.svexr05 = value; }
	public String getSvexr05() { return this.svexr05;}
	
	private String svexr06 = null;
	public void setSvexr06(String value) {  this.svexr06 = value; }
	public String getSvexr06() { return this.svexr06;}
	
	private String svexr07 = null;
	public void setSvexr07(String value) {  this.svexr07 = value; }
	public String getSvexr07() { return this.svexr07;}
	
	private String sverr = null;
	public void setSverr(String value) {  this.sverr = value; }
	public String getSverr() { return this.sverr;}
	
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
	
	//wg1-wg8 array of SVKDAAE (database field)
	private String wg1 = null;
	public void setWg1(String value) {  this.wg1 = value; }
	public String getWg1() { return this.wg1; }
	private String wg2 = null;
	public void setWg2(String value) {  this.wg2 = value; }
	public String getWg2() { return this.wg2; }
	private String wg3 = null;
	public void setWg3(String value) {  this.wg3 = value; }
	public String getWg3() { return this.wg3; }
	private String wg4 = null;
	public void setWg4(String value) {  this.wg4 = value; }
	public String getWg4() { return this.wg4; }
	private String wg5 = null;
	public void setWg5(String value) {  this.wg5 = value; }
	public String getWg5() { return this.wg5; }
	private String wg6 = null;
	public void setWg6(String value) {  this.wg6 = value; }
	public String getWg6() { return this.wg6; }
	private String wg7 = null;
	public void setWg7(String value) {  this.wg7 = value; }
	public String getWg7() { return this.wg7; }
	private String wg8 = null;
	public void setWg8(String value) {  this.wg8 = value; }
	public String getWg8() { return this.wg8; }
	
	//wh1-wh8 array of SVKDSAE (database field)
	private String wh1 = null;
	public void setWh1(String value) {  this.wh1 = value; }
	public String getWh1() { return this.wh1; }
	private String wh2 = null;
	public void setWh2(String value) {  this.wh2 = value; }
	public String getWh2() { return this.wh2; }
	private String wh3 = null;
	public void setWh3(String value) {  this.wh3 = value; }
	public String getWh3() { return this.wh3; }
	private String wh4 = null;
	public void setWh4(String value) {  this.wh4 = value; }
	public String getWh4() { return this.wh4; }
	private String wh5 = null;
	public void setWh5(String value) {  this.wh5 = value; }
	public String getWh5() { return this.wh5; }
	private String wh6 = null;
	public void setWh6(String value) {  this.wh6 = value; }
	public String getWh6() { return this.wh6; }
	private String wh7 = null;
	public void setWh7(String value) {  this.wh7 = value; }
	public String getWh7() { return this.wh7; }
	private String wh8 = null;
	public void setWh8(String value) {  this.wh8 = value; }
	public String getWh8() { return this.wh8; }
		
	//wi1-wi8 array of SVBLAE (database field)
	private String wi1 = null;
	public void setWi1(String value) {  this.wi1 = value; }
	public String getWi1() { return this.wi1; }
	private String wi2 = null;
	public void setWi2(String value) {  this.wi2 = value; }
	public String getWi2() { return this.wi2; }
	private String wi3 = null;
	public void setWi3(String value) {  this.wi3 = value; }
	public String getWi3() { return this.wi3; }
	private String wi4 = null;
	public void setWi4(String value) {  this.wi4 = value; }
	public String getWi4() { return this.wi4; }
	private String wi5 = null;
	public void setWi5(String value) {  this.wi5 = value; }
	public String getWi5() { return this.wi5; }
	private String wi6 = null;
	public void setWi6(String value) {  this.wi6 = value; }
	public String getWi6() { return this.wi6; }
	private String wi7 = null;
	public void setWi7(String value) {  this.wi7 = value; }
	public String getWi7() { return this.wi7; }
	private String wi8 = null;
	public void setWi8(String value) {  this.wi8 = value; }
	public String getWi8() { return this.wi8; }
	
	//wj1-wj8 array of SVBLGAE (database field)
	private String wj1 = null;
	public void setWj1(String value) {  this.wj1 = value; }
	public String getWj1() { return this.wj1; }
	private String wj2 = null;
	public void setWj2(String value) {  this.wj2 = value; }
	public String getWj2() { return this.wj2; }
	private String wj3 = null;
	public void setWj3(String value) {  this.wj3 = value; }
	public String getWj3() { return this.wj3; }
	private String wj4 = null;
	public void setWj4(String value) {  this.wj4 = value; }
	public String getWj4() { return this.wj4; }
	private String wj5 = null;
	public void setWj5(String value) {  this.wj5 = value; }
	public String getWj5() { return this.wj5; }
	private String wj6 = null;
	public void setWj6(String value) {  this.wj6 = value; }
	public String getWj6() { return this.wj6; }
	private String wj7 = null;
	public void setWj7(String value) {  this.wj7 = value; }
	public String getWj7() { return this.wj7; }
	private String wj8 = null;
	public void setWj8(String value) {  this.wj8 = value; }
	public String getWj8() { return this.wj8; }
	
	//wk1-wk8 array of SVBLSAE (database field)
	private String wk1 = null;
	public void setWk1(String value) {  this.wk1 = value; }
	public String getWk1() { return this.wk1; }
	private String wk2 = null;
	public void setWk2(String value) {  this.wk2 = value; }
	public String getWk2() { return this.wk2; }
	private String wk3 = null;
	public void setWk3(String value) {  this.wk3 = value; }
	public String getWk3() { return this.wk3; }
	private String wk4 = null;
	public void setWk4(String value) {  this.wk4 = value; }
	public String getWk4() { return this.wk4; }
	private String wk5 = null;
	public void setWk5(String value) {  this.wk5 = value; }
	public String getWk5() { return this.wk5; }
	private String wk6 = null;
	public void setWk6(String value) {  this.wk6 = value; }
	public String getWk6() { return this.wk6; }
	private String wk7 = null;
	public void setWk7(String value) {  this.wk7 = value; }
	public String getWk7() { return this.wk7; }
	private String wk8 = null;
	public void setWk8(String value) {  this.wk8 = value; }
	public String getWk8() { return this.wk8; }
		
	
	
	
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
