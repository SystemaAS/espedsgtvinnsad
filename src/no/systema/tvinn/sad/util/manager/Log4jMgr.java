package no.systema.tvinn.sad.util.manager;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import no.systema.main.context.TdsServletContext;
import no.systema.main.controller.GeneralTextRenderController;

public class Log4jMgr {

	private static final Logger logger = Logger.getLogger(Log4jMgr.class.getName());
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doLevelUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		logger.warn("(A)");
		String logLevel = request.getParameter("logLevel");
		String reloadPropertiesFile = request.getParameter("reloadPropertiesFile");
		
		logger.warn(logLevel);
		
		if (logLevel != null) {
			setLogLevelWithParameter(logLevel);
			logger.warn("Attempting to reload log4j properties file");
			loadLog4jPropertiesFile();
		} else {
			logger.warn("no logLevel or reloadPropertiesFile parameters were found");
		}
		
	}

	private void setLogLevelWithParameter(String logLevel) {
		
		Logger root = Logger.getRootLogger();
		Enumeration allLoggers = root.getLoggerRepository().getCurrentLoggers();

		boolean logLevelRecognized = true;
		if (Level.DEBUG.toString().equalsIgnoreCase(logLevel) || Level.INFO.toString().equalsIgnoreCase(logLevel) || Level.WARN.toString().equalsIgnoreCase(logLevel) ||
				Level.ERROR.toString().equalsIgnoreCase(logLevel) || Level.FATAL.toString().equalsIgnoreCase(logLevel) ) {
			root.setLevel(Level.toLevel(logLevel));
			while (allLoggers.hasMoreElements()){
		        Category tmpLogger = (Category) allLoggers.nextElement();
		        tmpLogger.setLevel(Level.toLevel(logLevel));
		    }
		} else {
			logLevelRecognized = false;
		}
		
		

		if (logLevelRecognized) {
			logger.warn("Log level has been set to: " + logLevel);
		} else {
			logger.warn("logLevel parameter '" + logLevel + "' level not recognized");
		}
	}

	private void loadLog4jPropertiesFile() {
		String webAppPath = TdsServletContext.getTdsServletContext().getRealPath("/");
		String log4jLocation = webAppPath + "WEB-INF/classes/log4j.properties";

		if (log4jLocation == null) {
			logger.warn("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String log4jProp = log4jLocation;
			File log4jFile = new File(log4jProp);
			if (log4jFile.exists()) {
				//logger.warn("Initializing log4j with: " + log4jProp);
				logger.warn("Initializing log4j ");
				PropertyConfigurator.configure(log4jProp);
			} else {
				logger.warn("*** log4j.properties" + " file not found, so initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
	}
	
	public void doLogoutLogger(){
		//go back to WARN level since we might have put INFO for debugging reasons
		Logger root = Logger.getRootLogger();
		Enumeration allLoggers = root.getLoggerRepository().getCurrentLoggers();
		root.setLevel(Level.WARN);
		while (allLoggers.hasMoreElements()){
	        Category tmpLogger = (Category) allLoggers.nextElement();
	        tmpLogger.setLevel(Level.WARN);
	    }
		
	}
	
}
