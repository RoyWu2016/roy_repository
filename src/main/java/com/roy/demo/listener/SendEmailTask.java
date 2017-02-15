package com.roy.demo.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class SendEmailTask extends TimerTask {

	private static final Logger logger = Logger.getLogger(SendEmailTask.class);
	
	private ServletContext context = null;

	public SendEmailTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("Start to create file in E disk");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		String now = df.format(new Date());
		String fileName = "test_" + now + ".txt";
		File file = new File("e:" + File.separator + fileName);
		try {
			OutputStream out = new FileOutputStream(file);
			String str = "Hello world";
			byte[] b = str.getBytes();
			out.write(b);// 因为是字节流，所以要转化成字节数组进行输出
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 如果文件不存在会自动创建
		logger.info("Finished");
	}

}
