package com.smallcase.lushuju.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Package: com.smallcase.lushuju.aop
 * Author: smallcase
 * Date: Created in 2018/7/3 15:13
 */

@Aspect
@Configuration
@Slf4j
public class AOPConfig {

//    @Around("@within(org.springframework.stereotype.Controller)")
    @Around("target(com.smallcase.lushuju.service.HealthInfoService)")
//    @Around("execution(public * com.smallcase.lushuju.service.HealthInfoService.save(..))")
    public Object sampleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            String kind = pjp.getKind();
            log.info(kind);
            Object[] args = pjp.getArgs();
            System.out.println("args:" + Arrays.asList(args));
            //调用原有的方法
            Object o = pjp.proceed();
            System.out.println("return :" + o);

            return o;
        } catch (Throwable e) {
            throw e;
        }
    }
}
