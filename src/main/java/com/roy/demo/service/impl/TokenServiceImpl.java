package com.roy.demo.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.roy.demo.service.TokenService;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	@Override
	public boolean verifyToken(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

}
