package no.systema.tvinn.sad.sadimport.controller.view;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigRecord;
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
 * @date Apr, 2020
 * 
 */

@Controller
public class SadImportMainListViewController {
	private static final Logger logger = LoggerFactory.getLogger(SadImportMainListViewController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadImportMainListExcelView.do", method={RequestMethod.GET})
	public ModelAndView getItemListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "tvinnsadImportMainListExcelView";
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//Original for AS400 service: List<JsonSadImportTopicListRecord> mainList = null;
		List<SadImpDigRecord> mainList = null;
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

