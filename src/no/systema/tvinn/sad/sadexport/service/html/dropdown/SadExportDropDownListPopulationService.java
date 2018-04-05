/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

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
 * @date Maj 2, 2014
 * 
 */
public class SadExportDropDownListPopulationService {
	private static final Logger logger = Logger.getLogger(SadExportDropDownListPopulationService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TvinnSadConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	
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
	 * List of Berakningsenheter on Avgiftsberkningarna (TDS Import- Item lines)
	 * 
	 * @return
	 */
	public List<String> getBerakningsEnheterList(){
		String LIST_FILE = "berakningsEnheterList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	/**
	 * Hours per day
	 * 
	 * @return
	 */
	public List<String> getHoursList(){
		String LIST_FILE = "clockHoursList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	/**
	 * Minutes per hour
	 * @return
	 */
	public List<String> getMinutesList(){
		String LIST_FILE = "clockMinutesList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
}
