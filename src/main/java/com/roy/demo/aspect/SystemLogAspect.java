package com.roy.demo.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.roy.demo.annotation.SystemLog;
import com.roy.demo.controller.UserController;
import com.roy.demo.model.LogInfo;
import com.roy.demo.service.LogService;

@Aspect    
@Component   
public class SystemLogAspect {
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private LogService logService;
	
    //Controller层切点    
    @Pointcut("@annotation(com.roy.demo.annotation.SystemLog)")    
    public  void controllerAspect() {   
    	
    }  
    
    @Before("controllerAspect()")    
    public  void doBefore(JoinPoint joinPoint) { 
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();  
        
        String ip = request.getRemoteAddr();
        try{
        	logger.info("=====前置通知开始=====");    
        	logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
        	logger.info("方法描述:" + getControllerMethodDescription(joinPoint));    
        	logger.info("请求IP:" + ip);    
        	
        	LogInfo log = new LogInfo();
    		log.setUserName("Roy");
    		log.setOperation(getControllerMethodDescription(joinPoint));
    		int i = logService.addLog(log);
    		
    		logger.info("=====前置通知结束=====");    
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemLog.class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }   
	
}
