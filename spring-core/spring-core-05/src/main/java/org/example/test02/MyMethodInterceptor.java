package org.example.test02;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ??????
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private static final MyLazyLoader MY_LAZY_LOADER = new MyLazyLoader();

    private volatile boolean initialized;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        ensureInitialized(obj);
        System.out.println("Before method: " + method.getName());
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After method: " + method.getName());
        return result;
    }

    private void ensureInitialized(Object obj) throws Exception {
        if (initialized) {
            return;
        }
        synchronized (this) {
            if (!initialized) {
                User loaded = (User) MY_LAZY_LOADER.loadObject();
                User target = (User) obj;
                target.name=loaded.getName();
                initialized = true;
            }
        }
    }
}
