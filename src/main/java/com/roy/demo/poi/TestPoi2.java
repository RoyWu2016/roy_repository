package com.roy.demo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class TestPoi2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File kekkaFile = null;  
        File kekkaFile1 = null;  
        InputStream input = null;  
        POIFSFileSystem fs = null;  
        HSSFWorkbook wb = null;  
        FileOutputStream fos = null;  
        HSSFSheet sheet = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        HSSFCellStyle TABLE_TITLE_STYLE = null;  
        kekkaFile = new File("C:/Users/wuyiyi/Desktop/result.xls");  
        kekkaFile1 = new File("C:/Users/wuyiyi/Desktop/result3.xlsx");  
        input = new FileInputStream(kekkaFile);  
        fs = new POIFSFileSystem(input);  
        wb = new HSSFWorkbook(fs);  
        // cell style  
        TABLE_TITLE_STYLE = wb.createCellStyle();  
        TABLE_TITLE_STYLE.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        TABLE_TITLE_STYLE.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        TABLE_TITLE_STYLE.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        TABLE_TITLE_STYLE.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        TABLE_TITLE_STYLE.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        TABLE_TITLE_STYLE.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);  
        TABLE_TITLE_STYLE.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        // get first sheet of workbook  
        sheet = wb.getSheetAt(0);  
        System.out.println(sheet.getSheetName());
        
        // get first row  
        row = sheet.getRow(1);  
        // get first col  
        cell = row.getCell(1);  
        // set value of cell 
        System.out.println(cell.getStringCellValue());
        cell.setCellValue("ni hao a!");  
        TABLE_TITLE_STYLE.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);  
        cell.setCellStyle(TABLE_TITLE_STYLE);  
        fos = new FileOutputStream(kekkaFile1);  
        wb.write(fos);  
        fos.flush();  
        fos.close();  
        input.close();  
	}

}
