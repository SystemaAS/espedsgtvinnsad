/**
 * 
 */
package no.systema.main.util.io;
import java.io.*;

/**
 * @author oscardelatorre
 *
 */
public class FileUtil {

	/**
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public String readInputStreamAsString(InputStream in) throws IOException {

	    BufferedInputStream bis = new BufferedInputStream(in);
	    ByteArrayOutputStream buf = new ByteArrayOutputStream();
	    int result = bis.read();
	    while(result != -1) {
	      byte b = (byte)result;
	      buf.write(b);
	      result = bis.read();
	    }        
	    return buf.toString();
	}

	
}

