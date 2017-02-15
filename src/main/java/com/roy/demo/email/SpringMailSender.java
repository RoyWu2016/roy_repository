package com.roy.demo.email;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SpringMailSender {

	private static final Logger logger = Logger.getLogger(SpringMailSender.class);

	// Spring的邮件工具类，实现了MailSender和JavaMailSender接口
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSenderImpl mailSender;

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor taskExecutor;

	/**
	 * 简单邮件发送
	 * 
	 */
	public void simpleSend() {
		// 构建简单邮件对象，见名知意
		logger.info("Start to send an email");
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setFrom("roy_yiyi@163.com");
			messageHelper.setSubject("Hello world"); // 主题
			messageHelper.setText("Hello world via spring mail sender"); // 内容
			messageHelper.setTo("roywu2016@qq.com"); // 发送给

			mailSender.send(mimeMessage); // 发送邮件

		} catch (Exception e) {
			logger.error("the email send error ! {}", e);
		}

	}

	/**
	 * 带附件的邮件发送
	 * 
	 * @throws MessagingException
	 */
	public void attachedSendPic() throws MessagingException {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setFrom("roy_yiyi@163.com");
		helper.setTo("roywu2016@qq.com");
		helper.setSubject("Hello Attachment");
		helper.setText("This is a mail with attachment");
		// 加载文件资源，作为附件
		MimeBodyPart attch1 = new MimeBodyPart();
		DataSource ds2 = new FileDataSource("E:/hah.jpg");
		DataHandler dh2 = new DataHandler(ds2);
		attch1.setDataHandler(dh2);
		attch1.setFileName("slogo.jpg"); // 设置附件2文件名，全都是字母不会出现汉字
		
		helper.addAttachment("attachment.jpg", ds2);

		mailSender.send(msg);
	}
	
	public void attachedSendExcel() throws MessagingException, IOException {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setFrom("roy_yiyi@163.com");
		helper.setTo("roywu2016@qq.com");
		helper.setSubject("Hello Excel Attachment");
		helper.setText("This is a mail with excel attachment");
		
		XSSFWorkbook wb = new XSSFWorkbook();
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
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);
		InputStream excelStream = new ByteArrayInputStream(out.toByteArray());
		out.close();
		
		MimeBodyPart attch1 = new MimeBodyPart();
		DataSource source = new ByteArrayDataSource(excelStream, "application/msexcel");
		DataHandler dh = new DataHandler(source);
		attch1.setDataHandler(dh);
		
		helper.addAttachment("nps.xlsx", source);

		mailSender.send(msg);
	}

	/**
	 * 发送富文本邮件
	 * 
	 * @throws MessagingException
	 */
	public void richContentSend() throws MessagingException {
		MimeMessage msg = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setFrom(mailSender.getUsername());
		helper.setTo("mosaic@126.com");
		helper.setSubject("Rich content mail");
		// 第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
		helper.setText("<body><p>Hello Html Email</p><img src='cid:file'/></body>", true);

		FileSystemResource file = new FileSystemResource(
				"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
		helper.addInline("file", file);

		mailSender.send(msg);
	}

}
