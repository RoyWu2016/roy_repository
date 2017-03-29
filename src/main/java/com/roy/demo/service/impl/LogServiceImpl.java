package com.roy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roy.demo.dao.LogInfoMapper;
import com.roy.demo.model.LogInfo;
import com.roy.demo.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogInfoMapper logInfoMapper;

	public int addLog(LogInfo log) {
		// TODO Auto-generated method stub
		return logInfoMapper.addLog(log);
	}

}
