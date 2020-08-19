package com.xiang.springboot01.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ServiceAspect.java
 * @Description TODO
 * @createTime 2020年08月17日 16:54:00
 */
@Aspect
@Component
public class ServiceAspect {

    private final static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);



    @Pointcut("@annotation(com.xiang.springboot01.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){

    }

    @Before(value = "com.xiang.springboot01.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinpoint) {
        LOGGER.debug("-----before service-----");
    }

    @Around(value = "com.xiang.springboot01.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LOGGER.debug("------around service---");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.xiang.springboot01.aspect.ServiceAspect.servicePointCut()")
    public void afterService() {
        LOGGER.debug("----after service----");
    }
}
