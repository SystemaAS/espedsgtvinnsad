/**
 * 
 */
package no.systema.main.util.io;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import no.systema.main.util.AppConstants;
import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Maj 4, 2015
 * 
 * Auxiliary class in order to render content from a file (PDF, HTML, TEXT, etc)
 * Usually when we MUST build the content inside a Controller after a certain routine.
 * 
 * 
 */
public class FileContentRenderer {
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	private static Logger logger = Logger.getLogger(FileContentRenderer.class.getName());
	
	/**
	 * 
	 * @param response
	 * @param absoluteFilePath
	 * @throws Exception
	 */
	public void renderContent(HttpServletResponse response, String absoluteFilePath) throws Exception{
		String fileType = this.payloadContentFlusher.getFileType(absoluteFilePath);
        
		if(AppConstants.DOCUMENTTYPE_PDF.equals(fileType)){
        		response.setContentType(AppConstants.HTML_CONTENTTYPE_PDF);
        }else if(AppConstants.DOCUMENTTYPE_TIFF.equals(fileType) || AppConstants.DOCUMENTTYPE_TIF.equals(fileType)){
    			response.setContentType(AppConstants.HTML_CONTENTTYPE_TIFF);
        }else if(AppConstants.DOCUMENTTYPE_JPEG.equals(fileType) || AppConstants.DOCUMENTTYPE_JPG.equals(fileType)){
        		response.setContentType(AppConstants.HTML_CONTENTTYPE_JPEG);
        }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
    			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
        }else if(AppConstants.DOCUMENTTYPE_DOC.equals(fileType)){
    			response.setContentType(AppConstants.HTML_CONTENTTYPE_WORD);
        }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType)){
    			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
        }
        //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
        response.setHeader ("Content-disposition", "filename=\"archiveDocument." + fileType + "\"");
        
        logger.info("Start flushing file payload...");
        //send the file output to the ServletOutputStream
        try{
        		this.payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
        		//payloadContentFlusher.flushServletOutput(response, "plain text test...".getBytes());
        	
        }catch (Exception e){
        		e.printStackTrace();
        }
		
    }
	

}
