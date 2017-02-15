package com.roy.demo.config;

import org.springframework.beans.factory.annotation.Value;

//@Component
public class ServiceConfig {
	
//	@Value("${user.service.url}")
	private String userServiceUrl;
	
//	@Value("${court.service.url}")
	private String logServiceUrl;
	
	@Value("${public.api.url}")
	private String apiServiceUrl;

	public String getApiServiceUrl() {
		return apiServiceUrl;
	}

	public void setApiServiceUrl(String apiServiceUrl) {
		this.apiServiceUrl = apiServiceUrl;
	}

	public String getUserServiceUrl() {
		return userServiceUrl;
	}

	public void setUserServiceUrl(String userServiceUrl) {
		this.userServiceUrl = userServiceUrl;
	}

	public String getLogServiceUrl() {
		return logServiceUrl;
	}

	public void setLogServiceUrl(String logServiceUrl) {
		this.logServiceUrl = logServiceUrl;
	}
	

}
