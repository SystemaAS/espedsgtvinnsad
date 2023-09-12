package no.systema.tvinn.sad.digitollv2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedirectCleaner {
	
	public String clean (String origValue) {
		
		String retval = "";
		try {
			String tmp = origValue.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("=", "-");
			System.out.println("tmp:" + tmp);
			String out = tmp.replaceAll("\\[", "(").replaceAll("\\]", ")");
			retval = out;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return retval;
		
	}
}
