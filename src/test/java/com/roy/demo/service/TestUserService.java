package com.roy.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.roy.demo.model.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestUserService {

	private static final Logger LOGGER = Logger.getLogger(TestUserService.class);

	@Autowired
	private UserService userService;

	@Test
	public void testQueryById1() {
		String userInfo = userService.getUserById("1");
		LOGGER.info(userInfo);
	}

	@Test
	public void testQueryAll() {
		List<UserInfo> userInfos = userService.getUsers();
		LOGGER.info(JSON.toJSON(userInfos));
	}

	@Test
	public void testInsert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUname("xiaoyu33");
		userInfo.setUnumber(533);
		int result = userService.insert(userInfo);
		System.out.println(result);
	}

	@Test
	public void testAdderWithParameterMap() {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("addend1", 3);
		parms.put("addend2", 4);
		userService.adderWithParameterMap(parms);
		LOGGER.info("result: " + parms.get("sum"));
		assertEquals(7, parms.get("sum"));
	}
}
