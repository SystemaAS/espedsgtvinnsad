package no.systema.main.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//javax
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//java
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

//application imports
import no.systema.main.util.AppConstants;
import no.systema.main.validator.LoginValidator;
import no.systema.main.model.SystemaWebUser;


/**
 * 
 * File Upload Controller 
 * 
 * @author oscardelatorre
 * @date Apr 21, 2015
 * 
 * 
 */

@Controller
@Scope("session")
public class GeneralFileUploadController {
	
	private static final Logger logger = Logger.getLogger(GeneralFileUploadController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param name
	 * @param file
	 * @return
	 */
	@RequestMapping(value="uploadFile.do", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,@RequestParam("file") MultipartFile file, HttpSession session ) {
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return "Not logged in...?";
		}else{
			logger.info("User:" + appUser.getUser());
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	 
	                // Creating the directory to store file
	                String rootPath = System.getProperty("catalina.home");
	                File dir = new File(rootPath);
	                /*optional
	                File dir = new File(rootPath + File.separator + "tmpFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	 				*/
	                
	                // Create the file on server
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	 
	                logger.info("Server File Location=" + serverFile.getAbsolutePath());
	                return "You successfully uploaded file=" + name;
	                
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
		}
    }
	
	
}

