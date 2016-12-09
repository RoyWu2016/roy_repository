package com.roy.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenService {
	
	boolean verifyToken(HttpServletRequest request, HttpServletResponse response);

}
