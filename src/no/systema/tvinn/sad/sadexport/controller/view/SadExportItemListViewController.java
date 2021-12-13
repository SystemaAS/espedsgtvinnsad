package no.systema.tvinn.sad.sadexport.controller.view;

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
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * Working controller for the Item list non JSP-pages
 * The controller will manage all export functionality to different view formats such as:
 * 
 * (1) Excel, PDF, other are implemented here
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Nov 26, 2014
 * 
 */

@Controller
public class SadExportItemListViewController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(SadExportItemListViewController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadExportItemListExcelView.do", method={RequestMethod.GET})
	public ModelAndView getItemListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "tvinnsadExportItemListExcelView";
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonSadExportSpecificTopicItemRecord> itemList = null;
		
        //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
        response.setHeader ("Content-disposition", "filename=\"" + EXCEL_VIEW + ".xls\"");

		if(appUser==null){
			return this.loginView;
		}else{
			itemList = (List)session.getAttribute(session.getId() + TvinnSadConstants.SESSION_LIST);
		}	
		
		return new ModelAndView(EXCEL_VIEW, TvinnSadConstants.ITEM_LIST, itemList);
	}
}

