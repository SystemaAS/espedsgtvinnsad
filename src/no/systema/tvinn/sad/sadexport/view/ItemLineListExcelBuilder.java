/**
 * 
 */
package no.systema.tvinn.sad.sadexport.view;

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

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 26, 2014
 * 
 */
public class ItemLineListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public ItemLineListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSadExportSpecificTopicItemRecord> itemList = (List<JsonSadExportSpecificTopicItemRecord>) model.get(TvinnSadConstants.ITEM_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SAD-Export Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svavd.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svtdn.arende", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svfyl.fk", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svvnt.vareNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svvktn.nettov", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svntm.mengde", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svbelt.varansPris", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svavt.ff", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tvinn.sad.export.item.list.label.svavtp.tollsats", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.wd1.vareDescription", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSadExportSpecificTopicItemRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getSvavd());
            aRow.createCell(1).setCellValue(record.getSvtdn());
            aRow.createCell(2).setCellValue(record.getSvli());
            aRow.createCell(3).setCellValue(record.getSvfyl());
            aRow.createCell(4).setCellValue(record.getSvvnt());
            aRow.createCell(5).setCellValue(record.getSvvktn());
            aRow.createCell(6).setCellValue(record.getSvntm());
            aRow.createCell(7).setCellValue(record.getSvbelt());
            aRow.createCell(8).setCellValue(record.getSvavt());
            aRow.createCell(9).setCellValue(record.getSvavtp());
            aRow.createCell(10).setCellValue(record.getWd1());
        }
    }
	
}
