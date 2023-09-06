/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
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
 * @date Sep 2023
 */
@Service
public class SadDigitollDropDownListPopulationService {
	private final String FILE_RESOURCE_PATH = TvinnSadConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private static final Logger logger = LoggerFactory.getLogger(SadDigitollDropDownListPopulationService.class.getName());
	
	/**
	 * List of currencies
	 * @return
	 */
	public List<GenericDropDownDto> getPreviousDocumentsList(){
		String LIST_FILE = "CL228_previousDocumentType.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + "digitoll/" + LIST_FILE));
		for (String record : list) {
			String[] recordArray =  record.split(";");
			if(record.length()>0) {
				GenericDropDownDto dto = new GenericDropDownDto();
				dto.setCode(recordArray[0]);
				dto.setTxt1(recordArray[1]);
				if(recordArray.length>2) {
					dto.setTxt2(recordArray[2]);
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
		
	}
	
	public List<GenericDropDownDto> getContainerSizeAndType(){
		String LIST_FILE = "CL710_containerSizeAndType.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + "digitoll/" + LIST_FILE));
		for (String record : list) {
			String[] recordArray =  record.split(";");
			if(record.length()>0) {
				GenericDropDownDto dto = new GenericDropDownDto();
				dto.setCode(recordArray[0]);
				dto.setTxt1(recordArray[1]);
				if(recordArray.length>2) {
					dto.setTxt2(recordArray[2]);
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<GenericDropDownDto> getModeOfTransportDto(){
		String LIST_FILE = "CL018_modeOfTransport.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + "digitoll/" + LIST_FILE));
		for (String record : list) {
			String[] recordArray =  record.split(";");
			if(record.length()>0) {
				GenericDropDownDto dto = new GenericDropDownDto();
				dto.setCode(recordArray[0]);
				dto.setTxt1(recordArray[1]);
				
				dtoList.add(dto);
			}
		}
		return dtoList;
		
	}
	
	public List<GenericDropDownDto> getTypeOfIdentificationMeansOfTranportDto(){
		String LIST_FILE = "CL750_typeOfIdentification.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + "digitoll/" + LIST_FILE));
		for (String record : list) {
			String[] recordArray =  record.split(";");
			if(record.length()>0) {
				GenericDropDownDto dto = new GenericDropDownDto();
				dto.setCode(recordArray[0]);
				dto.setTxt1(recordArray[1]);
				
				dtoList.add(dto);
			}
		}
		return dtoList;
		
	}
	
	public List<GenericDropDownDto> getMeansOfTransportDto(){
		String LIST_FILE = "CL751_typeOfMeansOfTransport.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + "digitoll/" + LIST_FILE));
		for (String record : list) {
			String[] recordArray =  record.split(";");
			if(record.length()>0) {
				GenericDropDownDto dto = new GenericDropDownDto();
				dto.setCode(recordArray[0]);
				dto.setTxt1(recordArray[1]);
				
				dtoList.add(dto);
			}
		}
		return dtoList;
		
	}
	
	
	
	
	public List<GenericDropDownDto> getCountryList(){
		String LIST_FILE = "countryList.txt";
		List<GenericDropDownDto> dtoList = new ArrayList<GenericDropDownDto>();
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH  + LIST_FILE));
		for (String record : list) {
			GenericDropDownDto dto = new GenericDropDownDto();
			dto.setCode(record);
			
			dtoList.add(dto);
		}
		return dtoList;
		
	}
	
	
}
