package com.roy.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.roy.demo.annotation.TokenSecured;
import com.roy.demo.service.TokenService;

public class TokenCheckInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	@Qualifier("tokenService")
	private TokenService tokenService;

	private static final Logger LOGGER = Logger.getLogger(TokenCheckInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info(
				"################################################preHandle##########################################################");
		Boolean result = super.preHandle(request, response, handler);
		// Check if we are at the method level
		if (handler instanceof HandlerMethod) {
			HandlerMethod methodHandler = (HandlerMethod) handler;
			TokenSecured securedAnnotation = methodHandler.getMethodAnnotation(TokenSecured.class);

			// Check if the method contain the @ClientAccountTokenCheck
			// annotation
			if (securedAnnotation != null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "API call token not present or invalid.");
				return tokenService.verifyToken(request, response);
				// String refererUrl = request.getHeader("referer");
				// if (request.getRemoteAddr().equals(request.getLocalAddr()) )
				// {
				// //skip for unit test case running or while developing
				// return true;
				// } else if (refererUrl!=null &&
				// refererUrl.contains("swagger-ui.html")){
				// //skip for request from swagger document 'try-it-out'
				// return true;
				// } else {
				// return false;
				// }
				// }
			}
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
