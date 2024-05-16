/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Feb 2024
 * 
 */
public class TransportListExcelBuilder extends AbstractXlsView {
	private ApplicationContext context;
	
	public TransportListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<SadmotfRecord> itemList = (List<SadmotfRecord>) model.get(TvinnSadConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Digitoll-Transport Main list");
        sheet.setDefaultColumnWidth(10);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);
        style.setShrinkToFit(true);
         		
        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.lnr", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.turnr", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.eta", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.tollsted", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.bilnrFlynr", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.transportLand", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.transportNamn", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.driver", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.regDato", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.typeApi", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.mrn", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.tvinn.sad.manifest.list.search.label.statusApi", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
         
        for (SadmotfRecord record : itemList) {
        	
            Row aRow = sheet.createRow(rowCount++);
            
            aRow.createCell(0).setCellValue(record.getEtlnrt());
            aRow.createCell(1).setCellValue(record.getEtavd());
            aRow.createCell(2).setCellValue(record.getEtsg());
            
            aRow.createCell(3).setCellValue(record.getEtpro());
            aRow.createCell(4).setCellValue(record.getEtst());
            aRow.createCell(5).setCellValue(record.getEtetadStr() + " " + record.getEtetatStr());
            
            aRow.createCell(6).setCellValue(record.getEttsd());
            aRow.createCell(7).setCellValue(record.getEtkmrk());
            aRow.createCell(8).setCellValue(record.getEtlkt());
            
            aRow.createCell(9).setCellValue(record.getEtnat());
            aRow.createCell(10).setCellValue(record.getEtsjaf());
            aRow.createCell(11).setCellValue(record.getEtdtrStr());
            //
            String api = new String("");
            if(record.getEtktyp()!=null) {
            	if(record.getEtktyp().startsWith("2")) {
            		api = "RAIL";
            	}else if(record.getEtktyp().startsWith("4")) {
            		api = "AIR";
            	}else {
            		api = "ROAD";
            	}
            }
            aRow.createCell(12).setCellValue(api);
            aRow.createCell(13).setCellValue(record.getEtmid());
            //
            String st2 = new String ();
            if("S".equals(record.getEtst2()) || "N".equals(record.getEtst2()) || "D".equals(record.getEtst2()) || "M".equals(record.getEtst2()) || "C".equals(record.getEtst2()) ) {
            	if("S".equals(record.getEtst2())) {
            		st2 = "SUBMITTED";
            	}else if("N".equals(record.getEtst2())) {
            		st2 = "DENIED";
            	}else if("D".equals(record.getEtst2())) {
            		st2 = "SLETTET";
            	}else if("M".equals(record.getEtst2())) {
            		st2 = "ERROR";
            	}else if("C".equals(record.getEtst2())) {
            		st2 = "COMPLETED";
            	}
            	
            }else {
            	st2 = record.getEtst2();
            }
            aRow.createCell(14).setCellValue(st2);
            
           
        }
    }
	
}
