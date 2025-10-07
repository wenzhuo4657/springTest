package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect()
public class UserProxy {

    @Pointcut("execution(* org.example.User.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("UserProxy around before");
        Object result = pjp.proceed();
        System.out.println("UserProxy around after");
        return result;
    }
}
