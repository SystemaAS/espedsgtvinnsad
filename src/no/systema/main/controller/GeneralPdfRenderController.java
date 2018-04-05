package no.systema.main.controller;

import java.util.*;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.io.PayloadContentFlusher;

import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;


/**
 * 
 * Pdf Render Controller 
 * 
 * @author oscardelatorre
 * @date Feb 14, 2014
 * 
 * 
 */

@Controller
@Scope("session")
public class GeneralPdfRenderController {
	private static final Logger logger = Logger.getLogger(GeneralPdfRenderController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	private final String FILE_RESOURCE_PATH = AppConstants.RESOURCE_FILES_PATH;
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="renderLocalPdf.do", method={ RequestMethod.GET })
	public ModelAndView doRenderLocalPdf(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalPdf...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			String localFileName = request.getParameter("fn");
			String localFilePath = this.FILE_RESOURCE_PATH + localFileName; 
			
			//String path="/WEB-INF/ProjectFiles/Risultati/risultati_test.txt";
			//InputStream inputStream = this.getServletConfig().getServletContext().getResourceAsStream(path);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			logger.info("Local PDF-path:" + localFilePath);
			if(localFilePath!=null && !"".equals(localFilePath)){
				
                String absoluteFilePath = localFilePath;
                
                //must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(localFilePath);
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
                }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType) || AppConstants.DOCUMENTTYPE_XLSX.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
                }
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"MyDocument." + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		InputStream inputStream = session.getServletContext().getResourceAsStream(absoluteFilePath);
                		this.payloadContentFlusher.flushServletOutputOnLocalServletFile(response, inputStream);
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	
}

