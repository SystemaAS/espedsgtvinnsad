/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.service.html.dropdown;

import java.util.List;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * This service fetches values into drop downs in HTML
 * Criteria is based upon whether the drop down list is static or dynamic.
 * 
 * This class is used ONLY for STATIC lists (e.g. currency codes, country codes, etc)
 * 
 * The servlet context is necessary in order to get a text-xml file within the webb-application path...
 * All static lists are retrieved from a file on disk.
 *  
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public class SadNctsExportDropDownListPopulationService {
	private final String FILE_RESOURCE_PATH = TvinnSadConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private static final Logger logger = Logger.getLogger(SadNctsExportDropDownListPopulationService.class.getName());
	
	/**
	 * List of currencies
	 * @return
	 */
	public List<String> getCurrencyList(){
		String LIST_FILE = "currencyList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record);
		}*/
		return list;
	}
	
	/**
	 * List of countries
	 * @return
	 */
	public List<String> getCountryList(){
		String LIST_FILE = "countryList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	/**
	 * List of language codes
	 * @return
	 */
	public List<String> getLanguageList(){
		String LIST_FILE = "languageList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
}
