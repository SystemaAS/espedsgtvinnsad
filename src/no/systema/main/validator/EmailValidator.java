package no.systema.main.validator;

import java.util.*;
import java.util.regex.*;
import org.apache.log4j.Logger;


/**
 * Utility class
 * @author oscardelatorre
 * 
 * 
 */
public class EmailValidator {
     //private final String EMAIL_REGEX_SIMPLE = ".+@([a-z\\-]|[A-Z\\-]|\\d)+\\.[a-z][a-z]+";
	private final String EMAIL_REGEX_SIMPLE =  "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
	private static final Logger logger = Logger.getLogger(EmailValidator.class.getName());
	
    /**
     * validates email
     * @param str
     * 
     */
    public boolean validateEmail(String str){
    	boolean retval = false;
        Pattern pattern = Pattern.compile(this.EMAIL_REGEX_SIMPLE);
        //check email pattern
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()){
            retval = true;
        }
        return retval;
    }
    /**
     * validates emails that are separated by a character
     * @param str
     * @param separatorChar
     * 
     * @retval
     * 
     */
    public boolean validateEmailCompoundAddresses(String str, String separatorChar){
        boolean retval = false;
        
    	logger.info("Inside emailCompoundAddresses, original string before split: " + str);
    	String[]emails = str.split(separatorChar);
    	List <String>list = Arrays.asList(emails);
    	
    	if(list!=null && list.size()>0){
        	outer: for(String record: list){
        		if(record!=null && !"".equals(record)){
		        	Pattern pattern = Pattern.compile(this.EMAIL_REGEX_SIMPLE);
			        Matcher matcher = null;
			        //check email pattern
			        matcher = pattern.matcher(record.trim());
			        if (matcher.find()){
			            retval = true;
			        }else{
			        	retval = false;
			        	break outer;
			        }
        		}
        	}
    	}
        return retval;
    }
}
