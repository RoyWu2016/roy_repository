package com.roy.demo.service;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.roy.demo.model.LogInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class LogServiceTest {
	
	private static final Logger LOGGER = Logger.getLogger(TestUserService.class);

	@Autowired
	private LogService logService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddLog() {
		LogInfo log = new LogInfo();
		log.setUserName("Roy");
		log.setOperation("test");
		int i = logService.addLog(log);
		LOGGER.info(i);
	}

}
