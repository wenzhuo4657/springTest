package org.example.test02;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 环绕回调
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private  final static MyLazyLoader myLazyLoader = new MyLazyLoader();

    private User user;
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (user==null)user=(User)myLazyLoader.loadObject();
        System.out.println("Before method: " + method.getName());
        proxy.invokeSuper(user, args);
        System.out.println("After method: " + method.getName());
        return null;
    }
}
