package no.systema.main.testsuite;

/**
 * 
 * @author oscardelatorre
 * @date Apr 09, 2018
 * 
 */
public class JsonSpecialCharactersManager {
	
	public String cleanRecord(String value){
		String retval = value;
		if(retval!=null){
			//CHAR: |
			if(retval.contains("|")){
				retval = retval.replaceAll("\\|", " ");
			}
			//CHAR: \
			if(retval.contains("\\")){
				retval = retval.trim().replaceAll("\\\\", " ");
			}
			//CHAR: "
			if(retval.contains("\"")){
				retval = retval.replaceAll("\"", "'");
			}
			//ASCII: 172
			int a172 = 172;
			String aHak172 = new Character((char) a172).toString();
			if(retval.contains(aHak172)){
				retval = retval.replaceAll(aHak172, " ");
			}
			
		}
		
		return retval;
	}
	
	
}
