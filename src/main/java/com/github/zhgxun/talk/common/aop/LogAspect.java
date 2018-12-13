package com.github.zhgxun.talk.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 异常抛出时前端难以捕捉, 尤其是登陆或者认证需要请求和响应流时, 不如拦截器方便
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution (public * com.github.zhgxun.talk.controller.*.*(..))")
    public void log() {
    }

    /**
     * 请求参数
     *
     * @param joinPoint 切入
     */
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

    /**
     * 请求耗时
     *
     * @param joinPoint 切入
     * @return 切入
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        long begin = System.currentTimeMillis();
        try {
            String method = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getName();
            Object o = joinPoint.proceed();
            log.info("AOP Around Func<doAround> Class<{}>.Method<{}> Cost<{}>ms",
                    className, method, (System.currentTimeMillis() - begin));
            return o;
        } catch (Throwable e) {
            log.error("", e);
        }
        return null;
    }
}
