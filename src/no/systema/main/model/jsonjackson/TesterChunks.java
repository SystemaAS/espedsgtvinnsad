/**
 * 
 */
package no.systema.main.model.jsonjackson;
import java.util.*;
/**
 * @author oscardelatorre
 *
 */
public class TesterChunks {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TesterChunks tester = new TesterChunks();
		tester.run();
	}
	
	private void run(){
		JsonSystemaUserRecord user = new JsonSystemaUserRecord();
		user.setProg("#' OnClick='window.open('http://gw.systema.no/sycgip/syfra.pgm?user=OSCAR&UsrSpcName=GX549SJMFO','FraktKalkWind','width=750,height=500,left=5,top=20, scrollbars resizable')");
		
		System.out.println(user.getProgChunks());
		System.out.println(user.getProgChunksUrl());
	}
}
