package com.roy.demo.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc  
@EnableSwagger2  
//@ComponentScan(basePackages = {"com.roy.demo.controller"})  
//@Configuration 
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
    
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("Spring 中使用Swagger2构建RESTful APIs")  
                .termsOfServiceUrl("http://blog.csdn.net/he90227")  
                .contact("逍遥飞鹤")  
                .version("1.1")  
                .build();  
    }  

}
