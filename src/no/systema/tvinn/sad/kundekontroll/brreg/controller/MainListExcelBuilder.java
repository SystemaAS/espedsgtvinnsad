package no.systema.tvinn.sad.kundekontroll.brreg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import no.systema.main.context.TdsAppContext;
import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckRecord;
/**
 * Creates a excel view on invalide kunder checked against data.brreg.no
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 27, 2016
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
        List<JsonEnhetsRegisteretDataCheckRecord> itemList = (List<JsonEnhetsRegisteretDataCheckRecord>) model.get("list");
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("invalide kunder");
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

        //Note: the locale must be fetched from the response since we are working with the Spring Interceptor.
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Kundnr");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Kundenavn");
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue("Org.nr");
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue("HovedEnhet");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("UnderEnhet");
        header.getCell(4).setCellStyle(style);
  
        header.createCell(5).setCellValue("HovedEnhets Org.nr");
        header.getCell(5).setCellStyle(style);
  
        header.createCell(6).setCellValue("Konkurs");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("Merverdi");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("Under avvikling");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("Under tvangsavvikling");
        header.getCell(9).setCellStyle(style);
  
        //Added for Schenker, remove ?
        header.createCell(10).setCellValue("Organisasjonsform");
        header.getCell(10).setCellStyle(style);
 
            
        // create data rows
        int rowCount = 1;
         
        for (JsonEnhetsRegisteretDataCheckRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getKundenr());
            aRow.createCell(1).setCellValue(record.getKundenavn());
            aRow.createCell(2).setCellValue(record.getOrgnr());
            aRow.createCell(3).setCellValue(record.getExistsashovedenhet());
            aRow.createCell(4).setCellValue(record.getExistsasunderenhet());
            aRow.createCell(5).setCellValue(record.getOverordnetenhetorgnr());
            aRow.createCell(6).setCellValue(record.getKonkurs());
            aRow.createCell(7).setCellValue(record.getRegistrertimvaregisteret());
            aRow.createCell(8).setCellValue(record.getUnderavvikling());
            aRow.createCell(9).setCellValue(record.getUndertvangsavviklingellertvangsopplosning());
            aRow.createCell(10).setCellValue(record.getOrganisasjonsform());

        }
    }
	
}
