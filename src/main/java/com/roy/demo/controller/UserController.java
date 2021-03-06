package com.roy.demo.controller;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roy.demo.annotation.SystemLog;
import com.roy.demo.annotation.TokenSecured;
import com.roy.demo.config.ServiceConfig;
import com.roy.demo.model.UserInfo;
import com.roy.demo.service.UserService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceConfig config;

//	@TokenSecured
	@RequestMapping("/showInfo")
	public ResponseEntity<String> showUserInfo(@RequestParam("userId") String userId) {
		String userInfo = userService.getUserById(userId);

		// modelMap.addAttribute("userInfo", userInfo);
		return new ResponseEntity<String>(userInfo, HttpStatus.OK);
	}

//	@TokenSecured
	@SystemLog(description = "query data")
	@RequestMapping("/showInfos/date/{dateTime}/flag")
	public Object showUserInfos(@PathVariable("dateTime") Date dateTime,
			@RequestParam(value = "flag", required = false) boolean flag) {
		List<UserInfo> userInfos = userService.getUsers();
		logger.info(JSON.toJSONString(userInfos));
		// return userInfos;
		return config.getApiServiceUrl() + "+" + config.getLogServiceUrl();
	}

	@RequestMapping("/exportExcle")
	public @ResponseBody void exportExcle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		createFile(wb, response);
	}

	@RequestMapping("/uploadFiles")
	public String uploadFiles(@RequestParam(value = "file", required = false) MultipartFile file, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("invoke: user/uploadFiles");
		String path = request.getSession().getServletContext().getRealPath("upload");  
		logger.info("File Path: " + path);
		String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } 
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
  
        return "result"; 
	}
	
	private void createFile(HSSFWorkbook wb, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int i = 0;
		int j = 0;
		double trans_amt = 0.00;
		double ref_amt = 0.00;
		String[] refundLogs = new String[2];
		String str1 = "20110812|34234234242432|345.00|323.00";
		String str2 = "20110504|45656464535345|231.34|231.34";
		refundLogs[0] = str1;
		refundLogs[1] = str2;

		Sheet sheet = wb.createSheet("AI Orders List (2016-09-01 - 2016-10-01");
		sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
		Row row = sheet.createRow(0);
		for (i = 1; i <= 10; i++) {
			sheet.createRow(i);
		}

		for (i = 0; i < 12; i++) {
			row.createCell(i);
		}

		row = sheet.getRow(4);
		Cell cell = row.getCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("List of Orders from 2016-09-01 to 2016-11-29");

		row = sheet.getRow(6);
		cell = row.getCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Date: 2016-October-13");

		row = sheet.getRow(7);
		cell = row.getCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Client login: MrPriceFootwear");

		sheet.createRow(11);

		downloadFile(wb, response);
	}

	private void downloadFile(HSSFWorkbook book, HttpServletResponse response) throws IOException {

		MessageFormat format = new MessageFormat("Test_{0,date,yyyyMMddhhmm}.xls");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + format.format(new Object[] { new Date() }) + "\" ");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
		response.setHeader("Pragma", "public");
		ServletOutputStream os = response.getOutputStream();

		book.write(os);
		os.flush();
		os.close();
	}

}
