package no.systema.tvinn.sad.sadexport.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2016
 * 
 */
@Data
public class KundensVareRegisterUpdateItemRecord {

	private String slknr = null;
	private String slalfa = null;
	private String sltxt = null;
	private String sloppl = null;
	private String slvekt = null;
	private String sltanr = null;
	private String sltar = null;
	private String slpva = null;
	private String slsats = null;
	private String sltn = null;
	
	private String levenr = null;
	private String varenr = null;
	private String w2lk = null;
	private String w2vnti = null;
	private String w2vktb = null;
	private String varebe = null;
	private String svvt = null;
	
	
	
	
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

