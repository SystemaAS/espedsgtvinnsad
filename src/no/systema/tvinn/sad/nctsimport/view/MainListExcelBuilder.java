/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportTopicListRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportTopicListRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Apr 2020
 * 
 */
public class MainListExcelBuilder extends AbstractXlsView {
	private ApplicationContext context;
	
	public MainListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSadNctsImportTopicListRecord> itemList = (List<JsonSadNctsImportTopicListRecord>) model.get(TvinnSadConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("TVINN-NctsImport Main list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);
         
        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.mrnNr", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.type", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.ansvarig", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.godsnr", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tvinn.sad.ncts.import.list.search.label.frigivningsDatum", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSadNctsImportTopicListRecord record : itemList) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd());
            aRow.createCell(1).setCellValue(record.getSign());
            aRow.createCell(2).setCellValue(record.getOpd());
            
            aRow.createCell(3).setCellValue(record.getMrnNr());
            aRow.createCell(4).setCellValue(record.getDatum());
            aRow.createCell(5).setCellValue(record.getStatus());
            
            aRow.createCell(6).setCellValue(record.getForenklad());
            aRow.createCell(7).setCellValue(record.getAnsNavn());
            aRow.createCell(8).setCellValue(record.getGodsNr());
            aRow.createCell(9).setCellValue(record.getDatumFr());

        }
    }
	
}
