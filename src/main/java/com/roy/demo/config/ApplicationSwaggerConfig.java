package com.roy.demo.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationSwaggerConfig {
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationSwaggerConfig.class);
	
    @Bean
    public Docket api() {
    	LOGGER.info("################################ into Docket api() #####################################");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.roy.demo.controller"))
                .paths(PathSelectors.any())//过滤的接口
                .build();
    }

}
