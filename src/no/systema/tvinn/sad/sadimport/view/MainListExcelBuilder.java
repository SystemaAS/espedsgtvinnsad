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
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.SadImpDigRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Feb 2024
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
        List<SadImpDigRecord> itemList = (List<SadImpDigRecord>) model.get(TvinnSadConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("SAD-Import-Digitoll Main list");
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
        //
        CellStyle styleDigitoll = workbook.createCellStyle();
        styleDigitoll.setFillForegroundColor(HSSFColor.HSSFColorPredefined.DARK_BLUE.getIndex());
        styleDigitoll.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleDigitoll.setFont(font);
        
        
        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.lopenr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.ekspnr", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.transpId", new Object[0], request.getLocale()));
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
        //Digitoll
        header.createCell(12).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.lnrt", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(styleDigitoll);
        header.createCell(13).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.eta", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(styleDigitoll);
        header.createCell(14).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.bilnr", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(styleDigitoll);
        header.createCell(15).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.vikt", new Object[0], request.getLocale()));
        header.getCell(15).setCellStyle(styleDigitoll);
        header.createCell(16).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.vikthouse", new Object[0], request.getLocale()));
        header.getCell(16).setCellStyle(styleDigitoll);
        
        /* ONLY with SQL SELECT_CLAUSE_DEEP 
        header.createCell(14).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.masterDocNr", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(styleDigitoll);
        header.createCell(15).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.houseTur", new Object[0], request.getLocale()));
        header.getCell(15).setCellStyle(styleDigitoll);
        header.createCell(16).setCellValue(this.context.getMessage("systema.tvinn.sad.import.list.search.label.digitoll.houseOpd", new Object[0], request.getLocale()));
        header.getCell(16).setCellStyle(styleDigitoll);
        */
        
        // create data rows
        int rowCount = 1;
         
        for (SadImpDigRecord record : itemList) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getSiavd());
            aRow.createCell(1).setCellValue(record.getSisg());
            aRow.createCell(2).setCellValue(record.getSitdn());
            //aRow.createCell(3).setCellValue(record.getSidt()); //ISO-format
            aRow.createCell(3).setCellValue(record.getSidtno()); //NO-format
            aRow.createCell(4).setCellValue(record.getSitll());
            aRow.createCell(5).setCellValue(record.getSitle());
            aRow.createCell(6).setCellValue(record.getSitrid());
            
            aRow.createCell(7).setCellValue(record.getSist());
            
            aRow.createCell(8).setCellValue(record.getSinas());
            aRow.createCell(9).setCellValue(record.getSinak());
            aRow.createCell(10).setCellValue(record.getSivkb());
            aRow.createCell(11).setCellValue(record.getSign());
           
            //Digitoll
            if(record.getEtlnrt()!=null) { 
            	aRow.createCell(12).setCellValue(record.getEtlnrt());
            }else {
            	aRow.createCell(12).setCellValue("");
            }
            
            
            if(record.getEtetadno()!=null && !record.getEtetadno().equals("null")) { 
            	aRow.createCell(13).setCellValue(record.getEtetadno()); //NO-format);
            }else {
            	aRow.createCell(13).setCellValue("");
            }
            
            if(record.getEtkmrk()!=null && !record.getEtkmrk().equals("null")) { 
            	aRow.createCell(14).setCellValue(record.getEtkmrk());
            }else {
            	aRow.createCell(14).setCellValue("");
            }
            
            if(record.getEmvkb()!=null && !record.getEmvkb().equals("null")) { 
            	aRow.createCell(15).setCellValue(record.getEmvkb());
            }else {
            	aRow.createCell(15).setCellValue("");
            }
            if(record.getEhvkb()!=null && !record.getEhvkb().equals("null")) { 
            	aRow.createCell(15).setCellValue(record.getEhvkb());
            }else {
            	aRow.createCell(15).setCellValue("");
            }
            /* ONLY with SQL SELECT_CLAUSE_DEEP 
            if(record.getEmdkm()!=null && !record.getEmdkm().equals("null")) { 
            	aRow.createCell(14).setCellValue(record.getEmdkm());
            }else {
            	aRow.createCell(14).setCellValue("");
            }
            
            if(record.getEhpro()!=null) { 
            	aRow.createCell(15).setCellValue(record.getEhpro());
            }else {
            	aRow.createCell(15).setCellValue("");
            }
            
            if(record.getEhtdn()!=null) { 
            	aRow.createCell(16).setCellValue(record.getEhtdn());
            }else {
            	aRow.createCell(16).setCellValue("");
            }
            */
        }
    }
	
}
