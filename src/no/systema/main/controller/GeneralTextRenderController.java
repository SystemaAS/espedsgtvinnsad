package no.systema.main.controller;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.util.AppConstants;
import no.systema.main.util.AppResources;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.validator.DateValidator;
import no.systema.tvinn.sad.util.manager.Log4jMgr;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;


/**
 * 
 * Text(file) Render Controller 
 * 
 * @author oscardelatorre
 * @date Aug 25, 2014
 * 
 * 
 */

@Controller
@Scope("session")
public class GeneralTextRenderController {
	//OBSOLETE:  static final ResourceBundle resources = AppResources.getBundle();
	
	private static final Logger logger = LoggerFactory.getLogger(GeneralTextRenderController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private final String RELATIVE_LOGFILE_PATH = "logs/" + ApplicationPropertiesUtil.getProperty("logsg.logger.file");   //OBSOLETE: resources.getString("log4j.logger.file");
	private final String RELATIVE_LOGFILE_EXPRESS_FORTOLLING_SERVICE_PATH = "logs/logsg_syjservicestn-expft.log";
	private final String RELATIVE_LOGFILE_SADTVINN_SERVICE_PATH = "logs/logsg_syjservicestn.log";
	private final String RELATIVE_LOGFILE_ROAD_ENTRY_PATH = "logs/logsg_digitoll-roadentry.log";
	private final String RELATIVE_LOGFILE_SADTVINN_PATH = "logs/logsg_espedtvinnsad.log";
	private final String RELATIVE_LOGFILE_CATALINA_OUT = "logs/catalina.out";
	
	private final String SERVLET_CONTEXT_WEBAPPS_ROOT = "webapps";
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="renderLocalLogsg.do", method={ RequestMethod.GET })
	public ModelAndView doRenderLocalLogsg(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalLogsg...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String date = request.getParameter("date");
		Log4jMgr log4jMgr = new Log4jMgr();
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			StringBuilder logFile = new StringBuilder();
			if(pathRootIndex!=-1){
				logFile.append(path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_PATH);
				if(StringUtils.isNotEmpty(date)) {
					if(new DateValidator().validateDateIso203_YYYY_MM_DD(date)) {
						logFile.append("." + date);
					}
				}
				logger.info("logFile:" + logFile);
			}
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile.toString());
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"logsgLogger.txt" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutput(response, logFile.toString());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	
	@RequestMapping(value="renderLocalLogsgService.do", method={ RequestMethod.GET })
	public ModelAndView doRenderLocalLogsgService(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalLogsgExpFt...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String date = request.getParameter("date");
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				//log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			StringBuilder logFile = new StringBuilder();
			
			if(pathRootIndex!=-1){
				logFile.append( path.substring(0,pathRootIndex) + this.RELATIVE_LOGFILE_SADTVINN_SERVICE_PATH);
				if(StringUtils.isNotEmpty(date)) {
					if(new DateValidator().validateDateIso203_YYYY_MM_DD(date)) {
						logFile.append("." + date);
					}
				}
				logger.info("logFile:" + logFile.toString());
			}
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile.toString());
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"log4jCustomer.txt" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutput(response, logFile.toString());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	/**
	 * Special for log4j_syjservicestn-expft.log
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="renderLocalLogsgExpft.do", method={ RequestMethod.GET })
	public ModelAndView doRenderLocalLog4jExpFt(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalLogsgExpFt...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String date = request.getParameter("date");
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				//log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			StringBuilder logFile = new StringBuilder();
			if(pathRootIndex!=-1){
				logFile.append(path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_EXPRESS_FORTOLLING_SERVICE_PATH);
				if(StringUtils.isNotEmpty(date)) {
					if(new DateValidator().validateDateIso203_YYYY_MM_DD(date)) {
						logFile.append("." + date);
					}
				}
				logger.info("logFile:" + logFile.toString());
			}
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile.toString());
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"logsgDigitollLogger.txt" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutput(response, logFile.toString());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	
	@RequestMapping(value="renderLocalLogsgRoadEntry.do", method={ RequestMethod.GET })
	public ModelAndView doRenderLocalRoadEntry(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalLogsgExpFt...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String date = request.getParameter("date");
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				//log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			StringBuilder logFile = new StringBuilder();
			if(pathRootIndex!=-1){
				logFile.append(path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_ROAD_ENTRY_PATH);
				if(StringUtils.isNotEmpty(date)) {
					if(new DateValidator().validateDateIso203_YYYY_MM_DD(date)) {
						logFile.append("." + date);
					}
				}
				logger.info("logFile:" + logFile.toString());
			}
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile.toString());
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"logsgDigitollRoadEntryLogger.txt" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutput(response, logFile.toString());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	
	@RequestMapping(value="renderLocalCatalina.do", method={ RequestMethod.GET })
	public ModelAndView doRenderCatalina(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderLocalLogsgExpFt...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Log4jMgr log4jMgr = new Log4jMgr();
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				//log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			String logFile = null;
			if(pathRootIndex!=-1){
				logFile = path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_CATALINA_OUT;
				logger.info("logFile:" + logFile);
			}
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile);
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"log4jCustomer.txt" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutput(response, logFile);
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="renderExternalHouseBupFiles.do", method={ RequestMethod.GET })
	public ModelAndView doRenderExternalHouseFiles(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderExternalHouseBupFiles...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String fp = request.getParameter("fp");
		
		Log4jMgr log4jMgr = new Log4jMgr();
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			try{
				log4jMgr.doLevelUpdate(request, response);
			}catch(Exception e){
				e.toString();
			}
			
			//String path = TdsServletContext.getTdsServletContext().getRealPath("/");
			//logger.info("ServletContext:" + path);
			//int pathRootIndex = path.indexOf(SERVLET_CONTEXT_WEBAPPS_ROOT);
			String logFile = fp;
			
			//String logFile = null;
			/*if(pathRootIndex!=-1){
				logFile = path.substring(0,pathRootIndex) + RELATIVE_LOGFILE_PATH;
				logger.info("logFile:" + logFile);
			}*/
			
			if(logFile!=null ){
				//must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(logFile);
                if(AppConstants.DOCUMENTTYPE_LOG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_XML.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTXML);
                }//--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"digitollFile" + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		//InputStream inputStream = session.getServletContext().getResourceAsStream(logFile);
                		this.payloadContentFlusher.flushServletOutputDigitollBupFiles(response, logFile);
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	
}

