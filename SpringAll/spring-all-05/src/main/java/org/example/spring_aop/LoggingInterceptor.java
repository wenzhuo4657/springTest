package org.example.spring_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("UserProxy around before");
        try {
            return invocation.proceed();
        } finally {
            System.out.println("UserProxy around after");
        }
    }
}

