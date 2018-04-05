package no.systema.main.validator;

import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;


/**
 * Utility class
 * @author oscardelatorre
 * 
 * 
 */
public class DateValidator {
	private static final Logger logger = Logger.getLogger(DateValidator.class.getName());
	//This pattern checks ONLY the logical part of HH:mm
	//The logical part for a date, including leap years is too complicated to implement here. 
	//Java and Apache mechanisms do not include any nice library to avoid this issue, therefore we do not implement any logical control on date, ONLY in time.
	private final String DATE_ISO_203_YYYYMMDDHHmm = "^\\d{4}\\d{2}\\d{2}(([0-1]?[0-9])|(2[0-3]))[0-5][0-9]$";
	//private final String DATE_ISO_YYYYMMDD = "^\\d{4}\\d{2}\\d{2}$";
	private final String DATE_ISO_YYYYMMDD = "^(19|20|99)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";
	//HHmm only
	private final String TIME_HHmm = "(([0-1]?[0-9])|(2[0-3]))[0-5][0-9]"; //OK HH:mm
	
    /**
     * validates date as in correct mask
     * @param str
     * 
     */
    public boolean validateDateIso203_YYYYMMDDhhmm(String str){
    		boolean retval = false;
    		Pattern pattern = Pattern.compile(this.DATE_ISO_203_YYYYMMDDHHmm);
        //check date pattern
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()){
        		//Now check if it is logically correct
    			retval = true;
            //System.out.println("MATCH on dtm!");
        }else{
        		//System.out.println("ERROR!");
        }
        return retval;
    }
    /**
     * 
     * @param str
     * @return
     */
    public boolean validateDateIso203_YYYYMMDD(String str){
		boolean retval = false;
		Pattern pattern = Pattern.compile(this.DATE_ISO_YYYYMMDD);
		//check date pattern
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
	    		//Now check if it is logically correct
			retval = true;
	        //System.out.println("MATCH on dtm!");
		}else{
    			//System.out.println("ERROR!");
		}
		return retval;
}

    
    /**
     * 
     * @param str
     * @return
     */
    public boolean validateTimeHHmm(String str){
		boolean retval = false;
		if(str!=null && str.length()==2){
			retval = true;
		}else{
			Pattern pattern = Pattern.compile(this.TIME_HHmm);
			//check date pattern
			Matcher matcher = pattern.matcher(str);
			if (matcher.matches()){
	    			//Now check if it is logically correct
				retval = true;
				//System.out.println("MATCH on dtm!");
			}else{
				//System.out.println("ERROR!");
			}
		}
		return retval;
    }
}
