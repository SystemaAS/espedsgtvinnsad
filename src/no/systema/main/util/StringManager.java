/**
 * 
 */
package no.systema.main.util;

import java.util.*;
import org.apache.log4j.Logger;

/**
 * Utility class to manage message notes issues
 * 
 * @author oscardelatorre
 * @date Aug 11, 2016
 * 
 */
public class StringManager {
	private static Logger logger = Logger.getLogger(StringManager.class.getName());
	
	/**
	 * Get lines separated with CR
	 * 
	 * @param message
	 * @return
	 */
	public String paddingString(String value, int limit){
		String SPACE = " ";
		int FTX_LIMIT = limit;
		StringBuffer str = new StringBuffer(value);
		int len = str.length();
		for (int x=len+1;x<=FTX_LIMIT;x++){
			str.append(SPACE);
		}
		return str.toString();
	}
	
	/**
	 * 
	 * @param value
	 * @param fieldLimit
	 * @return
	 */
	public String leadingStringWithNumericFiller(String value, int fieldLimit, String fillerChar){
		String FILLER = fillerChar;
		int FTX_LIMIT = fieldLimit;
		StringBuffer str = new StringBuffer();
		String filler = new String();
		try{
			if(value!=null && !"".equals(value)){
				int len = value.length();
				for (int x=1;x<=FTX_LIMIT-len;x++){
					if(x==1){
						filler = FILLER;
					}else{
						filler = filler + FILLER;
					}
				}
				str.append(filler + value);
			}
			
		}catch(Exception e){
			
		}
		return str.toString();
	}
	/**
	 * 
	 * @param value
	 * @param charToRemove
	 * @return
	 */
	public String removeChar(String value, String charToRemove){
		String retval = value;
		if(value!=null && !"".equals(value)){
			retval = value.replace(charToRemove, "");
		}
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isNotNull(String value){
		boolean retval = false;
		if(value!=null && !"".equals(value)){
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isNull(String value){
		boolean retval = false;
		if(value==null || "".equals(value)){
			retval = true;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String adjustNullStringToIntegerForDbUpdate(String value){
		String retval = value;
		String ZERO = "0";
		
		//Integers
		if(this.isNull(value)){
			retval = ZERO;
		}
		return retval;
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String adjustNullStringToDecimalForDbUpdate(String value){
		String retval = value;
		String ZERO = "0";
		
		if(this.isNotNull(value)){
			String tmp = value.replace(",", ".");
			retval = tmp;
		}else{
			retval = ZERO;
		}
		return retval;
	}
	
}
