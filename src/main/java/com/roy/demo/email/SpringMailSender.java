package com.roy.demo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SpringMailSender {
	
	private static final Logger logger = Logger.getLogger(SpringMailSender.class);

	//Spring的邮件工具类，实现了MailSender和JavaMailSender接口
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
            messageHelper.setSubject("Hello world"); //主题
            messageHelper.setText("Hello world via spring mail sender");   //内容
            messageHelper.setTo("roywu2016@qq.com"); //发送给
            
            mailSender.send(mimeMessage);    //发送邮件

        } catch (Exception e) {
            logger.error("the email send error ! {}", e);
        }
        
	}

	/**
	 * 带附件的邮件发送
	 * 
	 * @throws MessagingException
	 */
	public void attachedSend() throws MessagingException {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setFrom(mailSender.getUsername());
		helper.setTo("mosaic@126.com");
		helper.setSubject("Hello Attachment");
		helper.setText("This is a mail with attachment");
		// 加载文件资源，作为附件
		ClassPathResource file = new ClassPathResource("Chrysanthemum.jpg");
		// 加入附件
		helper.addAttachment("attachment.jpg", file);
		// 发送邮件
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
