package com.roy.demo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class TestPoi {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		File file = new File("E:\\aipoi.xls");
//		if (file.exists()) {
//			file.delete();
//		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MMMM-dd",Locale.ENGLISH);
		Calendar rightNow = Calendar.getInstance();
		String endStr = sf.format(rightNow.getTime());
		rightNow.add(Calendar.MONTH, -3);
		String startStr = sf.format(rightNow.getTime());
		
		String inspectionPeriod = startStr + " - " + endStr;
		
//		OutputStream os = new FileOutputStream("AI Report List(2016-09-10 - 2016-10-14).xls");
		Workbook wb = new HSSFWorkbook();
//		TestPoi test = new TestPoi();
//		test.createFile(os, wb);
		
//		FileInputStream fis = new FileInputStream("AI Report List(2016-09-10 - 2016-10-14).xls");  
//		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(fis));  
		TestPoi test = new TestPoi();
//		fis.close();
		OutputStream os = new FileOutputStream("E:\\");
		test.createFile(os, wb);
	}

	private void createFile(OutputStream os, Workbook wb) throws IOException {
		// TODO Auto-generated method stub
		int i = 0;
		Font font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		CellStyle cs1 = wb.createCellStyle();
		cs1.setAlignment(CellStyle.ALIGN_CENTER);
		cs1.setFillForegroundColor(HSSFColor.WHITE.index);
		cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs1.setFont(font);

		CellStyle cs2 = wb.createCellStyle();
		cs2.setFillForegroundColor(HSSFColor.WHITE.index);
		cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		CellStyle cs3 = wb.createCellStyle();
		cs3.setAlignment(CellStyle.ALIGN_CENTER);
		cs3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs3.setFont(font);

		cs3.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		cs3.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		cs3.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		cs3.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		// RegionUtil

		Sheet sheet = wb.createSheet("AI Orders List (2016-09-01 - 2016-10-01");
		Row row = null;
		for (i = 0; i <= 10; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < 9; j++) {
				row.createCell(j).setCellStyle(cs1);
			}
		}

		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));

		row = sheet.getRow(4);
		Cell cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("List of Orders from 2016-09-01 to 2016-11-29");
		cell.setCellStyle(cs1);

		row = sheet.getRow(6);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Date: 2016-October-13");
		cell.setCellStyle(cs2);

		row = sheet.getRow(7);
		cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Client login: MrPriceFootwear");
		cell.setCellStyle(cs2);

		String[] title = new String[] { "Type", "Product Name", "Product Ref / SKU", "P/O Number", "Report received on",
				"Factory Name", "Result", "Status", "AI Rerence" };
		row = sheet.createRow(10);
		for (int k = 0; k < 9; k++) {
			cell = row.createCell(k);
			cell.setCellStyle(cs3);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(title[k]);
			sheet.autoSizeColumn((short) k);
		}

		wb.write(os);
	}

}
