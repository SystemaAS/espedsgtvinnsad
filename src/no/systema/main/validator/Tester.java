/**
 * 
 */
package no.systema.main.validator;

/**
 * @author oscardelatorre
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateValidator validator = new DateValidator();
		if(validator.validateTimeHHmm("2000")){
			System.out.println("MATCH");
		}else{
			System.out.println("NO MATCH [ERROR]");
		}
		//validator.validateDateIso203_YYYYMMDD("30001201");
	}
	
}
