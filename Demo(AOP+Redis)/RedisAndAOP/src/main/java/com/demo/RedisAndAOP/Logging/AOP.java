package com.demo.RedisAndAOP.Logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOP {

    @Around("execution(* com.demo..*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logApiAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[AOP] Called: " + joinPoint.getSignature() + " at " + now);

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;

        System.out.println("⏱Time taken: " + time + "ms");
        return result;
    }


}
