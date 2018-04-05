/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.service;

import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * The class gets a std Json (file based) definition.
 * It is used when lacking AS400-json service implementation
 * 
 *  
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public class SadNctsImportTextFileJsonService {
	private static final Logger logger = Logger.getLogger(SadNctsImportTextFileJsonService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TvinnSadConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private final String LOCAL_JSON_FILE = "jsonYOUR_JSON_FILE_HERE_PLEASEString.txt";
	
	/**
	 * JSON- from file SkatNctsImport
	 * 
	 * @param skatDropDownListPopulationService
	 * @return
	 */
	public String getStatusJsonPayloadFromTextFile(TvinnSadDropDownListPopulationService dropDownListPopulationService){
		String jsonPayload = null;
		try{
			TextFileReaderService fileService = new TextFileReaderService();
			List<String> list = this.textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + this.LOCAL_JSON_FILE));
			
			for(String record : list){
				//logger.info("File content:" + record);
				jsonPayload = record;	
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonPayload;

	}
}
