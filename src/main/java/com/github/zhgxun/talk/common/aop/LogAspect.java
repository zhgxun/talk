package com.github.zhgxun.talk.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution (public * com.github.zhgxun.talk.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("URL : {}", request.getRequestURL().toString());
            log.info("HTTP_METHOD : {}", request.getMethod());
            log.info("IP : {}", request.getRemoteAddr());
            log.info("CLASS_METHOD : {}.{}",
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
        }
    }
}
