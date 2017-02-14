package com.roy.demo.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AutoEmailListener implements ServletContextListener {

	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer = new Timer(true);
		event.getServletContext().log("定时器已启动");// 添加日志，可在tomcat日志中查看到
		// 调用exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时；
		timer.schedule(new SendEmailTask(event.getServletContext()), 0, 10 * 1000);
	}

}
