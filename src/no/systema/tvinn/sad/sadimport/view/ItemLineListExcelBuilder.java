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

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 25, 2014
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
        List<JsonSadImportSpecificTopicItemRecord> itemList = (List<JsonSadImportSpecificTopicItemRecord>) model.get(TvinnSadConstants.ITEM_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SAD-Import Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svavd.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svtdn.arende", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svlk.opprLand", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svvnt.vareNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svtn.tn", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svpre.preference", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svbelt.varansPris", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svvktn.nettov", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svntm.mengde", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svpva.enhetPVA", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svas.tollsats", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svmfr.mva", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.svkdaae.avgift", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.tvinn.sad.import.item.list.label.wd1.vareDescription", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSadImportSpecificTopicItemRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getSvavd());
            aRow.createCell(1).setCellValue(record.getSvtdn());
            aRow.createCell(2).setCellValue(record.getSvli());
            
            aRow.createCell(3).setCellValue(record.getSvlk());
            aRow.createCell(4).setCellValue(record.getSvvnt());
            aRow.createCell(5).setCellValue(record.getSvtn());
            aRow.createCell(6).setCellValue(record.getSvpre());
            aRow.createCell(7).setCellValue(record.getSvbelt());
            aRow.createCell(8).setCellValue(record.getSvvktn());
            aRow.createCell(9).setCellValue(record.getSvntm());
            aRow.createCell(10).setCellValue(record.getSvpva());
            aRow.createCell(11).setCellValue(record.getSvas());
            aRow.createCell(12).setCellValue(record.getSvmfr());
            aRow.createCell(13).setCellValue(record.getSvkdaae());
            aRow.createCell(14).setCellValue(record.getWd1());
        }
    }
	
}
