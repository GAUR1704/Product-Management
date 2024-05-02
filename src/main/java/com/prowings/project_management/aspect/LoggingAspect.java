package com.prowings.project_management.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.prowings.project_management.service.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    	
        long startTime = System.currentTimeMillis();

        System.out.println("Call recieved to " + joinPoint.getSignature().toShortString() + " method");
        
        try {
            Object result = joinPoint.proceed();
            
           long endTime = System.currentTimeMillis();
            
            System.out.println("Method " + joinPoint.getSignature().toShortString() + " executed in " + (endTime - startTime) + "ms");
            
            return result;
            
        } catch (Throwable e) {
        	
           long endTime = System.currentTimeMillis();
            
            System.out.println("Method " + joinPoint.getSignature().toShortString() + " failed after " + (endTime - startTime) + "ms");
            
            throw e;
        }
    }
}
