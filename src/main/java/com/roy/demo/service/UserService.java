package com.roy.demo.service;

import java.util.List;

import com.roy.demo.model.UserInfo;

public interface UserService {
	
	UserInfo getUserById(int id);
	
	List<UserInfo> getUsers();
	
	int insert(UserInfo userInfo);

}
