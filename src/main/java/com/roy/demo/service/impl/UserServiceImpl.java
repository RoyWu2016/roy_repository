package com.roy.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.roy.demo.dao.UserInfoMapper;
import com.roy.demo.model.UserInfo;
import com.roy.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Cacheable("getUserById")
	public String getUserById(String id) {
		// TODO Auto-generated method stub
		UserInfo user = userInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
		return JSON.toJSONString(user);
	}

	public List<UserInfo> getUsers() {
		// TODO Auto-generated method stub
		return userInfoMapper.selectAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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

	public int adderWithParameterMap(Map params) {
		// TODO Auto-generated method stub
		int result = userInfoMapper.adderWithParameterMap(params);
		return result;
	}

	public void quartzCreateFile() {
		logger.info("Start to select from db");
		UserInfo user = userInfoMapper.selectByPrimaryKey(1);
		logger.info("Start to create file");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		String now = df.format(new Date());
		String fileName = "user_" + now + ".txt";
		File file = new File("e:" + File.separator + fileName);
		try {
			OutputStream out = new FileOutputStream(file);
			String str = user.getUname();
			byte[] b = str.getBytes();
			out.write(b);// 因为是字节流，所以要转化成字节数组进行输出
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 如果文件不存在会自动创建
	}

}
