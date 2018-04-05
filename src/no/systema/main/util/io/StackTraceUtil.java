/**
 * 
 */
package no.systema.main.util.io;
import java.io.*;
/**
 * Utility class for accessing the java stack
 * 
 * @author oscardelatorre
 * @date Aug 8, 2013
 */


public class StackTraceUtil {
	/**
	 * 
	 * @param e
	 * @return
	 */
	public final String printStackTrace(Exception e){
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		
		return writer.toString();
	}
	
}
