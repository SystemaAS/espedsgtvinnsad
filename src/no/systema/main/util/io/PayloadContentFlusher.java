/**
 * 
 */
package no.systema.main.util.io;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Aug 5, 2013
 * 
 */
public class PayloadContentFlusher {
	
	private static final Logger logger = Logger.getLogger(PayloadContentFlusher.class.getName());
	private static final int BYTE_BLOCK_SIZE = 1024;
	
	
	/**
	 * Gets the file payload from a unique Url location
	 * 
	 * @param response
	 * @param urlFilePath
	 * @throws Exception
	 */
	public void flushServletOutput(HttpServletResponse response, String urlFilePath) throws Exception{
		//String okPDF= "/nas/arc/SE201300010000218HQkiSfL1Uj.pdf"; //this one works as test (belongs to oppdragsnr.=218
		
		if(urlFilePath!=null && urlFilePath.contains("http")){
			ServletOutputStream writer = response.getOutputStream();
	    		//test conditions
	        //absoluteFilePath = "/ownfiles/SVIU0001-0000129";
	        logger.info("Url file link:" + urlFilePath);
	        URL url = new URL(urlFilePath);
	        URLConnection urlCon = url.openConnection();
	
	        if(urlCon !=null){
	    			InputStream is = new BufferedInputStream(urlCon.getInputStream());
	            byte[] buff = new byte[BYTE_BLOCK_SIZE];
	            int bytesRead = 0;
	
	            while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
	                writer.write(buff, 0, bytesRead);
	            }
	            writer.flush();
	            writer.close();
	            is.close();
	        	
	        }else{
	        		logger.info("[ERROR] Unable to open Connection on: " + urlFilePath );
	        }
		}else{
			//redirect to private method (file on disk). Usually when the directory has been mapped in Apache/Tomcat 
			//server.xml
			this.flushServletOutputOnFile(response, urlFilePath);
		}

    }
	
	/**
	 * Gets the file payload from file on disk
	 * 
	 * @param response
	 * @param filePath
	 * @throws Exception
	 */
	private void flushServletOutputOnFile(HttpServletResponse response, String filePath) throws Exception{
		//String okPDF= "/nas/arc/SE201300010000218HQkiSfL1Uj.pdf"; //this one works as test (belongs to oppdragsnr.=218
		
		ServletOutputStream writer = response.getOutputStream();
    		
		logger.info("File on disk:" + filePath);
        if(filePath!=null && new File(filePath).exists()){
			InputStream is = new FileInputStream(filePath);
            byte[] buff = new byte[BYTE_BLOCK_SIZE];
            int bytesRead = 0;

            while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
                writer.write(buff, 0, bytesRead);
            }
            writer.flush();
            writer.close();
            is.close();
        	
        }else{
        	String errorMsg = "[ERROR] Unable to open file on disk: " + filePath;
    		logger.info(errorMsg);
    		//InputStream is = new FileInputStream(filePath);
    		writer.write(errorMsg.getBytes("UTF-8"));
            writer.flush();
            writer.close();         
        }
    }
	
	/**
	 * As in
	 * Local PDF-path: /WEB-INF/resources/files/SKAT_EDI_vejledning_CUSDEC_vers_2_7.pdf
	 * 
	 * @param response
	 * @param is
	 * @throws Exception
	 */
	public void flushServletOutputOnLocalServletFile(HttpServletResponse response, InputStream is){
		try{
			ServletOutputStream writer = response.getOutputStream();
		    byte[] buff = new byte[BYTE_BLOCK_SIZE];
	        int bytesRead = 0;
	
	        while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
	            writer.write(buff, 0, bytesRead);
	        }
	        writer.flush();
	        writer.close();
	        is.close();
		}catch (Exception e){
			e.printStackTrace();
		}
    }
	

	/**
	 * 
	 * @param response
	 * @param text
	 * @throws Exception
	 */
	public void flushServletOutput(HttpServletResponse response, byte[] text){
		try{
			ServletOutputStream writer = response.getOutputStream();
			//write the desired text now
			writer.write(text);
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public String getFileType (String filePath){
		String retval = "pdf"; //default
		
		int x = filePath.lastIndexOf(".");
		String suffix = filePath.substring(x + 1);
		retval = suffix;
		
		return retval;
	}

}
