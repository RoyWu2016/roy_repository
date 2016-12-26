package com.roy.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.roy.demo.dao.UserInfoMapper;
import com.roy.demo.model.UserInfo;
import com.roy.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public UserInfo getUserById(int id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public List<UserInfo> getUsers() {
		// TODO Auto-generated method stub
		return userInfoMapper.selectAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)//, rollbackFor = Exception.class
	public int insert(UserInfo userInfo) {
		// TODO Auto-generated method stub
		int result = userInfoMapper.insert(userInfo);
		try {
			String string = null;
			if (string.equals("")) {
				int i = 0;
			}
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

}
