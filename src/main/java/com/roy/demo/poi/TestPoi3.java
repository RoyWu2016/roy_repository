  package com.roy.demo.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TestPoi3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Workbook wb = new HSSFWorkbook();

		Sheet sheet1 = wb.createSheet("��Ʒ����");
		Row row = sheet1.createRow((short) 0);
		Cell cell = row.createCell(0);
		cell.setCellValue("����");
		row.createCell(1).setCellValue("����");
	}

}
