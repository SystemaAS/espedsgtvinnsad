/**
 * 
 */
package no.systema.tvinn.sad.sadimport.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Apr 2020
 * 
 */
public class MainListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public MainListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSadImportTopicListRecord> itemList = (List<JsonSadImportTopicListRecord>) model.get(TvinnSadConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("TVINN-Import Main list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.extrefnr", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.lopenr", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.ekspnr", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.avsandare", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.mottagare", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.vikt", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.godsnr", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.innstikk", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.email", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.omber.omber", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        header.createCell(15).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.omber.status", new Object[0], request.getLocale()));
        header.getCell(15).setCellStyle(style);
        header.createCell(16).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.omber.datum", new Object[0], request.getLocale()));
        header.getCell(16).setCellStyle(style);
        header.createCell(17).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.omber.lopenr", new Object[0], request.getLocale()));
        header.getCell(17).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSadImportTopicListRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd());
            aRow.createCell(1).setCellValue(record.getSg());
            aRow.createCell(2).setCellValue(record.getOpd());
            
            aRow.createCell(3).setCellValue(record.getH_xref());
            aRow.createCell(4).setCellValue(record.getDatum());
            aRow.createCell(5).setCellValue(record.getSitll());
            aRow.createCell(6).setCellValue(record.getSitle());
            
            aRow.createCell(7).setCellValue(record.getStatus());
            aRow.createCell(8).setCellValue(record.getAvsNavn());
            aRow.createCell(9).setCellValue(record.getMotNavn());
            
            aRow.createCell(10).setCellValue(record.getSivkb());
            aRow.createCell(11).setCellValue(record.getSign());
            aRow.createCell(12).setCellValue(record.getSimi());
            aRow.createCell(13).setCellValue(record.getEpjn());
            
            aRow.createCell(14).setCellValue(record.getO2_simf());
            aRow.createCell(15).setCellValue(record.getO2_sist());
            aRow.createCell(16).setCellValue(record.getO2_sidt());
            aRow.createCell(17).setCellValue(record.getO2_sitll());
            
        }
    }
	
}
