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
		event.getServletContext().log("destroy email timer");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer = new Timer(true);
		event.getServletContext().log("start email timer");
		timer.schedule(new SendEmailTask(event.getServletContext()), 0, 10 * 1000);
	}

}
