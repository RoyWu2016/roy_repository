package com.roy.demo.poi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImageTest {
	
	 public static void main(String[] args) {  
         FileOutputStream fileOut = null;     
         BufferedImage bufferImg = null;     
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray    
        try {  
//            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();     
//            bufferImg = ImageIO.read(new File("E:/logo.png"));     
//            ImageIO.write(bufferImg, "png", byteArrayOut);  
//              
//            HSSFWorkbook wb = new HSSFWorkbook();     
//            HSSFSheet sheet1 = wb.createSheet("test picture");    
//            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）  
//            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();     
//            //anchor主要用于设置图片的属性  
//            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 5, 8);     
//            anchor.setAnchorType(3);     
//            //插入图片    
//            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));   
            fileOut = new FileOutputStream("E:/Excel.xlsx");     
//            // 写入excel文件     
//             wb.write(fileOut);   
        	
        	XSSFWorkbook wb = new XSSFWorkbook(); 
        	XSSFSheet sheet = wb.createSheet("test picture");   
        	String fileName = "E:/logo.png";  
        	  
        	int i = 0;
    		Font font = wb.createFont();
    		font.setFontName("Verdana");
    		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    		CellStyle tileCS = wb.createCellStyle();
    		tileCS.setAlignment(CellStyle.ALIGN_CENTER);
    		tileCS.setFillForegroundColor(HSSFColor.WHITE.index);
    		tileCS.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    		tileCS.setFont(font);

    		CellStyle dateCS = wb.createCellStyle();
    		dateCS.setFillForegroundColor(HSSFColor.WHITE.index);
    		dateCS.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

    		CellStyle tableHeadeCS = wb.createCellStyle();
    		tableHeadeCS.setAlignment(CellStyle.ALIGN_CENTER);
    		tableHeadeCS.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    		tableHeadeCS.setFont(font);
    		tableHeadeCS.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
    		tableHeadeCS.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
    		tableHeadeCS.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
    		tableHeadeCS.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
    		
    		CellStyle tableCS = wb.createCellStyle();
    		tableCS.setAlignment(CellStyle.ALIGN_CENTER);
    		tableCS.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    		tableCS.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
    		tableCS.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
    		tableCS.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
    		tableCS.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
    		tableCS.setWrapText(true);

//    		Sheet sheet = wb.createSheet("AI Orders List (" + inspectionPeriod + ")");
    		Row row = null;
    		for (i = 0; i <= 10; i++) {
    			row = sheet.createRow(i);
    			for (int j = 0; j < 9; j++) {
    				row.createCell(j).setCellStyle(tileCS);
    			}
    		}

    		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));
    		
//    		String fileName = config.getExcleLoggoCommonSource() + File.separator + "logo.png";
//    		String fileName = "E:" +  File.separator + "logo.png";
//    		logger.info("fine the logo resource: " + fileName);
    		InputStream is = new FileInputStream(fileName);  
        	byte[] bytes = IOUtils.toByteArray(is);  
        	  
        	int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);  
        	  
        	CreationHelper helper = wb.getCreationHelper();  
        	Drawing drawing = sheet.createDrawingPatriarch();  
        	ClientAnchor anchor = helper.createClientAnchor();  
        	  
        	anchor.setRow1(0);  
        	anchor.setCol1(0); 
        	
//            anchor.setRow1(5);  
//            anchor.setCol1(3);  
//            anchor.setDx1(40);  
//            anchor.setDy1(8);  
//              
//            anchor.setRow2(5);  
//            anchor.setCol2(3);  
//            anchor.setDx2(741);  
//            anchor.setDy2(152); 
        	
        	Picture pict = drawing.createPicture(anchor, pictureIdx);  
        	pict.resize();  

    		row = sheet.getRow(4);
    		Cell cell = row.createCell(0);
    		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    		cell.setCellValue("List of Orders from ");
    		cell.setCellStyle(tileCS);

    		row = sheet.getRow(6);
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MMMM-dd",Locale.ENGLISH);
    		cell = row.createCell(0);
    		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    		cell.setCellValue("Date: " + sf.format(new Date()));
    		cell.setCellStyle(dateCS);

    		row = sheet.getRow(7);
    		cell = row.createCell(0);
    		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    		cell.setCellValue("Client login: ");
    		cell.setCellStyle(dateCS);

    		String[] title = new String[] { "Type", "Product Name", "Product Ref / SKU", "P/O Number", "Report received on",
    				"Factory Name", "Result", "Status", "AI Rerence" };
    		row = sheet.createRow(10);
    		for (int k = 0; k < 9; k++) {
    			cell = row.createCell(k);
    			cell.setCellStyle(tableHeadeCS);
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			cell.setCellValue(title[k]);
    			sheet.autoSizeColumn((short) k);
    		}
        	
        	wb.write(fileOut);   
        	
             System.out.println("----Excle文件已生成------");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(fileOut != null){  
                 try {  
                    fileOut.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  

}
