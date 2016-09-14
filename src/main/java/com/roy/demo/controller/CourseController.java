package com.roy.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	protected Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@RequestMapping("/showInfo/{courseId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable("courseId") int courseId){
//		logger.info("-----------------------showUserInfo-----------------------");
//		UserInfo userInfo = userService.getUserById(userId);
//		modelMap.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}

}
