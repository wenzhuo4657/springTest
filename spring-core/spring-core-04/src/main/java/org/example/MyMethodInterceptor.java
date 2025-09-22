package org.example;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method: " + method.getName());
        System.out.println("(挥手，笑)");
        proxy.invokeSuper(obj, args);
        System.out.println("After method: " + method.getName());
        return null;
    }
}
