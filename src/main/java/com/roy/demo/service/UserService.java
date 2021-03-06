package com.roy.demo.service;

import java.util.List;
import java.util.Map;

import com.roy.demo.model.UserInfo;

public interface UserService {
	
	String getUserById(String id);
	
	List<UserInfo> getUsers();
	
	int insert(UserInfo userInfo);
	
	int adderWithParameterMap(Map params);
	
	void quartzCreateFile();

}
