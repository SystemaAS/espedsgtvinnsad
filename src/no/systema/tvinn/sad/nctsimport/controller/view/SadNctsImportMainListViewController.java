package no.systema.tvinn.sad.nctsimport.controller.view;

import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicListRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;



/**
 * Working controller for the Main list non JSP-pages
 * The controller will manage all export functionality to different view formats such as:
 * 
 * (1) Excel, PDF, other are implemented here
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Apr 2020
 * 
 */

@Controller
public class SadNctsImportMainListViewController {
	private static final Logger logger = LogManager.getLogger(SadNctsImportMainListViewController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadNctsImportMainListExcelView.do", method={RequestMethod.GET})
	public ModelAndView getItemListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "tvinnsadNctsImportMainListExcelView";
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonSadNctsImportTopicListRecord> mainList = null;
		
        //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
        response.setHeader ("Content-disposition", "filename=\"" + EXCEL_VIEW + ".xls\"");

		if(appUser==null){
			return this.loginView;
		}else{
			mainList = (List)session.getAttribute(session.getId() + TvinnSadConstants.SESSION_LIST);
		}	
		
		return new ModelAndView(EXCEL_VIEW, TvinnSadConstants.MAIN_TOPIC_LIST, mainList);
	}
}

