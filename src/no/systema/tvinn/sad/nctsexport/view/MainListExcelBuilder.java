/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.view;

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

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicListRecord;
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
        List<JsonSadNctsExportTopicListRecord> itemList = (List<JsonSadNctsExportTopicListRecord>) model.get(TvinnSadConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("TVINN-NctsExport Main list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.lrnNr", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.mrnNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.mottagare", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.export.list.search.label.bruttovikt", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSadNctsExportTopicListRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd());
            aRow.createCell(1).setCellValue(record.getSign());
            aRow.createCell(2).setCellValue(record.getOpd());
            
            aRow.createCell(3).setCellValue(record.getLrnNr());
            aRow.createCell(4).setCellValue(record.getMrnNr());
            aRow.createCell(5).setCellValue(record.getDatum());
            
            aRow.createCell(6).setCellValue(record.getStatus());
            aRow.createCell(7).setCellValue(record.getMotnavn());
            aRow.createCell(8).setCellValue(record.getBruttoVikt());
            
            
        }
    }
	
}
